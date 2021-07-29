package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Restaurante")
public class Restaurante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "restaurante_id", updatable = false, nullable = false, unique = true)
	private int id;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private int aforo;
	
	@Transient
	private List<Reserva> reserva;
	
	
	public Restaurante() {
		// TODO Auto-generated constructor stub
	}
	
	public Restaurante(String nombre, String direccion, String telefono, int aforo, List<Reserva> reserva) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.aforo = aforo;
		this.reserva = reserva;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", aforo=" + aforo + ", reserva=" + reserva + "]";
	}
	
}
