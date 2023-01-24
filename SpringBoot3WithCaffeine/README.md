
### Auto Generate Config Files

```shell
./mvnw clean package
java \
-agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
-jar target/SpringBoot3WithCaffeine-0.0.1-SNAPSHOT.jar
```

### Generate Binary Executable File
```shell
./mvnw -Pnative clean native:compile
```

### Generate Docker Images
```shell
./mvnw -Pnative spring-boot:build-image
```

```shell
docker run --rm -p 8080:8080 springboot3withcaffeine:latest
```
