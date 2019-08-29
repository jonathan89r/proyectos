/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabDetalleVenta;
import persistencia.TabVenta;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_detalle_venta {

    //    metodo para guardar
    public int guardar(TabDetalleVenta pro) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
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

    //    metodo para mostrar todos los datos
    public List mostrarDetalle() {
        List<TabDetalleVenta> listaModo = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabDetalleVenta t");
            listaModo = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listaModo;
    }

    //    metodo para mostrar todos los datos
    public List mostrarVentas() {
        List<TabVenta> listaModo = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabVenta t");
            listaModo = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listaModo;
    }

//    metodo para actualizar un proveedor
    public int actualizar(TabDetalleVenta prove) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(prove);
            em.getTransaction().commit();
            flag = 1;

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;

    }

//    metodo para mostrar por un id
    public TabDetalleVenta mostrarPorID(int id_detalle) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabDetalleVenta det = new TabDetalleVenta();
        em.getTransaction().begin();

        try {
            det = em.find(TabDetalleVenta.class, id_detalle);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return det;
    }

//    metodo para eliminar un dato
    public TabDetalleVenta eliminar(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabDetalleVenta det = new TabDetalleVenta();
        em.getTransaction().begin();

        try {
            det = em.find(TabDetalleVenta.class, id);
            em.remove(det);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return det;
    }
}
