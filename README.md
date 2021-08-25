# MeetingWebsiteEmulator

ru
Программа эмулирует очередь демонстрации пользователей на странице сайта знакомств.
По умолчанию порядок очереди зависит от времени регистрации на сайте.
После каждого показа пользователь отправляется в конец очереди.
Пользователь может оплатить услугу по перемещению в начало очереди.

Для запуска эмуляции необходимо запустить сервер Redis,
по умолчанию в файле src/main/resources/application.properties
указан host=127.0.0.1, port=6379 и key=USERS, если ваш север
запускается с другими параметрами, замените настройки, на ваши, в 
файле src/main/resources/application.properties.
После запуска сервера запустите класс Main.

Параметры подключения к базе, сохранения, получения, обновления и удаления данных  
описаны в классе com.grommash88.app.storage.RedisStorage имплементирующем 
интерфейс com.grommash88.app.storage.Storage и классе com.grommash88.app.properties.Props

Логика демонстрации пользователей реализована в классе com.grommash88.app.site.SiteImpl
имплементирующем интерфейс com.grommash88.app.storage.Site.

Модель пользователя реализована в классе com.grommash88.app.model.User.

Логирование сообщений в консоль и исключений в файл logs/exception.log
реализовано в классе com.grommash88.app.util.AppLogger, настройки логирования 
описаны в файле src/main/resources/log4j2.xml, шаблоны сообщений описаны в классе
com.grommash88.app.util.Msgs.

eng
The program emulates the queue of demonstrating users on a page of a dating site.
By default, the order of the queue depends on the time of registration on the site.
After each impression, the user is sent to the back of the queue.
The user can pay for the service to move to the head of the queue.

To start emulation, you need to start the Redis server,
by default in src/main/resources/application.properties file
host=127.0.0.1, port=6379 and key=USERS are specified if your north
runs with different parameters, replace the settings with yours, in
src/main/resources/application.properties file.
After starting the server, run the Main class.

Parameters for connecting to the database, saving, retrieving, updating and deleting data
are described in the com.grommash88.app.storage.RedisStorage class which implements
interface com.grommash88.app.storage.Storage and class com.grommash88.app.properties.Props

The user demo logic is implemented in the com.grommash88.app.site.SiteImpl class
implementing the com.grommash88.app.storage.Site interface.

The user model is implemented in the com.grommash88.app.model.User class.

Logging messages to the console and exceptions to the logs/exception.log file
implemented in the com.grommash88.app.util.AppLogger class, logging settings
are described in src/main/resources/log4j2.xml file, message templates are described in class
com.grommash88.app.util.Msgs.