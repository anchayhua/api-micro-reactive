pipeline {
    agent any // Esto indica que se puede ejecutar en cualquier agente de Jenkins

    options {
        buildDiscarder (logRotator (numToKeepStr: '5'))        
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('jenkins-dockerhub')
        SONAR_CREDENTIALS = credentials('devops')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm // Esto verifica el código fuente desde tu repositorio
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package' // Maven para construir el proyecto
            }
        }

        stage('Test JUnit') {
            steps {
                sh 'mvn test' // Ejecuta pruebas unitarias
            }
        }

        stage('Run SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar \
                        -Dsonar.projectKey=devops \
                        -Dsonar.projectName=devops \
                        -Dsonar.token=$SONAR_CREDENTIALS_PSW' // Analiza y prueba en sonar el proyecto
            }
        }

        stage('Buil, Login & Push Image Docker Hub') {
            steps {
                sh 'docker build -t anchayhua/api-micro-reactive:latest .' // Construye la imagen Docker
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push anchayhua/api-micro-reactive' // Sube la imagen a un registro de Docker
            }
        }

        stage('Test kubectl') {
            steps {
                echo 'Solo falta la autenticacion en el K8s'
                // sh 'kubectl version'
            }
        }

        // stage('Deploy') {
        //     steps {
        //         echo 'La aplicación se ha desplegado correctamente.'
        //     }
        // }
    }

    post {
        always {
            sh 'docker logout'
        }
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
