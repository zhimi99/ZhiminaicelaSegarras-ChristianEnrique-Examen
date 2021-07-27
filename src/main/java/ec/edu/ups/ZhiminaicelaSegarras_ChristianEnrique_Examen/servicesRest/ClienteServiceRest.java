package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.ClienteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;


@Path("/cliente")
public class ClienteServiceRest {
	
	
	@Inject
	private ClienteFacade clienteFacade;
	
	
	@GET
	@Path("buscarCedula")
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
}
