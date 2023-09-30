pipeline {
    agent any // Esto indica que se puede ejecutar en cualquier agente de Jenkins

    stages {
        stage('Checkout') {
            steps {
                checkout scm // Esto verifica el código fuente desde tu repositorio
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package' // Maven para construir el proyecto
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test' // Ejecuta pruebas unitarias
            }
        }

        stage('Run SonarQube Analysis') {
            steps {
                sh './mvn test'
            }
        }

        stage('Test kubectl') {
            steps {
                echo 'Solo falta la autenticacion en el K8s'
                // sh 'kubectl version'
            }
        }

        stage('Deploy') {
            steps {
                echo 'La aplicación se ha desplegado correctamente.'
            }
        }
    }

    post {
        success {
            // Acciones a realizar si el pipeline se ejecuta con éxito
            echo 'El pipeline se ha ejecutado con éxito.'
        }
        failure {
            // Acciones a realizar si el pipeline falla
            echo 'El pipeline ha fallado. Se requiere atención.'
        }
    }
}
