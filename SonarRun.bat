mvn clean verify sonar:sonar `
    "-Dsonar.projectKey=BankIUT" `
    "-Dsonar.host.url=http://localhost:9000" `
    "-Dsonar.login=d48adf5259fe5a27dfd4c9bc835fbf9d698bf441"
