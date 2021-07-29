package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.servicesRest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.ReservaFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb.RestauranteFacade;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Reserva;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils.ReservaTmp;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.utils.claseTmp;


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
	
	private claseTmp  claseTmp;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/guardarReserva")
	public Response guardarReserva(ReservaTmp reservaTmp) {
		
		Cliente cliente = new Cliente();
		Restaurante restaurante = new Restaurante();
		Reserva reserva = new Reserva();
		claseTmp = new claseTmp();
		
		int aforoDisponible = 0;
		int aforoUsado = 0;
		
		try {
			
			cliente = this.clienteFacade.buscarClienteCedula(reservaTmp.getCedula());
			
			if(cliente.equals(cliente)) {
				
				restaurante = this.restauranteFacade.buscarRestauranteNombre(reservaTmp.getRestaurante().getNombre());
				//List<Reserva> reservaList =  reservaFacade.reservasPorFechaHoraRestaurante(reserva.getFechaReserva(), reserva.getHora(), restaurante);
				
				//if(reservaList.size() > 0) {
					
				//	for(int i=0; i<reservaList.size(); i++) {
					
				//		reservaList.get(i).getNumClientes();
						
						//implemnetacion para  fecha hora  comprobar aforo en e sa fecha 
						//help mee..
				//	}
				//}
				
				
				if(restaurante.equals(restaurante)) {
					aforoUsado = this.reservaFacade.comprobarAforoRestaurante(reservaTmp.getRestaurante().getNombre(), reservaTmp.getFecha(), reservaTmp.getHora());
					aforoDisponible  = restaurante.getAforo() - aforoUsado;
					
					if(aforoDisponible > reservaTmp.getNumClientes() ) {
						reserva = new Reserva(reservaTmp.getFecha(), reservaTmp.getHora(), cliente, reservaTmp.getNumClientes(), restaurante);
						this.reservaFacade.create(reserva);
						claseTmp.setMensaje("Reserva Guardada");
						claseTmp.setEstado(1);
						
						return Response.ok(claseTmp).build();
						
					} else {
						claseTmp.setMensaje("El Restaurante esta lleno");
						claseTmp.setEstado(2);
						return Response.ok(claseTmp).build();
					}
					
				} else {
					claseTmp.setMensaje("El Restaurante no existe");
					claseTmp.setEstado(3);
					return Response.ok(claseTmp).build();
				}
				
			}else {
				claseTmp.setMensaje("El Cliente no Existe");
				claseTmp.setEstado(4);
				return Response.ok(claseTmp).build();
			}
			
		} catch (Exception e) {
			//claseTmp.setMensaje("El Restaurante esta lleno");
			//claseTmp.setEstado(0);
			System.out.println("Error de reserva.... " + e);
			return Response.serverError().build();
		}
	}
		
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarReservasRestaurante")
	public Response listReservaRestaurante(@QueryParam("nombre") String nombre,
			@QueryParam("fecha") String fecha) {
		
		System.out.println("/listarReservasRestaurante...." +nombre+"  " + fecha);
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
			for (Reserva r: lista) {
				System.out.print(r.getRestaurante().getNombre());
			}
			return Response.ok(lista).build();
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			
			return Response.serverError().build();
		}
	}

//	public Mensaje nuevaReserva(ReservaHelper reservaHelper) {
//		Mensaje mensaje = new Mensaje();
//		cliente = clienteOn.buscarPorCedula(reservaHelper.getCliente().getCedula());
//		restaurante = restauranteOn.buscarPorId(reservaHelper.getRestaurante().getId());
//		if (cliente != null && restaurante != null) {
//			reserva = new Reserva();
//			String[] horas = reservaHelper.getHora().split(":");
//			String hora = horas[0] + ":" + horas[1];
//			reserva.setHora(hora);
//			reserva.setCliente(cliente);
//			reserva.setRestaurante(restaurante);
//			reserva.setCantidadPersonas(reservaHelper.getCantidadPersonas());
//			reserva.setFecha(reservaHelper.getFecha());
//			// validar si hay disponibilidad de aforo para la fecha y hora
//			List<Reserva> reservas = reservasPorFechaHoraRestaurante(reserva.getFecha(), hora, reserva.getRestaurante());
//			if (reservas.size() > 0) {
//				boolean existe = reservaDao.verificarReservaPorFechaHoraRestauranteCedula(reserva.getFecha(), hora, reserva.getRestaurante(), cliente.getCedula());
//				if (existe) {
//					mensaje.setMensaje("El cliente: "+cliente.getNombres() +" "+cliente.getApellidos()+ " ya tiene una reserva para esa fecha y hora");
//					mensaje.setEstado(false);
//				} else {
//					int aforoTotalRestaurante = reserva.getRestaurante().getAforo();
//					int aforoActualRestaurante = 0;
//					for (Reserva r : reservas) {
//						aforoActualRestaurante += r.getCantidadPersonas();
//					}
//					int nuevoAforoReserva = 0;
//					if (aforoActualRestaurante <= aforoTotalRestaurante) {
//						nuevoAforoReserva = aforoActualRestaurante + reserva.getCantidadPersonas();
//						if (nuevoAforoReserva <= aforoTotalRestaurante) {
//							cliente.agregarReserva(reserva);
//							restaurante.agregarReserva(reserva);
//							reservaDao.nuevaReserva(reserva);
//							mensaje.setMensaje("Reserva creada correctamente");
//							mensaje.setEstado(true);
//						} else {
//							int disponibilidad = aforoTotalRestaurante - aforoActualRestaurante;
//							mensaje.setMensaje("Disponemos unicamente para " + disponibilidad + " personas actualmente");
//							mensaje.setEstado(false);
//						}
//					} else {
//						mensaje.setMensaje("Aforo esta al maximo, seleccione otra fecha");
//						mensaje.setEstado(false);
//					}
//				}
//			} else {
//				int aforoTotalRestaurante = reserva.getRestaurante().getAforo();
//				int reservaNumeroPersonas = reserva.getCantidadPersonas();
//				if (reservaNumeroPersonas <= aforoTotalRestaurante) {
//					cliente.agregarReserva(reserva);
//					restaurante.agregarReserva(reserva);
//					reservaDao.nuevaReserva(reserva);
//					mensaje.setMensaje("Reserva creada correctamente");
//					mensaje.setEstado(true);
//				} else {
//					mensaje.setMensaje("Disponemos unicamente para " + aforoTotalRestaurante + " personas actualmente");
//					mensaje.setEstado(false);
//				}
//
//			}
//		}
//		return mensaje;
//	}
}
