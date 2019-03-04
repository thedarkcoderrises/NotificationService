def containerId=""
pipeline {
    agent none
    stages {

        stage('Build') {
            agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /root/.m2:/root/.m2'
                    }
                  }
            steps {
                    sh 'mvn -X clean install -DskipTests'
                  }
            }

        stage('Build DockerImage') {
            agent any
            steps{
                    script{
                        containerId = sh (
                        script :'docker ps -aqf "name=notification"',
                        returnStdout: true
                        ).trim()
                            if("${containerId}"!= ''){
                                  sh 'docker stop notification'
                                  sh 'docker rm notification'
                                  sh 'docker rmi $(docker images --filter=reference=notification --format "{{.ID}}")'
                            }
                    }
                    sh 'docker build -t notification:1.0 .'
                }
         }
        stage('Deployment') {
            agent any
             steps {
                    sh 'docker run -p 8082:8080 -v /home/ec2-user/logs/notification:/logs -e kafka.url=kafka:9092 -e AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} -e AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} --name notification --link=kafka notification:1.0'
                }
        }
    }
 }