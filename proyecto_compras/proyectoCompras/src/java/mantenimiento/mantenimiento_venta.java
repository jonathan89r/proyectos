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
import persistencia.DetVent;
import persistencia.TabVenta;

/**
 *
 * @author omar.hernandezusam
 */
public class mantenimiento_venta {

    public int guardar(TabVenta venta) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag;
        em.getTransaction().begin();
        try {
            em.persist(venta);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }

        return flag;
    }

    public List consultar() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<TabVenta> listventa = new ArrayList<>();
        
        try {
            Query query = em.createNamedQuery("TabVenta.findAll");
            listventa = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return listventa;
    }

    public TabVenta consultarId(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabVenta consultarid = null;
        em.getTransaction().begin();
        try {
            consultarid = em.find(TabVenta.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return consultarid;
    }

    public int actualizar(TabVenta venta) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag;
        em.getTransaction().begin();;
        try {
            em.merge(venta);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }

        return flag;
    }

    public int eliminar(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag;
        em.getTransaction().begin();
        try {
            TabVenta delete = em.find(TabVenta.class, id);
            em.remove(delete);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return flag;
    }

    public List consultarVentas() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<DetVent> listventa = null;
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT d FROM DetVent d");
            em.getTransaction().commit();
            listventa = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listventa;
    }
    
  public List consultarGrafico(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<TabVenta> listventa = new ArrayList<>();
        
        try {
            Query query = em.createNamedQuery("TabVenta.findByIdVenta").setParameter("IdVenta", id);
            listventa = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return listventa;
    }

}
