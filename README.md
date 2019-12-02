## Keystore Service

> Simple spring-boot miniservice application for encrypting and decrypting information on demand, while properly encoding and decoding it for transport.

#### How to get the project source
- Git:
Just clone this project in your local development folder

## Prerequisites
- JDK 1.8
- Maven 3.2+
- Docker


### Running application

1- Compile application:

```
	mvn clean install
```

<br/>

2 - Configure spring boot properties modifying the property file:

```
/src/main/resources/application.properties
```

<br/>

3 -  Run database container
<br/><br/>
If you are ruuning this application locally its possible run the MSSQL database in a container, please search in this document by "Running MSSQL database as a container" in Running the Services section.

<br/>

4 - Run java application   
<br/>
With all configurated, just type:

```
java -jar ./target/itau-latam-ppid-encryptor-*.jar
```

<br/>
<br/>

5 - Import config files to Postman:
Import files located in assets/postman to your Postman IDE

<br/>
<br/>
<br/>



## Running the Services
> Let **${WORKSPACE}** be the path pointing to this project's source
  - Ex.: In Windows environments, **${WORKSPACE}** -> //c//work//itau//areas//ms//work//itau-latam-keystore
  - Ex.: In Unix environments, **${WORKSPACE}** -> /home/fgbelet/areas/ms/work/itau-latam-keystore



### Running MSSQL database as a container service ~~
This is the external Database this service consumes 
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

For Windows based environments:

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



### Running Cucumber/Karate(BDD) as a container service ~~

This is the external BDD service to be fed  with the desided **.feature** files

Karate container service is a part of docker-compose stack created to run all integration tests.

to run this service separately:


```
IP_HOSTNAME=$(hostname -I | awk '{print $1}')
```

```bash
docker run \
  --rm \
  --name karate-container \
  -e IP_HOSTNAME=$IP_HOSTNAME \
  -v `pwd`/assets/bdd/karate-config.js/:/src/assets/bdd/karate-config.js \
  -v `pwd`/assets/bdd/features/:/src/features \
  -v `pwd`/assets/bdd/reports:/src/reports \
  -p 15155:8080 \
  qbarlas/karate-dsl
```

To run the BDD tests for this project its necessary to execute docker to run the database. See (<b>Running MSSQL database as a container service</b>)



### Running BDD tests using Docker compose ~~

Access folder ./assets and type:

```
sudo IP_HOSTNAME=$(hostname -I | awk '{print $1}') docker-compose up
```

This BDD engine will read integration tests from folder ./assets/bdd/features

After all feature tests are executed. Status reports can be collected in ./bdd/reports folder.


Running on CI pipeline:

```
sudo IP_HOSTNAME=$(hostname -I | awk '{print $1}') docker-compose up --abort-on-container-exit
```

