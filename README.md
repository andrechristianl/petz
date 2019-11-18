## Keystore Service

> Simple spring-boot miniservice application for encrypting and decrypting information on demand, while properly encoding and decoding it for transport.

#### How to get the project source
- Git:
Temporary: https://github.com/guiban/itau-latam-keystore

## Prerequisites
- JDK 1.8
- Maven 3.2+
- Docker


## Running the Services
> Let **${WORKSPACE}** be the path pointing to this project's source
  - Ex.: In Windows environments, **${WORKSPACE}** -> //c//work//itau//areas//ms//work//itau-latam-keystore
  - Ex.: In Unix environments, **${WORKSPACE}** -> /home/fgbelet/areas/ms/work/itau-latam-keystore

___
### This Service ~~
> This is the service that this repository hosts, informally called *encryptor service*
___
### MSSQL ~~
> This is the external Database this service consumes 
For Unix based environments:
```bash
docker run \
  --rm \
  --name mssql-keystore-container \
  -e ACCEPT_EULA=Y \
  -v `pwd`/assets/docker/mssql/scripts:/tmp/scripts \
  -e "SA_PASSWORD=Itau@2019" \
  -p 1433:1433 \
  mcr.microsoft.com/mssql/server:2017-latest bash /tmp/scripts/launch-db.sh
```

>For Windows based environments:
```bash
docker run \
  --rm \
  --name mssql-keystore-container \
  -e ACCEPT_EULA=Y \
  -v ${WORKSPACE}//assets//docker//mssql//scripts:/tmp/scripts \
  -e "SA_PASSWORD=Itau@2019" \
  -p 1433:1433 \
  mcr.microsoft.com/mssql/server:2017-latest bash /tmp/scripts/launch-db.sh
```

___
### Karate (BDD) ~~
> This is the external BDD service to be fed  with the desided **.feature** files
```bash
docker run \
  --rm \
  --name karate-container \
  -v ${WORKSPACE}/assets/bdd/features:/tmp/features \
  -v ${WORKSPACE}/assets/bdd/reports:/tmp/reports \
  -p 15155:8080 \
  qbarlas/karate-dsl
```