pipeline {
    agent any

    stages {
        stage ('Build') {
            steps {
                // Build war file
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn -Dmaven.test.failure.ignore=true clean compile package'
            }
        }

        // Deploy build to server
        stage ('Deploy') {
            steps {
                ftpPublisher masterNodeName: 'master',
                    alwaysPublishFromMaster: false,
                    continueOnError: false,
                    failOnError: false,
                    paramPublish: [parameterName: ''],
                    publishers: [
                        [
                            configName: 'VS2',
                            transfers: [
                                [
                                    asciiMode: false,
                                    cleanRemote: false,
                                    excludes: '',
                                    flatten: false,
                                    makeEmptyDirs: true,
                                    noDefaultExcludes: true,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: '',
                                    remoteDirectorySDF: false,
                                    removePrefix: '',
                                    sourceFiles: 'target/*, target/**'
                                ]
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: true
                        ]
                    ]
            }
        }

        stage ('Test') {
            steps {
                // Build war file
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn test'
            }
        }
    }
}
