package Control.InicioSesion;

public final class inicioSesionData {

    private static String legajo;

    public static String getLegajo() {
        return legajo;
    }

    public static void setLegajo(String legajo) {
        inicioSesionData.legajo = legajo;
    }
}