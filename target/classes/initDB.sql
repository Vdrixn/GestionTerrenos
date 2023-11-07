CREATE DATABASE IF NOT EXISTS GestionTerrenos;
USE GestionTerrenos;

-- Creación de la tabla 'Terrenos'
CREATE TABLE IF NOT EXISTS Terrenos (
    -- Añade aquí las columnas que necesitas, por ejemplo:
    idTerreno INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    ubicacion VARCHAR(255),
    tamaño_hectareas DECIMAL(10,2),
    -- ... otras columnas ...
    PRIMARY KEY (idTerreno)
    -- posibles CONSTRAINTS o índices adicionales
);

-- Creación de la tabla 'Parcelas'
CREATE TABLE IF NOT EXISTS Parcelas (
    -- Añade aquí las columnas que necesitas, por ejemplo:
    idParcela INT AUTO_INCREMENT,
    idTerreno INT,
    numeroParcela VARCHAR(50),
    limites TEXT,
    -- ... otras columnas ...
    PRIMARY KEY (idParcela),
    FOREIGN KEY (idTerreno) REFERENCES Terrenos(idTerreno) 
    -- posibles CONSTRAINTS o índices adicionales
);

-- Creación de la tabla 'Arrendatarios'
CREATE TABLE IF NOT EXISTS Arrendatarios (
    -- Añade aquí las columnas que necesitas, por ejemplo:
    idArrendatario INT AUTO_INCREMENT,
    DNI VARCHAR(50),
    nombre VARCHAR(100),
    edad INT,
    sexo VARCHAR(50),
    -- ... otras columnas ...
    PRIMARY KEY (idArrendatario)
    -- posibles CONSTRAINTS o índices adicionales
);

-- Creación de la tabla 'Recibos'
CREATE TABLE IF NOT EXISTS Recibos (
    -- Añade aquí las columnas que necesitas, por ejemplo:
    idRecibo INT AUTO_INCREMENT,
    idArrendatario INT,
    idParcela INT,
    fecha_emision DATE,
    importe DECIMAL(10,2),
    IVA DECIMAL(10,2),
    IRPF DECIMAL(10,2),
    -- ... otras columnas ...
    PRIMARY KEY (idRecibo),
    FOREIGN KEY (idArrendatario) REFERENCES Arrendatarios(idArrendatario),
    FOREIGN KEY (idParcela) REFERENCES Parcelas(idParcela)
    -- posibles CONSTRAINTS o índices adicionales
);

-- Continuar con otras tablas necesarias ...

-- Nota: Asegúrate de modificar los tipos de datos y las restricciones para que coincidan con tus necesidades específicas.
