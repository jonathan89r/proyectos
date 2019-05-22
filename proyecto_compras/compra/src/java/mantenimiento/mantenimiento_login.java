/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabGerencia;

/**
 *
 * @author william.fuentesusam
 */
public class mantenimiento_login {

    EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();

    public TabGerencia consultarTodo(String usuario, String pass) {

        Query query = em.createQuery("SELECT t FROM TabGerencia t WHERE t.usuario = :usuario AND t.pass = :pass");
        query.setParameter("usuario", usuario);
        query.setParameter("pass", pass);

        TabGerencia usuarios = null;
        List<TabGerencia> lista = query.getResultList();

        for (TabGerencia object : lista) {
            usuarios = object;
        }
        return usuarios;
    }

    public int actualizarpass(String pass, int id) {
        int flag = 0;
        em.getTransaction().begin();
        try {
            TabGerencia usu = em.find(TabGerencia.class, id);
            usu.setPass(pass);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;

    }

    public TabGerencia consultarID(int id) {
        em.getTransaction().begin();
        TabGerencia usu = null;
        try {
            usu = em.find(TabGerencia.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return usu;
    }
    
    public TabGerencia existenciaUsuario(String usuario){
        Query query = em.createQuery("SELECT t FROM TabGerencia t WHERE t.usuario = :usuario");
        query.setParameter("usuario", usuario);
        
        TabGerencia user = null;
        List<TabGerencia> lista = query.getResultList();
        
        for (TabGerencia object : lista){
            user = object;
        }
        return user;
    }
    
     public int actualizar(TabGerencia g){
        em.getTransaction().begin();
        int flag = 0;
        
        try {
            em.merge(g);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        }finally{
            em.clear();
        }
        return flag;
    }

}
