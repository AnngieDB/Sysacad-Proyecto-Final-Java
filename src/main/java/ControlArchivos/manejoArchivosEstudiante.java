package ControlArchivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static ControlArchivos.manejoArchivos.leerArchivoJSON;

public class manejoArchivosEstudiante {

    /**
     * Metodo que compara un DNI de un estudiante, devuelve false si no lo encontró, devuelve true si lo encontró
     * @param filePath
     * @param estudiante
     * @return
     */
    public static boolean compararEstudianteDNICarrera(String filePath, JSONObject estudiante) {

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONArray estudiantesArray = new JSONArray(tokener);

            String dniEstudiante = estudiante.getString("dni");
            String codigoCarreraEstudiante = estudiante.getString("codigoCarrera");

            for (int i = 0; i < estudiantesArray.length(); i++) {
                JSONObject estudianteExistente = estudiantesArray.getJSONObject(i);
                String dniExistente = estudianteExistente.getString("dni");
                String codigoCarreraExistente = estudianteExistente.getString("codigoCarrera");

                // Verificar si el DNI y el código de carrera coinciden
                if (dniEstudiante.equals(dniExistente) && codigoCarreraEstudiante.equals(codigoCarreraExistente)) {
                    return true; // Coincidencia encontrada
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
        }

        return false;
    }


    public static JSONObject retornarEstudiante(String legajo,  String fileName)
    {
        JSONArray arreglo = new JSONArray(leerArchivoJSON(fileName));

        boolean encontrado = false;

        int i = 0;

        while(i<arreglo.length() && !encontrado)
        {
            JSONObject jsonObject = arreglo.getJSONObject(i);

            if(jsonObject.getString("legajo").equals(legajo))
            {
                encontrado = true;
            }

            i++;
        }
        if(encontrado)
        {
            return arreglo.getJSONObject((i-1));
        }

        return null;
    }


}
