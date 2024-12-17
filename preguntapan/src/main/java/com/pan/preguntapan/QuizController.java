package com.pan.preguntapan;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class QuizController {

    @GetMapping
    public String inicio(HttpSession session) {
        session.setAttribute("sessionData", new SessionData());
        return "inicio";
    }

    @PostMapping("/guardar-datos")
    public String guardarDatos(@RequestParam String nombre, @RequestParam int edad, HttpSession session) {
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        sessionData.setNombre(nombre);
        sessionData.setEdad(edad);
        // Redirige a la primera pregunta
        return "redirect:/pregunta1"; 
    }

    @GetMapping("/pregunta1")
    public String pregunta1() {
        return "pregunta1"; // Devuelve la vista pregunta1.html
    }

    @GetMapping("/pregunta2")
    public String pregunta2() {
        return "pregunta2"; 
    }

    @GetMapping("/pregunta3")
    public String pregunta3() {
        return "pregunta3"; 
    }
    @GetMapping("/pregunta4")
    public String pregunta4() {
        return "pregunta4"; 
    }

  
    @GetMapping("/pregunta5")
    public String pregunta5() {
        return "pregunta5"; 
    }


    @GetMapping("/pregunta6")
    public String pregunta6() {
        return "pregunta6"; 
    }


    @GetMapping("/pregunta7")
    public String pregunta7() {
        return "pregunta7"; 
    }
 


    
    @PostMapping("/guardar-respuesta/{pregunta}")
    public String guardarRespuesta(@PathVariable String pregunta, @RequestParam String value, HttpSession session) {
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        
        // Aquí se agregan puntos para el tipo de pan correspondiente
        switch (value) {
            case "bagget":
                sessionData.agregarPuntuacion("bagget", 1);
                break;
            case "croissant":
                sessionData.agregarPuntuacion("croissant", 1);
                break;
            case "briyox":
                sessionData.agregarPuntuacion("briyox", 1);
                break;
            case "pandemolde":
                sessionData.agregarPuntuacion("pandemolde", 1);
                break;
            default:
                // En caso de que no haya opción válida (debería evitarse con validación en el frontend)
                break;
        }
        
        // Siguiente pregunta
        int nextQuestion = Integer.parseInt(pregunta.replace("pregunta", "")) + 1;

        if (nextQuestion <= 7) {
            // Redirige a la siguiente pregunta
            return "redirect:/pregunta" + nextQuestion;
        } else {
            // Redirige al resultado
            return "redirect:/resultado";
        }
    }

    @GetMapping("/resultado")
    public String resultado(HttpSession session, Model model) {
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        // Determinamos el tipo de pan con más puntos
        Map<String, Integer> puntuaciones = sessionData.getPuntuaciones();
        String tipoPan = puntuaciones.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getKey();

        // Agregar el nombre del usuario y el tipo de pan al modelo
        model.addAttribute("nombre", sessionData.getNombre());
        model.addAttribute("tipoPan", tipoPan);
        model.addAttribute("puntuacionTotal", sessionData.getPuntuacionTotal()); // Agregamos la puntuación total


        // Pasar la URL de la imagen con extensión .jpg
        String imagenUrl = "/images/" + tipoPan + ".jpg"; // Ruta de la imagen con extensión .jpg
        model.addAttribute("imagenUrl", imagenUrl);

        return "resultado"; 
    }
    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        // Invalida la sesión actual
        session.invalidate(); 
        // Redirige al inicio
        return "redirect:/"; 
    }
}