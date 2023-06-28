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

public class Scanner {

    private final String source;

    private final List<Token> tokens;
    
    public static int linea;

    private static final Map<String, Tipo_Token> palabrasReservadas;
    
    private final StringBuilder lexema;
    
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("clase", Tipo_Token.CLASE);
        palabrasReservadas.put("ademas", Tipo_Token.ADEMAS);
        palabrasReservadas.put("si",Tipo_Token.SI);
        palabrasReservadas.put("nulo",Tipo_Token.NULO); 
        palabrasReservadas.put("imprimir", Tipo_Token.IMPRIMIR);
        palabrasReservadas.put("retornar", Tipo_Token.DEVOLVER);
        palabrasReservadas.put("falso", Tipo_Token.FALSO);
        palabrasReservadas.put("verdadero", Tipo_Token.VERDADERO);
        palabrasReservadas.put("mientras", Tipo_Token.MIENTRAS);
        palabrasReservadas.put("para", Tipo_Token.PARA);
        palabrasReservadas.put("funcion", Tipo_Token.FUNCION);
        palabrasReservadas.put("super", Tipo_Token.SUPER);
        palabrasReservadas.put("este", Tipo_Token.ESTE);
        palabrasReservadas.put("variable", Tipo_Token.VARIABLE);
    }

    Scanner(String source){
        this.source = source;
        this.tokens = new ArrayList<>();
        linea=1;
        this.lexema=new StringBuilder();
    }
    
    public List<Token> scanTokens(){
        int estado = 0;
        
        for (int i = 0; i <= source.length(); i++) {
            char flujo = Leerflujo(i,source.length());
            linea = incrementaLinea(flujo);
            
            switch (estado) {
                
                case 0:
                    
                    if (flujo != '\0') {
                       if(flujo =='<'){
                           estado = 1;
                           lexema.append(flujo);
                        } 
                        else if(flujo == '='){
                           estado = 2;
                           lexema.append(flujo);
                        }
                        else if(flujo =='>'){
                           estado = 3;
                           lexema.append(flujo);
                        }
                        else if(flujo == '!'){
                           estado = 4;
                           lexema.append(flujo);
                        }
                        else if(flujo == '('){
                           estado = 5;
                           lexema.append(flujo);
                        }
                        else if(flujo ==')'){
                           estado = 6;
                           lexema.append(flujo);
                        }
                        else if(flujo =='['){
                           estado = 7;
                           lexema.append(flujo);
                        }
                        else if(flujo ==']'){
                           estado = 8;
                           lexema.append(flujo);
                        }
                       else if(flujo =='{'){
                           estado = 9;
                           lexema.append(flujo);
                        }
                         else if(flujo =='}'){
                           estado = 10;
                           lexema.append(flujo);
                        }
                       else if(flujo =='"'){
                           estado = 11;
                           lexema.append(flujo);
                        }
                        else if(flujo >= '0' && flujo <='9'){
                           estado = 12;
                           lexema.append(flujo);
                        }
                        else if(flujo == '+'){
                           estado = 18;
                           lexema.append(flujo);
                        }
                       else if(flujo == '-'){
                           estado = 19;
                           lexema.append(flujo);
                        }
                       else if(flujo == '*'){
                           estado = 20;
                           lexema.append(flujo);
                        }
                       else if(flujo == '/'){
                           estado = 21;
                           lexema.append(flujo);
                        }
                       else if(flujo == '%'){
                           estado = 22;
                           lexema.append(flujo);
                        }
                       else if(flujo == '&'){
                           estado = 23;
                           lexema.append(flujo);
                        }
                       else if(flujo == '|'){
                           estado = 24;
                           lexema.append(flujo);
                        }
                        else if(flujo >= 'a' && flujo <='z' || flujo >= 'A' && flujo <='Z' ){
                           estado = 25;
                           lexema.append(flujo);
                        }
                        else if(flujo =='_'){
                           estado = 26;
                           lexema.append(flujo);
                        }
                        else if(flujo ==' ' || flujo == '\t' || flujo =='\n' || flujo == '\r'){
                           estado = 27;
                           lexema.append(flujo);
                        }
                        else if(flujo ==';'){
                           lexema.append(flujo);
                           AgregarToken(Tipo_Token.PUNTO_Y_COMA,lexema.toString());
                        }
                       else if(flujo ==','){
                           lexema.append(flujo);
                           AgregarToken(Tipo_Token.COMA,lexema.toString());
                        }
                       else if(flujo =='.'){
                           lexema.append(flujo);
                           AgregarToken(Tipo_Token.PUNTO,lexema.toString());
                        }
                       else{
                           throw new RuntimeException("No se que es esto:" + flujo);
                       }
                    }
                break;
                //OPERADORES R//
                case 1:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.MENOR_IGUAL,lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.MENOR,lexema.toString());
                    }
                break;
                
                case 2:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.IGUAL,lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.ASIGNAR,lexema.toString());
                    }  
                break;
                
                case 3:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.MAYOR_IGUAL,lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.MAYOR,lexema.toString());
                    }
                break;
                
                case 4:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.DIFERENTE_DE,lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.NO,lexema.toString());
                    }
                break;
                
                case 5:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.PARENTESIS_IZQ,lexema.toString());    
                break;
                
                case 6:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.PARENTESIS_DER,lexema.toString());
                break;
                
                case 7:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.CORCHETE_IZQ,lexema.toString());
                break;
                
                case 8:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.CORCHETE_DER,lexema.toString());
                break;
                
                case 9:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.LLAVE_IZQ,lexema.toString());  
                break;
                
                case 10:
                    i--;
                    estado=0;
                    AgregarToken(Tipo_Token.LLAVE_DER,lexema.toString());   
                break;
                
                case 11:
                    if(flujo != '"' && flujo != '\0'){
                        lexema.append(flujo);
                    }
                    else if (flujo == '\0'){
                        throw new RuntimeException("No se que es esto:" + lexema);
                    }
                    else{
                        estado=0;
                        lexema.append(flujo);
                        AgregaToken(Tipo_Token.CADENA, lexema.toString(),lexema.substring(1,lexema.length()-1));
                    }
                break;
                
                case 12:
                    if(flujo >= '0' && flujo <= '9'){
                        lexema.append(flujo);
                    }
                    else if(flujo == '.'){
                        estado =13;
                        lexema.append(flujo);
                    }
                    else if(flujo == 'e' || flujo == 'E'){
                        estado = 15;
                        lexema.append(flujo);
                    } 
                    else{
                        i--;
                        estado=0;
                        AgregaToken(Tipo_Token.NUMERO,lexema.toString(),Integer.parseInt(lexema.toString()));
                    }
                break;
                
                case 13:
                    if(flujo >= '0' && flujo <= '9'){
                        estado=14;
                        lexema.append(flujo);
                    }
                    else{
                        throw new RuntimeException("No se que es esto:" + lexema);
                    }
                break;
                
                case 14:
                    if(flujo >= '0' && flujo <= '9'){
                        lexema.append(flujo);
                    }
                    else if(flujo == 'e' || flujo == 'E'){
                        estado=15;
                        lexema.append(flujo);
                    }
                    else{
                    i--;
                    estado=0;
                    AgregaToken(Tipo_Token.NUMERO, lexema.toString(),Float.parseFloat(lexema.toString()));
                    }
                break;
                
                case 15:
                    if(flujo >= '0' && flujo <= '9'){
                        estado = 17;
                        lexema.append(flujo);
                    }
                    else if(flujo == '+' || flujo == '-'){
                        estado=16;
                        lexema.append(flujo);
                    }
                    else{
                        throw new RuntimeException("No se que es esto:" + lexema);
                    }
                break;
                
                case 16:
                    if(flujo >= '0' && flujo <= '9'){
                        estado = 17;
                        lexema.append(flujo);
                    }
                    else{
                        throw new RuntimeException("No se que es esto:" + lexema);
                    }
                break;
                
                case 17:
                    if(flujo >= '0' && flujo <= '9'){
                        lexema.append(flujo);
                    }
                    else{
                    i--;
                    estado=0;
                    AgregaToken(Tipo_Token.NUMERO, lexema.toString(),Double.parseDouble(lexema.toString()));
                    }
                break;
                
                case 18:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.INCREMENTO, lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.SUMA,lexema.toString());
                    }
                break;
                
                case 19:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.DECREMENTO, lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.RESTA,lexema.toString());
                    }
                break;
                
                case 20:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.POR_IGUAL, lexema.toString());
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.MULTIPLICACION,lexema.toString());
                    }
                break;
                
                case 21:
                    estado=0;
                    if(flujo=='='){
                        lexema.append(flujo);
                        AgregarToken(Tipo_Token.DIV_IGUAL, lexema.toString());
                    }
                    else if (flujo =='/'){
                      estado=28;
                      lexema.append(flujo);
                    }
                    else if(flujo == '*'){
                        estado=29;
                        lexema.append(flujo);
                    }
                    else{
                        i--;
                        AgregarToken(Tipo_Token.DIVISION,lexema.toString());
                    }
                break;
                
                case 22:
                   estado=0;
                   if(flujo == '='){
                       lexema.append(flujo);
                       AgregarToken(Tipo_Token.MODULOIGUAL,lexema.toString());
                   }              
                   else{
                       i--;
                       AgregarToken(Tipo_Token.MODULO, lexema.toString());
                   }
                break;
                
                case 23:
                   if(flujo == '&'){
                       estado = 0;
                       lexema.append(flujo);
                       AgregarToken(Tipo_Token.Y, lexema.toString());
                   }  
                   else{
                       throw new RuntimeException("No se que es esto:" + lexema);
                   }
                break;
                
                case 24:
                    if(flujo == '|'){
                       estado = 0;
                       lexema.append(flujo);
                       AgregarToken(Tipo_Token.O,lexema.toString());
                   }  
                   else{
                       throw new RuntimeException("No se que es esto:" + lexema);
                   }
                break;
                
                case 25:
                    if(flujo >= 'a' && flujo <='z' || flujo >= 'A' && flujo <='Z' || flujo >= '0' && flujo <='9' || flujo == '_'){
                        lexema.append(flujo);
                    }
                    else{
                        i--;
                        estado=0;
                        AgregarToken(Tipo_Token.IDENTIFICADOR,lexema.toString());
                    }
                break;
                
                case 26:
                    if(flujo >= 'a' && flujo <='z' || flujo >= 'A' && flujo <='Z' || flujo >= '0' && flujo <='9'){
                        estado = 25;
                        lexema.append(flujo);
                    }
                    else if(flujo == '_'){
                        lexema.append(flujo);
                    }
                    else{
                        throw new RuntimeException("No se que es esto:" + lexema);
                    }
                    
                break;
                
                case 27:
                    if(flujo ==' ' || flujo == '\t' || flujo =='\n' || flujo == '\r'){
                        lexema.append(flujo);
                    }
                    else{
                        i--;
                        estado = 0;
                        lexema.delete(0, lexema.length());
                    }
                break;
                
                case 28:
                    if(flujo != '\n'){
                        lexema.append(flujo);
                    }
                    else{
                        i--;
                        estado = 0;
                        lexema.delete(0, lexema.length());
                    }
                    
                break;
                
                case 29:
                    if(flujo == '*'){
                        estado=30;
                    }
                    else{
                        lexema.append(flujo);
                    }
                break;
                
                case 30:
                    if(flujo == '/'){
                        estado = 31;
                    }
                    else{
                        lexema.append(flujo);
                    }
                break;
                
                case 31:
                    i--;
                    estado=0;
                    lexema.delete(0,lexema.length());
                    
                break;
                
                default:
                    throw new RuntimeException("No se que es esto:" + flujo);
            }
        }
        tokens.add(new Token(Tipo_Token.EOF, "", linea));
        return tokens;
    }
    
    private void AgregarToken(Tipo_Token tipo, String lexema) {
        if(tipo == Tipo_Token.IDENTIFICADOR){
            tipo = palabrasReservadas.getOrDefault(lexema, Tipo_Token.IDENTIFICADOR);
        }
        tokens.add(new Token(tipo, lexema,  linea));
        this.lexema.delete(0,this.lexema.length());
    }
    private void AgregaToken(Tipo_Token tipo, String lexema, Object literal){
        tokens.add(new Token(tipo, lexema, literal ));
        this.lexema.delete(0,this.lexema.length());
    }
    private int incrementaLinea(char flujo){
        if(flujo == '\n')
            linea++;
        return linea;
    }
    private char Leerflujo(int index, int longi){
        if(index>=longi){
            return '\0';
        }
        return source.charAt(index);
    }
    
   
}
