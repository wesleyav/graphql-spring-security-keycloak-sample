# Projeto básico usando GraphQL, Spring Security e Keycloak

> Status do Projeto: :heavy_check_mark: CONCLUÍDO

<table>
  <tr>
    <td><img widht="100" height="100" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" /></td>
    <td><img widht="100" height="100" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain-wordmark.svg" /></td>
    <td><img widht="100" height="100" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/graphql/graphql-plain-wordmark.svg" /></td>
    <td><img widht="100" height="100" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg" /></td>
    <td><img widht="100" height="100" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/tomcat/tomcat-original-wordmark.svg" /></td>
    <td><img widht="100" height="100" src="./src/main/resources/docs/img/keycloak-logo.svg" /></td>
  </tr>
</table>

### Descrição
Este é um projeto simples que ajuda a entender a estrutura e funcionamento do GraphQL com Spring Boot. Utiliza o MySQL como banco de dados, possui apenas duas tabelas (city e country) que foram obtidas do banco de dados de amostra Sakila disponível em (https://dev.mysql.com/doc/sakila/en/). Possui autenticação via Keycloak.

### Pré-requisitos
* Java JDK 11
* Base de dados Sakila

### Modelo Entidade Relacionamento (MER)
![](/src/main/resources/docs/img/graphql-mer.png)

### Banco de dados MySQL
#### Cria banco de dados sakila
```sql
CREATE DATABASE `sakila` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
```
#### Cria as tabelas:
```sql
-- sakila.country definition

CREATE TABLE `country` (
  `country_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.city definition

CREATE TABLE `city` (
  `city_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `country_id` smallint unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`city_id`),
  KEY `idx_fk_country_id` (`country_id`),
  CONSTRAINT `fk_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

#### Adiciona registros na tabela Country:
```sql
INSERT INTO sakila.country (country_id, country, last_update) VALUES(1, 'Afghanistan', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(2, 'Algeria', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(3, 'American Samoa', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(4, 'Angola', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(5, 'Anguilla', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(6, 'Argentina', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(7, 'Armenia', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(8, 'Australia', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(9, 'Austria', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(10, 'Azerbaijan', '2006-02-15 04:44:00');
```

#### Adiciona registros na tabela City:
```sql
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(1, 'A Corua (La Corua)', 87, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(2, 'Abha', 82, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(3, 'Abu Dhabi', 101, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(4, 'Acua', 60, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(5, 'Adana', 97, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(6, 'Addis Abeba', 31, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(7, 'Aden', 107, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(8, 'Adoni', 44, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(9, 'Ahmadnagar', 44, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(10, 'Akishima', 50, '2006-02-15 04:45:25');
```

### Dependências do GraphQL, GraphiQL e Playground
```xml
<dependencies>
    <!-- GraphQL dependencies -->
    <!-- https://mvnrepository.com/artifact/com.graphql-java/graphql-java-tools -->
    <dependency>
        <groupId>com.graphql-java</groupId>
        <artifactId>graphql-java-tools</artifactId>
        <version>5.2.4</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.graphql-java/graphql-spring-boot-starter -->
    <dependency>
        <groupId>com.graphql-java</groupId>
        <artifactId>graphql-spring-boot-starter</artifactId>
        <version>5.0.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.graphql-java-kickstart/graphiql-spring-boot-starter -->
    <dependency>
        <groupId>com.graphql-java-kickstart</groupId>
        <artifactId>graphiql-spring-boot-starter</artifactId>
        <version>11.1.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.graphql-java-kickstart/playground-spring-boot-starter -->
    <dependency>
        <groupId>com.graphql-java-kickstart</groupId>
        <artifactId>playground-spring-boot-starter</artifactId>
        <version>11.1.0</version>
    </dependency>
    <!-- Keycloak dependencies -->
    <dependency>
        <groupId>org.keycloak</groupId>
        <artifactId>keycloak-spring-boot-starter</artifactId>
    </dependency>
</dependencies>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.keycloak.bom</groupId>
            <artifactId>keycloak-adapter-bom</artifactId>
            <version>14.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### Como rodar a aplicação:
No terminal, clone o projeto:
```git
git clone https://github.com/wesleyav/graphql-spring-security-keycloak-sample.git
```
Na raiz do projeto, execute:
```bash
mvn spring-boot: run
```

### Endpoints
Esse projeto possui 3 endpoints que podem ser customizados no arquivo application-dev.properties conforme informações disponiveis em https://github.com/graphql-java-kickstart/graphql-spring-boot.

/graphql - endpoint para solicitações via POST
/graphiql - endpoint para solicitações via GET (console web)
/playground - endpoint para solicitações via GET (console web)

### Keycloak
Para acessar os endpoints é necessário autenticar via Keycloak. Uma vez configurado o servidor Keycloak e criado e configurado um usuário, ao tentar acessar algum dos endpoints do GraphQL será solicitado login e senha no Keycloak.

![](/src/main/resources/docs/img/keycloak-login.png)
<br>

#### Acessando o console GraphiQL
No browser, acesse http://localhost:8082/graphiql

![](/src/main/resources/docs/img/graphiql.png)
<br>

##### Query cidade por Id:

![](/src/main/resources/docs/img/graphiql-query-cityById.png)
<br>

##### Query cidades:

![](/src/main/resources/docs/img/graphiql-query-cities.png)
<br>

#### Acessando o console Playground
No browser, acesse http://localhost:8085/playground

![](/src/main/resources/docs/img/playground.png)
<br>

##### Query cidade por Id:

![](/src/main/resources/docs/img/playground-query-cityById.png)
<br>

##### Query cidades:

![](/src/main/resources/docs/img/playground-query-cities.png)
<br>

#### Acessando via Postman

##### Query cidade por Id:
![](/src/main/resources/docs/img/postman-query-cityById.png)
<br>

##### Query cidades:
![](/src/main/resources/docs/img/postman-query-cities.png)
<br>

## Licença 

The [MIT License]() (MIT)
Copyright :copyright: 2022 - Projeto básico usando GraphQL, Spring Security e Keycloak