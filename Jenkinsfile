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

// Kept in place for after built steps, (i.e., running ui test with blazemeter)
//             post {
//                 // If Maven was able to run the tests, even if some of the test
//                 // failed, record the test results and archive the jar file.
//                 success {
//                     junit '**/target/surefire-reports/TEST-*.xml'
//                     archiveArtifacts 'target/*.jar'
//                 }
//             }
        }

        stage('Report') {
            steps {
                // use the BlazeMeter Jenkins plugin to send test results
                blazemeter apiKey: '7b7bd39a4347e3bfc8e96ae4', testId: 'COVER-Login', resultsFile: '**/target/surefire-reports/**'
            }
        }
    }
}
