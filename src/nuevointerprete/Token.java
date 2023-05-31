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
    
}
