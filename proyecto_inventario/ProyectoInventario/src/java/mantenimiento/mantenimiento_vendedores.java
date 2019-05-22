/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Login;
import persistencia.Sucursal;
import persistencia.Vendedor;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_vendedores {

//    Metodo para guardar
    public int guardarDatosVendedores(Vendedor login) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(login);
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
    public int ActualizarDatosVendedores(Vendedor l) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(l);
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
    public Vendedor eliminarDatosVendedores(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Vendedor log = null;
        try {
            log = em.find(Vendedor.class, id);
            em.remove(log);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return log;
    }

    //--------------------------------------------------------------------------------------------------------------------
//    Metodo para consultar datos de vendedores
    public List consultarTodosVendedores() {
        List<Vendedor> listaVendedor = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT v FROM Vendedor v");
            em.getTransaction().commit();
            listaVendedor = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaVendedor;
    }

    //----------------------------------------------------------------------------------------------------------------------------------
//    Consultar datos por un id
    public Vendedor consultarVendedoresporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Vendedor log = null;
        em.getTransaction().begin();
        try {
            log = em.find(Vendedor.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return log;
    }

//    Consultar datos de sucursal
    public List consultarTodosSucursal() {
        List<Sucursal> listaSucursal = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT s FROM Sucursal s");
            em.getTransaction().commit();
            listaSucursal = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaSucursal;
    }

//    Consultar todos los usuarios registrados
    public List consultarTodosLogin() {
        List<Login> listaLogin = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT l FROM Login l");
            em.getTransaction().commit();
            listaLogin = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaLogin;
    }

}