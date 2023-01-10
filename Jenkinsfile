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
	stage("Sonarqube analysis && Build"){
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
	}
}
