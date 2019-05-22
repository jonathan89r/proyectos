
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Login;


/**
 *
 * @author jose.murciaUSAM
 */
public class mantenimiento_recuperarcontra {

    EntityManager em;

 

    public Login verificar(String user, String respuesta, String pregunta) {

        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Query q = em.createNamedQuery("Login.user_respuesta", Login.class).setParameter("user", user).setParameter("respuesta", respuesta).setParameter("pregunta", pregunta);
        List<Login> lista = q.getResultList();
        
        if (!lista.isEmpty()) {
           
                return lista.get(0);         
        } 
        return null;

    }

    public boolean actualizarcontra(int id_usuario, String pass) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Login uc = em.find(Login.class, id_usuario);
            uc.setPass(pass);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                em.getTransaction().rollback();

            }
            throw new ExceptionInInitializerError(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}