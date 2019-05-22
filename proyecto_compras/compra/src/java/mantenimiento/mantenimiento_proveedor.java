/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabCategoria;
import persistencia.TabEstadoGlobal;
import persistencia.TabGerencia;
import persistencia.TabGiro;
import persistencia.TabProvedor;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_proveedor {

//    metodo para guardar
    public int guardar(TabProvedor pro) {
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
    public List mostrarGiro() {
        List<TabGiro> listaModo = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabGiro t");
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
    public List mostrarProveedores() {
        List<TabProvedor> listaModo = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabProvedor t");
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
    public int actualizar(TabProvedor prove) {
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
    public TabProvedor mostrarPorID(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabProvedor pro = null;
        em.getTransaction().begin();

        try {
            pro = em.find(TabProvedor.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return pro;
    }

//    metodo para eliminar un dato
    public TabProvedor eliminar(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        TabProvedor pro = null;
        em.getTransaction().begin();

        try {
            pro = em.find(TabProvedor.class, id);
            em.remove(pro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return pro;
    }

    public TabProvedor validar(String proveedor) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM TabProvedor t WHERE t.nombreProveedor = :nombreProveedor");
        query.setParameter("nombreProveedor", proveedor);

        TabProvedor usuarios = null;
        List<TabProvedor> lista = query.getResultList();

        for (TabProvedor object : lista) {
            usuarios = object;
        }
        return usuarios;
    }

    //    metodo para mostrar todos los datos
    public List mostrarEstado() {
        List<TabEstadoGlobal> listaModo = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabEstadoGlobal t");
            listaModo = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listaModo;
    }
}
