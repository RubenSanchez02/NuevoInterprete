package nuevointerprete;

public class Arbol {
    private final Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }
    
    public Arbol(){}

    public void setRaiz(Nodo raiz){
        this.raiz = raiz;
    }

    public void recorrer(){
        for(Nodo n : raiz.getHijos()){
            Token t = n.getValue();
            switch (t.tipo){
                // Operadores aritméticos
                /*case SUMA:
                case RESTA:
                case MULTIPLICACION:
                case DIVISION:
                case MENOR:
                case MENOR_IGUAL:
                case MAYOR:
                case MAYOR_IGUAL:
                case IGUAL:*/
                case ASIGNAR:
                    SolverAritmetico solver = new SolverAritmetico();
                    solver.resolver(n);
                case DIFERENTE_DE:
                    SolverAritmetico solver2 = new SolverAritmetico(n);
                    Object res = solver2.resolver();
                    System.out.println(res);
                    break;

                case VARIABLE:
                    // Crear una variable. Usar tabla de simbolos
                    if (!TablasSimbolos.existeIdentificador(n.getHijos().get(0).getValue().lexema)){
                        if (n.getHijos().size() == 2){
                            Nodo derecho = n.getHijos().get(1);
                            SolverAritmetico solverAritmetico =  new SolverAritmetico(derecho);
                            Object res_derecho = solverAritmetico.resolver();

                            TablasSimbolos.asignar(n.getHijos().get(0).getValue().lexema, res_derecho);
                        } else{
                            TablasSimbolos.asignar(n.getHijos().get(0).getValue().lexema, null);
                        }
                    }
                    break;
                    
                    case IMPRIMIR:
                    Nodo hijo = n.getHijos().get(0);
                    SolverAritmetico solver_hijo = new SolverAritmetico(hijo);
                    Object res_hijo = solver_hijo.resolver();

                    System.out.println(res_hijo);
                    break;
                    
                    case SI:
                    // Nodo IZQ para la condicion
                    // Nodo DER para la instruccion
                    if (n.getHijos().size() == 1 || n.getHijos().get(1).getValue().tipo == Tipo_Token.ADEMAS){
                        System.err.println("Error : Falta instruccion dentro del si");
                        System.exit(1);
                    }

                    Nodo condicion = n.getHijos().get(0);
                    SolverAritmetico solverCondicion = new SolverAritmetico(condicion);
                    boolean condicionCumplida = (Boolean) solverCondicion.resolver();

                    if (condicionCumplida){
                        for (int i = 1; i < n.getHijos().size() - 1; i++){
                            Nodo auxRaiz = new Nodo(null);
                            Nodo instruccion = n.getHijos().get(i);
                            auxRaiz.insertarHijo(instruccion);
                            Arbol arbolInstruccion = new Arbol(auxRaiz);
                            arbolInstruccion.recorrer();
                        }
                    } else if (n.getHijos().get(n.getHijos().size() - 1).getValue().tipo == Tipo_Token.ADEMAS) {
                        Nodo auxRaiz = new Nodo(null);
                        Nodo sino = n.getHijos().get(n.getHijos().size() - 1);

                        auxRaiz.insertarHijo(sino);

                        Arbol arbolInstruccion = new Arbol(auxRaiz);
                        arbolInstruccion.recorrer();
                    }
                    break;
                    
                    case PARA:
                    //primero hijo para inicializacion
                    //segundo hijo para condicion
                    //tercer hijo para incremento
                    //cuarto hijo para instruccion
                    if (n.getHijos().size() == 3){
                        System.err.println("Error : Falta instruccion dentro del para");
                        System.exit(1);
                    }
                    SolverAritmetico solverPara = new SolverAritmetico();
                    Arbol arbolInstruccionPara = new Arbol();
                    Nodo auxRaizPara = new Nodo(null);

                    Nodo auxdecla = new Nodo(null);
                    Nodo declaracion = n.getHijos().get(0);
                    auxdecla.insertarHijo(declaracion);
                    Arbol arbolDeclaracion = new Arbol(auxdecla);
                    arbolDeclaracion.recorrer();

                    Nodo paracondicion = n.getHijos().get(1);
                    //SolverAritmetico solverParaCondicion = new SolverAritmetico(paracondicion);
                    boolean condicionParaCumplida = (Boolean) solverPara.resolver(paracondicion);

                    while (condicionParaCumplida) {
                        for (int i = 3; i < n.getHijos().size(); i++) {
                            Nodo instruccionPara = n.getHijos().get(i);
                            auxRaizPara.insertarHijo(instruccionPara);

                            arbolInstruccionPara.setRaiz(auxRaizPara);
                            arbolInstruccionPara.recorrer();
                            auxRaizPara.clear();
                        }

                        // Aquí agregar el código para el incremento
                        Nodo incremento = n.getHijos().get(2);
                        //SolverAritmetico solverCondicionwhile = new SolverAritmetico(incremento);
                        solverPara.resolver(incremento);
                        condicionParaCumplida = (Boolean) solverPara.resolver(paracondicion);
                    }
                    break;
                    
                    
                case MIENTRAS:
                    if (n.getHijos().size() == 1){
                        System.err.println("Error : Falta instruccion dentro del mientras");
                        System.exit(1);
                    }
                    SolverAritmetico solverMientras = new SolverAritmetico();
                    Arbol arbolInstruccionwhile = new Arbol();
                    Nodo auxRaizwhile = new Nodo(null);
                    Nodo condicionwhile = n.getHijos().get(0);
                    boolean condicionCumplidawhile = (Boolean) solverMientras.resolver(condicionwhile);

                    while (condicionCumplidawhile) {
                        for (int i = 1; i < n.getHijos().size(); i++) {
                            Nodo instruccionwhile = n.getHijos().get(i);
                            auxRaizwhile.insertarHijo(instruccionwhile);

                            arbolInstruccionwhile.setRaiz(auxRaizwhile);
                            arbolInstruccionwhile.recorrer();
                            auxRaizwhile.clear();
                        }
                        condicionCumplidawhile = (Boolean) solverMientras.resolver(condicionwhile);
                    }
                    break;

            }
        }
    }

}

