package quizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorWeb {

	// Serviciosss
	@Autowired
	private ServicioAñadirBD servicioAñadirBD;
	@Autowired
	private ServicioRecuperarBD servicioRecuperarBD;

//	-------------------------------------------

	@GetMapping("/")
	public String mostrarPaginaDeInicio(HttpSession session) {
		// Reinicia las variables de sesión
		session.setAttribute("nombre", "");
		session.setAttribute("puntosAgua", 0);
		session.setAttribute("puntosAire", 0);
		session.setAttribute("puntosTierra", 0);
		session.setAttribute("puntosFuego", 0);

		return "inicio";
	}

	@PostMapping("/pregunta1")
	public String mostrarPreguntas() {
		return "pregunta1";
	}

	@PostMapping("/pregunta2")
	public String procesarPregunta1(@RequestParam(name = "nombre", required = false) String nombre,
			HttpSession session) {
		if (nombre.equals("")) {
			return "pregunta1";
		}
		// Guarda el nombre en la variable de sesión
		session.setAttribute("nombre", nombre);

		// Redirige a la siguiente pregunta
		return "pregunta2";
	}

	private boolean actualizarPuntos(String opcion, HttpSession session) {
		boolean booleano = true;
		if ("aire".equals(opcion)) {
			session.setAttribute("puntosAire", ((int) session.getAttribute("puntosAire")) + 1);
		} else if ("fuego".equals(opcion)) {
			session.setAttribute("puntosFuego", ((int) session.getAttribute("puntosFuego")) + 1);
		} else if ("agua".equals(opcion)) {
			session.setAttribute("puntosAgua", ((int) session.getAttribute("puntosAgua")) + 1);
		} else if ("tierra".equals(opcion)) {
			session.setAttribute("puntosTierra", ((int) session.getAttribute("puntosTierra")) + 1);
		} else {
			// Opción de no reconocida devuelve false
			booleano = false;
		}
		return booleano;
	}

	@PostMapping("/pregunta3")
	public String procesarRespuestasPregunta2(
			@RequestParam(name = "musica", required = false) String[] respuestasMusica, HttpSession session) {
		// Procesa las respuestas de la pregunta 2 y actualiza los puntos en la sesión
		if (respuestasMusica != null) {
			for (String respuesta : respuestasMusica) {
				// En caso de opción de no reconocida redirige al inicio
				if (!actualizarPuntos(respuesta, session)) {
					return "inicio";
				}
					
			}
		} else {
			// En caso de no rellenar ningun checkbox
			// Redirige a la misma pregunta
			return "pregunta2";
		}
		// Redirige a la siguiente pregunta
		return "pregunta3";
	}

	@PostMapping("/pregunta4")
	public String procesarRespuestaPregunta3(@RequestParam(name = "preguntaButton", required = false) String respuesta,
			HttpSession session) {
		// Verifica si la respuesta no es nula
		if (respuesta != null) {
			// En caso de opción de no reconocida redirige al inicio
			if (!actualizarPuntos(respuesta, session))
				return "inicio";
		} else {
			// Manejo de caso en el que la respuesta es nula
			// Redirige a la misma pregunta
			return "pregunta3";
		}

		// Redirige a la siguiente pregunta
		return "pregunta4";
	}

	@PostMapping("/pregunta5")
	public String procesarPregunta4(@RequestParam(name = "destino", required = false) String respuesta,
			HttpSession session) {
		// Verifica si la respuesta no es nula
		if (respuesta != null) {
			// En caso de opción de no reconocida redirige al inicio
			if (!actualizarPuntos(respuesta, session))
				return "inicio";
		} else {
			// Manejo de caso en el que la respuesta es nula
			// Redirige a la misma pregunta
			return "pregunta4";
		}

		// Redirige a la siguiente pregunta
		return "pregunta5";

	}

	@PostMapping("/pregunta6")
	public String procesarPregunta5(@RequestParam(name = "estacion", required = false) String respuesta,
			HttpSession session) {
		// Verifica si la respuesta no es nula
		if (respuesta != null) {
			// En caso de opción de no reconocida redirige al inicio
			if (!actualizarPuntos(respuesta, session))
				return "inicio";
		} else {
			// Manejo de caso en el que la respuesta es nula
			// Redirige a la misma pregunta
			return "pregunta5";
		}

		// Redirige a la siguiente pregunta
		return "pregunta6";

	}

	@PostMapping("/pregunta7")
	public String procesarPregunta6(@RequestParam(name = "clima", required = false) String respuesta,
			HttpSession session) {
		// Verifica si la respuesta no es nula
		if (respuesta != null) {
			// En caso de opción de no reconocida redirige al inicio
			if (!actualizarPuntos(respuesta, session))
				return "inicio";
		} else {
			// Manejo de caso en el que la respuesta es nula
			// Redirige a la misma pregunta
			return "pregunta6";
		}

		// Redirige a la siguiente pregunta
		return "pregunta7";

	}

	@PostMapping("/resultados")
	public String mostrarResultados(HttpSession session, Model model) {
		// Obtiene el nombre del usuario de la sesión
		String nombre = (String) session.getAttribute("nombre");

		// Obtiene los puntos de cada elemento con la sesión
		int puntosAgua = (int) session.getAttribute("puntosAgua");
		int puntosFuego = (int) session.getAttribute("puntosFuego");
		int puntosTierra = (int) session.getAttribute("puntosTierra");
		int puntosAire = (int) session.getAttribute("puntosAire");

		// Encuentra el elemento con más puntos
		String elemento = "agua";
		int maxPuntos = puntosAgua;

		if (puntosFuego > maxPuntos) {
			elemento = "fuego";
			maxPuntos = puntosFuego;
		}

		if (puntosTierra > maxPuntos) {
			elemento = "tierra";
			maxPuntos = puntosTierra;
		}

		if (puntosAire > maxPuntos) {
			elemento = "aire";
		}

//		Uso del servicioAñadirBD
		servicioAñadirBD.anadirPuntuacion(nombre, elemento);

		// Define la descripción del resultado según el elemento
		String descripcion = "";
		// Define la ruta para la imagen de fondo en el resultado según el elemento
		String rutaImagen = "";

		if ("agua".equals(elemento)) {
			descripcion = "Eres una persona emocional, intuitiva y adaptable. Te gusta la tranquilidad, la armonía y la profundidad. Te llevas bien con las personas que son sensibles, comprensivas y leales.";
			rutaImagen = "/imagenes/agua.jpg";
		} else if ("fuego".equals(elemento)) {
			descripcion = "Eres una persona energética, creativa y apasionada. Te gusta la acción, el cambio y la aventura. Te llevas bien con las personas que son divertidas, valientes y optimistas.";
			rutaImagen = "/imagenes/fuego.jpg";
		} else if ("tierra".equals(elemento)) {
			descripcion = "Eres una persona práctica, estable y materialista. Te gusta la seguridad, la comodidad y la naturaleza. Te llevas bien con las personas que son realistas, trabajadoras y responsables.";
			rutaImagen = "/imagenes/tierra.jpg";
		} else if ("aire".equals(elemento)) {
			descripcion = "Eres una persona comunicativa, inteligente y libre. Te gusta el conocimiento, la variedad y la innovación. Te llevas bien con las personas que son curiosas, abiertas y sociables.";
			rutaImagen = "/imagenes/aire.jpg";
		}

		// Agrega los atributos al modelo para la plantilla
		model.addAttribute("nombre", nombre);
		model.addAttribute("elemento", elemento);
		model.addAttribute("descripcion", descripcion);
		model.addAttribute("rutaImagen", rutaImagen);

		// Muestra la plantilla de resultados
		return "resultados";
	}

	@GetMapping("/puntuaciones")
	public String mostrarPuntuaciones(Model model) {
		// Uso del servicioRecuperarBD
		List<NombrePuntuacion> datosPuntuaciones = servicioRecuperarBD.recuperarBaseDatosPuntuaciones();

		if (datosPuntuaciones.isEmpty()) {
			// No hay datos de puntuaciones, muestra la página de error
			return "errorPuntuaciones";
		} else {
			// Hay datos de puntuaciones, muestra la tabla
			model.addAttribute("datosPuntuaciones", datosPuntuaciones);
			return "puntuaciones";
		}
	}

}
