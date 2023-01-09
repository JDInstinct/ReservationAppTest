pipeline {
    agent any
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
	stage("Build"){
            steps {
                dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
                    sh "
			chmod +x ./mvnw
			./mvnw clean install
			"
                }
            }
        }
        stage("Test"){
            steps {
                dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
                    sh "./mvnw test"
				}
			}
		}
	}
}
