This repository is the example code used in the video showing the integration of [Cloud COVER](https://cloudscroll.io/) and [Blazemeter](https://www.blazemeter.com/) to a Spring Boot web application.

## COVER Information

Here you can find the [COVER User Manual](https://suresofttech.notion.site/User-Manual-1efbc1ea8ae242d681aa403d00b34894) for Cloud COVER setup.

- Add certificate-key.caas to .caas directory
- Set CAAS_CONFIG_PATH to path to .caas directory
- Download and install jacov

```shell
mvn install:install-file -Dfile="jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"
```

- Build with coverage
```shell
mvn clean -DskipTests=true source:jar com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup compile package
```

- Run jar or deploy war file
```shell
java -javaagent:java-runtime.jar -cp target/spring-boot-first-web-application-git-0.0.1-SNAPSHOT.jar com.in28minutes.springboot.web.SpringBootFirstWebApplication
```

## STATIC Information

```shell 
mvn clean compile && mvn com.codescroll.acp:static-maven-plugin:static -Dkey=TOOLSURVEY "-Dserver=10.1.10.17"
```

## Sonarqube (Local) Information

```shell 
mvn clean verify sonar:sonar jacoco:report "-Dsonar.projectKey=SpringBootDemo" "-Dsonar.projectName='SpringBootDemo'" "-Dsonar.host.url=http://10.1.10.17:9000" "-Dsonar.token=sqp_618b320df75ad265d941c19d057e6cf819db353d" "-Dmaven.test.failure.ignore=true"
```
