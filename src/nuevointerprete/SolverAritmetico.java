package nuevointerprete;

public class SolverAritmetico {

    private Nodo nodo;

    public SolverAritmetico(Nodo nodo) {
        this.nodo = nodo;
    }

    public SolverAritmetico(){}
    
    public Object resolver(){
        return resolver(nodo);
    }
    
    
    public Object resolver(Nodo n){
        // No tiene hijos, es un operando
        if(n.getHijos() == null){
            if(n.getValue().tipo == Tipo_Token.NUMERO || n.getValue().tipo == Tipo_Token.CADENA){
                return n.getValue().literal;
            }
            else if(n.getValue().tipo == Tipo_Token.IDENTIFICADOR){
                // Ver la tabla de símbolos
                if(TablasSimbolos.existeIdentificador(n.getValue().lexema)){
                    return TablasSimbolos.obtener(n.getValue().lexema);
                }else {
                    System.err.println("Error!! : Variable " +  n.getValue().lexema + " no definida ");
                    System.exit(1);
                }
            }
            else if (n.getValue().tipo == Tipo_Token.FALSO) {
                return false;
            } 
            else if (n.getValue().tipo == Tipo_Token.VERDADERO) {
                return true;
            }
        }

        // Por simplicidad se asume que la lista de hijos del nodo tiene dos elementos
        Nodo izq = n.getHijos().get(0);
        Nodo der = n.getHijos().get(1);

        Object resultadoIzquierdo = resolver(izq);
        Object resultadoDerecho = resolver(der);

        if(resultadoIzquierdo instanceof Double && resultadoDerecho instanceof Double){
            switch (n.getValue().tipo){
                case Y:
                    return ((Boolean)resultadoIzquierdo && (Boolean) resultadoDerecho);
                case O:
                    return ((Boolean)resultadoIzquierdo || (Boolean) resultadoDerecho);
            }
        }
        else if(resultadoIzquierdo instanceof Double && resultadoDerecho instanceof Double){
            switch (n.getValue().tipo){
                case SUMA:
                    return ((Double)resultadoIzquierdo + (Double) resultadoDerecho);
                case RESTA:
                    return ((Double)resultadoIzquierdo - (Double) resultadoDerecho);
                case MULTIPLICACION:
                    return ((Double)resultadoIzquierdo * (Double) resultadoDerecho);
                case DIVISION:
                    return ((Double)resultadoIzquierdo / (Double) resultadoDerecho);
                case MENOR:
                    return ((Double)resultadoIzquierdo < (Double) resultadoDerecho);
                case MENOR_IGUAL:
                    return ((Double)resultadoIzquierdo <= (Double) resultadoDerecho);
                case MAYOR:
                    return ((Double)resultadoIzquierdo > (Double) resultadoDerecho);
                case MAYOR_IGUAL:
                    return ((Double)resultadoIzquierdo >= (Double) resultadoDerecho);
                /*case COMPARACION:
                    return (((Double) resultadoIzquierdo).equals((Double) resultadoDerecho));
                case DIFERENTE_DE:
                    return (!((Double) resultadoIzquierdo).equals((Double) resultadoDerecho));
                case IGUAL:
                    // Asignar el valor de la derecha a la variable de la izquierda
                    if (izq.getValue().tipo == TipoToken.IDENTIFICADOR){
                        TablaSimbolos.asignar(izq.getValue().lexema, resultadoDerecho);
                    }
                    break;*/
                }
        }
        else if(resultadoIzquierdo instanceof String || resultadoDerecho instanceof String){
            String valor;

            if (! (n.getValue().tipo == Tipo_Token.SUMA)){
                return null;
            }

            // Ejecutar la concatenación

            if (!(resultadoDerecho instanceof String)){
                valor = resultadoDerecho.toString();
                return resultadoIzquierdo + valor;
            }

            if (!(resultadoIzquierdo instanceof String)){
                valor = resultadoIzquierdo.toString();
                return valor + resultadoDerecho;
            }

            return (String) resultadoIzquierdo + resultadoDerecho;
        }

        return null;
    }
}
