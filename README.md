# CheckTask Application

## Описание
Консольное приложение для генерации чека на основе данных из файлов CSV. Поддерживает обработку скидочных карт и оптовых скидок.

## Установка и запуск
1. Склонируйте репозиторий:
   ```sh
   git clone <repository-url>
2. Перейдите в терминал и введите:
   ```sh
   mkdir -p out
   javac -d out src/main/java/ru/clevertec/check/*.java src/main/java/ru/clevertec/check/model/*.java src/main/java/ru/clevertec/check/service/*.java src/main/java/ru/clevertec/check/util/*.java src/main/java/ru/clevertec/check/exception/*.java
   
3. Запустите приложение командой:
   ```sh
   java -cp out src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100

4. Результат выведется на консоль и создаться файл в котором продублируется результат:
    ```sh
    result.csv
