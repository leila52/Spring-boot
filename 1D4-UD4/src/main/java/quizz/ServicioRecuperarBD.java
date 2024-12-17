package quizz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRecuperarBD {
    @Autowired
    private PuntuacionRepository puntuacionRepository;

    public List<NombrePuntuacion> recuperarBaseDatosPuntuaciones() {
        List<Puntuacion> puntuaciones = puntuacionRepository.findAll();
        List<NombrePuntuacion> datosPuntuaciones = new ArrayList<>();

        for (Puntuacion puntuacion : puntuaciones) {
            NombrePuntuacion nombrePuntuacion = new NombrePuntuacion();
            nombrePuntuacion.setNombre(puntuacion.getJugador().getNombre());
            nombrePuntuacion.setPuntuacion(puntuacion.getPuntuacion());
            datosPuntuaciones.add(nombrePuntuacion);
        }

        return datosPuntuaciones;
    }
}

