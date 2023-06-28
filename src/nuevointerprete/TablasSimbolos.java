package nuevointerprete;

import java.util.HashMap;
import java.util.Map;

public class TablasSimbolos {

    private final static Map<String, Object> values = new HashMap<>();

    static boolean existeIdentificador(String identificador)
    {
        return values.containsKey(identificador);
    }

    static Object obtener(String identificador)
    {
        if (values.containsKey(identificador))
        {
            return values.get(identificador);
        }
        throw new RuntimeException("Variable no definida '" + identificador + "'.");
    }

    static void asignar(String identificador, Object valor)
    {
        values.put(identificador, valor);
    }


}