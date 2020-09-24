## Projeto API Patrimonial
Este programa tem o objetivo implementar uma api rest para gerenciamento de patrimônio empresarial.

## Implementação
Este projeto foi implementado usando spring boot com padrão MVC contendo as seguintes dependências:<br>
Spring Web<br>
Spring Data JPA<br>
Spring Boot DevTools<br>
Spring Security<br>
Postgres Driver<br>
Lombok<br>
Model Mapper<br>
Spring Security<br>

IDE: Spring Tool Suite 4


## Iniciando o projeto
Clone o repositório

```
git clone https://github.com/webvirtua/patrimonyManagementApi.git
```
Crie a base de dados postgres com o nome patrimony.

```
create database patrimony;
```

Atualize username e password no arquivo resources/application.properties.

```
spring.datasource.username=USUARIO-BANCO
spring.datasource.password=SENHA-BANCO

```

1. Instale o lombok na IDE
2. Import como Existing Maven Projects
3. Selecione o diretório e finish
4. Clique com obotão direito no projeto e run > java application

## Obter Autorização (Spring Security)
Utilizado Basic Auth para autorização.<br>
Digite as credenciais para obter autorização:<br>
Username: **luiz**<br>
Password: **123**<br>

## Acesso aos Recursos
Corpo e resposta das requisição são em formato JSON.

Padrão de response:

```
{
    "success": 1,
    "status": 200,
    "totalResults": 1,
    "resultsPerPage": 0,
    "totalPages": 0,
    "page": 0,
    "successMessage": "Resultados Obtidos",
    "errorMessage": null,
    "validationsErrosMessage": null,
    "data": {
        "id": 3352,
        "brand": {
            "id": 71,
            "name": "Marca A"
        },
        "name": "Patrimônio C",
        "tumble": 3352,
        "description": "Descrição do item."
    }
}
```

### Patrimônio
GET Recebe um ID como parâmetro e retorna um patrimônios buscando pelo ID. (parâmetro ID é obrigatório)<br>
**/v1/patrimony/{id}**

GET Retorna uma lista com todos os patrimônios cadastrados.<br>
**/v1/patrimony**

POST Recebes os dados na requisição e insere um patrimônio. (no corpo da requisição description é opcional e o restante obrigatório)<br>
**/v1/patrimony**

Exemplo:

```
{
    "brand": {
        "id": {id}
    },
    "name": "Nome do Patrimônio",
    "description": "Descrição"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um patrimônio. (parâmetro ID é obrigatório) (no corpo da requisição description é opcional e o restante obrigatório)<br>
**/v1/patrimony/{id}**

Exemplo:

```
{
    "brand": {
        "id": {id}
    },
    "name": "Nome do Patrimônio",
    "description": "Descrição"
}
```

DELETE Recebe um ID como parâmetro e deleta um patrimônio. (parâmetro ID é obrigatório)<br>
**/v1/patrimony/{id}**

### Marca
GET Recebe um ID como parâmetro e retorna uma marca buscando pelo ID. (parâmetro ID é obrigatório)<br>
**/v1/brands/{id}**

GET Retorna uma lista com todos as marcas cadastradas.<br>
**/v1/brands**

POST Recebes os dados na requisição e insere uma marca. (o corpo da requisição é obrigatórios)<br>
**/v1/brands**

Exemplo:

```
{
    "name": "Marca"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza uma marca. (parâmetro ID é obrigatório) (o corpo da requisição é  obrigatório)<br>
**/v1/brands/{id}**

Exemplo:

```
{
    "name": "Marca"
}
```

DELETE Recebe um ID como parâmetro e deleta uma marca. (parâmetro ID é obrigatório)<br>
**/v1/brands/{id}**

### Usuário
GET Recebe um ID como parâmetro e retorna um usuário buscando pelo ID. (parâmetro ID é obrigatório)<br>
**/v1/users/{id}**

GET Retorna uma lista com todos os usuários cadastrados.<br>
**/v1/users**

POST Recebes os dados na requisição e insere um usuário. Este recurso não necessita de autenticação. (o corpo da requisição é  obrigatório)<br>
**/v1/users**

Exemplo:

```
{
    "name": "Nome da Pessoa",
    "email": "email@pes.com",
    "password": "123"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um usuário. (parâmetro ID é obrigatório) (o corpo da requisição é  obrigatório)<br>
**/v1/users/{id}**

Exemplo:

```
{
    "name": "Nome da Pessoa",
    "email": "email@pes.com",
    "password": "123"
}
```

DELETE Recebe um ID como parâmetro e deleta um usuário. (parâmetro ID é obrigatório)<br>
**/v1/users/{id}**


