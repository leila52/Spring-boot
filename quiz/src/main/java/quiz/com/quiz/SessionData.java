package quiz.com.quiz;

import java.util.HashMap;
import java.util.Map;

public class SessionData {
    private String nombre;
    private int edad;
    private Map<String, Integer> puntuaciones;

    public SessionData() {
        this.puntuaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Map<String, Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public void agregarPuntuacion(String pregunta, int puntuacion) {
        puntuaciones.put(pregunta, puntuacion);
    }

    public int getTotalPuntuacion() {
        return puntuaciones.values().stream().mapToInt(Integer::intValue).sum();
    }
}
