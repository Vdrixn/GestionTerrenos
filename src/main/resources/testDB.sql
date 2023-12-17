DROP TABLE IF EXISTS Recibos;
DROP TABLE IF EXISTS Parcelas;
DROP TABLE IF EXISTS Terrenos;
DROP TABLE IF EXISTS Arrendatarios;
CREATE TABLE Arrendatarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL CHECK (edad >= 18),
    sexo ENUM('M', 'F', 'O') NOT NULL,
    fechaRegistro DATE NOT NULL
);
CREATE TABLE Terrenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    tamHectareas INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    limiteBase INT NOT NULL,
    limiteAltura INT NOT NULL, 
    fechaRegistro DATE NOT NULL
);
CREATE TABLE Parcelas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idTerreno INT NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    limiteBase INT NOT NULL,
    limiteAltura INT NOT NULL, 
    fechaRegistro DATE NOT NULL,
    FOREIGN KEY (idTerreno) REFERENCES Terrenos(id)
);
CREATE TABLE Recibos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idArren INT NOT NULL,
    idParcela INT NOT NULL,
    fechaEmision DATE NOT NULL,
    importe DECIMAL(10, 2) NOT NULL,
    iva DECIMAL(5, 2) NOT NULL,
    irpf DECIMAL(5, 2) NOT NULL,
    alquilado TINYINT(1) not null,
    pagado TINYINT(1) not null,
    FOREIGN KEY (idArren) REFERENCES Arrendatarios(id),
    FOREIGN KEY (idParcela) REFERENCES Parcelas(id)
);