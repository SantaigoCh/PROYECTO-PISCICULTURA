--Para que puedan probar en la base de datos el login por el momento, que también tiene la opción de guardar contraseñas hasheadas y validar usuarios con contraseña hasheadas de igual manera con sha256
-- Tabla de usuarios (para autenticación y control de roles)
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL,
    correo TEXT UNIQUE NOT NULL,
    contrasena TEXT NOT NULL,  -- Encriptada desde Java
    rol TEXT NOT NULL CHECK (rol IN ('administrador', 'piscicultor'))
);

-- Tabla de departamentos (ej: Antioquia, Cundinamarca, etc.)
CREATE TABLE Departamento (
    id SERIAL PRIMARY KEY,
    nombre TEXT UNIQUE NOT NULL
);

-- Tabla de municipios (vinculados a un departamento)
CREATE TABLE Municipio (
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL,
    departamento_id INTEGER NOT NULL REFERENCES Departamento(id)
);

-- Tabla de variables medidas (ej: pH, temperatura, producción, etc.)
CREATE TABLE Variable (
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL,
    tipo TEXT NOT NULL CHECK (
        tipo IN ('calidad_agua', 'calidad_carne', 'oxigeno', 'produccion', 'ventas')
    )
);

-- Tabla de registros de mediciones o reportes
CREATE TABLE Registro (
    id SERIAL PRIMARY KEY,
    valor NUMERIC,              -- Valor numérico (opcional si hay observación)
    observacion TEXT,           -- Comentario textual opcional (ej: "agua turbia")
    fecha DATE NOT NULL,
    variable_id INTEGER NOT NULL REFERENCES Variable(id),
    municipio_id INTEGER NOT NULL REFERENCES Municipio(id),
    usuario_id INTEGER NOT NULL REFERENCES Usuario(id)
);
