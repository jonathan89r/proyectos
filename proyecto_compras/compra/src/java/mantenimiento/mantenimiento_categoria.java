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
import persistencia.TabCategoria;
import persistencia.TabProvedor;

/**
 *
 * @author roberto.hernandezUSA
 */
public class mantenimiento_categoria {
    
    
    public List consultarCategoria(){
        EntityManager em=jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<TabCategoria> listaCategoria=new ArrayList<>();
        
        try {
            Query q=em.createQuery("SELECT t FROM TabCategoria t");
            listaCategoria=q.getResultList();
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }finally{
            em.close();
        }
        return listaCategoria;
    }
    
    public boolean guardar(TabCategoria objeto) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(objeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }

    }

    public boolean borrar(int id) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabCategoria objeto = new TabCategoria();
        try {
            objeto = em.find(TabCategoria.class, id);
            em.remove(objeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }

    }
    
    public TabCategoria consultarId(int id){
        EntityManager em=jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabCategoria objeto = new TabCategoria();
        try {
            objeto = em.find(TabCategoria.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }finally{
            em.close();
        }
        return objeto;
    }

    public boolean actualizar(TabCategoria objeto) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        TabCategoria objeto2 = new TabCategoria();
        try {
            objeto2 = em.find(TabCategoria.class, objeto.getIdCategoria());
            objeto2.setNombreCategoria(objeto.getNombreCategoria());

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }

    }
    
}
