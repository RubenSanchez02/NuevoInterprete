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
public class Token {
    final Tipo_Token tipo;
    final String lexema;
    final Object literal;
    final int linea;

    public Token(Tipo_Token tipo, String lexema, Object literal, int linea) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.linea = linea;
    }
    
    public Token(Tipo_Token tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
        this.linea = 0;
    }
    
   @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if(this.tipo == ((Token)o).tipo){
            return true;
        }

        return false;
    }


    
    @Override
    public String toString(){
        return "Linea [" + linea + "]: " + tipo + " " + lexema + " " + literal;
    }

    // Métodos auxiliares
    public boolean esOperando(){
        switch (this.tipo){
            case IDENTIFICADOR:
            case NUMERO:
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
                return true;
            default:
                return false;
        }
    }

    public boolean esPalabraReservada(){
        switch (this.tipo){
            case VARIABLE:
            case SI:
            case IMPRIMIR:
            case ADEMAS:
                return true;
            default:
                return false;
        }
    }

    public boolean esEstructuraDeControl(){
        switch (this.tipo){
            case SI:
            case ADEMAS:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t){
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia(){
        switch (this.tipo){
            case MULTIPLICACION:
            case DIVISION:
                return 3;
            case SUMA:
            case RESTA:
                return 2;
            case IGUAL:
                return 1;
            case MAYOR:
            case MAYOR_IGUAL:
                return 1;
        }

        return 0;
    }

    public int aridad(){
        switch (this.tipo) {
            case MULTIPLICACION:
            case DIVISION:
            case SUMA:
            case RESTA:
            case IGUAL:
            case MAYOR:
            case MAYOR_IGUAL:
                return 2;
        }
        return 0;
    }
}