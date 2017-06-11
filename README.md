# ![BubbleMC](http://i.imgur.com/2WAUYyB.png)
Плагин для Minecraft сервера выполняющий команды из базы данных

  - Выполнение команд от имени сервера
  - Удаление команды после выполнения
  - Работа с MySql
  - Обработка исключений, невозможность сбоев, например, повторного выполнения команды
  - Гибкий конфигурационный файл
  - Работа с [Bubble](https://github.com/MarshalX/Bubble) из-под коробки

## Установка
1. [Скачать](https://github.com/MarshalX/BubbleMC/releases/latest) последнюю версию плагина
2. Закинуть .jar файл в папку plugins вашего сервера
3. Перезапустить сервер для появления конфигурационного файла
4. Настроить плагин
5. Перезапустить сервер или выполнить reload для применения новых настроек

## Настройка
```
database:
  host: localhost     # IP адрес с БД
  port: 3306     # Порт на котором запущен MySql сервер
  user: root    # Логин пользователя
  password: root    # Пароль
  name: bubble    # Название базы данных
struct:
  table: bubble_task    # Таблица где хранятся команды
  columnCmd: task_cmd    # Колонка с выполняемой командой
  columnId: id    # Колонка с уникальным индефикатором задания
basic:
  prefix: '[Bubble] '    # Префикс плагина
  timer: 15    # Промежуток в секундах между проверками на наличие новых заданий
  delay: 5    # Задержка в секундах между попытками переподключения к БД
```

Конфиг структуры с значениями под автоматическую систему доната [Bubble](https://github.com/MarshalX/Bubble) создаётся при первом запуске плагина

## Лицензия
GNU General Public License v3.0
