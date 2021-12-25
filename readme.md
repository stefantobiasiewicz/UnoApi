## Uno game backend

###Technologies:
* Spring Boot 
* Mqtt
* OpenApi-generator
* Jpa
* Jib

###Build:
Build local jar
```
mvn clean install
```
Build lolacl docker image:
```
mvn compile jib:dockerBuild
```
###Run all dependencies (Postrge & Mqtt)
In main porject path
```
docker-compose up
```

###Run all components with application
When you start first time:
```
docker-compose -e spring.jpa.hibernate.ddl-auto=create -f docker-compose-local-dev.yml up 
```

Next start:
``` 
docker-compose -f docker-compose-local-dev.yml up
```