# Literalura - Sistema de Gestión de Libros

## 📚 Descripción
Literalura es una aplicación de consola desarrollada en Java que permite gestionar una biblioteca de libros utilizando la API de Gutendex. El sistema permite buscar libros, almacenarlos en una base de datos PostgreSQL y realizar diversas consultas sobre los libros y autores almacenados.

## 🚀 Funcionalidades
- Búsqueda de libros por título utilizando la API de Gutendex
- Almacenamiento automático de libros en base de datos
- Listado de todos los libros registrados
- Listado de autores registrados
- Búsqueda de autores vivos en un año específico
- Filtrado de libros por idioma
- Validación para evitar duplicados
- Manejo de errores y casos límite

## 🛠 Tecnologías Utilizadas
- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- PostgreSQL
- Maven
- Project Lombok
- Spring WebFlux

## 📋 Prerrequisitos
- JDK 17 o superior
- Maven 3.6 o superior
- PostgreSQL 12 o superior
- IDE Java (recomendado: IntelliJ IDEA o Eclipse)

## ⚙️ Configuración

### Base de Datos
1. Crear una base de datos en PostgreSQL:
```sql
CREATE DATABASE literalura;
```

2. Configurar las credenciales en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### Instalación
1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/literalura.git
```

2. Navegar al directorio del proyecto:
```bash
cd literalura
```

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

## 🎯 Uso

Al iniciar la aplicación, se mostrará un menú con las siguientes opciones:

1. **Buscar libro por título**
   - Ingresa el título del libro que deseas buscar
   - El sistema buscará en la API de Gutendex y guardará el libro si se encuentra

2. **Listar libros**
   - Muestra todos los libros almacenados en la base de datos

3. **Listar autores**
   - Muestra todos los autores registrados en el sistema

4. **Listar autores vivos en un año**
   - Ingresa un año específico
   - Muestra los autores que estaban vivos en ese año

5. **Listar libros por idioma**
   - Ingresa el código del idioma (ES, EN, FR, PT)
   - Muestra los libros disponibles en ese idioma

## 🚨 Manejo de Errores
La aplicación maneja los siguientes casos:
- Libros no encontrados en la API
- Intentos de duplicación de libros
- Errores de conexión con la API
- Errores de base de datos

## 📝 Ejemplos de Uso

### Búsqueda de libro
```
Ingrese el título del libro: Pride and Prejudice
```
Resultado:
```
Libro guardado: Pride and Prejudice
Autor: Austen, Jane
Idioma: EN
Descargas: 6493
```

### Búsqueda de autores vivos en un año
```
Ingrese el año: 1600
```
Resultado:
```
Cervantes, Miguel de
Shakespeare, William
```

## 🤝 Contribución
Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## 👥 Autores
- Keyla Valdes - Trabajo Inicial - (https://github.com/KE1LA)

## 🎉 Agradecimientos
- [Gutendex API](https://gutendex.com/) por proporcionar acceso a la biblioteca de libros
- [Project Gutenberg](https://www.gutenberg.org/) por su increíble colección de libros digitales
- Alura por el desafío y la inspiración para este proyecto