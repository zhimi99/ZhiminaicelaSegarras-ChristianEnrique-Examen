package ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.ejb;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ec.edu.ups.ZhiminaicelaSegarras_ChristianEnrique_Examen.entidades.Restaurante;

@Stateless
public class RestauranteFacade  extends AbstractFacade<Restaurante>{

	@PersistenceContext(unitName = "ZhiminaicelaSegarras-ChristianEnrique-ExamenPersistenceUnit")
	private EntityManager em;
	
	private Restaurante restaurante;
	
	public RestauranteFacade() {
		super(Restaurante.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Restaurante buscarRestauranteNombre(String nombre)throws SQLException{
		restaurante = new Restaurante();
		String sql = "SELECT res FROM Restaurante res "
				+ "WHERE res.nombre =:nombre";
		System.out.println(sql+" "+nombre);
		try {
			restaurante = em.createQuery(sql, Restaurante.class).setParameter("nombre", nombre).getSingleResult();
		} catch (NoResultException e) {
			restaurante = null;
		}
		
		return restaurante;
	}

}
