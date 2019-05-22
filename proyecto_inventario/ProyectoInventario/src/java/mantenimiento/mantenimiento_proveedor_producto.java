/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Proveedor;
import persistencia.ProveedorProducto;
import persistencia.Productos;

/**
 *
 * @author victor.rosalesUSAM
 */
public class mantenimiento_proveedor_producto {

//    Metodo para guardar datos
    public int guardarDatos(ProveedorProducto proveedorProducto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(proveedorProducto);
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
        List<ProveedorProducto> listaProveedorProducto = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM ProveedorProducto p");
            em.getTransaction().commit();
            listaProveedorProducto = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaProveedorProducto;
    }

//    Metodo para consultar por un id
    public ProveedorProducto consultarProveedorporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        ProveedorProducto pro = null;
        em.getTransaction().begin();
        try {
            pro = em.find(ProveedorProducto.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return pro;
    }

//    Metodo para actualizar 
    public int ActualizarDatosProveedor(ProveedorProducto p) {
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

//    Metodo para eliminar
    public ProveedorProducto eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        ProveedorProducto pro = null;
        try {
            pro = em.find(ProveedorProducto.class, id);
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

//    Metodo para consultar todo de proveedores
    public List consultarTodosProveedorP() {
        List<Proveedor> listaProveedorP = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM Proveedor p");
            em.getTransaction().commit();
            listaProveedorP = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaProveedorP;
    }

//    Metodo para consultar todo de productos
    public List consultarTodosProducto() {
        List<Productos> listaProducto = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT p FROM Productos p");
            em.getTransaction().commit();
            listaProducto = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaProducto;
    }

}
