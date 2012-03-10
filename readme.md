## ruFix - фикс кодировки для Bukkit ##
ruFix это плагин перекодировки русского языка для сервера Minecraft.

Работает с сервером Bukkit версии 1.1-R1 и старше (после коммита f8096d).

Преобразует кодировку от клиентов в UTF-8 (или что-либо другое что вам нужно), что позволяет без проблем общаться в чате по-русски и писать сообщения на русском языке из консоли сервера ("say привет").

Для перезагрузки конфига и таблиц используйте команду **/rufixreload**.

## Конфигурация ##

Все настройки плагина хранятся в файле config.yml в папке ruFix.

**Tables:** - тут указываются файлы .tbl, содержащие таблицы символов для конвертирования. По-умолчанию - ru

**Debug:** false - при значении true выводит отладочную информацию. Много информации.

**LogFile:** UTF-8 - кодировка для лог-файла сервера. Работает только при включенной опции **ParseLogFile**

**Console:** UTF-8 - кодировка консоли. Работает только при включенной опции **ParseConsole**

**ParseConsole:** true - позволяет выключить обработку данных из консоли. Полезно на linux-машинах.

**ParseLogFile:** true - позволяет выключить обработку лог-файла. Помогает избежать проблем с некоторыми плагинами, которые работают с ним.

## ToDo: ##

* ???

* Есть идеи?

---------------------------------------

## ruFix - encoding fix for Bukkit ##
ruFix is a plugin that converts encoding in chat and console in Minecraft.

It works with Bukkit 1.1-R1 and newer (after f8096d commit).

How it works: it translates encoding from clients and makes it UTF-8 (or anything you like). This helps to read and write in chat in other languages than english. Also, this fixes console commands ("say привет").

To reload config and encoding tables use **/rufixreload**.

## Configuration explained ##

All configuration data is stored in config.yml file in ruFix folder.

**Tables:** - here .tbl files are listed. They contain characters to be replaced and values to replace. By default - ru

**Debug:** false - if "true" shows debug info. Lots of.

**LogFile:** UTF-8 - this is encoding your log-file should be converted to. Works only when **ParseLogFile** is "true"

**Console:** UTF-8 - proper console encoding. Works only when **ParseConsole** is "true"

**ParseConsole:** true - switch this to "false" if you don't need console encoding to be changed. Useful on linux machines.

**ParseLogFile:** true - switch this to "false" if you don't want your log-file encoding to be changed. Helps to avoid conflicts with logfile-related plugins.

## ToDo: ##

* ???

* Any ideas?
