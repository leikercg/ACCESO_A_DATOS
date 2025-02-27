
CREATE OR REPLACE TYPE DIRECCION AS OBJECT
(
  CALLE  VARCHAR2(25),
  CIUDAD VARCHAR2(20),
  CODIGO_POST NUMBER(5)
);
/
-- 
-- 
CREATE OR REPLACE TYPE PERSONA AS OBJECT
(
  CODIGO NUMBER,
  NOMBRE VARCHAR2(35),
  DIREC  DIRECCION,
  FECHA_NAC DATE
);
/
--PROBANDO TIPOS
DECLARE
  DIR DIRECCION := DIRECCION(NULL,NULL,NULL);
  P PERSONA     := PERSONA(NULL,NULL,NULL,NULL);
  DIR2 DIRECCION;  -- SE INICIA CON NEW
  P2 PERSONA;      -- SE INICIA CON NEW
BEGIN  
  DIR.CALLE:='La Mina, 3';  
  DIR.CIUDAD:='Guadalajara'; 
  DIR.CODIGO_POST:=19001; 
  --
  P.CODIGO:=1; 
  P.NOMBRE := 'JUAN'; 
  P.DIREC := DIR; 
  P.FECHA_NAC := '10/11/1988';
  DBMS_OUTPUT.PUT_LINE('NOMBRE: ' ||P.NOMBRE ||
         ' * CALLE:'||P.DIREC.CALLE);
  --
  DIR2 := NEW DIRECCION ('C/Madrid 10','Toledo',45002);
  P2:= NEW PERSONA(2,'JUAN', DIR2, SYSDATE);
  DBMS_OUTPUT.PUT_LINE('NOMBRE: ' ||P2.NOMBRE || 
     ' * CALLE: '  ||P2.DIREC.CALLE );  
END;
/
-- ACTIVIDAD 1


CREATE OR REPLACE TYPE DIRECCION AS OBJECT
(
  CALLE  VARCHAR2(25),
  CIUDAD VARCHAR2(20),
  CODIGO_POST NUMBER(5),
  MEMBER PROCEDURE SET_CALLE(C VARCHAR2),
  MEMBER FUNCTION GET_CALLE RETURN VARCHAR2, 
  CONSTRUCTOR FUNCTION DIRECCION (CALLE VARCHAR2, CIUDAD VARCHAR2) RETURN SELF AS RESULT
);
/


CREATE OR REPLACE TYPE BODY DIRECCION AS
  --
  MEMBER PROCEDURE SET_CALLE(C VARCHAR2) IS
  BEGIN
    CALLE := C;
  END;
  --
  MEMBER FUNCTION GET_CALLE RETURN VARCHAR2 IS
  BEGIN
    RETURN CALLE;
  END;
  --
  CONSTRUCTOR FUNCTION DIRECCION (CALLE VARCHAR2, CIUDAD VARCHAR2)
                                   RETURN SELF AS RESULT IS  
  BEGIN
    SELF.CALLE := CALLE;
    SELF.CIUDAD := CIUDAD;
    SELF.CODIGO_POST := NULL;
    RETURN;
  END;

END;
  --


/
----USO DEL TIPO DIRECCION
DECLARE
  DIR DIRECCION := DIRECCION(NULL,NULL,NULL);
BEGIN
  DIR.SET_CALLE('La Mina, 3');
  DBMS_OUTPUT.PUT_LINE(DIR.GET_CALLE); 
  
  DIR := NEW DIRECCION ('C/Teruel 25','Zaragoza');
  DBMS_OUTPUT.PUT_LINE(DIR.GET_CALLE);

  DIR := NEW DIRECCION ('C/Madrid 10','Toledo',45002);
  DBMS_OUTPUT.PUT_LINE(DIR.GET_CALLE); 
END;
/

CREATE OR REPLACE TYPE RECTANGULO AS OBJECT
(
  BASE   NUMBER,
  ALTURA NUMBER,
  AREA   NUMBER,
  CONSTRUCTOR FUNCTION RECTANGULO 
             (BASE NUMBER, ALTURA NUMBER)
    RETURN SELF AS RESULT
);
/
-------------------
CREATE TABLE TABLAREC (VALOR INTEGER);
/


CREATE OR REPLACE TYPE RECTANGULO AS OBJECT
(
  BASE   NUMBER,
  ALTURA NUMBER,
  AREA   NUMBER,
  STATIC PROCEDURE PROC1 (ANCHO INTEGER, ALTO INTEGER),
  MEMBER PROCEDURE PROC2 (ANCHO INTEGER, ALTO INTEGER),
  CONSTRUCTOR FUNCTION RECTANGULO (BASE NUMBER, ALTURA NUMBER)
                       RETURN SELF AS RESULT
);
/  


CREATE OR REPLACE TYPE BODY RECTANGULO AS
-- 
  CONSTRUCTOR FUNCTION RECTANGULO (BASE NUMBER, ALTURA NUMBER)
                                   RETURN SELF AS RESULT IS  
  BEGIN
    SELF.BASE := BASE;
    SELF.ALTURA := ALTURA;
    SELF.AREA := BASE * ALTURA;
    RETURN;
  END;
--
  STATIC PROCEDURE PROC1 (ANCHO INTEGER, ALTO INTEGER) IS
  BEGIN
    INSERT INTO TABLAREC VALUES(ANCHO*ALTO);
    --ALTURA := ALTO; -- DARIA ERROR NO SE PUEDE ACCEDER A LOS ATRIBUTOS DEL TIPO
    DBMS_OUTPUT.PUT_LINE('FILA INSERTADA');
    COMMIT;
  END;
--
MEMBER PROCEDURE PROC2 (ANCHO INTEGER, ALTO INTEGER) IS
  BEGIN    
    SELF.ALTURA := ALTO; --SE PUEDE ACCEDER A LOS ATRIBUTOS DEL TIPO
    SELF.BASE := ANCHO;
    AREA := ALTURA*BASE;
    INSERT INTO TABLAREC VALUES(AREA);
    DBMS_OUTPUT.PUT_LINE('FILA INSERTADA');
    COMMIT;
  END;
--
END;
/


--
DECLARE
  R1 RECTANGULO;
  R2 RECTANGULO;
  R3 RECTANGULO := RECTANGULO(NULL, NULL, NULL);
BEGIN
  R1 := NEW RECTANGULO(10, 20, 200);
  DBMS_OUTPUT.PUT_LINE('AREA R1: '||R1.AREA);

  R2 := NEW RECTANGULO(10,20);
  DBMS_OUTPUT.PUT_LINE('AREA R2: '||R2.AREA); 
 
  R3.BASE := 5;
  R3.ALTURA := 15;
  R3.AREA := R3.BASE * R3.ALTURA;
  DBMS_OUTPUT.PUT_LINE('AREA R3: '||R3.AREA);

  --USO DE LOS M�TODOS DEL TIPO  RECTANGULO
  RECTANGULO.PROC1(10, 20);   --LLAMADA AL M�TODO STATIC
  --RECTANGULO.PROC2(20, 30); --ERROR, LLAMADA AL M�TODO MEMBER
  --R1.PROC1(5, 6);           --ERROR, LLAMADA AL M�TODO STATIC 
  R1.PROC2(5, 10);            --LLAMADA AL M�TODO MEMBER
END;
/


--------------------------------------------------------
--ACTIVIDAD2


--COMPARAR OBJETOS

CREATE OR REPLACE TYPE PERSONA AS OBJECT
(
  CODIGO NUMBER,
  NOMBRE VARCHAR2(35),
  DIREC DIRECCION,
  FECHA_NAC DATE,
  MAP MEMBER FUNCTION POR_CODIGO RETURN NUMBER
);
/
CREATE OR REPLACE TYPE BODY PERSONA AS
  MAP MEMBER FUNCTION POR_CODIGO RETURN NUMBER IS
  BEGIN
    RETURN CODIGO;
  END;
END;
/
-------------
DECLARE
  P1 PERSONA := PERSONA(NULL,NULL,NULL,NULL);
  P2 PERSONA:= PERSONA(NULL,NULL,NULL,NULL);
BEGIN
  P1.CODIGO := 1;
  P1.NOMBRE := 'JUAN';
  P2.CODIGO :=1;
  P2.NOMBRE :='JUAN';
  IF P1= P2 THEN
    DBMS_OUTPUT.PUT_LINE('OBJETOS IGUALES');   
  ELSE
    DBMS_OUTPUT.PUT_LINE('OBJETOS DISTINTOS');
  END IF;
  
  IF P1.CODIGO = P2.CODIGO AND P1.NOMBRE=P2.NOMBRE
  THEN
     DBMS_OUTPUT.PUT_LINE('OBJETOS IGUALES - ');  
  END IF;
  
END;
/
---------------------TABLAS DE OBJETOS -- 
CREATE TABLE ALUMNOS OF PERSONA (
  CODIGO PRIMARY KEY
);

--INSERCION DE DATOS
INSERT INTO ALUMNOS VALUES( 
  1, 'Juan P�rez ', 
  DIRECCION ('C/Los manantiales 5', 'GUADALAJARA', 19005),
  '18/12/1991'
);

INSERT INTO ALUMNOS (CODIGO, NOMBRE, DIREC, FECHA_NAC) VALUES ( 
  2, 'Julia Bre�a',   
  DIRECCION ('C/Los espartales 25', 'GUADALAJARA', 19004),
  '18/12/1987'
);

--El siguiente bloque PL/SQL inserta una fila en la tabla ALUMNOS:
DECLARE
  DIR DIRECCION := DIRECCION('C/Sevilla 20', 'GUADALAJARA', 19004);
  PER PERSONA := PERSONA(5, 'MANUEL',DIR, '20/10/1987');
BEGIN  
  INSERT INTO ALUMNOS VALUES(PER); --insertar 
  COMMIT; 
END;
/


-------------------

DECLARE
  CURSOR C1 IS SELECT * FROM ALUMNOS;
BEGIN
  FOR I IN C1 LOOP
    DBMS_OUTPUT.PUT_LINE(I.NOMBRE ||
      ' - Calle: '|| I.DIREC.CALLE);
  END LOOP;
END;
/
--MODIFICAR LA DIRECCION COMPLETA
DECLARE
   D DIRECCION:= DIRECCION 
       ('C/Galiano 5','Guadalajara',19004);
BEGIN
    UPDATE ALUMNOS 
       SET DIREC = D WHERE NOMBRE ='Juan P�rez'; 
  COMMIT;
END;
/


-------ACTIVIDAD 3-----------------------------------

--
-- EJEMPLOS SOBRE VARRAYS 

CREATE TYPE TELEFONO AS VARRAY(3) OF VARCHAR2(9); 
/
DESC TELEFONO;

CREATE TABLE AGENDA 
(
  NOMBRE VARCHAR2(15),
  TELEF TELEFONO
);

DESC AGENDA;

--Insertamos varias filas:
SELECT * FROM AGENDA;

INSERT INTO AGENDA VALUES 
('MANUEL', 
   TELEFONO ('656008876', '927986655', '639883300')); 

INSERT INTO AGENDA (NOMBRE, TELEF) VALUES 
       ('MARTA', TELEFONO ('649500800'));
COMMIT;

SELECT * FROM AGENDA ;

--MODIFICION
UPDATE AGENDA 
  SET TELEF =  TELEFONO ('656008876', 
                         '927986655')
WHERE NOMBRE = 'MANUEL';

----------------------------------------------------
--RECORRER ELEMENTOS DEL ARRAY--
DECLARE
  CURSOR C1 IS SELECT * FROM AGENDA;
  CAD VARCHAR2(50);
BEGIN
  FOR I IN C1 LOOP
    DBMS_OUTPUT.PUT_LINE(I.NOMBRE ||
        ', N�mero de Telefonos: '||I.TELEF.COUNT);
    CAD:='*';
    FOR J IN 1 .. I.TELEF.COUNT LOOP   
      CAD:=CAD ||I.TELEF(J)||'*';
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(CAD);
  END LOOP;
END;
/


------------------------------
--CREAR PROCEDIMIENTO PARA INSERTAR DATOS EN LA AGENDA
-- EL PROCEDIMIENTO RECIBE EL NOMBRE Y EL ARRAY DE TELEFONOS.
CREATE OR REPLACE PROCEDURE INSERTAR_AGENDA 
  ( N VARCHAR2, T TELEFONO) AS
BEGIN
  INSERT INTO AGENDA VALUES (N, T);
END INSERTAR_AGENDA;
/
--USO DEL PROCEDIMIENTO
BEGIN
  INSERTAR_AGENDA('LUIS', TELEFONO('949009977'));
  INSERTAR_AGENDA('MIGUEL', TELEFONO('949004020',
    '678905400'));
  COMMIT;
END;
/
SELECT * FROM AGENDA;
----------------------------------------------
--EJEMPLO QUE VISUALIZA TELEFONOS DE UN NOMBRE

DECLARE 
  TEL TELEFONO := TELEFONO(NULL, NULL, NULL); 
BEGIN
  SELECT TELEF INTO  TEL FROM AGENDA WHERE NOMBRE = 'MARTA';

  --Visualizar Datos
  DBMS_OUTPUT.PUT_LINE('N� DE TEL�FONOS ACTUALES:   ' || TEL.COUNT);
  DBMS_OUTPUT.PUT_LINE('�NDICE DEL PRIMER ELEMENTO: ' || TEL.FIRST);
  DBMS_OUTPUT.PUT_LINE('�NDICE DEL �LTIMO ELEMENTO: ' || TEL.LAST);
  DBMS_OUTPUT.PUT_LINE('M�XIMO N� DE TLFS PERMITIDO:' || TEL.LIMIT);

  --A�ade un n�mero de tel�fono a MARTA   
  TEL.EXTEND; 
  TEL(TEL.COUNT):= '123000000';     
  UPDATE AGENDA A SET A.TELEF = TEL WHERE NOMBRE = 'MARTA';   
    
  --Elimina un tel�fono   
  SELECT TELEF INTO TEL FROM AGENDA WHERE NOMBRE = 'MANUEL';     
  TEL.TRIM;   --Elimina el �ltimo elemento del array    
  TEL.DELETE; --Elimina todos los elementos
  UPDATE AGENDA A SET A.TELEF = TEL WHERE NOMBRE = 'MANUEL';    
END;
/

--ACTIVIDAD 5


----  EJEMPLOS SOBRE TABLAS ANIDADAS

CREATE TYPE TABLA_ANIDADA 
    AS TABLE OF DIRECCION; 
/
CREATE TABLE EJEMPLO_TABLA_ANIDADA 
(
 ID NUMBER(2),
 APELLIDOS VARCHAR2(35),
 DIREC  TABLA_ANIDADA
)
NESTED TABLE DIREC STORE AS DIREC_ANIDADA;

desc direc_anidada;
desc direccion;
desc tabla_anidada;


desc EJEMPLO_TABLA_ANIDADA ;
--INSERTAR DATOS

INSERT INTO EJEMPLO_TABLA_ANIDADA VALUES 
(1, 'RAMOS', 
  TABLA_ANIDADA (
    DIRECCION ('C/Los manantiales 5', 'GUADALAJARA', 19004),
    DIRECCION ('C/Los manantiales 10', 'GUADALAJARA', 19004),
    DIRECCION ('C/Av de Paris 25', 'C�CERES', 10005),
    DIRECCION ('C/Segovia 23-3A', 'TOLEDO', 45005)
  )
);

INSERT INTO EJEMPLO_TABLA_ANIDADA VALUES (2, 'MART�N', 
  TABLA_ANIDADA (
    DIRECCION ('C/Huesca 5', 'ALCAL� DE H', 28804),
    DIRECCION ('C/Madrid 20', 'ALCORC�N', 28921)
  )
);

--Se inserta el id, el nombre y la tabla anidada vac�a:

insert into EJEMPLO_TABLA_ANIDADA 
    values (5, 'PEREZ', TABLA_ANIDADA());


insert into EJEMPLO_TABLA_ANIDADA 
    values (15, 'PEREZ', null);
    
--CONSULTAS

SELECT * FROM EJEMPLO_TABLA_ANIDADA;
--
--CONSULTA TODAS LAS DIRECCIONES DE CADA ID
--SI LA TABLA ANIDADA ES NULA NOSE MUESTRAN LAS FILAS
SELECT  ID, APELLIDOS, DIRECCION.* 
FROM EJEMPLO_TABLA_ANIDADA,  TABLE(DIREC) DIRECCION;

--DIRECCIONES DEL ID 1     
SELECT  ID, APELLIDOS, DIRECCION.* 
FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC) DIRECCION 
WHERE ID=1;

--SOLO LA CALLE
SELECT  ID, APELLIDOS, DIRECCION.CALLE 
FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC) DIRECCION 
WHERE ID=1;

--USANDO CURSOR PARA SELECCIONAR CALLES
-- POR CADA APELLIDO SUS CALLES, 1 FILA POR APELLIDO
SELECT ID, APELLIDOS,  CURSOR(SELECT TT.CALLE  FROM TABLE(DIREC) TT )
FROM EJEMPLO_TABLA_ANIDADA ;

--MAS CONSULTAS CON CURSOR

SELECT ID, APELLIDOS, CURSOR(SELECT count(TT.CALLE)
                     FROM TABLE(T.DIREC) TT)
FROM EJEMPLO_TABLA_ANIDADA T;

SELECT ID, APELLIDOS, count(*)
FROM EJEMPLO_TABLA_ANIDADA T, TABLE(T.DIREC)
GROUP BY ID, APELLIDOS;

----Si queremos seleccionar el n�mero 
--de direcciones de cada persona DE LA CIUDAD DE GUADALAJARA
SELECT ID, APELLIDOS, CURSOR (
                      SELECT count(*) 
                      FROM  TABLE(DIREC)  
                      where ciudad ='GUADALAJARA')
FROM EJEMPLO_TABLA_ANIDADA;
--
--apellidos que tienen 2 direcciones en la ciudad de GUADALAJARA
SELECT ID, APELLIDOS, CURSOR (
                      SELECT count(*) 
                      FROM  TABLE(DIREC)  
                      where ciudad ='GUADALAJARA')
FROM EJEMPLO_TABLA_ANIDADA
     where (SELECT count(*) FROM TABLE(DIREC)              
            where ciudad ='GUADALAJARA') = 2;

--
SELECT ID, APELLIDOS, 
   (SELECT count(*) FROM 
        TABLE(EJEMPLO_TABLA_ANIDADA.DIREC)  
                 where ciudad ='GUADALAJARA')
FROM EJEMPLO_TABLA_ANIDADA
  where (SELECT count(*) FROM 
          TABLE(EJEMPLO_TABLA_ANIDADA.DIREC)  
         where ciudad ='GUADALAJARA') = 2;

--

--Utilizando la tabla anidada en la consulta, 
--la consulta resulta m�s sencilla:
SELECT ID, APELLIDOS, count(*)
FROM EJEMPLO_TABLA_ANIDADA T, table(T.DIREC) tt
    where tt.ciudad ='GUADALAJARA' 
 group by ID, APELLIDOS 
 having count(*) = 2;

--acceso a filas de la columna que es tabla anidada
-- LAS CALLES DEL ID 1
SELECT CALLE, CIUDAD,CODIGO_POST  FROM THE 
    ( SELECT DIREC 
      FROM EJEMPLO_TABLA_ANIDADA wherE ID=1 )   
      ;
WHERE CIUDAD='GUADALAJARA';
--
DESC DIRECCION;
SELECT  CALLE FROM THE 
    (SELECT T.DIREC 
    FROM EJEMPLO_TABLA_ANIDADA T WHERE ID=1) ;


-- EL SIGUIENTE EJEMPLO OBTIENE LAS DIRECCIONES COMPLETAS DEL IDENTIFIDOR 1 
SELECT  ID, APELLIDOS, DIRECCION.* 
FROM EJEMPLO_TABLA_ANIDADA,
    TABLE(DIREC) DIRECCION ;

-- SOLO DEL ID 1
SELECT  ID, DIRECCION.* 
FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC) DIRECCION 
;--WHERE ID=1;

 SELECT  ID, APELLIDOS, DIRECCION.* 
     FROM EJEMPLO_TABLA_ANIDADA , TABLE(DIREC) DIRECCION;

--------------------------------------------------
--INSERCION EN LA TABLA
------------------------------------------------------------
INSERT INTO TABLE 
  (SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA 
               WHERE ID = 1) 
VALUES (DIRECCION
('C/Los manantiales 15', 'GUADALAJARA', 19005)
  );

 SELECT  ID, APELLIDOS, DIRECCION.* 
     FROM EJEMPLO_TABLA_ANIDADA , TABLE(DIREC) DIRECCION
     WHERE ID=1;
	 
--INSERTAR UNA DIRECCION AL IDENTIFICADOR SIN DIRECCIONES
--(NO TIENE VALOR NULO)
SELECT * FROM EJEMPLO_TABLA_ANIDADA;
--
INSERT INTO TABLE 
  (SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 5) 
VALUES (DIRECCION
('C/Sevilla 24, 1A', 'GUADALAJARA', 19003) );

--INSERTAR EN COLUMNA CON VALOR NULO
INSERT INTO EJEMPLO_TABLA_ANIDADA VALUES (6,'GIL', null);

INSERT INTO TABLE  --ERROR porque la columna es nula
(SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 6) 
VALUES (DIRECCION ('C/Madrid 5', 'OROPESA', 45560));

--SI LA COLUMNA ES NULA HACER UPDATE PARA LA PRIMERA DIRECCION
Update EJEMPLO_TABLA_ANIDADA
set direc =
  TABLA_ANIDADA (DIRECCION ('C/Madrid 5', 'OROPESA', 45560))
Where ID = 6 ;
--a�ado otra direccion
INSERT INTO TABLE 
  (SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 6) 
VALUES (DIRECCION
('C/Toledo 34, 8A', 'GUADALAJARA', 19003) );

--
DESC USER_NESTED_TABLES;
SELECT * FROM USER_NESTED_TABLES;

----------------------------------------
--modificacion
     
UPDATE TABLE 
(SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 1) PRIMERA
SET VALUE(PRIMERA) = DIRECCION ('C/Pilon 11', 'TOLEDO', 45589)
WHERE
VALUE(PRIMERA)=DIRECCION('C/Los manantiales 5', 'GUADALAJARA', 19004);

select *  from the (
SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA e WHERE ID = 1);
ROLLBACK;
--Modificamos (para el ID 1) todas las direcciones que tengan la ciudad de
--GUADALJARA, le damos el valor MADRID:

UPDATE TABLE 
(SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 1) PRIMERA
SET PRIMERA.ciudad =  'madrid'
WHERE
PRIMERA.ciudad='GUADALAJARA';

--BORRADO
DELETE FROM TABLE 
(SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 1) PRIMERA
WHERE
VALUE(PRIMERA)=DIRECCION('C/Los manantiales 10', 'GUADALAJARA', 19004);

ROLLBACK;
-- BORRAMOS  TODAS LAS DIRECCIONES CON CIUDAD = 'GUADALAJARA'
DELETE FROM TABLE 
(SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID = 1) PRIMERA
WHERE
 PRIMERA.CIUDAD ='GUADALAJARA';

--OBTENER SOLO LAS DIRECCIONES DE GUADALAJARA
SELECT CALLE, CIUDAD, CODIGO_POST 
FROM THE( SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID =1)
WHERE CIUDAD ='GUADALAJARA';
--------------------------------------------
---PROCEDIMIENTO
CREATE OR REPLACE PROCEDURE VER_DIREC(IDENT NUMBER) AS
  CURSOR C1 IS 
        SELECT CALLE FROM THE 
       (SELECT T.DIREC FROM EJEMPLO_TABLA_ANIDADA T WHERE ID = IDENT);
BEGIN
  FOR I IN C1 LOOP
    DBMS_OUTPUT.PUT_LINE(I.CALLE);
  END LOOP;
END VER_DIREC;
/
--Probando el procedimiento
BEGIN
  VER_DIREC(1);
END;
/


-----

CREATE OR REPLACE FUNCTION EXISTE_DIREC 
   (IDEN NUMBER, PCALLE VARCHAR2,  PCIU VARCHAR2, CP NUMBER)
RETURN VARCHAR2 AS
  IDT NUMBER;
  TABLAANID TABLA_ANIDADA;
  CUENTA NUMBER;
BEGIN
 --COMPROBAR SI EXISTE ID, 
   SELECT COUNT(ID) INTO CUENTA 
       FROM EJEMPLO_TABLA_ANIDADA WHERE ID = IDEN;
   IF CUENTA = 0 THEN
       RETURN 'NO EXISTE EL ID: '||IDEN||', EN LA TABLA'; 
   END IF ;
   IF CUENTA > 1 THEN
      RETURN 'EXISTEN VARIOS REGISTROS CON EL MISMO  ID: '||IDEN ;
   END IF;
   
 --EL ID EXISTE, COMPROBAR SI LA CALLE EXISTE:
  SELECT ID INTO IDT
            FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC)
            WHERE ID= IDEN 
            AND UPPER(CALLE)=UPPER(PCALLE) 
            AND UPPER(CIUDAD) = UPPER(PCIU) 
            AND CODIGO_POST= CP;
            
  RETURN ('LA DIRECCI�N : '||PCALLE || '*' ||PCIU
              || '*' || CP  || 'YA EXISTE PARA ESE ID: '||IDEN);   
EXCEPTION
WHEN NO_DATA_FOUND THEN  
  RETURN 'NO EXISTE LA DIRECCION : '||PCALLE || '*' ||PCIU
              || '*' || CP  || ' PARA EL ID: '||IDEN;
END EXISTE_DIREC;
/


BEGIN
  DBMS_OUTPUT.PUT_LINE(EXISTE_DIREC(1, 'C/Madrid 5', 'OROPESA', 45560));
  DBMS_OUTPUT.PUT_LINE(EXISTE_DIREC(1,'C/Los manantiales 5', 'GUADALAJARA', 19004));
  DBMS_OUTPUT.PUT_LINE(EXISTE_DIREC(5,'C/Los manantiales 5', 'GUADALAJARA', 19004));  
END;
/

--ACTIVIDAD 7--



--------------------- REFERENCIAS-

drop type EMPLEADO_T;

CREATE TYPE EMPLEADO_T AS OBJECT (
  NOMBRE     VARCHAR2(30),
  JEFE       REF EMPLEADO_T
);
/
drop table EMPLEADO;
CREATE TABLE EMPLEADO OF EMPLEADO_T;

INSERT INTO EMPLEADO VALUES (EMPLEADO_T ('GIL', NULL));

select * from EMPLEADO;
INSERT INTO EMPLEADO SELECT EMPLEADO_T ('ARROYO', REF(E))
  			FROM EMPLEADO E WHERE E.NOMBRE = 'GIL';

--no inserta nada, SANCHEZ NO EXISTE   
INSERT INTO EMPLEADO SELECT EMPLEADO_T ('PEREZ', REF(E))
  			FROM EMPLEADO E WHERE E.NOMBRE = 'SANCHEZ';

INSERT INTO EMPLEADO SELECT EMPLEADO_T ('PEREZ', REF(E))
  			FROM EMPLEADO E WHERE E.NOMBRE = 'GIL';
  
commit;
select * from EMPLEADO;
--
SELECT NOMBRE, DEREF(P.JEFE) FROM EMPLEADO P;
---
--INSERTAR OTRA FILA QUE EL JEFE APUNTE A PEREZ

INSERT INTO EMPLEADO 
  SELECT EMPLEADO_T ('FERNANDEZ', REF(E))
  FROM EMPLEADO E WHERE E.NOMBRE = 'PEREZ';
  --
INSERT INTO EMPLEADO 
  SELECT EMPLEADO_T ('RAMOS', REF(E))
  FROM EMPLEADO E WHERE E.NOMBRE = 'PEREZ';

COMMIT;

SELECT REF(P) FROM EMPLEADO P WHERE NOMBRE='GIL';


SELECT NOMBRE, DEREF(P.JEFE) FROM EMPLEADO P;
SELECT NOMBRE, DEREF(P.JEFE).nombre FROM EMPLEADO P;
SELECT NOMBRE, P.JEFE.nombre FROM EMPLEADO P;
SELECT REF(P) FROM EMPLEADO P WHERE NOMBRE='GIL';

 --
INSERT INTO EMPLEADO 
  SELECT EMPLEADO_T ('RAMOS', REF(E))
  FROM EMPLEADO E WHERE E.NOMBRE = 'PEREZ';


 --CAMBIAMOS EL JEFE DE RAMOS, LE ASIGNAMOS GIL
UPDATE EMPLEADO
    SET JEFE = (SELECT REF(E) FROM EMPLEADO E WHERE NOMBRE = 'GIL')
    WHERE NOMBRE = 'RAMOS';

SELECT NOMBRE, P.JEFE.nombre FROM EMPLEADO P;

---ACTIVIDAD 8


---------------------FIN REFERENCIAS-


---------------- HERENCIA ----------

--Se define el tipo persona
--
CREATE OR REPLACE TYPE TIPO_PERSONA AS OBJECT(
  DNI VARCHAR2(10),
  NOMBRE VARCHAR2(25),
  FEC_NAC DATE,
  -- Los m�todos no se pueden redefinir
  MEMBER FUNCTION EDAD RETURN NUMBER, 
  FINAL MEMBER FUNCTION GET_DNI RETURN VARCHAR2,
  MEMBER FUNCTION GET_NOMBRE RETURN VARCHAR2,
  MEMBER PROCEDURE VER_DATOS 
) NOT FINAL;      -- El tipo permite derivar subtipos 

/
--Cuerpo del tipo persona
--
CREATE OR REPLACE TYPE BODY TIPO_PERSONA AS
  MEMBER FUNCTION EDAD RETURN NUMBER IS
    ED NUMBER;
  BEGIN
    ED := TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(FEC_NAC, 'YYYY');
  RETURN ED;
  END;
  --
  FINAL MEMBER FUNCTION GET_DNI RETURN VARCHAR2 IS
  BEGIN
    RETURN DNI;
  END;
  --
  MEMBER FUNCTION GET_NOMBRE RETURN VARCHAR2 IS
  BEGIN
    RETURN NOMBRE;
  END;
  --
  MEMBER PROCEDURE VER_DATOS IS
  BEGIN
    DBMS_OUTPUT.PUT_LINE(DNI|| '*' || NOMBRE ||'*'||EDAD());
  END;
END;
/
--Se define el tipo alumno
--
CREATE OR REPLACE TYPE TIPO_ALUMNO UNDER TIPO_PERSONA(
  				        --se define un subtipo 
  CURSO VARCHAR2(10),
  NOTA_FINAL NUMBER,
  MEMBER FUNCTION NOTA RETURN NUMBER,
  OVERRIDING MEMBER PROCEDURE VER_DATOS --se redefine ese m�todo
);
/
--Cuerpo del tipo alumno
--
CREATE OR REPLACE TYPE BODY  TIPO_ALUMNO AS
  MEMBER FUNCTION NOTA RETURN NUMBER IS
  BEGIN
    RETURN NOTA_FINAL;
  END;
  --
  OVERRIDING MEMBER PROCEDURE VER_DATOS IS  --se redefine ese m�todo
  BEGIN
    DBMS_OUTPUT.PUT_LINE(CURSO|| '*' ||NOTA_FINAL);
  END;
END;
/

------

DECLARE 
  -- Al asignar datos al alumno damos valores para
  --        DNI, NOMBRE, FECHA_NAC, CURSO, NOTA

  A1 TIPO_ALUMNO := TIPO_ALUMNO(NULL, NULL, NULL, NULL, NULL);
  A2 TIPO_ALUMNO := TIPO_ALUMNO('871234533A', 'PEDRO',
                                '12/12/1996', 'SEGUNDO', 7);
  NOM A1.NOMBRE%TYPE;
  DNI A1.DNI%TYPE;
  NOTAF A1.NOTA_FINAL%TYPE;
BEGIN
  A1.NOTA_FINAL := 8;
  A1.CURSO := 'PRIMERO';
  A1.NOMBRE := 'JUAN';
  A1.FEC_NAC := '20/10/1997';
  A1.VER_DATOS;

  NOM := A2.GET_NOMBRE();
  DNI := A2.GET_DNI();
  NOTAF := A2.NOTA();
  A2.VER_DATOS;

  DBMS_OUTPUT.PUT_LINE(A1.EDAD());
  DBMS_OUTPUT.PUT_LINE(A2.EDAD());
END;
/
 
-----

CREATE TABLE TALUMNOS OF TIPO_ALUMNO (DNI PRIMARY KEY);

INSERT INTO TALUMNOS VALUES
      ('871234533A', 'PEDRO', '12/12/1996', 'SEGUNDO', 7);
INSERT INTO TALUMNOS VALUES
      ('809004534B', 'MANUEL', '12/12/1997', 'TERCERO', 8);

SELECT * FROM TALUMNOS;
SELECT DNI, NOMBRE, CURSO, NOTA_FINAL FROM TALUMNOS;
SELECT P.GET_DNI(), P.GET_NOMBRE(), P.EDAD(), P.NOTA() 
FROM TALUMNOS P;

----FIN HERENCIA


---------------------FIN--------------------------------


















