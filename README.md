# Api Movies

API REST para la gestion de peliculas, generos, actores y años de estreno, desarrollada con Spring Boot y JPA/Hibernate.

Proyecto realizado para el ejercicio de P5 Digital Academy - Java Fundamentals / Spring Fundamentals.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- Spring Validation
- H2 Database (desarrollo)
- MySQL (produccion, via Docker)
- Maven

## Modelo de datos

La API gestiona 4 entidades:

- **Movie** (pelicula)
- **Genre** (genero)
- **Year** (año)
- **Actor** (actor)

### Relaciones

| Relacion | Tipo | Descripcion |
|---|---|---|
| Movie - Genre | N:1 | Una pelicula pertenece a un genero; un genero agrupa varias peliculas |
| Movie - Year | N:1 | Una pelicula se estrena en un año; un año agrupa varias peliculas |
| Movie - Actor | N:M | Una pelicula tiene varios actores; un actor participa en varias peliculas |

## Instalacion y puesta en marcha

### Requisitos previos

- Java 21
- Maven (o usar el wrapper incluido `mvnw` / `mvnw.cmd`)
- Docker (opcional, solo si se quiere usar el perfil de MySQL)

### Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd movies-api
```

### Ejecutar con base de datos H2 (por defecto, sin dependencias externas)

```bash
./mvnw spring-boot:run
```

La aplicacion arrancara en `http://localhost:8080` con datos de prueba ya cargados (ver `src/main/resources/data.sql`).

La consola de H2 esta disponible en `http://localhost:8080/h2-console` con estos datos de conexion:

- JDBC URL: `jdbc:h2:mem:moviesdb`
- Usuario: `sa`
- Contraseña: *(vacia)*

### Ejecutar con MySQL (via Docker)

1. Levantar el contenedor de MySQL:
```bash
docker compose up -d
```

2. Ejecutar la aplicacion con el perfil `mysql`:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Documentacion de endpoints

Prefijo base de todos los endpoints: `/api/v1/movies`

| # | Metodo | Ruta | Descripción |
| --- | --- | --- | --- | --- |
| 1 | GET | `/api/v1/movies` | Obtener todas las peliculas |
| 2 | GET | `/api/v1/movies/{id}` | Obtener una pelicula por su Id |
| 3 | POST | `/api/v1/movies` | Añadir una nueva pelicula |
| 4 | PUT | `/api/v1/movies/{id}` | Actualizar los datos de una pelicula |
| 5 | DELETE | `/api/v1/movies/{id}` | Eliminar una pelicula |
| 6 | GET | `/api/v1/movies/search?title=...` o `?genre=...` | Buscar peliculas por titulo o genero |

### Capturas de pantalla

### 1. GET (`/api/v1/movies`)

![get-all-movies](./assets/img/GET-all_SS.jpg)

### 2. GET (`/api/v1/movies/{id}`)

![get-movies-id](./assets/img/GET-id_SS.jpg)

### 3. POST (`/api/v1/movies`)

![add-movies](./assets/img/POST_SS.jpg)

### 4. PUT (`/api/v1/movies/{id}`)

![update-movies](./assets/img/PUT_SS.jpg)

### 5. DELETE (`/api/v1/movies/{id}`)

![delete-movies](./assets/img/DELETE_SS.jpg)

### 6. GET (`/api/v1/movies/search?genre=...` o `?title=...`)

![get-movies-genre](./assets/img/GET-genre_SS.jpg)

![get-movies-title](./assets/img/GET-title_SS.jpg)

### Ejemplo de body para POST / PUT

```json
{
    "title": "The Dark Knight",
    "synopsis": "Batman se enfrenta al Joker en Ciudad Gotica",
    "genreId": 1,
    "yearId": 2,
    "actorIds": [1, 2]
}
```

### Ejemplo de respuesta

```json
{
    "id": 1,
    "title": "The Dark Knight",
    "synopsis": "Batman se enfrenta al Joker en Ciudad Gotica",
    "genreName": "Accion",
    "year": 2010,
    "actorNames": ["Keanu Reeves", "Carrie-Anne Moss"]
}
```

### Codigos de respuesta

| Codigo | Cuando ocurre |
|---|---|
| `200 OK` | Peticion exitosa (GET, PUT) |
| `201 Created` | Pelicula creada correctamente (POST) |
| `204 No Content` | Pelicula eliminada correctamente (DELETE) |
| `400 Bad Request` | Datos invalidos (validacion fallida, genero/año inexistente) |
| `404 Not Found` | La pelicula solicitada no existe |

## Estructura del proyecto

```
src/main/java/com/josedev/movies_api/
├── movie/
│   ├── MovieEntity.java
│   ├── MovieRepository.java
│   ├── InterfaceMovieService.java
│   ├── MovieServiceImpl.java
│   ├── MovieController.java
│   ├── dtos/
│   └── mappers/
├── genre/
├── year/
├── actor/
└── globals/
    └── GlobalExceptionHandler.java
```

## Autor

Jose - P5 Digital Academy