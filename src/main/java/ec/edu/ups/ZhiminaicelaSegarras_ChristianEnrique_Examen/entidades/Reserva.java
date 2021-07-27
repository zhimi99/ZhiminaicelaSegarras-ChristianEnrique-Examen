package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades;

import java.util.Date;

public class Reserva {

	private int id;
	private Date fechaReserva;
	private int numClientes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public int getNumClientes() {
		return numClientes;
	}
	public void setNumClientes(int numClientes) {
		this.numClientes = numClientes;
	}

}
