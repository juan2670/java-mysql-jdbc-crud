<div align="center">

# 🏋️‍♂️ Zona Fit — Gestión de Clientes

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connect-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Terminado-success?style=for-the-badge)

<p align="center">
  <strong>Sistema simple de administración para gimnasios (CRUD) desarrollado en Java con arquitectura por capas y persistencia en MySQL.</strong>
</p>

</div>

---

## ⚡ Descripción del Proyecto

**Zona Fit** es una aplicación de consola, diseñada para gestionar el ciclo de vida de los clientes de un gimnasio. El proyecto destaca por implementar **buenas prácticas de programación**, separando la lógica de negocio del acceso a datos mediante el patrón **DAO (Data Access Object)**.

este sistema incluye **validaciones** para garantizar la integridad de los datos antes de que lleguen a la base de datos.

---

## ✨ Características Principales

* **📂 Gestión Completa (CRUD):** Crear, Leer, Actualizar y Eliminar registros de clientes en tiempo real.
* **🛡️ Validaciones Robustas:**
    * Uso de **Regex** para nombres y apellidos (evita números o símbolos).
    * Manejo de excepciones (`try-catch`) para evitar cierres inesperados por entradas numéricas erróneas.
* **🏗️ Arquitectura Limpia:**
    * **Patrón DAO:** Desacopla la lógica SQL de la interfaz.
    * **Singleton:** Gestión eficiente de la conexión a la base de datos.
* **💾 Persistencia Real:** Conexión directa a MySQL mediante JDBC (sin frameworks, puro código nativo).

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Rol en el proyecto |
| :--- | :--- |
| **Java 17+** | Lenguaje principal y lógica de negocio. |
| **MySQL 8** | Motor de Base de Datos Relacional. |
| **JDBC** | API nativa para la comunicación Java-SQL. |
| **Maven/Gradle** | (Opcional) Gestión de dependencias. |

---

## 🚀 Instalación y Puesta en Marcha

Sigue estos pasos para ejecutar el proyecto en tu máquina local.

### 1. Prerrequisitos
* Tener instalado **Java JDK** (versión 17 o superior).
* Tener instalado **MySQL Server**.

### 2. Configuración de Base de Datos
Ejecuta el siguiente script en tu gestor SQL (Workbench, DBeaver, etc.) para crear la estructura necesaria:

```sql
CREATE DATABASE IF NOT EXISTS zona_fit;
USE zona_fit;

CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    membresia INT NOT NULL
);

-- Datos de prueba
INSERT INTO cliente (nombre, apellido, membresia) VALUES ('Juan', 'Perez', 100);
