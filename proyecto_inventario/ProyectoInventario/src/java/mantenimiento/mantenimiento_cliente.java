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

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_cliente {

//    Metodo para guardar
    public int guardarDatos(Cliente cliente) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(cliente);
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
    public int ActualizarDatos(Cliente c) {
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
    public Cliente eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Cliente clien = null;
        try {
            clien = em.find(Cliente.class, id);
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
//    Metodo para mostrar
    public List consultarTodosClientes() {
        List<Cliente> listaCliente = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c");
            em.getTransaction().commit();
            listaCliente = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaCliente;
    }
//    Metodo para mostrar por id
    public Cliente consultarClientesporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Cliente clien = null;
        em.getTransaction().begin();
        try {
            clien = em.find(Cliente.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return clien;
    }

}
