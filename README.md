# CheckTask Application

## Описание
Консольное приложение для генерации чека на основе данных из файлов CSV. Поддерживает обработку скидочных карт и оптовых скидок.

## Установка и запуск
1. Склонируйте репозиторий:
   ```sh
   git clone <repository-url>
2. Перейдите в директорию проекта:
   ```sh
   cd <project-directory>
3. Запустите приложение:
   ```sh
   java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100
