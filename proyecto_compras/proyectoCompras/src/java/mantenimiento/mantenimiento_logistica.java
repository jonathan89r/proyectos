/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TabInventario;
import persistencia.TabProducto;
import persistencia.InventarioProducto;
import persistencia.PedidoProyeccion;
import persistencia.TabCategoria;
import persistencia.TabProvedor;

/**
 *
 * @author william.fuentesusam
 */
public class mantenimiento_logistica {

    public int guardar(TabProducto pro, TabInventario inv) {

        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag = 0;

        em.getTransaction().begin();
        try {
            em.persist(pro);
            em.persist(inv);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }
    
     public int actualizar(TabProducto pro, TabInventario inv) {

        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        int flag = 0;

        em.getTransaction().begin();
        try {
            em.merge(pro);
            em.merge(inv);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

//    public boolean eliminar(TabProducto codigo, TabInventario id) {
//
//        TabProducto eliminar = null;
//        TabInventario eliminar1 = null;
//        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//
//        try {
//            eliminar = em.find(TabProducto.class, codigo.getCodigo());
//            em.remove(eliminar);
//            eliminar1 = em.find(TabInventario.class, id.getIdInventario());
//            em.remove(eliminar1);
//
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            return false;
//        } finally {
//            em.close();
//        }
//    }
    public List<InventarioProducto> lista() {

        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<InventarioProducto> listain = null;

        try {
            Query query = em.createQuery("SELECT i FROM InventarioProducto i");
            em.getTransaction().commit();
            listain = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listain;
    }

    
//    public InventarioProducto eliminar(int id){
//        
//        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        List<InventarioProducto> eliminar = null;
//        
//        try {
//            eliminar = em.find(g, eliminar)
//        } catch (Exception e) {
//        }
//
//    }
    
//         public List<PedidoProyeccion> lista2() {


    public TabProducto eliminar(String Codigo) {

        TabProducto eliminar = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            eliminar = em.find(TabProducto.class, Codigo);
            em.remove(eliminar);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return eliminar;
    }

    public TabInventario eliminar1(int id) {

        TabInventario eliminar1 = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            eliminar1 = em.find(TabInventario.class, id);
            em.remove(eliminar1);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return eliminar1;
    }

    public TabProducto mostrarporID(String Codigo) {

        TabProducto pro = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            pro = em.find(TabProducto.class, Codigo);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return pro;
    }

    public TabInventario mostrarporID2(int id) {

        TabInventario inv = null;
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {
            inv = em.find(TabInventario.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return inv;
    }

    public List<PedidoProyeccion> lista2() {


        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<PedidoProyeccion> lista = null;

        try {
            Query query = em.createQuery("SELECT p FROM PedidoProyeccion p");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return lista;
    }

    public List<TabCategoria> listacat() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<TabCategoria> lista = null;
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabCategoria t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return lista;
    }

    public List<TabProvedor> listapro() {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        List<TabProvedor> lista = null;
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT t FROM TabProvedor t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return lista;
    }

}
