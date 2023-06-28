/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevointerprete;

/**
 *
 * @author Sanch
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.Source;

public class Scanner
{

    private final String source;
    private int Estado = 0;

    private final List<Token> tokens = new ArrayList<>();

    private int linea = 1;

    private static final Map<String, Tipo_Token> palabrasReservadas;
    static
    {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("y", Tipo_Token.Y);
        palabrasReservadas.put("clase", Tipo_Token.CLASE);
        palabrasReservadas.put("ademas", Tipo_Token.ADEMAS);
        palabrasReservadas.put("falso", Tipo_Token.FALSO);
        palabrasReservadas.put("para", Tipo_Token.PARA);
        palabrasReservadas.put("funcion", Tipo_Token.FUNCION); //definir funciones
        palabrasReservadas.put("si", Tipo_Token.SI);
        palabrasReservadas.put("nulo", Tipo_Token.NULO);
        palabrasReservadas.put("o", Tipo_Token.O);
        palabrasReservadas.put("imprimir", Tipo_Token.IMPRIMIR);
        palabrasReservadas.put("devolver", Tipo_Token.DEVOLVER);
        palabrasReservadas.put("super", Tipo_Token.SUPER);
        palabrasReservadas.put("este", Tipo_Token.ESTE);
        palabrasReservadas.put("verdadero", Tipo_Token.VERDADERO);
        palabrasReservadas.put("variable", Tipo_Token.VARIABLE); //definir variables
        palabrasReservadas.put("mientras", Tipo_Token.MIENTRAS);
    }


    Scanner(String source)
    {
        this.source = source + " ";
    }

    List<Token> scanTokens()
    {
        int Estado, Posicion;
        char Caracter;
        String lexema = "";
        int inicioLexema = 0;

        Estado = 0;


        for (Posicion=0; Posicion<source.length(); Posicion++)
        {
            Caracter = source.charAt(Posicion);

            switch(Estado)
            {
                case 0:

                    if (Caracter=='(')
                    {
                        tokens.add(new Token(Tipo_Token.PARENTESIS_IZQ, "("));
                    }
                    else if(Caracter==')')
                    {
                        tokens.add(new Token(Tipo_Token.PARENTESIS_DER, ")"));
                    }
                    else if(Caracter=='{')
                    {
                        tokens.add(new Token(Tipo_Token.LLAVE_IZQ, "{"));
                    }
                    else if(Caracter=='}')
                    {
                        tokens.add(new Token(Tipo_Token.LLAVE_DER, "}"));
                    }
                    else if(Caracter==',')
                    {
                        tokens.add(new Token(Tipo_Token.COMA, ","));
                    }
                    else if(Caracter=='.')
                    {
                        tokens.add(new Token(Tipo_Token.PUNTO, "."));
                    }
                    else if(Caracter==';')
                    {
                        tokens.add(new Token(Tipo_Token.PUNTO_Y_COMA, ";"));
                    }
                    else if(Caracter=='-')
                    {
                        tokens.add(new Token(Tipo_Token.RESTA, "-"));
                    }
                    else if(Caracter=='+')
                    {
                        tokens.add(new Token(Tipo_Token.SUMA, "+"));
                    }
                    else if(Caracter=='*')
                    {
                        tokens.add(new Token(Tipo_Token.MULTIPLICACION, "*"));
                    }
                    else if(Caracter=='/')
                    {
                        Estado = 1;
                    }
                    else if(Caracter=='!')
                    {
                        Estado = 2;
                    }
                    else if(Caracter=='=')
                    {
                        Estado = 3;
                    }
                    else if(Caracter=='<')
                    {
                        Estado = 4;
                    }
                    else if(Caracter=='>')
                    {
                        Estado = 5;
                    }
                    else if(Caracter=='"')
                    {
                        Estado = 9;
                    }
                    else if(Character.isDigit(Caracter))
                    {
                        Estado = 10;
                        lexema = lexema + Caracter;
                    }
                    else if(Character.isAlphabetic(Caracter))
                    {
                        Estado = 16;
                        lexema = lexema + Caracter;
                        inicioLexema = Posicion;
                    }
                    break;

                case 1:

                    if(Caracter=='/')
                    {
                        Estado = 6;
                    }
                    else if(Caracter=='*')
                    {
                        Estado = 7;
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.DIVISION, "/"));
                        Estado = 0;
                    }

                    break;
                case 2:

                    if (Caracter=='=')
                    {
                        tokens.add(new Token(Tipo_Token.DIFERENTE_DE, "!="));
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.NO, "!"));
                    }

                    Estado = 0;

                    break;

                case 3:

                    if(Caracter=='=')
                    {
                        tokens.add(new Token(Tipo_Token.IGUAL, "=="));
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.ASIGNAR, "="));
                    }

                    Estado = 0;

                    break;

                case 4:

                    if (Caracter=='=')
                    {
                        tokens.add(new Token(Tipo_Token.MENOR_IGUAL, "<="));
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.MENOR, "<"));
                    }

                    Estado = 0;

                    break;

                case 5:

                    if (Caracter=='=')
                    {
                        tokens.add(new Token(Tipo_Token.MAYOR_IGUAL,">="));
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.MAYOR, ">"));
                    }

                    Estado = 0;

                    break;

                case 6:

                    if(Caracter=='\n')
                    {
                        Estado = 0;
                    }
                    else
                    {
                        Estado = 6;
                    }

                    break;

                case 7:

                    if(Caracter=='*')
                    {
                        Estado = 8;
                    }
                    else
                    {
                        Estado = 7;
                    }

                    break;

                case 8:

                    if(Caracter=='/')
                    {
                        Estado = 0;
                    }
                    else
                    {
                        Estado = 7;
                    }

                    break;

                case 9:

                    if(Caracter == '"')
                    {
                        tokens.add(new Token(Tipo_Token.CADENA,lexema,lexema));

                        lexema ="";
                        Estado = 0;
                    }
                    else
                    {
                        Estado = 9;
                        lexema = lexema + Caracter;
                    }


                    break;

                case 10:

                    if(Character.isDigit(Caracter))
                    {
                        Estado = 10;
                        lexema = lexema + Caracter;
                    }
                    else if(Caracter == '.')
                    {
                        Estado = 11;
                        lexema = lexema + Caracter;
                    }
                    else if(Caracter == 'E')
                    {
                        Estado = 13;
                        lexema = lexema + Caracter;
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.NUMERO,lexema,Double.valueOf(lexema)));
                        lexema="";

                        Estado = 0;
                    }

                    break;

                case 11:
                    if(Character.isDigit(Caracter))
                    {
                        Estado = 12;
                        lexema = lexema + Caracter;
                    }
                    break;

                case 12:
                    if(Character.isDigit(Caracter))
                    {
                        Estado = 12;
                        lexema = lexema + Caracter;
                    }
                    else if(Caracter == 'E')
                    {
                        Estado = 13;
                        lexema = lexema + Caracter;
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.NUMERO,lexema,Double.valueOf(lexema)));
                        lexema ="";
                        Estado = 0;
                    }
                    break;

                case 13:
                    if(Caracter == '+' || Caracter == '-')
                    {
                        Estado = 14;
                        lexema = lexema + Caracter;
                    }
                    else if(Character.isDigit(Caracter))
                    {
                        Estado = 15;
                        lexema = lexema + Caracter;
                    }
                    break;

                case 14:
                    if(Character.isDigit(Caracter))
                    {
                        Estado = 15;
                        lexema = lexema + Caracter;
                    }
                    break;

                case 15:
                    if(Character.isDigit(Caracter))
                    {
                        Estado = 15;
                        lexema = lexema + Caracter;
                    }
                    else
                    {
                        Posicion--;
                        tokens.add(new Token(Tipo_Token.NUMERO,lexema,Double.valueOf(lexema)));
                        lexema = "";

                        Estado = 0;
                    }
                    break;

                case 16:

                    if(Character.isAlphabetic(Caracter) || Character.isDigit(Caracter) )
                    {
                        lexema = lexema + Caracter;
                    }
                    else
                    {
                        Tipo_Token tt = palabrasReservadas.get(lexema);
                        if(tt == null)
                        {
                            tokens.add(new Token(Tipo_Token.IDENTIFICADOR, lexema));
                        }
                        else
                        {
                            tokens.add(new Token(tt, lexema));
                        }

                        Estado = 0;
                        Posicion--;
                        lexema = "";
                        inicioLexema = 0;
                    }
                    break;

            }
        }
        //Aquí va el corazón del scanner.


        /*
        Analizar el texto de entrada para extraer todos los tokens
        y al final agregar el token de fin de archivo
         */
        tokens.add(new Token(Tipo_Token.EOF,""));

        return tokens;
    }
}