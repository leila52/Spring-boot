package imc.com.formulario.demo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Persona {
     @NotNull(message = "altura no puede estar vacía")
    @Min(value = 50, message = "altura debe ser mayor a 50 cm")
    private Double altura;

    @NotNull(message = "peso no puede estar vacío")
    @Min(value = 10, message = "peso debe ser mayor a 10 kg")
    private Double peso;

    @NotNull(message = "edad no puede estar vacía")
    @Min(value = 1, message = " edad debe ser mayor a 0")
    private Integer edad;

    @NotNull(message = "género es obligatorio")
    @Size(min = 1, max = 1, message = "género debe ser 'M' o 'F'")
    private String genero;

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
