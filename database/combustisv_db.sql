CREATE DATABASE IF NOT EXISTS combustisv_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE combustisv_db;

CREATE TABLE IF NOT EXISTS tipos_combustible (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    unidad VARCHAR(20) DEFAULT 'galon'
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS gasolineras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    departamento VARCHAR(100),
    municipio VARCHAR(100),
    telefono VARCHAR(20),
    marca VARCHAR(50),
    activa TINYINT(1) DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS precios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gasolinera_id BIGINT NOT NULL,
    tipo_combustible_id BIGINT NOT NULL,
    precio_galon DECIMAL(8,2) NOT NULL,
    fecha_actualizacion DATE DEFAULT (CURRENT_DATE),
    vigente TINYINT(1) DEFAULT 1,
    FOREIGN KEY (gasolinera_id) REFERENCES gasolineras(id) ON DELETE CASCADE,
    FOREIGN KEY (tipo_combustible_id) REFERENCES tipos_combustible(id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO tipos_combustible (nombre, descripcion) VALUES
('Regular', 'Gasolina regular 87 octanos'),
('Premium', 'Gasolina premium 95 octanos'),
('Diesel', 'Diesel automotriz');

INSERT INTO gasolineras (nombre, direccion, departamento, municipio, marca) VALUES
('Puma San Salvador Centro', 'Blvd. Los Heroes', 'San Salvador', 'San Salvador', 'Puma'),
('Shell Escalon', 'Blvd. Escalon #3500', 'San Salvador', 'San Salvador', 'Shell'),
('Texaco Santa Tecla', 'Carretera Panamericana', 'La Libertad', 'Santa Tecla', 'Texaco'),
('Uno Soyapango', 'Blvd. del Ejercito', 'San Salvador', 'Soyapango', 'Uno'),
('Alba Petr. Mejicanos', 'Calle Principal', 'San Salvador', 'Mejicanos', 'Alba');

INSERT INTO precios (gasolinera_id, tipo_combustible_id, precio_galon) VALUES
(1,1,4.25),(1,2,4.55),(1,3,3.98),(2,1,4.30),(2,2,4.60),(2,3,4.05),
(3,1,4.28),(3,2,4.58),(3,3,4.00),(4,1,4.22),(4,2,4.50),(4,3,3.95),
(5,1,4.18),(5,2,4.48),(5,3,3.90);
