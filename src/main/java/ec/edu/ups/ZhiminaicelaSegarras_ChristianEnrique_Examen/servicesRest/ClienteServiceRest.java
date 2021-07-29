package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.ClienteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils.ClienteTmp;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils.claseTmp;


@Path("/cliente")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Stateless
public class ClienteServiceRest {
	
	
	@Inject
	private ClienteFacade clienteFacade;
	
	private claseTmp claseTmp;
	
	
	@GET
	@Path("/buscarCedula")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardarCliente(ClienteTmp clienteTmp) {
		
		claseTmp = new claseTmp();
		
		System.out.println(clienteTmp.toString());
		Cliente cliente = new Cliente(
				clienteTmp.getNombre(),
				clienteTmp.getApellido(),
				clienteTmp.getCedula(),
				clienteTmp.getCorreo(),
				clienteTmp.getDireccion(),
				clienteTmp.getTelefono());
		System.out.println(cliente.toString());
		
		try {
			clienteFacade.create(cliente);
			
			
			claseTmp.setMensaje("Datos Guardados Exitosamente");
			claseTmp.setEstado(1);

			return Response.ok(claseTmp).build();
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getLocalizedMessage());
			return Response.serverError().build();
		}
	}
	
}
