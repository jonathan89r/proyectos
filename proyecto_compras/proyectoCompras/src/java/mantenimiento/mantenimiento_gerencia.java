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
import persistencia.TabGerencia;
import persistencia.TabProvedor;

/**
 *
 * @author william.fuentesusam
 */
public class mantenimiento_gerencia {

    private EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
    private TabGerencia eliminar = null;
    private TabGerencia ger = null;
    private Query query = null;
    private List<TabGerencia> lista = null;
    private List<TabDepartamento> listadepa = null;

    public boolean metodos(int accion, TabGerencia datos) {
        em.getTransaction().begin();
        try {
            switch (accion) {
                case 1:
                    em.persist(datos);
                    break;
                case 2:
                    em.merge(datos);
                    break;
                case 3:
                    eliminar = em.find(TabGerencia.class, datos.getIdGerencia());
                    em.remove(eliminar);
                    break;
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.clear();
        }
    }

    public TabGerencia mostrarporID(int id) {
        em.getTransaction().begin();
        try {
            ger = em.find(TabGerencia.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return ger;
    }

    public List<TabGerencia> mostrar() {

        em.getTransaction().begin();

        try {
            query = em.createQuery("SELECT t FROM TabGerencia t");
            em.getTransaction().commit();
            lista = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
        return lista;
    }

    public List<TabDepartamento> mostrardepa() {

        em.getTransaction().begin();

        try {
            query = em.createQuery("SELECT t FROM TabDepartamento t");
            em.getTransaction().commit();
            listadepa = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }

        return listadepa;
    }

    public TabGerencia validar(String codEmpleado) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM TabGerencia t WHERE t.codEmpleado = :codEmpleado");
        query.setParameter("codEmpleado", codEmpleado);

        TabGerencia usuarios = null;
        List<TabGerencia> lista = query.getResultList();

        for (TabGerencia object : lista) {
            usuarios = object;
        }
        return usuarios;
    }

    public TabGerencia validarUser(String user) {
        EntityManager em = jpautil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM TabGerencia t WHERE t.usuario = :usuario");
        query.setParameter("usuario", user);

        TabGerencia usuarios = null;
        List<TabGerencia> lista = query.getResultList();

        for (TabGerencia object : lista) {
            usuarios = object;
        }
        return usuarios;
    }

    public TabGerencia validarLogin(String user, String pass) {
        EntityManager ems = jpautil.getEntityManagerFactory().createEntityManager();
        Query querys = ems.createQuery("SELECT t FROM TabGerencia t WHERE t.usuario = :usuario AND t.pass = :pass")
                .setParameter("usuario", user)
                .setParameter("pass", pass);

        TabGerencia usuarios = null;
        List<TabGerencia> listas = querys.getResultList();

        for (TabGerencia object : listas) {
            usuarios = object;
        }
        return usuarios;
    }

}
