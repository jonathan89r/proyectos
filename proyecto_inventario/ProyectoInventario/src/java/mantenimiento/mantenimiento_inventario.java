/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Cliente;
import persistencia.Inventario;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_inventario {

//    Metodo para guardar
    public int guardarDatosinventario(Inventario in) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(in);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }
//    Metodo para actualizar
    public int ActualizarDatos(Inventario c) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(c);
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
    public Inventario eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Inventario clien = null;
        try {
            clien = em.find(Inventario.class, id);
            em.remove(clien);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return clien;
    }
    
//    Metodo para consultar datos
    public List consultarTodosInventario() {
        List<Inventario> listaInventario = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT i FROM Inventario i");
            em.getTransaction().commit();
            listaInventario = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaInventario;
    }

//    Metodo para consultar por un id
    public Inventario consultarInventarioporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Inventario clien = null;
        em.getTransaction().begin();
        try {
            clien = em.find(Inventario.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return clien;
    }

    //-----------------------------------------------------------------------------------------
//    Consultar informacion de la tabla proveedor_producto
    public List consultarTodosproveedorProducto() {
        List<Inventario> listaInventario = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM ProveedorProducto p");
            em.getTransaction().commit();
            listaInventario = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaInventario;
    }
}
