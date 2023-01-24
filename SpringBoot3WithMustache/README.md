
### curl
```shell
curl -v -X POST localhost:8080/api/videos -d '{"name": "Learning Spring Boot 3"}' -H 'Content-type:application/json'
```

### Install node,npm,npx under ./node folder
```
./mvnw generate-resources
```
output example:
```shell
[INFO] --- frontend-maven-plugin:1.12.1:install-node-and-npm (default) @ SpringBoot3WithMustache ---
[INFO] Installing node version v16.14.2
[INFO] Downloading https://nodejs.org/dist/v16.14.2/node-v16.14.2-linux-x64.tar.gz to /u01/data/maven_repository/com/github/eirslett/node/16.14.2/node-16.14.2-linux-x64.tar.gz
[INFO] No proxies configured
[INFO] No proxy was configured, downloading directly
[INFO] Unpacking /u01/data/maven_repository/com/github/eirslett/node/16.14.2/node-16.14.2-linux-x64.tar.gz into /u01/workspace/springboot3withdb23c/SpringBoot3WithMustache/node/tmp
[INFO] Copying node binary from /u01/workspace/springboot3withdb23c/SpringBoot3WithMustache/node/tmp/node-v16.14.2-linux-x64/bin/node to /u01/workspace/springboot3withdb23c/SpringBoot3WithMustache/node/node
[INFO] Extracting NPM
[INFO] Installed node locally.
```

### Start adding modules, using Parcel

```shell
#PATH=./node:$PATH
#node/npm install --save-dev parcel
```

```shell
gu available
gu install nodejs
```

```shell
npm install --save-dev parcel
```

```shell
npm install --save react react-dom
```

### Generate Binary Executable File
```shell
./mvnw -Pnative clean native:compile
```

```shell
./target/springboot3withmustache
```

### Generate Docker Images
```shell
./mvnw -Pnative spring-boot:build-image
```

```shell
docker run --rm -p 8080:8080 springboot3withmustache:latest
```

