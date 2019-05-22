/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import persistencia.Cliente;
import persistencia.Factura;
import persistencia.Inventario;
import persistencia.Productos;
import persistencia.TipoPago;
import persistencia.Vendedor;

/**
 *
 * @author jonathan.rodriguez
 */
public class mantenimiento_factura {

//    public List consultarTodosVendedorProducto() {
//        List<Vendedor> listaVendedor = null;
//        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        try {
//            Query query = em.createQuery("SELECT v FROM Vendedor v");
//            em.getTransaction().commit();
//            listaVendedor = query.getResultList();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//
//        } finally {
//            em.close();
//        }
//        return listaVendedor;
//    }
//
//    public List consultarTodosClienteProducto() {
//        List<Vendedor> listaClientes = null;
//        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        try {
//            Query query = em.createQuery("SELECT c FROM Cliente c");
//            em.getTransaction().commit();
//            listaClientes = query.getResultList();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//
//        } finally {
//            em.close();
//        }
//        return listaClientes;
//    }
//
//    public List consultarTodosTipoPagoProducto() {
//        List<Vendedor> listatPAgo = null;
//        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        try {
//            Query query = em.createQuery("SELECT t FROM TipoPago t");
//            em.getTransaction().commit();
//            listatPAgo = query.getResultList();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//
//        } finally {
//            em.close();
//        }
//        return listatPAgo;
//    }
//    Metodo para guardar
    public int guardarFactura(Factura pro) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
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

//        Metodo para eliminar
    public Factura eliminarFactura(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        Factura pro = null;
        em.getTransaction().begin();
        try {
            pro = em.find(Factura.class, id);
            em.remove(pro);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return pro;
    }

//    Metodo para actualizar
    public int actualizarFactura(Factura id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.merge(id);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }
    
//    Metodo para mostrar todo
    public List consultarTodoFactura() {
        List<Factura> listafactura = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT f FROM Factura f");
            em.getTransaction().commit();
            listafactura = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listafactura;
    }

//    Metodo para mostrar datos por un id
    public Factura consultarPorIDFactura(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Factura pro = null;

        em.getTransaction().begin();

        try {
            pro = em.find(Factura.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return pro;
    }
//    Mostrar vendedores
    public List consultarTodoVendedor() {
        List<Vendedor> listavendedor = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT v FROM Vendedor v");
            em.getTransaction().commit();
            listavendedor = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listavendedor;
    }
//    Mostrar clientes
    public List consultarTodoCliente() {
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

//    Mostrar inventario
    public List consultarTodoInventario() {
        List<Inventario> listaInventario = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT i FROM Inventario i");
            em.getTransaction().commit();
            listaInventario = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaInventario;
    }

//    Mostrar usuarios registrados
    public List consultarTodoTipoPago() {
        List<TipoPago> listaTipoPago = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT t FROM TipoPago t");
            em.getTransaction().commit();
            listaTipoPago = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listaTipoPago;
    }

    
    public Factura ConsultarId1(int id) {
        //Esto me genera la conexion
        EntityManager em = Persistence.createEntityManagerFactory("ProyectoInventarioPU").createEntityManager();
        Factura consultaid = null;
        em.getTransaction().begin();
        try {
            //En estado mando el tipo de clase para que me devuelva el tipo de clase y que me traiga el estado
            consultaid = em.find(Factura.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return consultaid;
    }
}
