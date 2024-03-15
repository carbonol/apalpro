# apalpro
### ApAlPro: Apoyo para evaluación y retroalimentación de propuestas de solución a ejercicios o problemas en Algoritmia y Programación
#### Versión 1: Enero 27 de 2023

Este es un proyecto que fue diseñado con el propósito de construir una aplicación web para la evaluación y retroalimentación de propuestas de solución a ejercicios de programación (Python 3) que estuviere conformado por un backend en Java, un frontend en Angular (CLI) y una base de datos relacional (MySQL). En adición, el backend fue implementado con base en un patrón MVC y patrones DAO y DTO para el modelo (i.e., el M en el MVC). Además, se usan recursos y rutas con REST para la operación de la aplicación web.

Aunque se ha implementado y probado el funcionamiento de algunas de las rutas REST de este sistema, tales como:
1. Obtener la información de los roles de usuario (buscándolos todos, o por un ID).
2. Crear usuarios con roles de profesores o estudiantes [En una base de datos].
3. Crear ejercicios de programación (en el lenguaje Python 3) con sus respectivos casos de prueba [En una base de datos].
4. Obtener los datos de un ejercicio en la base de datos, con base al ID dado, pero que sólo muestre los casos de prueba visibles.

Aún hay mucho por implementar en este proyecto. En particular, aún no existe código relacionado con la interfaz del usuario que es visible a través de un browser (Es decir, no se ha desarrollado el frontend), y aún faltan muchas funcionalidades (principalmente rutas y clases DAO) que pueden implementarse en el backend y ser probadas con Postman. En particular, aún no se ha implementado un mecanismo de evaluación automática en este sistema. Como puede apreciarse, el trabajo en este repositorio está aún en progreso.

## Ambiente de desarrollo empleado

Este programa fue desarrollado con el IDE de Apache Netbeans como un proyecto Web Application de Java con Maven. Las especificaciones del ambiente de desarrollo empleado son las siguientes:

- **Sistema operativo (OS)**: Windows 10

- **Lenguaje de programación elegido inicialmente para probar la evaluación automática**: Python 3

- **Intérprete de Python instalado**: 3.11.1 (Anteriormente era Python 3.9.13)

### Especificaciones del nombre de proyecto en Java

- Project Name: apalpro
- Firm Name: alprosoft
- Artifact Id: apalpro
- Group Id: co.com.alprosoft
- Version: 1.0-SNAPSHOT
- Package: co.com.alprosoft.apalpro

### Base de datos: MySQL (Con soporte adicional de DBeaver)

1. Versión MySQL: MySQL Server 8.0.32 (Instalado con MySQL Installer Community, MySQL Community Server 8.0.32) (Anteriormente era MySQL 8.0.26)
2. [DBeaver](https://dbeaver.io/ "DBeaver"): Asistente para crear bases de datos MySQL (entre otros):  DBeaver 22.3.3 (23.01.2023) (Anteriormente era DBeaver 21.2.3.202110151816).

### Backend: Java

1. IDE: Apache Netbeans 16 (Anteriormente era la versión Apache Netbeans 12.4).
2. JDK: 17.0.6 (Anteriormente era la JDK 16.0.1 - Al respecto, tenga en cuenta que Payara Server no funciona con el JDK 16, sino con LTS tales como el JDK 17)
3. Payara Server 6.2023.1 (Payara Platform Community 6.2023.1)
4. Jakarta EE 10

#### Dependencias en el Backend
Como mínimo, se requieren las siguientes dependencias desde Maven, las cuales deben ser ingresadas en el archivo **pom.xml**:

1. [MySQL Connector Java](https://mvnrepository.com/artifact/mysql/mysql-connector-java "MySQL Connector Java"): versión 8.0.32
2. [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson "Gson"): versión 2.10.1
3. [Jakarta EE Platform API](https://mvnrepository.com/artifact/jakarta.platform/jakarta.jakartaee-api/10.0.0 "Jakarta EE Platform API"): versión [10.0.0](https://mvnrepository.com/artifact/jakarta.platform/jakarta.jakartaee-api/10.0.0 "10.0.0")

### Frontend: Angular

1. [NodeJS](https://nodejs.org/en/ "NodeJS"): Requerido para poder instalar Angular a nivel local: 18.13.0 LTS (Anteriormente era la v16.13.0)
2. npm: Gestor de paquetes (Package Manager) de NodeJS. Este es también requerido para poder instalar Angular, aunque usualmente viene incluido con NodeJS: npm 9.3.1 (Anteriormente era la 8.1.3)
2. [Angular CLI](https://angular.io/cli "Angular CLI"): Angular v15 (15.1.2) (Anteriormente era la 13.0.1)

### Software de apoyo para pruebas de rutas REST

1. [Postman](https://www.postman.com/ "Postman"): Empleado para probar rutas GET, POST, PUT y DELETE en la aplicación web y asegurarse de que las peticiones REST funcionan.

## Documentación relacionada con el stack tecnológico del software, incluyendo lenguajes de programación y demás herramientas relevantes

**Netbeans y Java**
https://netbeans.apache.org/help/index.html
https://docs.oracle.com/en/java/javase/16/
https://docs.oracle.com/en/java/javase/17/

**Python**
https://docs.python.org/3/

**MySQL**
https://dev.mysql.com/doc/

**DBeaver**
https://github.com/dbeaver/dbeaver/wiki

**NodeJS y npm**
https://nodejs.org/dist/latest-v18.x/docs/api/
https://docs.npmjs.com/

**Servidor Payara**
https://www.payara.fish/
https://docs.payara.fish/
https://docs.payara.fish/community/docs/Release%20Notes/Release%20Notes%206.2023.1.html

**Angular y Angular CLI**
https://angular.io/
https://angular.io/cli
https://angular.io/docs
https://angular.io/resources?category=community
https://blog.angular.io/?gi=c3d792913b37
https://angular.io/guide/setup-local

## Cómo usar este proyecto

Para usar y probar este proyecto, se requiere crear la base de datos e iniciar el servidor MySQL. Asegúrese de que los parámetros en la clase **DBConnection** (DBConnection.java) sean los correctos para realizar la conexión desde el backend (Java) hasta la base de datos. Luego, se debe iniciar el servidor Payara a través del IDE de Apache Netbeans, y a partir de este momento, se pueden probar las funcionalidades del sistema desarrollado en este repositorio.

### Rutas de prueba de funcionamiento del servidor

http://localhost:8080/
http://localhost:8080/apalpro/

### Funcionalidades implementadas

1) Obtener la información en la base de datos sobre los roles de usuario
1. GET localhost:8080/apalpro/resources/roles/get/all
2. GET localhost:8080/apalpro/resources/roles/{id}/get
    1. Ejemplo: GET localhost:8080/apalpro/resources/roles/1/get
    
2) Crear 1 usuario con rol de profesor
POST localhost:8080/apalpro/resources/users/create
**Body: raw: JSON (Postman)** 
````
    {
        "uname":"maurelio",
        "pwd":"123456",
        "names":"Marco Aurelio",
        "surnames":"Pérez Sánchez",
        "role_id":2
    }
````
3) Crear 1 usuario con rol de estudiante
POST localhost:8080/apalpro/resources/users/create
**Body: raw: JSON (Postman)**
````
{
    "uname":"lniebles",
    "pwd":"qwerty",
    "names":"Leandro Alejandro",
    "surnames":"Niebles Carbonó",
    "role_id":1
}
````

4) Crear 1 ejercicio de programación (en el lenguaje Python 3) con sus respectivos casos de prueba
POST localhost:8080/apalpro/resources/exercises/create/withTestCases
**Body: raw: JSON (Postman)**
````
{
    "name":"Suma de dos números enteros",
    "description":"Escriba un programa que lea dos números enteros y escriba el resultado de la suma de esos dos números en pantalla.",
    "test_cases": [
        {
            "input_data": "1\n2",
            "expected_output": "3",
            "is_visible": true
        },
        {
            "input_data": "5\n-1",
            "expected_output": "4"
        },
        {
            "input_data": "2\n-10",
            "expected_output": "-8"
        },
        {
            "input_data": "5\n0",
            "expected_output": "5"
        }
    ]
}
````

5) Permitir a un usuario (estudiante) ver los datos de un ejercicio, según su id, sólo con los casos de prueba visibles.
1. GET localhost:8080/apalpro/resources/exercises/{id}/get/withVisibleTestCases
    1. Ejemplo: GET localhost:8080/apalpro/resources/exercises/3/get/withVisibleTestCases

### Pendientes por desarrollar y probar - En desarrollo:
1) Crear (registrar) 1 propuesta de solución para un problema, que no cumpla con al menos un caso de prueba y almacenar los datos de la evaluación de la misma.
2) Crear (registrar) 1 propuesta de solución para un problema, que sí cumpla con al menos un caso de prueba y almacenar los datos de la evaluación de la misma.

## Licencia

MIT.

## Autor

Leandro Alejandro Niebles Carbonó (GitHub: carbonol).
