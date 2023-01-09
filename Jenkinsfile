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
                dir("/pom.xml") {
                    sh "ls -al /"
                }
            }
        }
	stage("Build"){
            steps {
                dir("/pom.xml") {
                    sh "mvn -B -DskipTests clean package"
                }
            }
        }
        stage("Test"){
            steps {
                dir("/pom.xml") {
                    sh "mvn test"
				}
			}
		}
	}
}
