package quizz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUNTUACION")
public class Puntuacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPuntuacion;

	@ManyToOne
	@JoinColumn(name = "ID_JUGADOR")
	private Jugador jugador;

	private String puntuacion;

//	getters y setters
	public long getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(long idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
