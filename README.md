# Arquitetura
A aplicação é uma aplicação RestFul Java com Spring Boot que utiliza banco em memória (H2) e faz consumo de API de Campanhas (https://github.com/Shadayy/campanha) para cadastro e associação de Sócio Torcedores.

Todas as APIs podem ser utilizadas diretamente pelo Swagger.

# Componentes
*  Seridor Tomcat
    *  http://localhost:8081
*  Documentação Swagger
    *  http://localhost:8081/swagger-ui.html#/
*  Banco de dados H2
    *  http://localhost:8081/h2

# Estrutura da aplicação
*  A aplicação faz uso do Flyway para upgrade do BD
*  A aplicação consome a API de Campanhas por `http` através da configuração `endpoint.campanha.listar`
*  A aplicação utiliza Hystrix como Circuit Breaker para tratar indisponibilidade da API de Campanhas
