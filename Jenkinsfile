pipeline {
    agent { docker { image 'maven:3.8.6-openjdk-11-slim' } }
    stages {
        stage('build') {
            steps {
//             Original build steps
//                 sh 'mvn clean install'
//                 sh 'mvn compile'
//                 sh 'mvn package'
                sh 'mvn clean source:jar com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup compile package'
            }
        }

    }
}