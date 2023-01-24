### Generate Binary Executable File
```shell
./mvnw -Pnative native:compile
```

```shell
./target/springboot3withjpa
```

### Generate Docker Images
```shell
./mvnw -Pnative spring-boot:build-image
```

```shell
docker run --rm -p 8080:8080 springboot3withjpa:latest
```

