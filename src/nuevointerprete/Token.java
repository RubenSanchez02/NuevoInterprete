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
    
      public boolean equals(Tipo_Token tipo){
        return this.tipo == tipo;
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
            case CADENA:
            case VERDADERO:
            case FALSO:
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
            case ASIGNAR:
            case NO:
            // FALTA COMPARACION//
            case MAYOR:
            case MAYOR_IGUAL:
            case MENOR:
            case MENOR_IGUAL:
            case DIFERENTE_DE:
            case O:
            case Y:
                return true;
            default:
                return false;
        }
    }

    public boolean esPalabraReservada(){
        switch (this.tipo){
            case Y:
            case CLASE:
            case ADEMAS:
            case FALSO:
            case VERDADERO:
            case PARA:
            case FUNCION:
            case SI:
            case NULO:
            case O:
            case IMPRIMIR:
            case DEVOLVER:
            case SUPER:
            case ESTE:
            case VARIABLE:
            case MIENTRAS:
            //FALTA SI NO//
                return true;
            default:
                return false;
        }
    }
    
    

    public boolean esEstructuraDeControl(){
        switch (this.tipo){
            case SI:
            case ADEMAS:
            case MIENTRAS:
            case PARA:
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
            case PUNTO:
                return 4;
            case MULTIPLICACION:
            case DIVISION:
                return 3;
            case SUMA:
            case RESTA:
                return 2;
            case IGUAL:
            case MAYOR:
            case MAYOR_IGUAL:
            case MENOR:
            case MENOR_IGUAL:
            case DIFERENTE_DE:
            //Terminar de agregar los nuevos tokens
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
            case ASIGNAR:
            case MAYOR:
            case MAYOR_IGUAL:
            case MENOR:
            case MENOR_IGUAL:
            case DIFERENTE_DE:
            //FALTA EL DE COMPARAR//
            case Y:
            case O:

                return 2;
            case NO:
                return 1;
        }
        return 0;
    }
}