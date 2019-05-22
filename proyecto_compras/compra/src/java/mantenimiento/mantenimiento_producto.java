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
import persistencia.TabProducto;
import persistencia.TabProvedor;

/**
 *
 * @author roberto.hernandezUSA
 */
public class mantenimiento_producto {

    public boolean guardar(TabProducto objeto) {
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

    public boolean borrarId(String id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabProducto objeto = null;
        try {
            objeto = em.find(TabProducto.class, id);
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

    public TabProducto consultarId(String id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabProducto objeto = null;
        try {
            objeto = em.find(TabProducto.class, id);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return objeto;
    }

    public boolean actualizar(TabProducto objeto) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabProducto objeto2 = null;
        try {
            objeto2 = em.find(TabProducto.class, objeto.getCodigo());
            objeto2.setNombre(objeto.getNombre());
            objeto2.setCategoria(objeto.getCategoria());
            objeto2.setProvedor(objeto.getProvedor());
            objeto2.setPrecio(objeto.getPrecio());
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public List consultarTodo() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<TabProvedor> listaProveedor = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT t FROM TabProducto t");
            listaProveedor = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaProveedor;
    }

}
