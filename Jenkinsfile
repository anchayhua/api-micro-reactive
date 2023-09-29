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
                // Usar Maven para construir el proyecto
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Ejecutar pruebas unitarias
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Aquí puedes agregar comandos para implementar tu aplicación en el entorno de destino
                // Por ejemplo, si estás implementando en un servidor de aplicaciones:
                // sh 'scp target/your-application.jar user@your-server:/path/to/deployment/'
                // ssh user@your-server 'sudo systemctl restart your-application'

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
