# API de Alunos

Projeto da disciplina de Desenvolvimento de APIs e Backend, feito por Antonio Magno (RA 101341). Ele junta as atividades das semanas 1 a 4 em uma aplicação só: começou com a entidade Curso, virou uma API REST de alunos, ganhou DTOs e tratamento de erro e, por fim, teve o PUT e o DELETE revisados com Lombok.

## O que foi usado

A aplicação é feita em Java 17 com Spring Boot e Maven. Para o banco usei o PostgreSQL, acessado através do Spring Data JPA com Hibernate, e o Lombok para não precisar escrever getters, setters e construtores na mão.

## Como o código está organizado

Tudo fica dentro de `com.example.alunos`, dividido em camadas. As entidades `Aluno` e `Curso` ficam em `entity`, o `AlunoRepository` fala com o banco em `repository`, as regras ficam no `AlunoService` e o `AlunoController` recebe as requisições HTTP. Os objetos `AlunoRequest` e `AlunoResponse` ficam em `dto` e a `AlunoNaoEncontradoException` fica em `exception`. A entidade `Curso` só existe para criar a tabela, ela ainda não tem endpoints nem relação com aluno porque as atividades não pediram isso.

## Configurando o PostgreSQL

Antes de rodar, é preciso criar o banco de dados. Com o PostgreSQL instalado, entre no `psql` (ou abra o pgAdmin) e execute:

```sql
CREATE DATABASE alunos_db;
```

Depois abra o arquivo `src/main/resources/application.properties` e troque `SEU_USUARIO` e `SUA_SENHA` pelo usuário e senha do seu PostgreSQL. Se a porta ou o nome do banco forem diferentes, altere também a linha da `spring.datasource.url`. As tabelas `aluno` e `curso` não precisam ser criadas à mão, o Hibernate cria sozinho quando a aplicação sobe. Cuidado para não enviar sua senha de verdade para o GitHub.

## Executando

Com o Java 17 e o Maven instalados, rode na pasta do projeto:

```bash
mvn spring-boot:run
```

Também dá para abrir o projeto no IntelliJ, Eclipse ou VS Code e executar a classe `AlunosApplication`. A API sobe em `http://localhost:8080`.

## Endpoints

Para listar todos os alunos use `GET /alunos`. Se quiser filtrar por nome, passe o parâmetro, como em `GET /alunos?nome=antonio`. A busca pega qualquer nome que contenha o trecho e não diferencia maiúscula de minúscula. Para buscar um aluno específico use `GET /alunos/{id}`. Os dois GET respondem 200.

Para cadastrar mande um `POST /alunos` com o JSON abaixo, e a resposta é 201 com o aluno criado e o id gerado.

```json
{
  "nome": "Maria Silva",
  "idade": 20
}
```

Para atualizar use `PUT /alunos/{id}` com o mesmo formato de JSON, e para excluir use `DELETE /alunos/{id}`. Os dois respondem 204 sem corpo. Se o id não existir no GET por id, no PUT ou no DELETE, a resposta é 404.

Tem também um endpoint extra, `GET /alunos/autor`, que devolve uma mensagem com o nome e o RA de quem fez o projeto.

## Testando

Dá para testar pelo Postman, pelo Insomnia ou pelo terminal. Um exemplo de cadastro com curl:

```bash
curl -X POST http://localhost:8080/alunos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Maria Silva", "idade": 20}'
```
