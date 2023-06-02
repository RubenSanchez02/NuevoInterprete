/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevointerprete;
import java.util.List;
/**
 *
 * @author Sanch
 */
public class Parser {

    private final List<Token> tokens;

    private final Token y = new Token(Tipo_Token.Y, "&&");
    private final Token clase = new Token(Tipo_Token.CLASE, "clase");
    private final Token ademas = new Token(Tipo_Token.ADEMAS, "ademas");
    private final Token para = new Token(Tipo_Token.PARA, "para");
    private final Token funcion = new Token(Tipo_Token.FUNCION, "funcion");
    private final Token si = new Token(Tipo_Token.SI, "si");
    private final Token nulo = new Token(Tipo_Token.NULO, "nulo");
    private final Token imprimir = new Token(Tipo_Token.IMPRIMIR, "imprimir");
    private final Token devolver = new Token(Tipo_Token.DEVOLVER, "devolver");
    private final Token supr = new Token(Tipo_Token.SUPER, "super");
    private final Token este = new Token(Tipo_Token.ESTE, "este");
    private final Token verdadero = new Token(Tipo_Token.VERDADERO, "verdadero");
    private final Token falso = new Token(Tipo_Token.FALSO, "falso");
    private final Token mientras = new Token(Tipo_Token.MIENTRAS, "mientras");
    private final Token variable = new Token(Tipo_Token.VARIABLE, "var");
    private final Token o = new Token(Tipo_Token.O, "||");
    private final Token no = new Token(Tipo_Token.NO, "!");
    private final Token igual = new Token(Tipo_Token.IGUAL, "==");
    private final Token diferente_de = new Token(Tipo_Token.DIFERENTE_DE, "!=");
    private final Token asignar = new Token(Tipo_Token.ASIGNAR, "=");
    private final Token menor = new Token(Tipo_Token.MENOR, "<");
    private final Token menor_igual = new Token(Tipo_Token.MENOR_IGUAL, "<=");
    private final Token mayor = new Token(Tipo_Token.MAYOR, ">");
    private final Token mayor_igual = new Token(Tipo_Token.MAYOR_IGUAL, ">=");
    private final Token numero = new Token(Tipo_Token.COMA, ",");
    private final Token cadena = new Token(Tipo_Token.EOF, "");
    private final Token logico = new Token(Tipo_Token.COMA, ",");
    private final Token identificador = new Token(Tipo_Token.IDENTIFICADOR, "");
    //private final Token letra = new Token(Tipo_Token, "");
    private final Token coma = new Token(Tipo_Token.COMA, ",");
    private final Token suma = new Token(Tipo_Token.SUMA, "+");
    private final Token resta = new Token(Tipo_Token.RESTA, "-");
    private final Token multiplicacion = new Token(Tipo_Token.MULTIPLICACION, "*");
    private final Token division = new Token(Tipo_Token.DIVISION, "/");
    private final Token por_igual = new Token(Tipo_Token.POR_IGUAL, "*=");
    private final Token div_igual = new Token(Tipo_Token.DIV_IGUAL, "/=");
    private final Token ampersand = new Token(Tipo_Token.AMPERSAND, "&");
    private final Token incremento = new Token(Tipo_Token.INCREMENTO, "+=");
    private final Token decremento = new Token(Tipo_Token.DECREMENTO, "-=");
    private final Token modulo = new Token(Tipo_Token.MODULO, "%");
    private final Token moduloigual = new Token(Tipo_Token.MODULOIGUAL, "%=");
    private final Token parentesis_izq = new Token(Tipo_Token.PARENTESIS_IZQ, "(");
    private final Token parentesis_der = new Token(Tipo_Token.PARENTESIS_DER, ")");
    private final Token corchete_izq = new Token(Tipo_Token.CORCHETE_IZQ, "[");
    private final Token corchete_der = new Token(Tipo_Token.CORCHETE_DER, "]");
    private final Token llave_izq = new Token(Tipo_Token.LLAVE_IZQ, "{");
    private final Token llave_der = new Token(Tipo_Token.LLAVE_DER, "}");
    private final Token punto = new Token(Tipo_Token.PUNTO, ".");
    private final Token punto_y_coma = new Token(Tipo_Token.PUNTO_Y_COMA, ";");
    private final Token declaracion = new Token(Tipo_Token.EOF, "");
    private final Token finCadena = new Token(Tipo_Token.EOF, "");

    private int i = 0;
    private boolean hayErrores = false;

    private Token preanalisis;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    public void parse(){
        i = 0;
        preanalisis = tokens.get(i);
        DECLARATION();
        if(!hayErrores && !preanalisis.equals(finCadena)){
            System.out.println("Error en la posición " + preanalisis.linea + ". No se esperaba el token " + preanalisis.tipo);
        }
        else if(!hayErrores && preanalisis.equals(finCadena)){
            System.out.println("Consulta válida");
        }

        /*if(!preanalisis.equals(finCadena)){
            System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.tipo);
        }else if(!hayErrores){
            System.out.println("Consulta válida");
        }*/
    }

    void PROGRAM(){
        DECLARATION();
    }
    
    void DECLARATION(){
        if(hayErrores) return;
        
        if(preanalisis.equals(identificador)){
            coincidir(identificador);
            CLASS_DECL();
            DECLARATION();
        }
    }
    
    void CLASS_DECL(){
        if(hayErrores) return;

        if(preanalisis.equals(clase)){
            coincidir(clase);
            coincidir(identificador);
            CLASS_INHER();
            coincidir(llave_izq);
            FUNCTIONS();
            coincidir(llave_der);
            
        }
    }
    
    void CLASS_INHER(){
        if(hayErrores) return;

        if(preanalisis.equals(menor)){
            coincidir(menor);
            coincidir(identificador);
        }

    }
    
    void FUN_DECL(){
    }
    
    void VAR_DECL(){
    }
    
    void VAR_INIT(){
    }

    void STATEMENT(){
    }
    
    void EXPR_STMT(){
    }
    
    void FOR_STMT(){
    }
    
    void FOR_STMT_1(){
    }
    
    void FOR_STMT_2(){
    }
    
    void FOR_STMT_3(){
    }
    
    void IF_STMT(){
    }
    
    void ELSE_STATEMENT(){
    }
    
    void PRINT_STMT(){
    }
    
    void RETURN_STMT(){
    }
    
    void RETURN_EXP_OPC(){
    }
    
    void WHILE_STMT(){
    }
    
    void BLOCK(){
    }
    
    void BLOCK_DECL(){
    }
    
    void EXPRESSION(){
    }
    
    void ASSIGNEMENT(){
    }
    
    void LOGIC_OR(){
    }
    
    void LOGIC_OR_2(){
    }
    
    void LOGIC_AND(){
    }
    
    void LOGIC_AND_2(){
    }
    
    void EQUALITY(){
    }
    
    void EQUALITY_2(){
    }
    
    void COMPARISON(){
    }
    
    void COMPARISON_2(){
    }
    
    void TERM(){
    }
    
    void TERM2(){
    }
    
    void FACTOR(){
    }
    
    void FACTOR_2(){
    }
    
    void UNARY(){
    }
    
    void CALL(){
    }
    
    void CALL_2(){
    }
    
    void CALL_OPC(){
    }
    
    void PRIMARY(){
    }
    
    void FUNCTION(){
        if(preanalisis.equals(identificador)){
            coincidir(identificador);
            coincidir(parentesis_izq);
            coincidir(parentesis_der);
        }
        else{
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.linea + ". Se esperaba un.");
        }
    }
    
    void FUNCTIONS(){
      if(hayErrores) return;

        if(preanalisis.equals(llave_izq)){
            FUNCTION();
            FUNCTIONS();
        }
    }
    
    void PARAMETERS_OPC(){
    }
    
    void PARAMETERS(){
    }
    
    void PARAMETERS_2(){
    }
    
    void ARGUMENTS_OPC(){
    }
    
    void ARGUMENTS(){
    }
    
    void ARGUMENTS_2(){
    }
    

    
    void coincidir(Token t){
        if(hayErrores) return;

        if(preanalisis.tipo == t.tipo){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.linea + ". Se esperaba un  " + t.tipo);

        }
    }

}
