# Outsera - Backend

Desafio prático para a empresa Outsera. Este repositório contém a implementação do backend.

## Ferramentas

- **IDE:** IntelliJ IDEA 2025
- **Java:** 21
- **Spring Boot:** 3.4.1
- **Banco de dados:** H2 (em memória)

## Como rodar o projeto

```bash
./gradlew bootRun
# ou por meio do intellij IDEA, executando a classe principal OutseraApplication.java
```

A aplicação estará disponível em: http://localhost:8081

## Acesso ao banco de dados H2

Com a aplicação rodando, acesse o console do H2:

- **URL:** http://localhost:8081/h2-console
- **JDBC URL:** `jdbc:h2:mem:outsera_backend_jonathas`
- **User:** `sa`
- **Password:** *(vazio)*

## Endpoints

```
GET http://localhost:8081/api/intervals             → Retorna os intervalos de prêmios dos produtores
GET http://localhost:8081/api/movies                → Lista todos os filmes
GET http://localhost:8081/api/movies?winner=true    → Lista apenas os filmes vencedores
```

## Como rodar os testes de integração

```bash
./gradlew test
```