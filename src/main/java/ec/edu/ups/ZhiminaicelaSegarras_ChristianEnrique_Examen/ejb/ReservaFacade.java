package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Reserva;

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
		
		String sql = "SELECT res FROM Reserva res, Cliente cli "
				+ "WHERE cli.cedula=:cedula AND res.cliente=cli.id";
		
		lista = em.createQuery(sql, Reserva.class).setParameter("cedula", cedula).getResultList();
		
		return lista;
	}
	
	public List<Reserva> listarReservaRestaurantes(String nombre, Date fecha)throws SQLException {
		List<Reserva> lista = new ArrayList<Reserva>();
		
		String sql = "SELECT rev FROM Reserva rev, Restaurante res "
				+ "WHERE res.nombre =:nombre AND rev.fecha=:fecha";
		
		lista = em.createQuery(sql, Reserva.class)
				.setParameter("nombre", nombre)
				.setParameter("fecha", fecha)
				.getResultList();
		
		return lista;
	}
	
	
	public int comprobarAforoRestaurante(String nombre, Date fecha, Date hora) {
		List<Reserva> lista = new ArrayList<Reserva>();
		int llenarAforo = 0;
		String sql = "SELECT res FROM Reserva res, Restaurante ret"
				+ " WHERE ret.nombre=:nombre AND res.restaurante=ret.id AND res.fecha=:fecha "
				+ "AND res.hora=:hora";
		lista = em.createQuery(sql, Reserva.class).setParameter("nombre", nombre).setParameter("fecha", fecha).setParameter("hora", hora).getResultList();
		
		for (Reserva reserva : lista) {
			llenarAforo = llenarAforo + reserva.getNumClientes();
		}
		
		return llenarAforo;
	}
	

}
