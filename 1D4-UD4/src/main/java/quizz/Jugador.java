package quizz;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="JUGADOR")
public class Jugador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_JUGADOR")
	private Long idJugador;
	
	@OneToMany(
	mappedBy ="jugador",
	cascade = CascadeType.ALL,
	orphanRemoval=true)
	private Set<Puntuacion> puntuaciones=new HashSet<>();
	
	private String nombre;
	
	//getters y setters 
	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	public boolean anadirPuntuacion(Puntuacion puntuacion) {
		puntuacion.setJugador(this);
		return getPuntuaciones().add(puntuacion);
	}
	public void eliminarPuntuacion(Puntuacion puntuacion) {
		getPuntuaciones().remove(puntuacion);
	}
	
	
}
