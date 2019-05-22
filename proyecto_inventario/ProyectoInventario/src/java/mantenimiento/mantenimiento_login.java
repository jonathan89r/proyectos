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


/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_login {

//    Metodo para guardar datos
    public int guardarDatos(Login login) {
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

    //--------------------------------------------------------------------------------------------------------------------
//    Metodo para consultar todos la informacion
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

    //----------------------------------------------------------------------------------------------------------------------------------
//    Consultar por un id
    public Login consultarLoginporID(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Login log = null;
        em.getTransaction().begin();
        try {
            log = em.find(Login.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return log;
    }

//    Metodo para actualizar
    public int ActualizarDatos(Login l) {
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

//    Metodo para eliminar datos
    public Login eliminarDatos(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Login log = null;
        try {
            log = em.find(Login.class, id);
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


//    Metodo para actualizar usuarios
    public int actualizarUserss(Login productos) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        
        try {
            Login pro = em.merge(productos);//LA FUNCION merge() SIRVE PARA ACTUALIZAR DATOS DE UNA ENTRADA, HAY QUE PASARLE UN OBJETO DEL TIPO DE LA PERSISTENCIA QUE CREAMOS
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        
        return flag;
    }
}
