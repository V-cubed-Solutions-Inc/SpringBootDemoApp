pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
//             Original build steps
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install'
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true compile'
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true package'

//                 COVER build steps
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn install:install-file -Dfile="src/test/resources/jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"'
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install source:jar "com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup" compile package'
            }
        }

         stage ('BlazeMeter') {
            steps {
                blazeMeterTest credentialsId:'1451217', serverUrl:'https://a.blazemeter.com', workspaceId:'1499451', notes:'Run All Workspace', sessionProperties:'', jtlPath:'', junitPath:'', getJtl:false, getJunit:false
            }
         }
    }
}
