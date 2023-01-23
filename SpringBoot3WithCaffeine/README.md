
### Auto Generate Config Files

```shell
java \
-agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
-jar target/SpringBoot3WithCaffeine-0.0.1-SNAPSHOT.jar
```