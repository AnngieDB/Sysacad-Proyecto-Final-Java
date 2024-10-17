package Consultas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static ControlArchivos.manejoArchivos.*;

public final class consultaArchivo {

    public static boolean buscarClave(String fileName, String dato, String buscado) {

        boolean flag = false;

        try {

            JSONArray arreglo = new JSONArray(leerArchivoJSON(fileName));

            for (int i = 0; i < arreglo.length(); i++) {

                JSONObject obj = arreglo.getJSONObject(i);

                if (obj.getString(buscado).equals(dato)) {

                    flag = true;

                }

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    //metodo prueba para buscar un nombre en un archivo JSON
    public static String buscarNombre(String fileName, String legajo) {

        String nombre = "";

        try {

            JSONArray arreglo = new JSONArray(leerArchivoJSON(fileName));

            for (int i = 0; i < arreglo.length(); i++) {

                JSONObject obj = arreglo.getJSONObject(i);

                if (obj.getString("legajo").equals(legajo)) {

                    nombre = obj.getString("nombre");

                }

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return nombre;

    }

}

