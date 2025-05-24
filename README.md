# Dux - API Equipos de Fútbol

API REST desarrollada con **Spring Boot 3** y **Java 17** para gestionar información de equipos de fútbol. Incluye autenticación JWT, documentación Swagger, base de datos H2 en memoria y está lista para correr en Docker.

---

## 📂 Estructura del proyecto

```
dux/
├── Dockerfile
├── README.md
├── docs/
│   └── swagger-authorize.png
├── src/
│   ├── main/
│   │   ├── java/com/challenge/dux/
│   │   │   ├── application/
│   │   │   │   ├── dto/
│   │   │   │   ├── exceptions/
│   │   │   │   ├── mapper/
│   │   │   │   └── service/
│   │   │   ├── domain/
│   │   │   │   ├── interfaces/
│   │   │   │   └── model/
│   │   │   ├── infrastructure/
│   │   │   │   ├── configuration/
│   │   │   │   ├── repository/
│   │   │   │   ├── security/
│   │   │   │   └── interfaces/controller/
│   │   │   └── DuxApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── data.sql
│   └── test/
```

---

## 🚀 Funcionalidades principales

- Obtener todos los equipos registrados.
- Buscar equipos por nombre o por ID.
- Crear, actualizar y eliminar equipos.
- Seguridad JWT con un usuario por defecto.
- Documentación automática con Swagger.
- Manejo centralizado de errores.
- Base de datos H2 precargada con 24 equipos.
- Pruebas unitarias de servicios con mocks.
- Dockerfile para contenerización.

---

## ⚙️ Instalación y ejecución

### 1. Clonar repositorio

```bash
git clone https://github.com/Meistt/Dux.git
```

### 2. Construir el proyecto con Maven
```bash
mvn clean install
```

### 3. Ejecutar la aplicación localmente

```bash
mvn spring-boot:run
```
La API estará disponible en `http://localhost:8080`

---

## 🐳 Uso con Docker
### 1. Construir imagen Docker
```bash
docker build -t dux-api .
```
### 2. Ejecutar contenedor
```bash
docker run -d -p 8080:8080 --name dux-container dux-api
```
### 3. Detener y eliminar contenedor (opcional)
```bash
docker stop dux-container
docker rm dux-container
```

---
## 🔐 Autenticación JWT

Para acceder a la mayoría de endpoints es necesario autenticarse:
* Endpoint: POST `/api/auth/login`
* Body JSON:
```json
{
  "username": "test",
  "password": "12345"
}
```
* Respuesta
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzQ3NzA3MDE1LCJleHAiOjE3NDc3OTM0MTV9.HT9iF3TZOSumGnZB1xWSVuS2mtKlq9VVQ2iB0hJ4_sA"
}
```
Guarda el token y úsalo en las siguientes peticiones como header: `Authorization: Bearer <token>`

## 📄 Documentación Swagger UI

La API incluye documentación interactiva disponible en:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📋 Endpoints principales

| Método | Ruta                  | Descripción                      |
|--------|-----------------------|--------------------------------|
| GET    | `/api/equipos`        | Obtener todos los equipos       |
| GET    | `/api/equipos/{id}`   | Obtener equipo por ID           |
| GET    | `/api/equipos/buscar` | Buscar equipo por nombre (query)|
| POST   | `/api/equipos`        | Crear nuevo equipo              |
| PUT    | `/api/equipos/{id}`   | Actualizar equipo existente     |
| DELETE | `/api/equipos/{id}`   | Eliminar equipo                |

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Maven
- JWT para seguridad
- Swagger para documentación
- H2 base de datos en memoria
- Docker para contenerización

