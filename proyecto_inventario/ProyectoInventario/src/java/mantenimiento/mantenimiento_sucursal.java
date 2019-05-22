
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Sucursal;


/**
 *
 * @author victor.rosalesUSAM
 */
public class mantenimiento_sucursal {

//    Metodo para guardar
    public int guardarDatos(Sucursal sucursal) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(sucursal);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

//    Metodo para consultar todo de sucursales
    public List consultarTodosSucursal() {
        List<Sucursal> listaSucursal = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT s FROM Sucursal s");
            em.getTransaction().commit();
            listaSucursal = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaSucursal;
    }

//    Metodo para consultar datos por un id
    public Sucursal consultarSucursalporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Sucursal sucu = null;
        em.getTransaction().begin();
        try {
            sucu = em.find(Sucursal.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return sucu;
    }

//    Metodo para actualizar
   public int ActualizarDatosSucursal(Sucursal l) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(l);
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
    public Sucursal eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Sucursal sucu = null;
        try {
            sucu = em.find(Sucursal.class, id);
            em.remove(sucu);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return sucu;
    }

}
