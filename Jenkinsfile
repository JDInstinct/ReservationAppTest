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
                    sh "ls -al /"
            }
        }
	stage("Build"){
            steps {
                dir("/") {
                    sh "mvn -B -DskipTests clean package"
                }
            }
        }
        stage("Test"){
            steps {
                dir("/") {
                    sh "mvn test"
				}
			}
		}
	}
}
