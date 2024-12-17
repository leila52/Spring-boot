package imc.com.formulario.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
public class IndiceMasaCorporalConttoller {
    @GetMapping("/")
    public ModelAndView mostrarFormulario(Persona persona) {
        return new ModelAndView("formulario");
    }

    @PostMapping("/")
    public ModelAndView calcularIndices(@Valid Persona persona, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("formulario");
            return modelAndView;
        }

        // Calcular IMC
        // Convertir a metros
        double alturaM = persona.getAltura() / 100.0; 
        double imc = persona.getPeso() / (alturaM * alturaM);

        // Calcular IGC
        double igc;
        //masculino
        if (persona.getGenero().equalsIgnoreCase("M")) {
            igc = (1.20 * imc) + (0.23 * persona.getEdad()) - 16.2;
        } 
        //femenino
        else {
            igc = (1.20 * imc) + (0.23 * persona.getEdad()) - 5.4;
        }

        // Clasificación IMC
        String clasificacionIMC=clasificacionIMC( imc);

        // Clasificación IGC
        String clasificacionIGC = clasificarIGC(persona.getGenero(), igc);

        // Agregar resultados al modelo
        modelAndView.addObject("imc", imc);
        modelAndView.addObject("igc", igc);
        modelAndView.addObject("clasificacionIMC", clasificacionIMC);
        modelAndView.addObject("clasificacionIGC", clasificacionIGC);
        modelAndView.setViewName("resultado");
        return modelAndView;
    }
    private String clasificacionIMC(double imc){
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
           return"Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    private String clasificarIGC(String genero, double igc) {
        if (genero.equalsIgnoreCase("M")) {
            if (igc < 6) {
                return "Esencial";
            } else if (igc < 14) {
                return "Atleta";
            } else if (igc < 18) {
                return "Fitness";
            } else if (igc < 25) {
                return "Promedio";
            } else {
                return "Obesidad";
            }
        } else {
            if (igc < 14) {
                return "Esencial";
            } else if (igc < 21) {
                return "Atleta";
            } else if (igc < 25) {
                return "Fitness";
            } else if (igc < 32) {
                return "Promedio";
            } else {
                return "Obesidad";
            }
        }
    }

}
