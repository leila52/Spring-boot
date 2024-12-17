package quizz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {
   Optional<Jugador> findByNombre(String nombre);
   
}
