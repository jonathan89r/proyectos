/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jonathan.rodriguez
 */
public class JpaUtil {

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoInventarioPU");

        } catch (Throwable ex) {
            System.err.println("Error al iniciar Sesion" + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {

        return emf;
    }

}
