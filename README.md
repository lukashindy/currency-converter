# Выполнение тестового задания "Конвертер валют"

Данный проект выполнен на основе задания: https://github.com/revkov/JAVA.SB2.TEST



# 1. PostgreSQL.

Скрипт на создание базы данных в PostgreSQL - выполнен перед разработкой на коде Java (Spring Framework, Spring Boot)

``` 
create database currency_converter

create table users (
    id             bigint primary key not null,
    login          varchar(30) UNIQUE NOT NULL,
    password       varchar(100) NOT NULL
);

CREATE TABLE currency (
    id         varchar(10)  PRIMARY KEY,
    num_code   char(3) NOT NULL,
    char_code  char(3) NOT NULL,
    nominal    money NOT NULL,
    name       varchar(30) NOT NULL
);
 
CREATE TABLE currency_rate (
    currency_id varchar(10),
    date        date NOT NULL,
    value       bigdecimal(18,4) NOT NULL,
    primary key (currency_id, date),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);
 
CREATE TABLE history (
    Id SERIAL PRIMARY KEY NOT NULL, 
    source_currency_id  varchar(10) NOT NULL,
    target_currency_id  varchar(10) NOT NULL,
    source_sum          bigdecimal(18,4) NOT NULL,
    target_sum          bigdecimal(18,4) NOT NULL,
    date_history        date NOT NULL,
    FOREIGN KEY (source_currency_id) REFERENCES currency (id),
    FOREIGN KEY (target_currency_id) REFERENCES currency (id)
);
```

В файле application.properties необходимо указан логопасс и ссылку на сервер для подключения к БД

- spring.datasource.url=jdbc:postgresql://localhost:5432/currency_converter
- spring.datasource.username=postgres
- spring.datasource.password=password


# 2. Java Spring Boot.

Данное приложение позволяет проводить расчеты по валютам, а также просматривать истории. Кроме того, приложение 
автоматически скачивает все валюты, указанные по ссылке в тестовом задании. Поскольку в нем нет самой российской валюты,
то также она также была добавлена в базу данных. 



