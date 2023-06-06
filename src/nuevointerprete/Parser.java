/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevointerprete;
import java.beans.Expression;
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
    private final Token logico = new Token(Tipo_Token.COMA, "logico");
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
        PROGRAM();
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
        if(hayErrores) return;
        
        if(preanalisis.equals(clase) || preanalisis.equals(funcion) ||
                preanalisis.equals(variable) || preanalisis.equals(no) ||
                preanalisis.equals(resta) || preanalisis.equals(verdadero) ||
                preanalisis.equals(falso) || preanalisis.equals(nulo) ||
                preanalisis.equals(este) || preanalisis.equals(numero) ||
                preanalisis.equals(cadena) || preanalisis.equals(identificador) ||
                preanalisis.equals(parentesis_izq) || preanalisis.equals(supr) ||
                preanalisis.equals(para) || preanalisis.equals(si) ||
                preanalisis.equals(imprimir) || preanalisis.equals(devolver) ||
                preanalisis.equals(mientras) || preanalisis.equals(llave_izq)) {
                DECLARATION();
        }
    }
    
    void DECLARATION(){
        if(hayErrores) return;

        if(preanalisis.equals(clase)){
            CLASS_DECL();
            DECLARATION();
        } else if (preanalisis.equals(funcion)) {
            FUN_DECL();
            DECLARATION();
        } else if (preanalisis.equals(variable)) {
            VAR_DECL();
            DECLARATION();
        } else if (preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr) || preanalisis.equals(para) ||
                preanalisis.equals(si) || preanalisis.equals(imprimir) ||
                preanalisis.equals(devolver) || preanalisis.equals(mientras) ||
                preanalisis.equals(llave_izq)){
            STATEMENT(); 
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
        else {
            System.out.println("Error");
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
        if(hayErrores) return;

        if (preanalisis.equals(funcion)) {
            coincidir(funcion);
            FUNCTION();
        }
        else {
            System.out.println("Error"); //Verificar
        }
    }
    
    void VAR_DECL(){
        if(hayErrores) return;

        if(preanalisis.equals(variable)){
            coincidir(variable);
            coincidir(identificador);
            VAR_INIT();
            coincidir(punto_y_coma);
        }
        else {
            System.out.println("Error");
        }
    }
    
    void VAR_INIT(){
        if(hayErrores) return;
        if(preanalisis.equals(asignar)) {
            coincidir(asignar);
            EXPRESSION();
        }
    }

    void STATEMENT(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr))
        {
            EXPR_STMT();
        }

        else if(preanalisis.equals(para)) {
            FOR_STMT();
        }

        else if(preanalisis.equals(si)){
            IF_STMT();
        }

        else if(preanalisis.equals(imprimir)) {
            PRINT_STMT();
        }

        if(preanalisis.equals(devolver)) {
            RETURN_STMT();
        }

        else if(preanalisis.equals(mientras)) {
            WHILE_STMT();
        }

        else if(preanalisis.equals(llave_izq)){
            BLOCK();
        }
        else {
            System.out.println("Error");
        }
    }
    
    void EXPR_STMT(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            EXPRESSION();
            coincidir(punto_y_coma);
        }
        else {
            System.out.println("Error");
        }
        }
    
    void FOR_STMT(){
        if(hayErrores) return;
        if (preanalisis.equals(para)){
            coincidir(para);
            coincidir(parentesis_izq);
            FOR_STMT_1();
            FOR_STMT_2();
            FOR_STMT_3();
            coincidir(parentesis_der);
            STATEMENT();
        }
        else {
            System.out.println("Error");
        }
    }
    
    void FOR_STMT_1(){
        if(hayErrores) return;
        if(preanalisis.equals(variable)){
            VAR_DECL();
        }else if (preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            EXPR_STMT();
        }else if (preanalisis.equals(punto_y_coma)) {
            coincidir(punto_y_coma);
        }
        else {
            System.out.println("Error");
        }
    }
    
    void FOR_STMT_2(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            EXPRESSION();
            coincidir(punto_y_coma);
        }
        if(preanalisis.equals(punto_y_coma)){
            coincidir(punto_y_coma);
        }
        else {
            System.out.println("Error");
        }
    }
    
    void FOR_STMT_3(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            EXPRESSION();
        }
    }
    
    void IF_STMT(){
        if(hayErrores) return;
        if(preanalisis.equals(si)){
            coincidir(si);
            coincidir(parentesis_izq);
            EXPRESSION();
            coincidir(parentesis_der);
            STATEMENT();
            ELSE_STATEMENT();
        }else{
            System.out.println("Error");
        }
    }
    
    void ELSE_STATEMENT(){
        if(hayErrores) return;
        if(preanalisis.equals(ademas)){
            coincidir(ademas);
            STATEMENT();}
    }
    
    void PRINT_STMT(){
        if(hayErrores) return;
        if(preanalisis.equals(imprimir)) {
            coincidir(imprimir);
            EXPRESSION();
            coincidir(punto_y_coma);
        }
        else{
            System.out.println("Error");
        }
    }
    
    void RETURN_STMT(){
        if(hayErrores) return;
        if(preanalisis.equals(devolver)) {
            coincidir(devolver);
            RETURN_EXP_OPC();
            coincidir(punto_y_coma);
        }
        else{
            System.out.println("Error");
        }
    }
    
    void RETURN_EXP_OPC() {
        if (hayErrores) return;
        if (preanalisis.equals(no) || preanalisis.equals(resta)
                || preanalisis.equals(verdadero) || preanalisis.equals(falso)
                || preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)){
            EXPRESSION();
        }
    }
    
    void WHILE_STMT(){
        if (hayErrores) return;
        if(preanalisis.equals(mientras)) {
            coincidir(mientras);
            coincidir(parentesis_izq);
            EXPRESSION();
            coincidir(parentesis_der);
            STATEMENT();
        }
        else{
            System.out.println("Error");
        }
    }
    
    void BLOCK(){
        if (hayErrores) return;
        if (preanalisis.equals(llave_izq)){
            coincidir(llave_izq);
            BLOCK_DECL();
            coincidir(llave_der);
        }
        else{
            System.out.println("Error");
        }
    }
    
    void BLOCK_DECL(){
        if (hayErrores) return;
        if(preanalisis.equals(clase) || preanalisis.equals(funcion) ||
                preanalisis.equals(variable) || preanalisis.equals(no) ||
                preanalisis.equals(resta) || preanalisis.equals(verdadero) ||
                preanalisis.equals(falso) || preanalisis.equals(nulo) ||
                preanalisis.equals(este) || preanalisis.equals(numero) ||
                preanalisis.equals(cadena) || preanalisis.equals(identificador) ||
                preanalisis.equals(parentesis_izq) || preanalisis.equals(supr) ||
                preanalisis.equals(para) || preanalisis.equals(si) ||
                preanalisis.equals(imprimir) || preanalisis.equals(devolver) ||
                preanalisis.equals(mientras) || preanalisis.equals(llave_izq)){
            DECLARATION();
            BLOCK_DECL();
        }
    }
    
    void EXPRESSION(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            ASSIGNEMENT();
        }
        else{
            System.out.println("Error");
        }
    }
    
    void ASSIGNEMENT(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            LOGIC_OR();
            ASSIGNEMENT_OPC();
        }
        else{
            System.out.println("Error");
        }
    }

    void ASSIGNEMENT_OPC(){
        if(hayErrores) return;
        if(preanalisis.equals(asignar)){
            coincidir(asignar);
            EXPRESSION();
        }
       
    }
    
    void LOGIC_OR(){
        if(hayErrores) return;
        if(preanalisis.equals(no) || preanalisis.equals(resta) ||
                preanalisis.equals(verdadero) || preanalisis.equals(falso) ||
                preanalisis.equals(nulo) || preanalisis.equals(este) ||
                preanalisis.equals(numero) || preanalisis.equals(cadena) ||
                preanalisis.equals(identificador) || preanalisis.equals(parentesis_izq) ||
                preanalisis.equals(supr)) {
            LOGIC_AND();
            LOGIC_OR_2();
        }
        else{
            System.out.println("Error");
        }

    }
    
    void LOGIC_OR_2(){
        if(hayErrores) return;
        if (preanalisis.equals(o)) {
            coincidir(o);
            LOGIC_AND();
            LOGIC_OR_2();
        }
    }
    
    void LOGIC_AND(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            EQUALITY();
            LOGIC_AND_2();
        }else {
            System.out.println("Error");
        }
    }
    
    void LOGIC_AND_2(){
        if(hayErrores) return;
        if(preanalisis.equals(y)) {
            coincidir(y);
            EQUALITY();
            LOGIC_AND_2();
        }
    }

    void EQUALITY(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            COMPARISON();
            EQUALITY_2();
        }else {
            System.out.println("Error");
        }
    }

    void EQUALITY_2(){
        if(hayErrores) return;
        if(preanalisis.equals(diferente_de)){
            coincidir(diferente_de);
            COMPARISON();
            EQUALITY_2();
        }else if(preanalisis.equals(igual)){
            coincidir(igual);
            COMPARISON();
            EQUALITY_2();
        }
    }

    void COMPARISON(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            TERM();
            COMPARISON_2();
        }else {
            System.out.println("Error");
        }

    }

    void COMPARISON_2(){
        if(hayErrores) return;
        if(preanalisis.equals(mayor)){
            coincidir(mayor);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.equals(mayor_igual)){
            coincidir(mayor_igual);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.equals(menor)){
            coincidir(menor);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.equals(menor_igual)){
            coincidir(menor_igual);
            TERM();
            COMPARISON_2();
        } 
    }

    void TERM(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            FACTOR();
            TERM2();
        }
        else {
            System.out.println("Error");
        }
    }

    void TERM2(){
        if(hayErrores) return;
        if (preanalisis.equals(resta)){
            coincidir(resta);
            FACTOR();
            TERM2();
        }else if(preanalisis.equals(suma)){
            coincidir(suma);
            FACTOR();
            TERM2();
        }

    }

    void FACTOR(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                    preanalisis.equals(supr)) {
            UNARY();
            FACTOR_2();
        }
        else{
            System.out.println("Error");
        }
    }

    void FACTOR_2(){
        if(hayErrores) return;
        if (preanalisis.equals(division)){
            coincidir(division);
            UNARY();
            FACTOR_2();
        } else if (preanalisis.equals(multiplicacion)){
            coincidir(multiplicacion);
            UNARY();
            FACTOR_2();
        }

    }

    void UNARY(){
        if(hayErrores) return;
        if(preanalisis.equals(no)) {
            coincidir(no);   
            UNARY();
        }else if(preanalisis.equals(resta)){
            coincidir(resta);
            UNARY();
        } else if(preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            CALL();
        }
        else {
            System.out.println("Error");
        }
    }

    void CALL(){
        if(hayErrores) return;
        if(preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            PRIMARY();
            CALL_2();
        }
        else {
            System.out.println("Error");
        }
    }
   
    void CALL_2(){
        if(hayErrores) return;
        if(preanalisis.equals(parentesis_izq)) {
            coincidir(parentesis_izq);
            ARGUMENTS_OPC();
            coincidir(parentesis_der);
            CALL_2();
        }else if (preanalisis.equals(punto)){
            coincidir(punto);
            coincidir(identificador);
            CALL_2();
        }


    }

    void CALL_OPC(){
        if(hayErrores) return;
        if(preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            CALL();
            coincidir(punto);
        }

    }

    void PRIMARY(){
        if(hayErrores) return;
        if(preanalisis.equals(verdadero)) {
            coincidir(verdadero);
        }else if (preanalisis.equals(falso)){
            coincidir(falso);
        }else if(preanalisis.equals(nulo)){
            coincidir(nulo);
        }else if (preanalisis.equals(este)){
            coincidir(este);
        }else if (preanalisis.equals(numero)){
            coincidir(numero);
        }else if (preanalisis.equals(cadena)){
            coincidir(cadena);
        }else if (preanalisis.equals(identificador)){
            coincidir(identificador);
        }else if (preanalisis.equals(parentesis_izq)){
            coincidir(parentesis_izq);
            EXPRESSION();
            coincidir(parentesis_der);
        }else if (preanalisis.equals(supr)){
            coincidir(supr);
            coincidir(punto);
            coincidir(identificador);
        }
        else {
            System.out.println("Error");
        }
    }

    void FUNCTION(){
        if(preanalisis.equals(identificador)){
            coincidir(identificador);
            coincidir(parentesis_izq);
            PARAMETERS_OPC();
            coincidir(parentesis_der);
            BLOCK();
        }
        else{
            hayErrores = true;
            System.out.println("Error ");
        }
    }

    void FUNCTIONS(){
        if(hayErrores) return;

        if(preanalisis.equals(funcion)){
            FUNCTION();
            FUNCTIONS();
        }
    }

    void PARAMETERS_OPC(){
        if(hayErrores) return;
        if(preanalisis.equals(identificador)) {
            PARAMETERS();
        }
    }

    void PARAMETERS(){
        if(hayErrores) return;
        if(preanalisis.equals(identificador)) {
            coincidir(identificador);
            PARAMETERS_2();
        }
        else{
            System.out.println("Error");
        }
    }

    void PARAMETERS_2(){
        if(hayErrores) return;
        if(preanalisis.equals(coma)) {
            coincidir(coma);
            coincidir(identificador);
            PARAMETERS_2();
        }

    }

    void ARGUMENTS_OPC(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            ARGUMENTS();
        }

    }

    void ARGUMENTS(){
        if(hayErrores) return;
        if(preanalisis.equals(no)||preanalisis.equals(resta)||
                preanalisis.equals(verdadero)||preanalisis.equals(falso)||
                preanalisis.equals(nulo)||preanalisis.equals(este)||
                preanalisis.equals(numero)||preanalisis.equals(cadena)||
                preanalisis.equals(identificador)||preanalisis.equals(parentesis_izq)||
                preanalisis.equals(supr)) {
            EXPRESSION();
            ARGUMENTS_2();
        }
        else{
            System.out.println("Error");
        }
    }

    void ARGUMENTS_2(){

        if(hayErrores) return;
        if (preanalisis.equals(coma))
        {
            coincidir(coma);
            EXPRESSION();
            ARGUMENTS_2();
        }

    }

    void coincidir(Token t){
        if(hayErrores) return;

        if(preanalisis.tipo == t.tipo){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println(preanalisis.linea + ") Error en la posición: " + i + " Se esperaba un:  " + t.tipo);

        }
    }

}
