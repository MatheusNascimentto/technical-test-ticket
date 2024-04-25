# Desenvolvimento de Microserviços em Java

Este projeto foi desenvolvido para estudo neste treinamento utilizei Spring Boot,
MySQL, Banco H2, Java,e diversas bibliotecas
Nele é possivel gerenciar informações de pessoas e boletos, cadastrar pessoas , cadastrar Boletos,
realizar pagamentos de boletos, editar/deletar/atualizar (Pessoas e Boletos)...

## Introdução


## Como executar o projeto

Pré-requisitos:

* Java 17 ou versões superiores.
* Intellij (IDE que foi utilizada para codar).
* Controle de versão GIT instalado na sua máquina.
* Banco MySQL
* Banco H2 usada para TEST acessaar(http://localhost:8080/h2-console)

```bash

#Executar o projeto


O arquivo do postman esta fixado no projeto para utilizar.

Porta da web : http://localhost:8080
Acessar Swagger: http://localhost:8080/swagger-ui


```

## Banco de dados já esta populado para teste

```bash

#Executar o Banco de Dados

Dentro do projeto em "resource" tem 2 properties a mais um "application-test.properties" para banco H2
e o outro e "application-dev.properties" para o MySQL. Ambos com Banco populado.

no "application.properties" principal esta as configs do banco H2 que fica mais facil para rodar e acessar a URL no navegador.
caso preferir e so mudar as configs para o MYSQL e abrir o banco pela sua maquina.


```


# Tecnologias utilizadas

## Back End

-Java
-Spring Boot
-Spring Data JPA
-Maven
-Lombok
-SpringDoc OpenAPI SWAGGER
-Mysql etc...


## Autor
Matheus Nascimento