pipeline {
    agent any

    stages {
        stage ('Build & Test') {
            steps {
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn -Dmaven.test.failure.ignore=true -DrepoToken=T4UDgHuVZbdlyAKIi2TloejSZuqv7ybYB clean test jacoco:report coveralls:report compile package'
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
    }
}
