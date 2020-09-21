## Projeto API Patrimonial
Este programa tem o objetivo implementar uma api rest para gerenciamento de patrimônio empresarial.

## Implementação
Este projeto foi implementado usando spring boot com padrão MVC contendo as seguintes dependências:<br>
Spring Web<br>
Spring Data JPA<br>
Spring Boot DevTools<br>
Postgres Driver<br>
Lombok
Model Mapper
Spring Security<br>


## Iniciando o projeto
Clone o repositório

```
git clone https://github.com/webvirtua/StartSpring.git
```
Crie a base de dados postgres com o nome patrimony

```
create database patrimony;
```
1. Import como Existing Maven Projects
2. Selecione o diretório e finish
3. Clique com obotão direito no projeto e run > java application

## Acesso aos Recursos

### Patrimônio
GET Recebe um ID como parâmetro e retorna um patrimônios buscando pelo ID.<br>
http://localhost:8080/v1/patrimony/{id}

GET Retorna uma lista com todos os patrimônios cadastrados.<br>
http://localhost:8080/v1/patrimony

POST Recebes os dados na requisição e insere um patrimônio.<br>
http://localhost:8080/v1/patrimony

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um patrimônio.<br>
http://localhost:8080/v1/patrimony/{id}

DELETE Recebe um ID como parâmetro e deleta um patrimônio.<br>
http://localhost:8080/v1/patrimony/{id}

### Marca
GET Recebe um ID como parâmetro e retorna uma marca buscando pelo ID.<br>
http://localhost:8080/v1/brands/{id}

GET Retorna uma lista com todos as marcas cadastradas.<br>
http://localhost:8080/v1/brands

POST Recebes os dados na requisição e insere uma marca.<br>
http://localhost:8080/v1/brands

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza uma marca.<br>
http://localhost:8080/v1/brands/{id}

DELETE Recebe um ID como parâmetro e deleta uma marca.<br>
http://localhost:8080/v1/brands/{id}

### Usuário
GET Recebe um ID como parâmetro e retorna um usuário buscando pelo ID.<br>
http://localhost:8080/v1/clients/{id}

GET Retorna uma lista com todos os usuários cadastrados.<br>
http://localhost:8080/v1/clients

POST Recebes os dados na requisição e insere um usuário.<br>
http://localhost:8080/v1/clients

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um usuário.<br>
http://localhost:8080/v1/clients/{id}

DELETE Recebe um ID como parâmetro e deleta um usuário.<br>
http://localhost:8080/v1/clients/{id}