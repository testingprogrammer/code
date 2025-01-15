# CondingChallengePrices 🚀

## Descripción 📝

**CondingChallengePrices** es una aplicación diseñada para obtener precios de un producto filtrando por brandId, productId y date
implementando arquitectura hexagonal utilizando Java 17 y Spring Boot 3.4.1
 
## Características ✨

- **Spring Boot**: Framework robusto para la creación de aplicaciones.
- **Liquibase**: Herramienta para el control de versiones de la base de datos.
- **MapStruct**: Generador de código para el mapeo entre entidades y DTOs.
- **JUnit & Mockito**: Frameworks de pruebas para asegurar la calidad del código.
- **JaCoCo**: Herramienta para medir la cobertura de pruebas de código.
- **Arquitectura Hexagonal**: Patrón de diseño que desacopla el núcleo de la aplicación de las interfaces externas, utilizando puertos y adaptadores

## Tecnologías 🛠️

- **Java 17** ☕
- **Spring Boot 3.4.1** 🖥️
- **JPA (Java Persistence API)** 🗃️
- **H2 Database (base de datos en memoria)** 💾
- **Liquibase** 🔄
- **MapStruct** 🔄
- **Lombok** 📝
- **Mockito** 🧪
- **Jacoco** 🧪

## Requisitos ✅

- **Java 17** o superior.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.

## Instalación 📦


### Compilar el Proyecto

mvn clean install

### Ejecutar la Aplicación 🚀

mvn spring-boot:run

## Pruebas 🧪

mvn test

## API Endpoint: Obtener precio

### Descripción
Este endpoint te permite obtener el precio de un producto en una fecha y hora específicas, para una marca determinada.

### URL
http://localhost:8080/api/v1/prices?date=2020-06-14T18:30:00&brandId=1&productId=35455

### Parámetros de consulta
- `date`: Fecha y hora en formato ISO (`2020-06-14T18:30:00`).
- `brandId`: ID de la marca (`1`).
- `productId`: ID del producto (`35455`).

### Ejemplo de Respuesta
```json
{
    "brandId": 1,
    "startDate": "2020-06-14T15:00:00",
    "endDate": "2020-06-14T18:30:00",
    "priceListId": 2,
    "productId": 35455,
    "amount": 25.45,
    "currency": "EUR"
}
```
