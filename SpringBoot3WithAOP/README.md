
### Auto Generate Config Files

```shell
./mvnw clean package
java \
-agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
-jar target/springboot3withaop.jar
```

### Generate Binary Executable File
```shell
./mvnw -Pnative clean native:compile
```

```shell
./target/springboot3withaop
```

### Generate Docker Images
```shell
./mvnw -Pnative spring-boot:build-image
```

```shell
docker run --rm -p 8080:8080 springboot3withaop:latest
```
