pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
//             Original build steps
                sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install'
                sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true compile'
                sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true package'
                // sh 'mvn install:install-file -Dfile="src/test/jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"'
                // sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install source:jar "com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup" compile package'
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
        stage('Run') {
            steps {
                bat 'D:/Jenkins/.jenkins/workspace/SpringDemo/run.bat'
                currentBuild.result = 'SUCCESS'
            }
        }
        stage('Test') {
            steps {
                // commands to run Selenium tests
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                // use the BlazeMeter Jenkins plugin to send test results
                blazemeter apiKey: 'your_api_key', testId: 'your_test_id', resultsFile: 'path/to/test/results'
            }
        }

    }
}
