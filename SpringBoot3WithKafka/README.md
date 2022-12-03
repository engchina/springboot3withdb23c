
prepare docker network
```shell
docker network create app-tier --driver bridge
docker network ls
```

start Zookeeper
```shell
docker run -d --name zookeeper-server \
    --network app-tier \
    -e ALLOW_ANONYMOUS_LOGIN=yes \
    -p 2181:2181 \
    --hostname zookeeper-server \
    bitnami/zookeeper:latest
```
start kafka
```shell
docker run -d --name kafka-server \
    --network app-tier \
    -e ALLOW_PLAINTEXT_LISTENER=yes \
    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092 \
    -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://192.168.31.45:9092 \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 \
    --hostname kafka-server \
    -p 9092:9092 \
    bitnami/kafka:latest
```
start kafka client
```shell
docker run -it --rm \
    --network app-tier \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 \
    bitnami/kafka:latest kafka-topics.sh --list --bootstrap-server kafka-server:9092
```
kafka tool
```shell
https://www.kafkatool.com/download.html
```