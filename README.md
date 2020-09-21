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
1. Instale o lombok na IDE
2. Import como Existing Maven Projects
3. Selecione o diretório e finish
4. Clique com obotão direito no projeto e run > java application

## Obter Autorização
Utilizado Basic Auth para autorização.<br>
Digite as credenciais para obter autorização:<br>
Username: **luiz**<br>
Password: **123**<br>

## Acesso aos Recursos

### Patrimônio
GET Recebe um ID como parâmetro e retorna um patrimônios buscando pelo ID.<br>
**/v1/patrimony/{id}**

GET Retorna uma lista com todos os patrimônios cadastrados.<br>
**/v1/patrimony**

POST Recebes os dados na requisição e insere um patrimônio.<br>
**/v1/patrimony**

```
{
    "brand": {
        "id": {id}
    },
    "name": "Nome do Patrimônio",
    "description": "Descrição"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um patrimônio.<br>
**/v1/patrimony/{id}**

```
{
    "brand": {
        "id": {id}
    },
    "name": "Nome do Patrimônio",
    "description": "Descrição"
}
```

DELETE Recebe um ID como parâmetro e deleta um patrimônio.<br>
**/v1/patrimony/{id}**

### Marca
GET Recebe um ID como parâmetro e retorna uma marca buscando pelo ID.<br>
**/v1/brands/{id}**

GET Retorna uma lista com todos as marcas cadastradas.<br>
**/v1/brands**

POST Recebes os dados na requisição e insere uma marca.<br>
**/v1/brands**

```
{
    "name": "Marca"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza uma marca. ID da marca é opcional na atualização<br>
**/v1/brands/{id}**

```
{
    "name": "Marca"
}
```

DELETE Recebe um ID como parâmetro e deleta uma marca.<br>
**/v1/brands/{id}**

### Usuário
GET Recebe um ID como parâmetro e retorna um usuário buscando pelo ID.<br>
**/v1/users/{id}**

GET Retorna uma lista com todos os usuários cadastrados.<br>
**/v1/users**

POST Recebes os dados na requisição e insere um usuário. Este recurso não necessita de autenticação.<br>
**/v1/users**

```
{
    "name": "Nome da Pessoa",
    "email": "email@pes.com",
    "password": "123"
}
```

PUT Recebe um ID e os dados na requisição como parâmetro e atualiza um usuário.<br>
**/v1/users/{id}**

```
{
    "name": "Nome da Pessoa",
    "email": "email@pes.com",
    "password": "123"
}
```

DELETE Recebe um ID como parâmetro e deleta um usuário.<br>
**/v1/users/{id}**


