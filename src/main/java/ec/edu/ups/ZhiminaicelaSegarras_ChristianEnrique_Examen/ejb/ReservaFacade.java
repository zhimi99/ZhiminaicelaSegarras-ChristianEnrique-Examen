package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Reserva;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;

@Stateless
public class ReservaFacade extends AbstractFacade<Reserva>{
	
	@PersistenceContext(unitName = "ZhiminaicelaSegarras-ChristianEnrique-ExamenPersistenceUnit")
	private EntityManager em;
	
	public ReservaFacade() {
		super(Reserva.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public List<Reserva> listarReservaClientes(String cedula)throws SQLException {
		List<Reserva> lista = new ArrayList<Reserva>();
		
		String sql = "SELECT res FROM Reserva res, Cliente cli, Restaurante r "
				+ "WHERE cli.cedula=:cedula AND res.cliente=cli.id AND  res.restaurante =r.id" ;
		
		lista = em.createQuery(sql, Reserva.class).setParameter("cedula", cedula).getResultList();
		
		return lista;
	}
	
	public List<Reserva> listarReservaRestaurantes(String nombre, String fecha)throws SQLException {
		List<Reserva> lista = new ArrayList<Reserva>();
		
		String sql = "SELECT rev FROM Reserva rev, Restaurante res, Cliente cli "
				+ "WHERE res.nombre =:nombre AND rev.fechaReserva=:fecha AND rev.restaurante=res.id AND rev.cliente=cli.id" ;
		
		lista = em.createQuery(sql, Reserva.class)
				.setParameter("nombre", nombre)
				.setParameter("fecha", fecha)
				.getResultList();
		
		return lista;
	}
	
	
	public int comprobarAforoRestaurante(String nombre, String fecha, String hora) {
		List<Reserva> lista = new ArrayList<Reserva>();
		int llenarAforo = 0;
		String sql = "SELECT res FROM Reserva res, Restaurante ret"
				+ " WHERE ret.nombre=:nombre AND res.restaurante=ret.id AND res.fechaReserva=:fecha "
				+ "AND res.hora=:hora";
		lista = em.createQuery(sql, Reserva.class).setParameter("nombre", nombre).setParameter("fecha", fecha).setParameter("hora", hora).getResultList();
		
		for (Reserva reserva : lista) {
			llenarAforo = llenarAforo + reserva.getNumClientes();
		}
		
		return llenarAforo;
	}

	// saca las reservas de uan fecha y hora de un restaurasnte
	public List<Reserva> reservasPorFechaHoraRestaurante(String fecha, String hora, Restaurante restaurante) {
		String jpql = "Select r From Reserva r where r.fechaReserva = :fecha and r.hora = :hora and r.restaurante = :restaurante";
		Query query = em.createQuery(jpql, Reserva.class);
		query.setParameter("fechaReserva", fecha);
		query.setParameter("hora", hora);
		query.setParameter("restaurante", restaurante);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}

	// valida si el cliente ya tiene una reserva para esa fecha y a esa hora en un restaurante
	public boolean verificarReservaPorFechaHoraRestauranteCedula(String fecha, String hora, Restaurante restaurante, String cedula) {
		String jpql = "Select r From Reserva r where r.fechaReserva = :fecha and r.hora = :hora and r.restaurante = :restaurante and r.cliente.cedula = :cedula";
		Query query = em.createQuery(jpql, Reserva.class);
		query.setParameter("fechaReserva", fecha);
		query.setParameter("hora", hora);
		query.setParameter("restaurante", restaurante);
		query.setParameter("cedula", cedula);
		List<Reserva> reservas = query.getResultList();
		return reservas.size() > 0? true: false;
	}

}
