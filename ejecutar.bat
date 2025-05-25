@echo off
:: Compilar todos los archivos
javac -cp ".;lib\sqlite-jdbc-3.49.1.0.jar" Main.java GestorContactos.java ConexionSQLite.java Contacto.java

:: Ejecutar el programa con el .jar en el classpath
java -cp ".;lib\sqlite-jdbc-3.49.1.0.jar" Main

pause
