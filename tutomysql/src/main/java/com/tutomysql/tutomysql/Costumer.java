package com.tutomysql.tutomysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;

  protected Costumer() {}

  public Costumer(String nombre, String apellido) {
    this.nombre = nombre;
    this.apellido = apellido;
  }

    @Override
    public String toString() {
        return String.format(
                "Costumer[id=%d, nombre='%s', apellido='%s']",
                id, nombre, apellido);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    } 
}
