# ToDo Products
<img src="https://github.com/jander96/product_checker_app/blob/main/Screenshot_20250320_113513.png" alt="Descripción de la imagen" width="150"/>

Es una app para la gestion de productos, el usuario puede aprobar o rechazar un producto . 
Consta de 2 features principales
- Productos por revisar:
    - Una lista proveniente de backend (para efectos de pruebas se uso mockapi.io) que muestra aquellos productos pendientes de revision
- Productos chequeados
    - La lista de todos los productos chequedos. Esta utiliza una base de datos local para recuperar todos aquellos productos que ya fueron marcados como aprobados o rechazados. Esta lista no permite edicion ya que muestra un producto que ya fue revisado, pero si es permitido eliminar el producto de la base de datos


## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes requisitos:

- **JDK** (Java Development Kit) versión `11` u otra compatible.  
- **Android Studio** (última versión recomendada).  
- **Gradle** (se recomienda utilizar la versión definida en el proyecto).  
- **Kotlin** (se recomienda utilizar la versión definida en el proyecto).  

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/jander96/product_checker_app.git
cd product_checker_app
```
### 2. Abrir en Android Studio
- El proyecto no requiere de variables de entorno ni claves privadas
- Asegurate de tener un emulador o dispositivo fisico conectado
- ▶️ Run proyect
- ✅ Listo

### Instalar archivo .apk

Puedes utilizar el archivo apk del repositorio [📦 ToDo Products](https://github.com/jander96/product_checker_app/blob/main/app/release/app-release.apk)

## Detalles tecnicos
- En el proyecto se utliza Arquitectura limpia con un enfoque modular `feature fisrt`
- Para la capa de presenter se utliza Arquitectura **MVVM**
- Se usa `ktor con client okhttp` para las llamdas a la api 
- Room para el caching local
- Ademas se implementa injeccion de dependencias con **Koin**
- Compose para el sistema de vistas declarativas
- Uso de Flows y corrutinas 


👋 
