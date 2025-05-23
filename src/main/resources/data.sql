-- Eliminar tablas si existen (el orden es importante por las FK)
DROP TABLE IF EXISTS equipo;
DROP TABLE IF EXISTS liga;
DROP TABLE IF EXISTS pais;

-- Crear tabla pais con columna autoincremental
CREATE TABLE pais (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      nombre VARCHAR(255) NOT NULL
);

-- Crear tabla liga con FK a pais
CREATE TABLE liga (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      nombre VARCHAR(255) NOT NULL,
                      fk_pais BIGINT,
                      CONSTRAINT fk_pais FOREIGN KEY (fk_pais) REFERENCES pais(id)
);

-- Crear tabla equipo con FK a liga
CREATE TABLE equipo (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        nombre VARCHAR(255) NOT NULL,
                        liga_id BIGINT,
                        CONSTRAINT fk_liga FOREIGN KEY (liga_id) REFERENCES liga(id)
);

-- Insertar datos iniciales manualmente con IDs fijos
INSERT INTO pais (id, nombre) VALUES
                                  (1, 'España'),
                                  (2, 'Inglaterra'),
                                  (3, 'Italia'),
                                  (4, 'Alemania'),
                                  (5, 'Francia'),
                                  (6, 'Portugal'),
                                  (7, 'Países Bajos'),
                                  (8, 'Escocia'),
                                  (9, 'Turquía'),
                                  (10, 'Rusia');

INSERT INTO liga (id, nombre, fk_pais) VALUES
                                           (1, 'La Liga', 1),
                                           (2, 'Premier League', 2),
                                           (3, 'Serie A', 3),
                                           (4, 'Bundesliga', 4),
                                           (5, 'Ligue 1', 5),
                                           (6, 'Primeira Liga', 6),
                                           (7, 'Eredivisie', 7),
                                           (8, 'Scottish Premiership', 8),
                                           (9, 'Süper Lig', 9),
                                           (10, 'Premier League Rusa', 10);

INSERT INTO equipo (id, nombre, liga_id) VALUES
                                             (1, 'Real Madrid', 1),
                                             (2, 'FC Barcelona', 1),
                                             (3, 'Manchester United', 2),
                                             (4, 'Liverpool FC', 2),
                                             (5, 'Juventus FC', 3),
                                             (6, 'AC Milan', 3),
                                             (7, 'Bayern Munich', 4),
                                             (8, 'Borussia Dortmund', 4),
                                             (9, 'Paris Saint-Germain', 5),
                                             (10, 'Olympique de Marseille', 5),
                                             (11, 'FC Porto', 6),
                                             (12, 'Sporting CP', 6),
                                             (13, 'Ajax Amsterdam', 7),
                                             (14, 'Feyenoord', 7),
                                             (15, 'Celtic FC', 8),
                                             (16, 'Rangers FC', 8),
                                             (17, 'Galatasaray SK', 9),
                                             (18, 'Fenerbahçe SK', 9),
                                             (19, 'FC Zenit Saint Petersburg', 10),
                                             (20, 'Spartak Moscow', 10),
                                             (21, 'SL Benfica', 6),
                                             (22, 'Besiktas JK', 9),
                                             (23, 'SSC Napoli', 3),
                                             (24, 'Atlético Madrid', 1);

-- Reiniciar el contador autogenerado (el valor debe ser mayor que el máximo ID insertado)
ALTER TABLE pais ALTER COLUMN id RESTART WITH 11;
ALTER TABLE liga ALTER COLUMN id RESTART WITH 11;
ALTER TABLE equipo ALTER COLUMN id RESTART WITH 25;
