package quizz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAñadirBD {
    @Autowired
    private JugadorRepositorio jugadorRepository;

    public void anadirPuntuacion(String nombreJugador, String puntuacion) {
        // Buscar el jugador por su nombre
        Optional<Jugador> jugadorOptional = jugadorRepository.findByNombre(nombreJugador);

        if (jugadorOptional.isPresent()) {
            Jugador jugador = jugadorOptional.get();
            
//            jugador.setNombre(nombreJugador);

            // Crear una nueva instancia de Puntuacion y establecer la relación con el jugador
            Puntuacion nuevaPuntuacion = new Puntuacion();
            nuevaPuntuacion.setPuntuacion(puntuacion);

            // Añadir la nueva puntuación a la lista de puntuaciones del jugador
            jugador.anadirPuntuacion(nuevaPuntuacion);

            // Guardar el jugador actualizado en la base de datos
            jugadorRepository.save(jugador);
        } else {
        	 Jugador jugador = new Jugador();
        	 
        	 jugador.setNombre(nombreJugador);
             // Crear una nueva instancia de Puntuacion y establecer la relación con el jugador
             Puntuacion nuevaPuntuacion = new Puntuacion();
             nuevaPuntuacion.setPuntuacion(puntuacion);
//             nuevaPuntuacion.setJugador(jugador);

             // Añadir la nueva puntuación a la lista de puntuaciones del jugador
             jugador.anadirPuntuacion(nuevaPuntuacion);

             // Guardar el jugador actualizado en la base de datos
             jugadorRepository.save(jugador);
        }
    }
}
