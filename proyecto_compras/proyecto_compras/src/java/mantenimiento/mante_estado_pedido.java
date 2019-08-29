package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabEstado;
import persistencia.TabEstadoPedido;

/**
 *
 * @author sergio.torresusam
 */
public class mante_estado_pedido {

    public int guardar(TabEstadoPedido esped) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(esped);
            em.getTransaction().commit();
            flag = 1;

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

    public List mostrarEsped() {
        List<TabEstadoPedido> listesped = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabEstadoPedido t");
            listesped = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listesped;
    }

    public List mostrarEstado() {
        List<TabEstado> listestado = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabEstado t");
            listestado = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listestado;
    }

    public int actualizar(TabEstadoPedido esped) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(esped);
            em.getTransaction().commit();
            flag = 1;

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;

    }

    public TabEstadoPedido mostrarPorID(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabEstadoPedido pro = null;
        em.getTransaction().begin();

        try {
            pro = em.find(TabEstadoPedido.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return pro;
    }

    public TabEstadoPedido eliminar(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabEstadoPedido pro = null;
        em.getTransaction().begin();

        try {
            pro = em.find(TabEstadoPedido.class, id);
            em.remove(pro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return pro;
    }

}
