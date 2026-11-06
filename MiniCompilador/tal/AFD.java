package tal;

import java.io.IOException;
import static tal.Token.Type.*;

public class AFD extends ALex
{
public AFD(String fileName) throws IOException
{
    super(fileName);
    setStart(this::start);
}

private void start()
{
    if(isSpaceChar())
        restart();
    else if(isEofChar())
        token(EOF);
    else if(isDigitChar())
        state(this::integerValue);
    else if(isChar('e'))
        state(this::wordE);
    else if(isChar('i'))
        state(this::wordI);
    else if(isChar('p'))
        state(this::wordP);
    else if(isChar('s'))
        state(this::wordS);
    else if(isChar('w'))
        state(this::wordW);
    else if(isIdCharStart())
        state(this::identifier);
    else if(isChar('"'))
        stateNoChar(this::stringValue);
    else if(isChar('+') || isChar('-'))
        state(this::sum);
    else if(isChar('*'))
        state(this::multiplication);
    else if(isChar('/'))
        state(this::slash);
    else if(isChar('='))
        state(this::assignment);
    else if(isChar('!'))
        state(this::negation);
    else if(isChar('<') || isChar('>'))
        state(this::comparison);
    else if(isChar('|'))
        state(this::orStart);
    else if(isChar('&'))
        state(this::andStart);
    else if(isChar('('))
        state(this::leftParenthesis);
    else if(isChar(')'))
        state(this::rightParenthesis);
    else
        error();
}

private void integerValue()
{
    if(isDigitChar())
        state(this::integerValue);
    else if(isIdChar())
        error();
    else
        token(INTVAL);
}

private void identifier()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(ID);
}

private void wordE()
{
    if(isChar('l'))
        state(this::wordEl);
    else if(isChar('n'))
        state(this::wordEn);
    else
        identifier();
}

private void wordEl()
{
    if(isChar('s'))
        state(this::wordEls);
    else
        identifier();
}

private void wordEls()
{
    if(isChar('e'))
        state(this::wordElse);
    else
        identifier();
}

private void wordElse()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(ELSE);
}

private void wordEn()
{
    if(isChar('d'))
        state(this::wordEnd);
    else
        identifier();
}

private void wordEnd()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(END);
}

private void wordI()
{
    if(isChar('f'))
        state(this::wordIf);
    else if(isChar('n'))
        state(this::wordIn);
    else
        identifier();
}

private void wordIf()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(IF);
}

private void wordIn()
{
    if(isChar('t'))
        state(this::wordInt);
    else
        identifier();
}

private void wordInt()
{
    if(isChar('e'))
        state(this::wordInte);
    else
        identifier();
}

private void wordInte()
{
    if(isChar('g'))
        state(this::wordInteg);
    else
        identifier();
}

private void wordInteg()
{
    if(isChar('e'))
        state(this::wordIntege);
    else
        identifier();
}

private void wordIntege()
{
    if(isChar('r'))
        state(this::wordInteger);
    else
        identifier();
}

private void wordInteger()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(INTEGER);
}

private void wordP()
{
    if(isChar('r'))
        state(this::wordPr);
    else
        identifier();
}

private void wordPr()
{
    if(isChar('i'))
        state(this::wordPri);
    else
        identifier();
}

private void wordPri()
{
    if(isChar('n'))
        state(this::wordPrin);
    else
        identifier();
}

private void wordPrin()
{
    if(isChar('t'))
        state(this::wordPrint);
    else
        identifier();
}

private void wordPrint()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(PRINT);
}

private void wordS()
{
    if(isChar('t'))
        state(this::wordSt);
    else
        identifier();
}

private void wordSt()
{
    if(isChar('r'))
        state(this::wordStr);
    else
        identifier();
}

private void wordStr()
{
    if(isChar('i'))
        state(this::wordStri);
    else
        identifier();
}

private void wordStri()
{
    if(isChar('n'))
        state(this::wordStrin);
    else
        identifier();
}

private void wordStrin()
{
    if(isChar('g'))
        state(this::wordString);
    else
        identifier();
}

private void wordString()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(STRING);
}

private void wordW()
{
    if(isChar('h'))
        state(this::wordWh);
    else
        identifier();
}

private void wordWh()
{
    if(isChar('i'))
        state(this::wordWhi);
    else
        identifier();
}

private void wordWhi()
{
    if(isChar('l'))
        state(this::wordWhil);
    else
        identifier();
}

private void wordWhil()
{
    if(isChar('e'))
        state(this::wordWhile);
    else
        identifier();
}

private void wordWhile()
{
    if(isIdChar())
        state(this::identifier);
    else
        token(WHILE);
}

private void stringValue()
{
    if(isChar('"'))
        stateNoChar(this::stringEnd);
    else if(isEofChar() || isChar('\n') || isChar('\r'))
        error();
    else
        state(this::stringValue);
}

private void stringEnd()
{
    token(STRVAL);
}

private void sum()
{
    token(SUM);
}

private void multiplication()
{
    token(MUL);
}

private void slash()
{
    if(isChar('/'))
        stateNoChar(this::lineComment);
    else if(isChar('*'))
        stateNoChar(this::blockComment);
    else
        token(MUL);
}

private void lineComment()
{
    if(isChar('\n') || isChar('\r') || isEofChar())
        restart();
    else
        stateNoChar(this::lineComment);
}

private void blockComment()
{
    if(isEofChar())
        error();
    else if(isChar('*'))
        stateNoChar(this::blockCommentStar);
    else
        stateNoChar(this::blockComment);
}

private void blockCommentStar()
{
    if(isChar('/'))
        restart();
    else if(isEofChar())
        error();
    else if(isChar('*'))
        stateNoChar(this::blockCommentStar);
    else
        stateNoChar(this::blockComment);
}

private void assignment()
{
    if(isChar('='))
        state(this::relation);
    else
        token(ASIGN);
}

private void negation()
{
    if(isChar('='))
        state(this::relation);
    else
        token(NEG);
}

private void comparison()
{
    if(isChar('='))
        state(this::relation);
    else
        token(REL);
}

private void relation()
{
    token(REL);
}

private void orStart()
{
    if(isChar('|'))
        state(this::orEnd);
    else
        error();
}

private void orEnd()
{
    token(OR);
}

private void andStart()
{
    if(isChar('&'))
        state(this::andEnd);
    else
        error();
}

private void andEnd()
{
    token(AND);
}

private void leftParenthesis()
{
    token(IPAR);
}

private void rightParenthesis()
{
    token(DPAR);
}
}
