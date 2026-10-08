package tal;

import java.io.*;
import static tal.Token.Type.*;

/**
 * Analizador léxico implementado mediante un
 * autómata finito determinista.
 *
 * Primera versión de la práctica.
 * Por ahora reconoce enteros, identificadores y algunos
 * operadores simples. El resto de tokens se añadirá
 * progresivamente en las siguientes sesiones.
 */
public class AFD extends ALex
{
/**
 * Construye el autómata.
 * @param fichero Fichero de texto que se debe analizar.
 * @throws IOException .
 */
public AFD(String fichero) throws IOException
{
    super(fichero);
    setStart(this::inicio);
}

/** Estado inicial del autómata. */
private void inicio()
{
    if(isDigitChar())
        state(this::intval);
    else if(isIdCharStart())
        state(this::id);
    else if(isSpaceChar())
        restart();
    else if(isChar('+') || isChar('-'))
        state(this::sum);
    else if(isChar('*') || isChar('/'))
        state(this::mul);
    else if(isChar('('))
        state(this::ipar);
    else if(isChar(')'))
        state(this::dpar);
    else if(isChar('='))
        state(this::asign);
    else if(isEofChar())
        token(EOF);
    else
        error();
}

/** Reconoce valores enteros. */
private void intval()
{
    if(isDigitChar())
        state(this::intval);
    else if(isIdChar())
        error();
    else
        token(INTVAL);
}

/** Reconoce identificadores. */
private void id()
{
    if(isIdChar())
        state(this::id);
    else
        token(ID);
}

private void sum()
{
    token(SUM);
}

private void mul()
{
    token(MUL);
}

private void ipar()
{
    token(IPAR);
}

private void dpar()
{
    token(DPAR);
}

private void asign()
{
    token(ASIGN);
}

} // AFD
