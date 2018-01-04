## Compilación
Para compilar el proyecto y generar un recopilado de ejecutables de la JVM, un archivo JAR.
Basta con ejecutar Apache-Ant sin argumentos, pues se ha especificado el target descrito por
defecto:

	[user@host p02]$ ant

## Linux man pages
Si es preferible que las instrucciones generales de uso sean agregadas al Linux man pages para así mante-
ner accesible la forma de uso del proyecto, preparamos el siguiente target en ant para este proposito:

	[user@host p02]$ ant man

Cuya ejecución solicitará permisos de superusuario para agregar la página de manual de este
proyecto. Una vez ingresada la contraseña de sudo será posible visitar el manual desde cualquier
ruta mediante el siguiente comando:

	[user@host ~]$ man proyecto2

## Generación de documentación
Para explorar la organización programática del proyecto sin necesidad de visitar todos los códi-
gos fuente involucrados en la construcción del proyecto, recomendamos generar la documentación
de código html, para así hallar la explicación de cada método implementado, sus entradas y salidas
y el papel que desempeñan en el protocolo. Para ello, basta efectuar:

	[user@host p02]$ ant doc

Esto generará un directorio doc/, que contiene un conjunto de documentos en formato html des-
criptivos de cada clase en el código, relacionados unos con otros a través de hipervínculos que
permiten una navegación fluida que no necesariamente es posible en la lectura directa del código.

## Precondiciones
Antes de comenzar la ejecución es importante que de las secciones anteriores se haya efectuado
cuando menos la compilación. En caso de querer ahorrar los pasos anteriores y simplemente convenimos que se ejecuten todos, basta con el siguiente target de ant:

	[user@host p02]$ ant all
    
También es importante que el archivo de la base de datos de SQLite se encuentre bajo la
ruta: src/sql/hi.db. De no hallarse ahí, pero sí el esquema de la base de datos en el archivo
src/sql/poke_app_db.sql, es posible generar la base de datos ejecutando:

	[user@host p02/src/sql]$ sqlite3 hi.db < poke_app_db.sql

Finalmente, sólo resta verificar que las imágenes a utilizar por la aplicación se encuentren
en la ruta adecuada. Por ejemplo, para el pokémon charmander, debe existir la imagen sta-
tic/charmander.png. En caso de no hayarse ninguno, a continuación damos una serie de instruc-
ciones 1 para que el script incluído en ese directorio descargue de internet nuevamente las imágenes
que la aplicación mostrará.

	[user@host p02/static]$ virtualenv py_env
	
	[user@host p02/static]$ source py_env/bin/activate
	
	(py_env)[user@host p02/static]$ pip install -r requirements
	
	(py_env)[user@host p02/static]$ python3 poke_script.py

Una vez asegurandonos de esto, podemos proceder a la ejecución de la aplicación.

## Ejecución de pruebas unitarias
Si se desea incluir nuevas pruebas en el directorio src/test/ para la verificación de alguna
función del protocolo o simplemente correr las ya existentes, ejecutamos lo siguiente:
	
	[user@host p02]$ ant test

Que compilará y ejecutará tanto el código de las pruebas como el de la aplicación y listará los
tiempos, errores y salidas sin errores de cada una.

## Ejecución
La ejecución del programa como viene especificado en el manual incluído se realiza
mediante el archivo JAR resultado de la compilación y posee una cantidad de argumentos tanto
para un cliente como para unservidor. Es importante señalar que decidimos manejar este archivo
como uno solo, es decir se utiliza tanto para la ejecución de un cliente como la de un servidor,
diferenciando el comportamiento del programa mediante los argumentos.
Para ejecutar la aplicación con sus valores por defecto para cada parte de la comunicación, efec-
tuamos al menos uno 2 de cada uno 3 de los siguientes comandos:
### Servidor
	
	[user@host p02]$ java -jar proyecto2.jar

### Cliente
	
	[user@host p02]$ java -jar proyecto2.jar -c