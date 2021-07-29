package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils;

public class ReservaTmp {
    private int id;
    private String cedula;
    private int numClientes;
    private String restaurante;
    private String fecha;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getNumClientes() {
        return numClientes;
    }

    public void setNumClientes(int numClientes) {
        this.numClientes = numClientes;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

	@Override
	public String toString() {
		return "ReservaTmp [id=" + id + ", cedula=" + cedula + ", numClientes=" + numClientes + ", restaurante="
				+ restaurante + ", fecha=" + fecha + ", hora=" + hora + ", getId()=" + getId() + ", getCedula()="
				+ getCedula() + ", getNumClientes()=" + getNumClientes() + ", getRestaurante()=" + getRestaurante()
				+ ", getFecha()=" + getFecha() + ", getHora()=" + getHora() + "]";
	}

}
