pipeline {
    agent any

    stages {
        stage ('Build') {
            steps {
//             Original build steps
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install'
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true compile'
//                 sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true package'

//                 COVER build steps
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn install:install-file -Dfile="src/test/resources/jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"'
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install source:jar "com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup" compile package'
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    ftpPublisher masterNodeName: 'master', alwaysPublishFromMaster: false, continueOnError: false, failOnError: false, paramPublish:[parameterName:''], publishers: [[configName: 'VS3', transfers: [[asciiMode: false, cleanRemote: false, excludes: '', flatten: false, makeEmptyDirs: true, noDefaultExcludes: true, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/*, target/**']], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true]]
                }
            }
        }

         stage ('BlazeMeter') {
            steps {
                sh 'echo \'paceholder\''
//                 blazeMeterTest credentialsId:'1451217', serverUrl:'https://a.blazemeter.com', workspaceId:'1499451', testId: '12473998', notes:'Run AddTodo', sessionProperties:'', jtlPath:'', junitPath:'', getJtl:false, getJunit:false
//                 blazeMeterTest credentialsId:'1451217', serverUrl:'https://a.blazemeter.com', workspaceId:'1499451', testId: '12473941', notes:'Run DisableOvertime', sessionProperties:'', jtlPath:'', junitPath:'', getJtl:false, getJunit:false
//                 blazeMeterTest credentialsId:'1451217', serverUrl:'https://a.blazemeter.com', workspaceId:'1499451', testId: '12473872', notes:'Run Login', sessionProperties:'', jtlPath:'', junitPath:'', getJtl:false, getJunit:false
            }
         }
    }
}
