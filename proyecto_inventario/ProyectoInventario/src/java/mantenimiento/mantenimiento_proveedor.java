
package mantenimiento;




import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Proveedor;


/**
 *
 * @author victor.rosalesUSAM
 */
public class mantenimiento_proveedor{

//    Metodo para guardar
    public int guardarDatos(Proveedor proveedor) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(proveedor);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }
//    Metodo para consultar todo
    public List consultarTodosProveedor() {
        List<Proveedor> listaProveedor = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM Proveedor p");
            em.getTransaction().commit();
            listaProveedor = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaProveedor;
    }

//    Metodo para consultar por un id
    public Proveedor consultarProveedorporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Proveedor pro = null;
        em.getTransaction().begin();
        try {
            pro = em.find(Proveedor.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return pro;
    }

//    Metodo para actualizar 
   public int ActualizarDatosProveedor(Proveedor p) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(p);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

//   Metodo para eliminar
    public Proveedor eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Proveedor pro = null;
        try {
            pro = em.find(Proveedor.class, id);
            em.remove(pro);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return pro;
    }

}