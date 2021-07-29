package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils;

public class RestauranteTmp {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private int aforo;

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

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
    
    
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "RestauranteTmp [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", aforo=" + aforo + ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDireccion()="
				+ getDireccion() + ", getAforo()=" + getAforo() + ", getTelefono()=" + getTelefono() + "]";
	}

}
