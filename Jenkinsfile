pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
//             Original build steps
//                 sh 'mvn clean install'
//                 sh 'mvn compile'
//                 sh 'mvn package'
                sh 'mvn install:install-file -Dfile="src/test/jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"'
                sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install source:jar "com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup" compile package'
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
    }
}
