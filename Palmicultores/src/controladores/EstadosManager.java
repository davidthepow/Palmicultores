/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import database.Estados;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ignacio Hernandez
 */
public class EstadosManager {

    EstadosJpaController jpaEstados;
    EntityManagerFactory emf;
    EntityManager em;

    public EstadosManager() {
        emf = Persistence.createEntityManagerFactory("PalmicultoresPU");
        em = emf.createEntityManager();
        jpaEstados = new EstadosJpaController(emf);
    }

    public void createEstado(BigInteger clave, String nombre) {
        try {
            Estados estado = new Estados();
            estado.setClave(clave);
            estado.setNombre(nombre);
            em.getTransaction().begin();
            jpaEstados.create(estado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public void createEstado(String nombre, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
