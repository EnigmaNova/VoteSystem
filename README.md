# 🗳️ VoteSystem — Веб-приложение для проведения опросов

Это Spring Boot-приложение позволяет создавать и проходить опросы. Реализована регистрация пользователей, аутентификация, работа с базой данных, отображение опросов и подсчёт голосов. Проект подготовлен для развёртывания в Docker-среде.

---

## 🚀 Быстрый старт (через Docker Compose)

### 1. Клонировать репозиторий:
```bash
git clone https://github.com/EnigmaNova/votesystem.git
cd votesystem
```

### 2. Создайте `.env` файл в корне проекта и укажите значения:
```
DB_NAME=your_db
DB_USER=your_user
DB_PASSWORD=your_password
```

### 3. Запустить проект:
```bash
docker-compose up --build
```

### 4. Перейти в браузере:
[http://localhost:8080](http://localhost:8080)

---

## ⚙️ Технологии

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- Thymeleaf  
- PostgreSQL  
- Docker + Docker Compose

---

## 📦 Структура проекта

Проект структурирован по слоям:
```
src/
├── controllers     // REST и MVC контроллеры
├── services        // бизнес-логика
├── repositories    // работа с базой
├── models          // JPA-сущности
├── dto             // классы передачи данных
```

---

## 🧩 Основной функционал

- Регистрация и логин пользователей  
- Просмотр активных опросов  
- Голосование  
- Просмотр результатов  
- Защита роутов и данных с помощью Spring Security  
- Работа с переменными окружения через `.env`

---

## 📄 Запуск без Docker

Если нужно, можно запустить проект вручную:

1. Установите PostgreSQL и создайте базу данных  
2. Укажите настройки в `application.properties` или через переменные окружения  
3. Соберите и запустите:
```bash
mvn spring-boot:run
```

---

## 👨‍💻 Автор

[EnigmaNova](https://github.com/EnigmaNova)
