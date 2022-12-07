Gestion date et heure

pointage
    -num employer 
    -date heure Entree
    -date heure sortie

Total heure travail olona iray par jour , par semaine, par mois


 id INT PRIMARY KEY AUTO_INCREMENT


SELECT nom, (pointage.dateheuresortie-pointage.dateheureentree) d FROM EMPLOYER
    JOIN POINTAGE 
        ON POINTAGE.idemployer = EMPLOYER.idemployer;



Total olona niasa par jour, par semaine, par mois

liste ny olona tsy nahafeno 40h par semaine (sy ny heure tsy ampy)

Total heure travail equipe par jour, par semaine, par mois

CREATE DATABASE GESTION;
\c Gestion;

CREATE TABLE EMPLOYER(
    idemployer SERIAL PRIMARY KEY,
    nom VARCHAR(200),
    prenom VARCHAR(200),
    telphone INT
);

-- Insert rows in a Table

INSERT INTO EMPLOYER 
(
  nom,
  prenom,
  telphone
)
VALUES
(
  'User1Name',
  'User1FirstName',
  0329459075
);

INSERT INTO EMPLOYER 
(
  nom,
  prenom,
  telphone
)
VALUES
(
  'User2Name',
  'User2FirstName',
  0331451015
);

INSERT INTO EMPLOYER 
(
  nom,
  prenom,
  telphone
)
VALUES
(
  'User3Name',
  'User3FirstName',
  0347519755
);


CREATE TABLE POINTAGE(
    idemployer INT,
    dateheureentree TIMESTAMP,
    dateheuresortie TIMESTAMP,
    FOREIGN KEY (idemployer) REFERENCES employer(idemployer)
);

-- Insert rows in a Table

INSERT INTO POINTAGE 
(
    idemployer,
    dateheureentree,
    dateheuresortie
)
VALUES
(
    2,
    '2022-12-11 06:30:22',
    '2022-12-11 18:30:22'
);

INSERT INTO POINTAGE 
(
    idemployer,
    dateheureentree,
    dateheuresortie
)
VALUES
(
    3,
    '2022-12-11 08:30:22',
    '2022-12-11 15:30:22'
);

INSERT INTO POINTAGE 
(
    idemployer,
    dateheureentree,
    dateheuresortie
)
VALUES
(
    5,
    '2022-12-21 12:02:17',
    '2022-12-21 16:11:12'
);
