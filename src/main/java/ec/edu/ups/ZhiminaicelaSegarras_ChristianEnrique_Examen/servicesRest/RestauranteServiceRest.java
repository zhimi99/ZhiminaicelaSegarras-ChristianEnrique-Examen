package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.RestauranteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils.RestauranteTmp;


@Path("/restaurante")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Stateless
public class RestauranteServiceRest {
	
	
	@Inject
	private RestauranteFacade restauranteFacade;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/guardarRestaurante")
	public String guardarRestaurante(RestauranteTmp restauranteTmp) {
		
		Restaurante restaurante = new Restaurante(restauranteTmp.getNombre(), restauranteTmp.getDireccion(), restauranteTmp.getDireccion(), restauranteTmp.getAforo(), null);

		try {

			this.restauranteFacade.create(restaurante);
			return "Restaurante:  Creado Exitosamente: codigo: "+restaurante.getId()+" \n Nombre: "+restauranteTmp.getNombre()+"., Aforo diario: "+restauranteTmp.getAforo();

		} catch (SQLException e) {

			System.out.println(e.getLocalizedMessage());
			return "error";

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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar")
	public Response buscarRestarurantePorNombre(@QueryParam("nombre") String nombre) {
		Restaurante restaurante;
		try {

			restaurante = restauranteFacade.buscarRestauranteNombre(nombre);
			if (restaurante != null) {
				return Response.ok(restaurante).build();
			} else {
				return Response.ok(null).build();
			}

		} catch (SQLException e) {

			System.out.println("Error al buscar restaurante....... "+e.getLocalizedMessage());

			return Response.serverError().build();
		}
	}
	
	
}
