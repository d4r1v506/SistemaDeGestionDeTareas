# Microservicio de Gestion De Tareas
## Descripción

Este microservicio forma parte del sistema de gestión de tareas desarrollado para la empresa XYZ. Su proposito es getionar la información de las tareas y asignación a los usuarios.

Es un Backend en Java con Spring Boot para un sistema de gestión de tareas. Este proyecto expone un conjunto de APIs RESTful para la gestión de tareas, proporciona funcionalidades CRUD, y permite su integración con otros microservicios del sistema, como el servicio de usuarios (user-service), a través de una arquitectura basada en microservicios y comunicación asíncrona.

## Características Principales
- Implementación de CRUD completo para la gestión de tareas.
- Validación de datos de entrada para garantizar integridad y consistencia.
- Integración con el API Gateway y comunicación asíncrona con otros microservicios.
- Contenedor Docker para facilitar el despliegue y escalabilidad.

## Entidad Tarea
Las tareas proyectos o actividades dentro del sistema. La estructura de la entidad tarea es la siguiente:
- **ID:** Identificador único.
- **Código de tarea:** Codigo alftanumerico de la tarea.
- **Titulo:** Nombre de la tarea.
- **Descripción:** breve información de la tarea.
- **Criterios de aceptación:** funciones de la tarea.
- **Fecha de inicio:** fecha de inicialización de la tarea.
- **Fecha de finalización:** fecha de terminación de la tarea.
- **Tiempo de desarrollo:** duración en horas de la tarea.
- **Estado de tarea:** estados de la tarea (Backlog, Doing, In Review, Done).
- **id Usuario:** identificador del usuario asignado a la tarea

## Entidad Estado
el estado del usuario dentro del sistema puede ser Activo/Inactivo. La estructura de la entidad estado es la siguiente:
- **ID:** identificador único.
- **Nombre:** Descripción unico del estado (Backlog, Doing, In Review, Done).

## Endpoints del Microservicio
**Obtener Token**
- **Método:** POST
- **URL:** /api/auth/getToken
- **RequestBody:**

{
    "username": "admin",
    "password": "admin123"
}

**Crear Tarea**
- **Método:** POST
- **URL:** /api/tareas/
- **Authorization:** Token Bearer
- **Request Body:**

{
  "codigoTarea": "T123",
  "titulo": "Desarrollar sistema de gestión de tareas",
  "descripcion": "Desarrollar un sistema para gestionar las tareas del equipo.",
  "criteriosAceptacion": "El sistema debe permitir crear, editar y eliminar tareas.",
  "fechaInicio": "2024-12-10T08:00:00",
  "fechaFinalizacion": "2024-12-20T17:00:00",
  "tiempoDesarrollo": 10,
  "estado": "BACKLOG"
}


- **Response:**

{
    "state": "SUCCESS",
    "data": {
        "id": 2,
        "codigoTarea": "T001",
        "titulo": "Desarrollar sistema de gestión de tareas",
        "descripcion": "Desarrollar un sistema para gestionar las tareas del equipo.",
        "criteriosAceptacion": "El sistema debe permitir crear, editar y eliminar tareas.",
        "fechaInicio": "2024-12-10",
        "fechaFinalizacion": "2024-12-20",
        "tiempoDesarrollo": 10,
        "estado": {
            "id": 1,
            "estado": "BACKLOG"
        },
        "idUsuario": null
    },
    "message": [
        "Tarea creada exitosamente"
    ]
}

- **Codigos de Estado:**
    - 201: Creado.
    - 400: Datos inválidos
    - 300: Prohibido

**Obtener tarea por id**
- **Método:** GET
- **URL:** /api/tareas/{id}
- **Authorization:** Token Bearer
- **Response:**

{
    "state": "SUCCESS",
    "data": {
        "id": 1,
        "codigoTarea": "T123",
        "titulo": "Desarrollar sistema de gestión de tareas",
        "descripcion": "Desarrollar un sistema para gestionar las tareas del equipo.",
        "criteriosAceptacion": "El sistema debe permitir crear, editar y eliminar tareas.",
        "fechaInicio": "2024-12-10",
        "fechaFinalizacion": "2024-12-20",
        "tiempoDesarrollo": 10,
        "estado": {
            "id": 1,
            "estado": "BACKLOG"
        },
        "idUsuario": "1719512392"
    },
    "message": [
        "Tarea obtenida exitosamente"
    ]
}

- **Codigos de Estado:**
    - 200: OK.
    - 404: Tarea no encontrada
    - 300: Prohibido

**Actualizar estado de Tarea**
- **Método:** PATCH
- **URL:** /api/tareas/{id}
- **Authorization:** Token Bearer
- **Request Body:**

{
  "estado": "DONE"
}

- **Response:**

{
    "state": "SUCCESS",
    "data": {
        "id": 2,
        "codigoTarea": "T001",
        "titulo": "Desarrollar sistema de gestión de tareas",
        "descripcion": "Desarrollar un sistema para gestionar las tareas del equipo.",
        "criteriosAceptacion": "El sistema debe permitir crear, editar y eliminar tareas.",
        "fechaInicio": "2024-12-10",
        "fechaFinalizacion": "2024-12-20",
        "tiempoDesarrollo": 10,
        "estado": {
            "id": 4,
            "estado": "DONE"
        },
        "idUsuario": null
    },
    "message": [
        "Tarea actualizada exitosamente"
    ]
}

- **Codigos de Estado:**
    - 200: OK.
    - 404: Tarea no encontrada
    - 300: Prohibido

**Eliminar Tarea**

- **Método:** DELETE
- **URL:** /api/tareas/{id}
- **Authorization:** Token Bearer
- **Response:**

{
    "state": "SUCCESS",
    "data": null,
    "message": [
        "Tarea con ID: 1 eliminada exitosamente"
    ]
}

- **Codigos de Estado:**
    - 200: OK.
    - 404: Tarea no encontrada
    - 300: Prohibido

**Asignar tarea a usuario**

- **Método:** PUT
- **URL:** /api/tareas/{id}
- **Authorization:** Token Bearer
- **Request Body**

{
    "idUsuario": "1719512392"
}
- **Response:**

{
    "state": "SUCCESS",
    "data": null,
    "message": [
        "Tarea asignada correctamente al usuario: 1719512392"
    ]
}

- **Codigos de Estado:**
    - 200: OK.
    - 500: Tarea no existe/usuario no encontrado/tarea en estado finalizado
    - 300: Prohibido

**Obtener tareas de un usuario**
- **Método:** GET
- **URL:** /api/tareas/usuario/{identificacion}
- **Authorization:** Token Bearer
- **Response**
{
    "state": "SUCCESS",
    "data": [
        {
            "id": 1,
            "codigoTarea": "T001",
            "titulo": "Desarrollar sistema de gestión de tareas",
            "descripcion": "Desarrollar un sistema para gestionar las tareas del equipo.",
            "criteriosAceptacion": "El sistema debe permitir crear, editar y eliminar tareas.",
            "fechaInicio": "2024-12-10",
            "fechaFinalizacion": "2024-12-20",
            "tiempoDesarrollo": 10,
            "estado": {
                "id": 1,
                "estado": "BACKLOG"
            },
            "idUsuario": "1719512392"
        }
    ],
    "message": [
        "Tarea obtenidas del usuario: 1719512392"
    ]
}

- **Codigos de Estado:**
    - 200: OK.
    - 404: Tareas no encontradas
    - 300: Prohibido

**Subir archivo JSON de tareas**
- **Método:** POST
- **URL:** /api/tareas/upload
- **Authorization:** Token Bearer
- **Response**
Archivo procesado correctamente, tareas creadas

- **Codigos de Estado:**
    - 200: OK.
    - 500: Error al procesar archivo JSON
    - 300: Prohibido
    

## Ejcución de Pruebas

**Requisitos Previos**
- Maven instalado o el IDE Spring Tool.
- Java 8
- PostgreSQL
- PGAdmin
- Git 

1. Abrir postgresql ejecutar el archivo query.sql para crear la base de datos en postgreSQL en localhost
2. Abrir una terminal y ejecutar el comando:

    git clone https://github.com/d4r1v506/SistemaDeGestionDeTareas.git
3. modificar el archivo application.properties la ip 172.17.0.2 por localhost

4. Ejecutar pruebas

    4.1. (opcion 1) Si se tiene Maven instalado ejecutar el comando:  
    mvn test

    4.2. (opción 2) en el ID Spring Tool clic derecho en la raiz del proyecto y seleccionar:

    Run As - JUnitTest

5. Verificar en la consola las pruebas

## Levantar el proyecto en Docker
El microservicio está preparado para ejecutarse en un contenedor Docker.

**Requisitos Previos**
- Docker Desktop instalado en el sistema
- Maven instalado para compilar el proyecto o el IDE Spring Tool.
- Java 8
- Git 

1. Abrir una terminal (cmd)
2. Clonar el proyecto con el comando:

    git clone https://github.com/d4r1v506/SistemaDeGestionDeTareas.git

3. Compilar el proyecto

    (opción 1)

   Si se tiene instalado Maven, ejecutar en una terminal dentro de la raiz del proyecto el comando:
   
   mvn clean package -DskipTests
   
    (opción 2)
    
    Abrir el proyecto con un IDE, de preferencia Spring tool.

   Dar clic derecho en la raiz del proyecto y seleccionar:
    - Run As - Maven Clean
    - Run As - Build.. 
    en el campo Goals poner: clean package -DskipTests y clic en Run

4. Validar que se creo dentro del directorio target el archivo *gestor_tareas.jar*

5. Crear el contenedor de la base postgreSQL "SI AUN NO ESTA CREADO", en la terminal escribimos el comando:

    docker run -d --name postgres_container -e POSTGRES_USER=postgres -e POSTGRES_PASWORD=postgres -p 5432:5432 postgres

    5.1. Ingresamos al contenedor de postgres con el comando:

    docker exec -it postgres_container psql -U postgres

    5.2. Una vez dentro del contenedor usamos el siguiente comando para visualizar las bases de datos: 
    
    \l

    5.3. Si no existe la base de datos *gestion_tareas* ejecutamos los query del archivo: query.sql

    *Nota:* Para conectarse a la base usamos el comando: 
    
    \c gestion_tareas

6. Construir la imagen del microservicio, ingresar a la raiz del proyecto desde la terminal y ejecutar el comando:

    docker build -t gestor-tareas:1.0 .

    6.1. Ejecutar el contenedor con el comando:

    docker run -d -p 8081:8081 --name gestor-tareas-container gestor-tareas:1.0

7. verificar que el microservicio está funcionando ingresando en el navegador:

    http://localhost:8081/gestor/api/tareas

8. Finalmente Abrir postman y ejecutar los endpoints del microservicio anteriormente descritos


