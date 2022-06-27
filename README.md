# Mediscreen

Mediscreen est une application qui permet d'aider à la détection du diabète. Elle scanne les notes d'un patient, écrites par le praticien, à la recherche de différents mots-clés. Elle évalue ainsi le risque du patient de développer la maladie.

Mediscreen est une application Spring Boot structurée en microservices.

## Prerequisites
- Docker 20.10

## Installing
To install Docker, follow the link: [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)

## Running
Go to the root repository of the project.

Execute the script `start.sh` (it may take more than 15 minutes to build the project, the Keycloak migration alone takes about 10 minutes, I don't know why...).

**Frontend**: [localhost:8090](http://localhost:8090/login), username=`user1`, password=`user1`, must have a `USER` role in Keycloak,

**Keycloak**: [localhost:8070](http://localhost:8070), username=`admin`, password=`admin`, client=`mediscreen-app`,

**Eureka**: [localhost:8761](http://localhost:8761), username=`tipikae`, password=`231045`,

**Config**: 
- repository: [https://github.com/tipikae/DA-JAVA-P9-mediscreen-config](https://github.com/tipikae/DA-JAVA-P9-mediscreen-config)
- server: [localhost:8888](http://localhost:8888), username=`tipikae`, password=`231045`,

**Gateway**: [localhost:8080](http://localhost:8080)
- API:  needs a valid token,
- Spring Boot Admin: username=`tipikae`, password=`231045`,

**Spring Boot Admin**: [localhost:8050](http://localhost:8050), username=`tipikae`, password=`231045`,

**Zipkin**: [localhost:9411](http://localhost:9411).



## Documentation
- API with **Swagger**: in your browser, open [localhost:8080/webjars/swagger-ui/index.html](http://localhost:8080/webjars/swagger-ui/index.html?urls.primaryName=note),


- Code with **Javadoc**: execute the Gradle `javadoc` task, then in your browser, open `build/docs/javadoc/index.html` of each sub-projects,


- Tests with **Jacoco**: execute the Gradle `verification` task, then in your browser, open `build/jacocoHtml/index.html` of each sub-projects.
