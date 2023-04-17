## Installing Tools
- PDF : https://github.com/in28minutes/SpringIn28Minutes/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf
- Video : https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
- GIT Repository : https://github.com/in28minutes/getting-started-in-5-steps

## COVER Information
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
