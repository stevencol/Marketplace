### Ejecución del Proyecto 🚀

#### Se recomienda utilizar Docker para ejecutar el proyecto, ya que se ha configurado un dockerCompose y es la forma más sencilla.

## Requisitos con Docker:

1. Docker instalado en tu máquina.
2. Docker en ejecución.
3. Puerto 8080 disponible (puedes cambiarlo en el Dockerfile al ejecutar la imagen).
4. Ejecutar el siguiente comando en la raíz del proyecto:

```bash
 docker-compose up  -d

```

*Si el puerto 8080 llega a estar ocupado en tu máquina local, lo puedes cambiar modificando la primera parte del
parámetro de puerto en el comando de ejecución.*

## Ejecución del Proyecto con Gradle ⚙️️

### Requisitos:

1. Java 21 instalado en tu máquina.
2. Dirigirse a la raíz del proyecto.
3. Ejecutar el siguiente comando:
4. Tener una base de datos con MySQL en ejecución y una bd llamada markeplace.

```bash
./gradlew clean build -x test -x validateStructure

$env:DATABASE_NAME="markeplace"
$env:DATABASE_PASSWORD="tu_password_real"
$env:DATABASE_URL="jdbc:mysql://localhost:3306/"
$env:DATABASE_USER="tu_usuario_real"
./gradlew bootRun
```