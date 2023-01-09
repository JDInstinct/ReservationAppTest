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
	
	stage('SonarQube analysis') {
		steps {
			dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
    			withMaven(maven: 'mvn') {
			withSonarQubeEnv(credentialsId: '', installationName: 'Sonarqube') {
      				sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    					}
  				}
			}
		}
   	}    
	stage("Build"){
            steps {
                dir("/var/jenkins_home/workspace/meeting-room-reservations/ReservationAppTest") {
		withMaven(maven: 'mvn') {
                    sh "mvn clean install"
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
