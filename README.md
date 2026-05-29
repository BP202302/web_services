# CombustiSV - Comparador de Precios de Combustibles

## Requisitos
- Java JDK 8+
- NetBeans 8.0
- XAMPP (Apache + MySQL activos)
- Maven 3.x

## Instalacion

### 1. Base de Datos
1. Iniciar XAMPP y activar Apache + MySQL
2. Abrir phpMyAdmin (http://localhost/phpmyadmin)
3. Importar el archivo `database/combustisv_db.sql`

### 2. Proyecto
1. Abrir NetBeans 8.0
2. File > Open Project > Seleccionar carpeta `combustisv-project`
3. Click derecho en el proyecto > Build
4. Click derecho > Run

### 3. Acceso
- Sitio web: http://localhost:8080/combustisv/
- Panel admin: http://localhost:8080/combustisv/admin

## Estructura
```
combustisv-project/
├── pom.xml
├── database/combustisv_db.sql
├── src/main/java/com/combustisv/
│   ├── CombustisvApplication.java
│   ├── config/DataLoader.java
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
└── src/main/resources/
    ├── application.properties
    ├── templates/
    └── static/css/
```

## Equipo
- Kevin Audias Martinez Perez (MP202301)
- Brandon Steven Barrera Portillo (BP202302)
- Hugo Eriberto Zepeda Palacios (ZP202301)

Universidad Politecnica de El Salvador - 2026
