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
        stage("Build"){
            steps {
                dir("ReservationAppTest") {
                    sh "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps {
                dir("ReservationAppTest") {
                    sh "mvn test"
				}
			}
		}
	}
}
