# AseoApp — API Backend

API REST para la gestión de aseo escolar en la UTTN. Permite a los jefes de grupo administrar equipos de limpieza, programar jornadas, enviar notificaciones y registrar evidencias fotográficas. Los alumnos reciben alertas y confirman su participación desde la app móvil.

---

## Funcionalidades

- Gestión de equipos — crear, editar y reasignar equipos de limpieza
- Jornadas de aseo — programar hora, gestionar estado y enviar alertas
- Notificaciones push — alertas automáticas y manuales vía Firebase (FCM)
- Evidencia fotográfica — los alumnos suben foto para finalizar el aseo
- Ausencias y reasignaciones — manejo de faltas y cambios de día
- Roles de usuario — vistas diferenciadas para jefe de grupo y alumno
- Dashboard — resumen de jornadas, confirmaciones y evidencias

---

## Stack

| Tecnología | Uso |
|---|---|
| Java + Spring Boot | Framework principal |
| SQL Server | Base de datos |
| Spring Data JPA / Hibernate | ORM y acceso a datos |
| Firebase Cloud Messaging | Notificaciones push |
| Spring Security | Autenticación y roles |
| Maven | Gestión de dependencias |

---

## Estructura del proyecto

```
src/main/java/dev/pablo/Appaseo/
├── Controllers/       # Endpoints REST
│   ├── AuthController
│   ├── AseoController
│   ├── EquipoController
│   ├── EvidenciaController
│   ├── JornadaController
│   └── UsuarioController
├── Models/            # Entidades JPA
│   ├── Usuario
│   ├── Equipo / EquipoMiembro
│   ├── Jornada / EstadoJornada
│   ├── Evidencia
│   ├── Confirmacion
│   ├── Ausencia
│   ├── RolLimpieza
│   └── TipoUsuario
├── Repository/        # Interfaces Spring Data
├── Service/           # Lógica de negocio
│   └── impl/
├── DTO/               # Objetos de transferencia
│   ├── JornadaDTO / JornadaDashboardDTO
│   ├── DashboardAlumnoDTO
│   ├── EquipoDTO
│   └── UsuarioRegistro
├── Config/            # Configuraciones
└── schedule/          # Tareas programadas (JornadaScheduler)
```

---

## Configuración local

### Requisitos

- Java 17+
- SQL Server
- Maven 3.8+
- Proyecto Firebase con FCM habilitado

### 1. Clona el repositorio

```bash
git clone https://github.com/PablilloRoy/AplicacionAseo-Api.git
cd AplicacionAseo-Api
```

### 2. Crea tu archivo de configuración local

Crea `src/main/resources/application-local.properties` (no se sube a GitHub):

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=Appaseo;encrypt=true;trustServerCertificate=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 3. Activa el perfil local

En `application.properties`:

```properties
spring.profiles.active=local
```

### 4. Corre el proyecto

```bash
./mvnw spring-boot:run
```

La API corre en `http://localhost:8080` por el momento

---

## App móvil

La aplicación cliente está desarrollada en React Native con Expo.

Repositorio: *(disponible próximamente)*

---

## Seguridad

Las credenciales de base de datos se manejan con perfiles de Spring y nunca se suben al repositorio. Las keys de Firebase van como variables de entorno.

---

## Autor

Luis Pablo — [@PablilloRoy](https://github.com/PablilloRoy)

Proyecto personal desarrollado mientras estudio en la UTTN, 2026.
