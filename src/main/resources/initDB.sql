-- Comprobación de la existencia de la base de datos y creación
CREATE DATABASE IF NOT EXISTS GestionTerrenos;
USE GestionTerrenos;

-- Creación de la tabla 'Arrendatarios'
CREATE TABLE IF NOT EXISTS Arrendatarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL CHECK (edad >= 18),
    sexo ENUM('M', 'F', 'O') NOT NULL, -- 'O' para otro, asumiendo que 'sexo' es un enumerado
    fechaRegistro DATE NOT NULL
);

-- Creación de la tabla 'Terrenos'
CREATE TABLE IF NOT EXISTS Terrenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    tamHectareas INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    limiteBase INT NOT NULL,
    limiteAltura INT NOT NULL, 
    fechaRegistro DATE NOT NULL
);

-- Creación de la tabla 'Parcelas'
CREATE TABLE IF NOT EXISTS Parcelas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idTerreno INT NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    limiteBase INT NOT NULL,
    limiteAltura INT NOT NULL, 
    fechaRegistro DATE NOT NULL,
    FOREIGN KEY (idTerreno) REFERENCES Terrenos(id)
);

-- Creación de la tabla 'Recibos'
CREATE TABLE IF NOT EXISTS Recibos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idArren INT NOT NULL,
    idParcela INT NOT NULL,
    fechaEmision DATE NOT NULL,
    importe DECIMAL(10, 2) NOT NULL,
    iva DECIMAL(5, 2) NOT NULL,
    irpf DECIMAL(5, 2) NOT NULL,
    FOREIGN KEY (idArren) REFERENCES Arrendatarios(id),
    FOREIGN KEY (idParcela) REFERENCES Parcelas(id)
);