DECLARE
    DIRECC1 DIRECCION := DIRECCION(null,null, null);  -- Aquí se usa el tipo DIRECCION correctamente
    PERSON PERSONA := PERSONA(NULL, NULL, NULL, NULL);  -- Usamos el tipo PERSONA correctamente
    ALUMNO T_ALUMNO := T_ALUMNO(NULL, NULL, NULL, NULL);  -- Usamos el tipo T_ALUMNO correctamente
BEGIN
    -- Asignación de valores
    DIRECC1.CALLE := 'LAMINA'; 
    DIRECC1.CIUDAD := 'ALBACETE'; 
    DIRECC1.CODIGO_POST := 44044;

    PERSON.CODIGO := 1; 
    PERSON.NOMBRE := 'JUAN';  
    PERSON.DIREC := DIRECC1;  -- Asignamos la variable DIRECC al atributo DIRECC de PERSON
    PERSON.FECHA_NAC := SYSDATE;

    ALUMNO.AL := PERSON;  -- Asignamos la variable PERSON a ALUMNO.AL
    ALUMNO.NOTA1 := 7;   
    ALUMNO.NOTA2 := 2;   
    ALUMNO.NOTA3 := 9;

    -- Mostrar resultados
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || ALUMNO.AL.NOMBRE || 
                         ' | Nota 1: ' || ALUMNO.NOTA1 || 
                         ' | Nota 2: ' || ALUMNO.NOTA2 || 
                         ' | Nota 3: ' || ALUMNO.NOTA3 || 
                         ' | Nota Media: ' || ALUMNO.MEDIANOTAS());
END;

