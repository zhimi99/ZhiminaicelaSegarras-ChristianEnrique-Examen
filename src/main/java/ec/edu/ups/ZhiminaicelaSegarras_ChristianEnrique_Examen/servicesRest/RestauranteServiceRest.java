package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.RestauranteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;


@Path("/restaurante")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Stateless
public class RestauranteServiceRest {
	
	
	@Inject
	private RestauranteFacade restauranteFacade;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/guardarRestaurante")
	public Response guardarRestaurante(@FormParam("nombre")String nombre, @FormParam("direccion") String direccion,
			@FormParam("telefono")String telefono, @FormParam("aforo") int aforo) {
		
		Restaurante restaurante = new Restaurante(0, nombre, direccion, telefono, aforo, null);
		
		try {
			
			this.restauranteFacade.create(restaurante);
			return Response.ok("Restaurante Creado Exitosamente: codigo: "+restaurante.getId()+" \n Nombre: "+nombre+"., Aforo diario: "+aforo).build();
		
		} catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage());
			return Response.serverError().build();
		}
		
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarRestaurante")
	public Response listarRestaurante() {
		List<Restaurante> lista = new ArrayList<Restaurante>();
		try {
			
			lista = restauranteFacade.findAll();
			
			return Response.ok(lista).build();
			
		} catch (SQLException e) {
			
			System.out.println("Error al listar Restaurantes....... "+e.getLocalizedMessage());
			
			return Response.serverError().build();
		}
	}
	
	
}
