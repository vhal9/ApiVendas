![GitHub repo size](https://img.shields.io/github/repo-size/vhal9/ApiVendas)
![GitHub top language](https://img.shields.io/github/languages/top/vhal9/ApiVendas)
![Versao Java](https://upload.wikimedia.org/wikipedia/commons/7/75/Blue_JDK_1.8%2B_Shield_Badge.svg)

# Api de Vendas

## Resumo

Este repositorio contém uma API de vendas desenvolvida durante o curso de [Spring Boot Expert](https://www.udemy.com/course/spring-boot-expert/). 
Este curso teve ênfase no Spring Boot, Web, Data e Security.

A aplicação foi desenvolvida utilizando Java em conjunto do Framework Spring e utilizado o Swagger para a documentação da mesma. 


## Dependências

- JDK 1.8
- Maven 3

## Tecnologias

- JAVA
- Spring Boot
- Lombok
- H2 Database
- Swagger

## Execução

Clone o projeto:

```
git clone https://github.com/vhal9/ApiVendas.git
```

Execute:

```
./deploy desenvolvimento
```

Acesso a aplicação pelo link:
```
http://localhost:8081/sistema-vendas/swagger-ui.html
```

## Importação IntelliJ

```
File -> Open -> Selecione a raiz do projeto
```

## Features

- Migrar a aplicação para o Postgresql.
- Adicionar verificação para validar o campo login como único.
- relacionar pedidos com os usuários.
- centralizar todas as mensagens de erros.
- redefinir regras de acesso a rota de produtos.
- incrementar a documentação do Swagger.

