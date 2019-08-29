/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabInventario;

/**
 *
 * @author roberto.hernandezUSA
 */
public class mantenimiento_inventario {

    public boolean guardar(TabInventario objeto) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(objeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean borrarId(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabInventario objeto = new TabInventario();
        try {
            objeto = em.find(TabInventario.class, id);
            em.remove(objeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean actualizar(TabInventario objeto) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabInventario objeto2 = new TabInventario();
        try {
            objeto2 = em.find(TabInventario.class, objeto.getIdInventario());
            objeto2.setProducto(objeto.getProducto());

            objeto2.setStock(objeto.getStock());

            objeto2.setStock(objeto.getStock());
            objeto2.setStock(objeto.getStock());

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public TabInventario consultarId(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabInventario objeto = new TabInventario();
        try {
            objeto = em.find(TabInventario.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return objeto;
    }

    public List consultarTodo() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<TabInventario> listaInventario = null;
        try {
            Query q = em.createQuery("SELECT t FROM TabInventario t");
            listaInventario = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listaInventario;
    }
}
