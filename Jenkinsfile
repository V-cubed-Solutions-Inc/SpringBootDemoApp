pipeline {
    agent any

    stages {
        stage ('Check COVER Config') {
            steps {
                sh 'sh ./src/test/check-cover.sh'
            }
        }

        // COVER build steps
        stage ('Build') {
            steps {
                // Install jacov maven plugin
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn install:install-file -Dfile="src/test/resources/jacov-maven-plugin.jar" -DgroupId="com.qualityscroll.caas" -DartifactId="jacov-maven-plugin" -Dpackaging="jar" -Dversion="1.0.0-SNAPSHOT"'

                // Build war file
                sh 'sh /opt/apache-maven-3.9.1-bin/apache-maven-3.9.1/bin/mvn -Dmaven.test.failure.ignore=true -DskipTests=true clean install source:jar "com.qualityscroll.caas:jacov-maven-plugin:1.0.0-SNAPSHOT:setup" compile package'
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
