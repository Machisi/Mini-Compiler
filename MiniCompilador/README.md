# Mini-compilador: hasta la sesión 4

Esta versión completa el análisis léxico sobre el proyecto original del profesor.

## Contenido

- `tal/AFD.java`: autómata léxico completo, con estados Runnable.
- `tal/Main.java`: muestra los tokens del fichero recibido como argumento.
- `tal/ALex.java`, `tal/ASin.java`, `tal/Token.java` y `tal/Code.java`: originales, sin cambios.
- `tal/ADR.java`: original del profesor, sin completar; el sintáctico corresponde a la sesión 5.
- `tal/tokens.txt`, `tal/numeros.txt` y `tal/primos.txt`: ejemplos originales.
- `ejemplos/`: ejemplos adicionales propios, identificados como tales.

Los archivos nuevos o modificados no contienen comentarios. Los originales conservan los comentarios del profesor. Las variables que se han añadido están en inglés.

## Compilar y ejecutar

Abre una terminal en esta carpeta, donde se encuentra la subcarpeta `tal`:

```powershell
javac -encoding UTF-8 -d bin tal/*.java
java -cp bin tal.Main tal/tokens.txt
java -cp bin tal.Main tal/primos.txt
```

Necesitas un JDK con `javac`. Se ha compilado también con compatibilidad Java 8. No hacen falta dependencias externas ni ejecutar JFLAP para compilar.

La salida enumera tipos y textos de tokens. STRVAL no conserva las comillas. Los comentarios y espacios se descartan. No se comprueba la sintaxis ni se ejecuta el programa.

## Decisiones léxicas

- Se usan las funciones de ALex para reconocer letras, dígitos, guiones bajos y espacios.
- Las palabras reservadas distinguen mayúsculas y minúsculas.
- Un número no puede continuar directamente con letras o guion bajo: `123abc` produce error, como en el esqueleto original.
- Las cadenas no pueden contener saltos de línea. No se ha añadido una sintaxis de escapes, que el enunciado no especifica.
- Los comentarios de bloque no se anidan, como en Java.
- Los signos de los números son tokens SUM independientes: `-5` produce SUM e INTVAL.
- La palabra de cierre es `end`, como en Token.java y en primos.txt. El `fin` del ejemplo transcrito no es una palabra reservada.

## Comprobación

Las dos versiones compilan. Se ha comprobado esta implementación de AFD con 131 casos léxicos, incluidos tokens.txt y numeros.txt originales, prefijos de palabras reservadas, todos los operadores al final de fichero, comentarios, cadenas y entradas incorrectas.

No se ha proporcionado el `test.zip` mencionado en el enunciado. Los ejemplos adicionales no son los tests oficiales.
