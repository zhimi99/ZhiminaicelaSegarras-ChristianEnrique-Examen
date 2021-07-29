package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "reserva_id", updatable = false, nullable = false, unique = true)
	private int id;
	
	private String fechaReserva;
	
	private String hora;
	
	private int numClientes;
	
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "reserva_cliente_id")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "reserva_restaurante_id")
	private Restaurante restaurante;
	
	
	public Reserva() {
		// TODO Auto-generated constructor stub
	}
	
	public Reserva(String fechaReserva, String hora,  Cliente cliente, int numClientes ,Restaurante restaurante) {
		this.fechaReserva = fechaReserva;
		this.hora =  hora;
		this.cliente = cliente;
		this.numClientes = numClientes;
		this.restaurante = restaurante;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getNumClientes() {
		return numClientes;
	}
	public void setNumClientes(int numClientes) {
		this.numClientes = numClientes;
	}

	
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaReserva=" + fechaReserva + ", hora=" + hora + ", numClientes="
				+ numClientes + ", cliente=" + cliente + ", restaurante=" + restaurante + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
