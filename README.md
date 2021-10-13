# Микросервис "Планер"
 
### Автор: Леонтьева Екатерина Андреевна
 
### Запуск локально для отладки:
 
* установка postgres
 
```
sudo apt install postgresql-10
```
 
* Создание пользователя и базы
 
```
sudo -s
su - postgres
psql
create user todo_list_user with password 'password'; # todo_list - заменить на наименование проекта
create database todo_list owner todo_list_user; # todo_list - заменить на наименование проекта
```
 
* Запустить приложение с профилем --spring.profiles.active=dev