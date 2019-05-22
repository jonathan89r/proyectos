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
import persistencia.PedidoProyeccion;
import persistencia.TabGerencia;
import persistencia.TabPedido;
import persistencia.TabProducto;
import persistencia.TabProvedor;
import persistencia.TabProyeccion;

/**
 *
 * @author william.fuentesusam
 */
public class mantenimiento_pedido {

    private EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
    private TabPedido eliminar = null;
    private TabPedido pe = null;
    private TabProyeccion elimi = null;
    private Query query = null;
    private List<TabPedido> lista = null;
    private List<TabProyeccion> listaPro = null;
    private int flag = 0;

    public int guardar(TabPedido p/*, TabProyeccion pr*/) {
        em.getTransaction().begin();
        try {
            em.persist(p);
            // em.persist(pr);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return flag;
    }

    public int actualizar(TabPedido p/*, TabProyeccion pr*/) {
        em.getTransaction().begin();
        try {
            em.merge(p);
            // em.merge(pr);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return flag;
    }

    public TabPedido eliminar(int id) {
        em.getTransaction().begin();
        try {
            eliminar = em.find(TabPedido.class, id);
            em.remove(eliminar);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return eliminar;
    }

    public TabPedido mostrarporID(int id) {
        em.getTransaction().begin();
        try {
            pe = em.find(TabPedido.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return pe;
    }

    public List<TabPedido> mostrar() {
        em.getTransaction().begin();
        try {
            query = em.createQuery("SELECT t FROM TabPedido t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return lista;
    }

    public List<TabGerencia> mostrargerente() {

        em.getTransaction().begin();
        List<TabGerencia> listage = null;
        try {
            query = em.createQuery("SELECT t FROM TabGerencia t");
            em.getTransaction().commit();
            listage = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return listage;
    }

    public List<TabProyeccion> mostrarproyeccion() {

        em.getTransaction().begin();
        List<TabProyeccion> listapro = null;
        try {
            query = em.createQuery("SELECT t FROM TabProyeccion t");
            em.getTransaction().commit();
            listapro = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return listapro;
    }

    public List consultarTodo() {
        EntityManager emn = jpautil.getEntityManagerFactory().createEntityManager();
        emn.getTransaction().begin();
        List<TabProvedor> listaProveedor = new ArrayList<>();
        try {
            Query q = emn.createQuery("SELECT t FROM TabProducto t");
            listaProveedor = q.getResultList();
            emn.getTransaction().commit();
        } catch (Exception e) {
            emn.getTransaction().rollback();

        } finally {
            emn.close();
        }
        return listaProveedor;
    }

    public List consultarDatos() {
        EntityManager emn = jpautil.getEntityManagerFactory().createEntityManager();
        emn.getTransaction().begin();
        List<PedidoProyeccion> listaProveedor = new ArrayList<>();
        try {
            Query q = emn.createQuery("SELECT p FROM PedidoProyeccion p");
            listaProveedor = q.getResultList();
            emn.getTransaction().commit();
        } catch (Exception e) {
            emn.getTransaction().rollback();

        } finally {
            emn.close();
        }
        return listaProveedor;
    }
    
    public TabProducto consultarId(String cod) {
        EntityManager emn = jpautil.getEntityManagerFactory().createEntityManager();
        emn.getTransaction().begin();
        TabProducto objeto = null;
        try {
            objeto = emn.find(TabProducto.class, cod);

            emn.getTransaction().commit();

        } catch (Exception e) {
            emn.getTransaction().rollback();

        } finally {
            emn.close();
        }
        return objeto;
    }
}
