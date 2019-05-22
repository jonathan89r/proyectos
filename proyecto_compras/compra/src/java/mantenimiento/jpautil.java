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
 * @author sergio.torresusam
 */
public class jpautil {

    private static final EntityManagerFactory emf;

    static {
        try {

            emf = Persistence.createEntityManagerFactory("sys_compraPU");
        } catch (Throwable ex) {
            System.getProperty("java.classpath");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
