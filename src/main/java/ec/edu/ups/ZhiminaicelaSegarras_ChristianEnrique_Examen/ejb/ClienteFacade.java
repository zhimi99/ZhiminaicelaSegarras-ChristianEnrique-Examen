package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Cliente;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente>{

	@PersistenceContext(unitName = "ZhiminaicelaSegarras-ChristianEnrique-ExamenPersistenceUnit")
	private EntityManager em;
	
	private Cliente cliente;
	
	public ClienteFacade() {
		super(Cliente.class);
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	public Cliente buscarClienteCedula(String cedula)throws SQLException{
		cliente = new Cliente();
		String sql = "SELECT cli FROM Cliente cli"
				+ " WHERE cli.cedula=:cedula";
		
		cliente = em.createQuery(sql, Cliente.class).setParameter("cedula", cedula).getSingleResult();
		
		return cliente;
	}
	
}
