package tal;

import java.io.IOException;

public class Main
{
public static void main(String[] args) throws IOException
{
    if(args.length != 1)
    {
        System.out.println("Uso: java tal.Main archivo.txt");
        return;
    }

    AFD lexer = new AFD(args[0]);

    try
    {
        Token currentToken;

        while((currentToken = lexer.read()).type != Token.Type.EOF)
            System.out.printf("%2d %8s  %s%n", currentToken.type.ordinal(),
                              currentToken.type, currentToken.name);
    }
    finally
    {
        lexer.close();
    }
}
}
