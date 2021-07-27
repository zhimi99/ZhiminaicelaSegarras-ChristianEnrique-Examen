package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.ReservaFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.RestauranteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Reserva;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;


@Path("/reservas")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Stateless
public class ReservaServiceRest {
	
	
	@Inject
	private ClienteFacade clienteFacade;
	
	@Inject
	private RestauranteFacade restauranteFacade;
	
	@Inject
	private ReservaFacade reservaFacade;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/guardarReserva")
	public Response guardarReserva(@FormParam("fecha") Date fecha, @FormParam("hora") Date hora, @FormParam("cedula") String cedula, @FormParam("numClientes") int numClientes, @FormParam("restaurante") String restaurant) {
		
		Cliente cliente = new Cliente();
		Restaurante restaurante = new Restaurante();
		Reserva reserva = new Reserva();
		int aforoDisponible = 0;
		int aforoUsado = 0;
		
		try {
			
			cliente = this.clienteFacade.buscarClienteCedula(cedula);
			
			if(cliente.equals(cliente)) {
				restaurante = this.restauranteFacade.buscarRestauranteNombre(restaurant);
				
				if(restaurante.equals(restaurante)) {
					aforoUsado = this.reservaFacade.comprobarAforoRestaurante(restaurant, fecha, hora);
					aforoDisponible  = restaurante.getAforo() - aforoUsado;
					
					if(aforoDisponible > numClientes ) {
						reserva = new Reserva(0, fecha, hora, cliente, numClientes, restaurante);
						this.reservaFacade.create(reserva);
						
						return Response.ok("Reserva Guardada").build();
						
					} else {
						return Response.ok("El Restaurante esta lleno").build();
					}
					
				} else {
					return Response.ok("El RESTAURANTE NO EXISTE").build();
				}
				
			}else {
				return Response.ok("El Cliente no Existe, ").build();
			}
			
		} catch (Exception e) {
			System.out.println("Error de reserva.... " + e);
			return Response.serverError().build();
		}
	}
		
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarReservasRestaurante")
	public Response listReservaRestaurante(@QueryParam("nombre") String nombre,
			@QueryParam("fecha") Date fecha) {
		
		List<Reserva> lista = new ArrayList<Reserva>();
		
		try {
			lista = this.reservaFacade.listarReservaRestaurantes(nombre, fecha);
			
			return Response.ok(lista).build();
			
		} catch (SQLException e) {
			
			System.out.println("Error listar restaurantes.... " + e.getLocalizedMessage());
			
			return Response.serverError().build();
		}
	}
	
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarReservaCliente")
	public Response listReservaCliente(@QueryParam("cedula")String cedula) {
		
		List<Reserva>lista = new ArrayList<Reserva>();
		
		try {
			lista = this.reservaFacade.listarReservaClientes(cedula);
			
			return Response.ok(lista).build();
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			
			return Response.serverError().build();
		}
	}	
	
}
