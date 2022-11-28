
```shell
docker run -d --name cassandra -p 9042:9042 -d cassandra:latest
```

```shell
docker exec -it cassandra sh
cqlsh> create keyspace db23c with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;
```