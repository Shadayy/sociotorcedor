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
*  A aplicação faz uso do `Flyway` para upgrade do BD
*  A aplicação consome a API de Campanhas por `http` através da configuração `endpoint.campanha.listar`
*  A aplicação utiliza `Hystrix` como `Circuit Breaker` para tratar indisponibilidade da API de Campanhas

# Estrutura do projeto

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── victor
│   │   │           └── campanha
│   │   │               ├── adapter -> Abstrações que serão utilizadas para consumo da API de Campanhas`
│   │   │               │   └── impl -> Implementações das abstrações`
│   │   │               ├── config -> Classes auxiliares para configurações do Spring. Anotadas com @Configuration e @ControllerAdvice`
│   │   │               ├── controller -> Declaração das APIs disponíveis para consumo`
│   │   │               ├── dto -> Declarão de entidades utilizadas para envio e recebimento de dados das APIs`
│   │   │               ├── entity -> Entidades de persistência utilizadas para armazenamento das informações`
│   │   │               ├── exception -> Declaração de exceptions utilizadas`
│   │   │               ├── repository -> Abstração utilizada para acesso aos dados persistidos`
│   │   │               ├── service -> Abstrações de regra de négocio para as APIs`
│   │   │               │   └── impl -> Implementações das abstrações`
│   │   │               └── util -> Classes utilitárias para manipulação e formatação dos dados`
│   │   └── resources
│   │       ├── application.properties -> Conjunto de parametrizações necessárias para funcionamento da aplicação`
│   │       └── db -> Pacote com scripts SQL`
│   │           └── migration -> Arquivos SQL para atualização do BD`

```
