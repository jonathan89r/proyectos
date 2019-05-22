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
 * @author victor.rosalesUSAM
 */
public class Validacion_login {

    EntityManager em;

     public Login validar(String user, String pass, int tipo_usuario) {

        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Query q = em.createNamedQuery("Login.findByTipoUsuarioandPass", Login.class).setParameter("user", user).setParameter("pass", pass);
        List<Login> lista = q.getResultList();

        if (!lista.isEmpty()) {

            return lista.get(0);

        } else {
            System.out.println("Error");
        }
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Query qe = em.createNamedQuery("Login.findByTipoUsuario", Login.class).setParameter("tipo_usuario", tipo_usuario);
        List<Login> lista2 = q.getResultList();
        if (!lista2.isEmpty()) {
            return lista.get(0);
        } else {
            System.out.println("Error");
        }

        return null;
    }
}
