// pipeline {
//     agent { docker { image 'maven:3.8.6-openjdk-11-slim' } }
//     stages {
//         stage('build') {
//             steps {
//                 sh 'mvn clean install'
//                 sh 'mvn compile'
//                 sh 'mvn package'
//             }
//         }
//     }
// }
//
pipeline {
    agent any

//     tools {
//         // Install the Maven version configured as "M3" and add it to the path.
//         maven "M3"
//     }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                checkout scm

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

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
