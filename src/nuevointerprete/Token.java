/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevointerprete;


public class Token {

    final Tipo_Token tipo;
    final String lexema;
    final Object literal;

    //final int posicion;

    /*public Token(TipoToken tipo, String lexema,int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
        this.posicion = posicion;
    }*/

    public Token(Tipo_Token tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
    }

    public Token(Tipo_Token tipo, String lexema, Object literal) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
    }

    public String toString(){
        return tipo + " " + lexema + " " + (literal == null ? " " : literal.toString());
    }

    // Métodos auxiliares
    public boolean esOperando(){
        switch (this.tipo){
            case IDENTIFICADOR:
            case NUMERO:
            case CADENA:
                return true;
            default:
                return false;
        }
    }

    public boolean esOperador(){
        switch (this.tipo){
            case SUMA:
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
            case IGUAL:
            case MAYOR:
            case MAYOR_IGUAL:
                //
            case MENOR_IGUAL:
            case MENOR:
            case DIFERENTE_DE:
            case ASIGNAR:
            case Y:
            case O:
                return true;
            default:
                return false;
        }
    }

    public boolean esPalabraReservada()
    {
        switch (this.tipo)
        {
            case VARIABLE:
            case SI:
            case IMPRIMIR:
            case ADEMAS:
            case MIENTRAS:
            case PARA:
                return true;
            default:
                return false;
        }
    }

    public boolean esEstructuraDeControl()
    {
        switch (this.tipo)
        {
            case SI:
            case PARA:
            case MIENTRAS:
            case ADEMAS:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t)
    {
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia()
    {
        switch (this.tipo)
        {
            case MULTIPLICACION:
            case DIVISION:
                return 7;
            case SUMA:
            case RESTA:
                return 6;
            case ASIGNAR:
                return 1;
            case MAYOR_IGUAL:
            case MAYOR:
            case MENOR:
            case MENOR_IGUAL:
                return 5;
            case DIFERENTE_DE:
            case IGUAL:
                return 4;
            case Y:
                return 3;
            case O:
                return 2;
        }

        return 0;
    }

    public int aridad()
    {
        switch (this.tipo)
        {
            case MULTIPLICACION:
            case DIVISION:
            case SUMA:
            case RESTA:
            case IGUAL:
            case ASIGNAR:
            case MAYOR:
            case MAYOR_IGUAL:
            case MENOR_IGUAL:
            case MENOR:
            case Y:
            case O:
                return 2;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Token))
        {
            return false;
        }

        if(this.tipo == ((Token)o).tipo)
        {
            return true;
        }

        return false;
    }
}