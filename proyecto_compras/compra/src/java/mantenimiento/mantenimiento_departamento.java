/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabDepartamento;

/**
 *
 * @author sergio.torresusam
 */
public class mantenimiento_departamento {
    
    public List<TabDepartamento> mostrar(){
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<TabDepartamento> lista = null;
        em.getTransaction().begin();
        
        try {
            Query query = em.createQuery("SELECT t FROM TabDepartamento t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return lista;
    }
    
}
