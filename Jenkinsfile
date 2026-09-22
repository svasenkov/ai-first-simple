// Jenkinsfile — прогон тестов на Jenkins (jenkins.qa.guru).
// Повторяет шаги .github/workflows/ci.yml (ADR-0004: Jenkinsfile
// не должен расходиться с ci.yml). Требование к агенту — Java 21,
// см. docs/kb/jenkins.md.

pipeline {
    // На jenkins.qa.guru требование «Java 21» — это label java-jdk21:
    // agent any мог бы попасть на js/python-агент (Alpine/musl — Chrome не встанет)
    agent { label 'java-jdk21' }

    environment {
        // Браузер — из Selenoid: локального Chrome на агентах нет.
        // JAVA_TOOL_OPTIONS действует на каждый JVM-процесс, включая
        // тест-воркеры Gradle — так -Dselenide.remote доезжает до тестов.
        // Страницу браузер открывает с GitHub Pages — URL зашит в тесте.
        JAVA_TOOL_OPTIONS = '-Dselenide.remote=https://user1:1234@selenoid.qa.guru/wd/hub'
    }

    stages {
        stage('Клонирование репозитория') {
            steps {
                checkout scm
            }
        }

        stage('Запуск тестов') {
            steps {
                sh './gradlew test'
            }
        }
    }

    post {
        always {
            // Плагин Allure сам генерирует html-отчёт по результатам
            // тестов и публикует его на странице сборки
            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'build/allure-results']]
        }
    }
}
