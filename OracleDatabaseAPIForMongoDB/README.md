# Spring Boot 3.0.x and Oracle Database 23c Database API for MongoDB

## 加载数据
本示例服务访问了一家共享单车公司的真实数据。我们在这个例子中使用了他们的数据，因为他们已经干净地暴露了关于其共享单车站点的JSON数据（见https://github.com/NABSA/gbfs/blob/master/gbfs.md）。
具体来说，这个例子使用了他们的两个数据集。
- station: 关于每个共享单车站点的JSON数据，包括其名称、位置、最大容量等。
- status: 关于共享单车站的当前状态的JSON数据，包括当前可用的自行车数量等数据。

加载这些数据集，我们将使用mongoimport。

```shell
# 用你的数据库的实际URI代替，使用\$external for `$external`
export URI="mongodb://<url>:27017/mongodb?authMechanism=PLAIN&authSource=\$external&ssl=true&retryWrites=false&loadBalanced=true"

# 使用jq(https://stedolan.github.io/jq/)分开嵌套文件，并将station_id字段改为_id
curl https://gbfs.citibikenyc.com/gbfs/en/station_information.json | \
jq -c '.data.stations[] | .["_id"] = .["station_id"] | del(.station_id)' | \
mongoimport -u <username> -p <password> --collection station --uri $URI

curl https://gbfs.citibikenyc.com/gbfs/en/station_status.json | \
jq -c '.data.stations[]' | \
mongoimport -u <username> -p <password> --collection status --uri $URI
```

如果成功，你应该在你的数据库中有两个集合：station和status。注意，你可能想在一段时间内多次运行status命令，以获得更多有趣的（变化的）数据来进行处理。加载的数据是运行命令时station的状态。

## 查看数据
你可以使用mongosh从命令行查看，
```shell
mongosh -u <username> -p <password> $URI
mongodb> show collections
station
status
mongodb> db.station.find({_id:120})
[
  {
    _id: '120',
    region_id: '71',
    lon: -73.95928168,
    eightd_has_key_dispenser: false,
    legacy_id: '120',
    rental_methods: [ 'KEY', 'CREDITCARD' ],
    external_id: '66db29e6-0aca-11e7-82f6-3863bb44ef7c',
    capacity: 19,
    short_name: '4452.03',
    lat: 40.68676793,
    electric_bike_surcharge_waiver: false,
    station_type: 'classic',
    eightd_station_services: [],
    has_kiosk: true,
    rental_uris: {
      android: 'https://bkn.lft.to/lastmile_qr_scan',
      ios: 'https://bkn.lft.to/lastmile_qr_scan'
    },
    name: 'Lexington Ave & Classon Ave'
  }
]
mongodb> db.station.updateOne({_id:120}, {$set:{capacity:24}});
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
mongodb> db.station.find({_id:120});
[
  {
    _id: '120',
    region_id: '71',
    lon: -73.95928168,
    eightd_has_key_dispenser: false,
    legacy_id: '120',
    rental_methods: [ 'KEY', 'CREDITCARD' ],
    external_id: '66db29e6-0aca-11e7-82f6-3863bb44ef7c',
    capacity: 24,
    short_name: '4452.03',
    lat: 40.68676793,
    electric_bike_surcharge_waiver: false,
    station_type: 'classic',
    eightd_station_services: [],
    has_kiosk: true,
    rental_uris: {
      android: 'https://bkn.lft.to/lastmile_qr_scan',
      ios: 'https://bkn.lft.to/lastmile_qr_scan'
    },
    name: 'Lexington Ave & Classon Ave'
  }
]
```

## 启动服务
要启动该服务，请运行以下命令，
```shell
./mvnw package
java -jar ./target/springboot3withdb23c.jar \
  --spring.data.mongodb.uri='mongodb://<username>:<password>@<url>:27017/<database_name>?authMechanism=PLAIN&authSource=$external&ssl=true&retryWrites=false&loadBalanced=true' \
  --spring.data.mongodb.database=<database_name>
```

## 测试服务
你可以使用cURL测试服务。
```shell
# 获取ID为120的station
curl http://localhost:8181/station/120
{
  "name" : "Lexington Ave & Classon Ave",
  "region_id" : "71",
  "lon" : -73.95928168,
  "lat" : 40.68676793,
  ...

# 获取station为120的状态数据
curl http://localhost:8181/status/search/findByStationId?id=120
"_embedded" : {
  "status" : [ {
    "station_id" : "120",
    "num_bikes_available" : 2,
  ...

# 使用一个事务删除station为120的station和所有相关的状态文件
curl -i -X DELETE http://localhost:8181/station/120
 HTTP/1.1 200 
 ...

# station 120 已不复存在
curl -i http://localhost:8181/station/120
 HTTP/1.1 404 
 ...
```

## SQL/JSON
下面是可以在station和status集合上运行的SQL/JSON的例子。这些查询可以在数据库操作中执行，

选择JSON文档作为文本。:

```sql
select json_serialize(data)
from station
```

项目字段作为列:
```sql
select 
  s.id,
  s.data.name.string(),
  s.data.region_id.string(),
  s.data.lat.number(),
  s.data.lon.number(),
  s.data.station_type.string()
from station s;
```

按region_id对站点进行分组:
```sql
select
    t.data.region_id.string() as region_id,
    count(*) as count
from station t
where t.data.station_type.string() = 'classic'
group by t.data.region_id.string();
```

使用json_dataguide自动创建关系型视图:
```sql
declare
  dg clob;
begin
select json_dataguide(data, dbms_json.FORMAT_HIERARCHICAL, dbms_json.pretty) into dg
from station;

dbms_json.create_view('STATION_VIEW', 'STATION', 'DATA', dg, resolveNameConflicts => true, path => '$._id');

select json_dataguide(data, dbms_json.FORMAT_HIERARCHICAL, dbms_json.pretty) into dg
from status;

dbms_json.create_view('STATUS_VIEW', 'STATUS', 'DATA', dg);
end;
/
```

从创建的视图中选择:
```sql
select * from station_view;
```

在集合之间进行连接:
```sql
select s."station_id" station_id,
       (select t."name" from station_view t where t."_id" = s."station_id") name,
       min(s."num_bikes_available") min,
       max(s."num_bikes_available") max,
       round(avg(s."num_bikes_available")) avg
from status_view s
group by s."station_id";
```
```sql
create view station_availability as
select s."station_id" station_id,
       (select t."name" from station_view t where t."_id" = s."station_id") name,
       min(s."num_bikes_available") min,
       max(s."num_bikes_available") max,
       round(avg(s."num_bikes_available")) avg
from status_view s
group by s."station_id";
```

选择总是有超过一半的可用容量的station:
```sql
select station_id, name, min, max, avg
from station_availability
where min > 20 and avg > 50
order by max desc;
```
