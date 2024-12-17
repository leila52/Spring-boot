package com.pan.preguntapan;

import java.util.HashMap;
import java.util.Map;

public class SessionData {
    private String nombre;
    private int edad;

    private Map<String, Integer> puntuaciones; // Para almacenar las puntuaciones de cada tipo de pan
    private int puntuacionTotal; // Para almacenar la puntuación total

    public SessionData() {
        // Inicializa los valores
        this.puntuaciones = new HashMap<>();
        this.puntuacionTotal = 0;
    }

    // Método para agregar puntuación
    public void agregarPuntuacion(String tipoPan, int puntos) {
        puntuaciones.put(tipoPan, puntuaciones.getOrDefault(tipoPan, 0) + puntos);
        this.puntuacionTotal += puntos; // Incrementa la puntuación total
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    // Métodos getter y setter para nombre, edad, y puntuaciones
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
}