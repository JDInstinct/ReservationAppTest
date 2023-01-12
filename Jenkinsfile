pipeline {
  agent any
  tools {
	  docker 'docker'
    stages {
        stage("Clean up"){
            steps {
                deleteDir()
            }
        }
        stage("Clone Repo"){
            steps {
                sh "git clone https://github.com/JDInstinct/ReservationAppTest"
            }
        }
        stage("Access"){
            steps {
                    sh "ls -al /var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest"
            }
        }
	stage("Sonarqube analysis && Build Jar"){
            steps {
                dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
		withMaven(maven: 'mvn') {
		withSonarQubeEnv('sonarqube') {	
                    sh "mvn -X clean install sonar:sonar"
		}
                }
            	}
       	    }
	}
        stage("Test"){
            steps {
                dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
		withMaven(maven: 'mvn') {
                    sh "mvn test"
		}
		}
		}
	    }
	stage("Build && Push Docker Image"){
            steps {
		dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
			sh '''
		    	docker login -u=cic-office-space-reservation+byoi_jenkins_pushbot -p=COXIHLCIH9UGLPBXEWUG1N11LUT61GAZ9FPC8VOYBN9QY2QCHJ0KE0XR6ITDD3UG registry.cirrus.ibm.com
			docker build -t registry.cirrus.ibm.com/cic-office-space-reservation/jenkins:latest .
		    	docker push registry.cirrus.ibm.com/cic-office-space-reservation/jenkins:latest
			docker logout
			'''
		}
            }
	}
        }
	}
}
