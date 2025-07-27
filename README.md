📚 LiterAlura: Catálogo de Libros - Gutendex API
🎯 Descripción
Sistema de catálogo de libros que consume la API de Gutendex para buscar, guardar y gestionar información de libros mediante una interfaz de consola interactiva.
✨ Características

🔍 Búsqueda de libros por título usando la API de Gutendex
💾 Almacenamiento automático en base de datos PostgreSQL
📋 Listado de libros guardados
👤 Búsqueda por autor
🌍 Filtrado por idioma
🏆 Top 10 libros más descargados
📊 Estadísticas por idioma
👥 Búsqueda de autores vivos en un año específico

🛠️ Tecnologías Utilizadas

Java 17
Spring Boot 3.2.0
Spring Data JPA
PostgreSQL
Maven
Jackson (para procesamiento JSON)

📋 Requisitos Previos
Software necesario:

Java 17 o superior
PostgreSQL 12 o superior
Maven 3.6 o superior
Git (opcional, para clonar el repositorio)

📖 Uso de la Aplicación
Menú Principal
Al ejecutar la aplicación, verás un menú con las siguientes opciones:
🔖 MENÚ PRINCIPAL
==================================================
1. 🔍 Buscar libro por título (API → BD)
2. 📋 Listar libros guardados
3. 👤 Buscar libros por autor
4. 🌍 Buscar libros por idioma
5. 🏆 Top 10 libros más descargados
6. 📊 Estadísticas por idioma
7. 👥 Buscar autores vivos en un año
8. 🚪 Salir

==================================================   
Funcionalidades Detalladas
1. 🔍 Buscar libro por título

Busca libros en la API de Gutendex
Guarda automáticamente los resultados en la base de datos
Evita duplicados

2. 📋 Listar libros guardados

Muestra todos los libros almacenados en la base de datos local
Información incluye: título, autor, idioma y número de descargas

3. 👤 Buscar libros por autor

Búsqueda en la base de datos local por nombre de autor
Búsqueda case-insensitive y por coincidencia parcial

4. 🌍 Buscar libros por idioma

Filtra libros por código de idioma (ej: en, es, fr, de, pt)
Muestra todos los libros disponibles en el idioma seleccionado

5. 🏆 Top 10 libros más descargados

Ranking de los libros más populares según número de descargas
Ordenados de mayor a menor

6. 📊 Estadísticas por idioma

Distribución de libros por idioma en la base de datos
Contador de libros por cada idioma disponible

7. 👥 Buscar autores vivos en un año

Busca autores que estaban vivos en un año específico
Considera fecha de nacimiento y muerte

==================================================
🗄️ Estructura de Base de Datos
Tablas Principales:

libros: Información principal de los libros
autores: Información de autores vinculados a libros
libro_idiomas: Idiomas disponibles para cada libro
libro_temas: Temas/categorías de los libros


==================================================
Configurar Base de Datos
Editar el archivo application.properties:

# Configuración de la Base de Datos PostgreSQL
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.dbname=${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

