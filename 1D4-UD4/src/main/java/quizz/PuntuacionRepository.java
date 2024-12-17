package quizz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Esta anotación de @Repository no es estrictamente necesaria pero esta bien usarla.
@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    // Aquí se agregan métodos personalizadoss de consulta si se necesitan
}

