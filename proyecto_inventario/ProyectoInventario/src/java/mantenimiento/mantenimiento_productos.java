/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Productos;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_productos {

//    Metodo para guardar
    public int guardarProductos(Productos pro) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(pro);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }
//    Metodo para eliminar
    public Productos eliminar(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        Productos pro = null;
        em.getTransaction().begin();
        try {
            pro = em.find(Productos.class, id);
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

//    Metodo para actualizar
    public int actualizarProductos(Productos pro) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(pro);
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
    public List consultarTodosProductos() {
        List<Productos> listaProductos = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM Productos p");
            em.getTransaction().commit();
            listaProductos = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listaProductos;
    }

//    Metodo para consultar por un id
    public Productos consultarPorIDPRODUCTOS(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Productos pro = null;

        em.getTransaction().begin();

        try {
            pro = em.find(Productos.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return pro;
    }

}
