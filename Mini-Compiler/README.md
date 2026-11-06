# Práctica TAL - Mini-compilador

Estado inicial de la práctica.

## Implementado por ahora

- Lectura de números enteros (`INTVAL`).
- Lectura de identificadores (`ID`).
- Ignorar espacios en blanco.
- Operadores simples `+`, `-`, `*`, `/`.
- Paréntesis `(` y `)`.
- Asignación simple `=`.

## Pendiente

- Palabras reservadas (`integer`, `string`, `if`, `else`, `while`, `end`, `print`).
- Cadenas de caracteres.
- Operadores relacionales y booleanos.
- Comentarios de línea y bloque.
- Completar el analizador sintáctico.
- Análisis semántico y generación de código.

## Compilar

Desde la raíz del proyecto:

```bash
mkdir -p out
javac -d out src/tal/*.java
```

## Probar el analizador léxico inicial

```bash
java -cp out tal.Main test/inicial.txt
```

Los ficheros originales de prueba del profesor también están dentro de `test/`, pero esta versión inicial todavía no pretende superarlos todos.
