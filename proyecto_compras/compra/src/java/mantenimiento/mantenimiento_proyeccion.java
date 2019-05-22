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
import persistencia.TabInventario;
import persistencia.TabProyeccion;

/**
 *
 * @author william.fuentesusam
 */
public class mantenimiento_proyeccion {

    EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
    private TabProyeccion pro = null;
    private TabProyeccion eliminar = null;
    private List<TabProyeccion> lista = null;
    private List<TabInventario> listainv = null;
    private List<TabDetalleVenta> listaven = null;
    private Query query = null;

    public int guardar(TabProyeccion g) {
        
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int flag = 0;
        try {
            em.persist(g);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return flag;
    }

    public TabProyeccion eliminar(int id) {
        em.getTransaction().begin();
        try {
            eliminar = em.find(TabProyeccion.class, id);
            em.remove(eliminar);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return eliminar;
    }

//
//    public boolean metodos(int accion, TabProyeccion datos) {
//        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        try {
//            switch (accion) {
//                case 1:
//                    em.persist(datos);
//                    break;
//                case 2:
//                    em.merge(datos);
//                    break;
//                case 3:
//                    eliminar = em.find(TabProyeccion.class, datos.getIdProyeccion());
//                    em.remove(eliminar);
//                    break;
//            }
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            return false;
//        } finally {
//            em.clear();
//        }
//    }
    public TabProyeccion mostrarporID(int id) {
        em.getTransaction().begin();
        try {
            pro = em.find(TabProyeccion.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return pro;
    }

    public List<TabProyeccion> mostrar() {
        em.getTransaction().begin();
        try {
            query = em.createQuery("SELECT t FROM TabProyeccion t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return lista;
    }

    public List<TabInventario> mostrarinventario() {
        em.getTransaction().begin();
        try {
            query = em.createQuery("SELECT t FROM TabInventario t");
            em.getTransaction().commit();
            listainv = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return listainv;
    }

    public List<TabDetalleVenta> mostrarventa() {
        em.getTransaction().begin();
        try {
            query = em.createQuery("SELECT t FROM TabDetalleVenta t");
            em.getTransaction().commit();
            listaven = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return listaven;
    }

}
