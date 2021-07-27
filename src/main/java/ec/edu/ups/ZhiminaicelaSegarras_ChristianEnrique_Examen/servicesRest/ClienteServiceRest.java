package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.ClienteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;


@Path("/cliente")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Stateless
public class ClienteServiceRest {
	
	
	@Inject
	private ClienteFacade clienteFacade;
	
	
	@GET
	@Path("/buscarCedula")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Cliente buscarClienteCedula(@QueryParam("cedula") String cedula) {
		try {
			return clienteFacade.buscarClienteCedula(cedula);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@POST
	@Path("/guardarCliente")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardarCliente(@FormParam("id") int id, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido,
			@FormParam("cedula") String cedula, @FormParam("correo")String correo,
			@FormParam("direccion")String direccion, @FormParam("telefono") String telefono) {
		
		Cliente cliente = new Cliente(id, nombre, apellido, cedula, correo,direccion, telefono);
		System.out.println(cliente.toString());
		
		try {
			clienteFacade.create(cliente);
			
			return Response.ok("Datos Guardados Exitosamente").build();
			
		} catch (SQLException e) {
			
			System.out.println(e.getLocalizedMessage());
			return Response.ok("Datos No Guardados...Error..").build();
		}
	}
	
}
