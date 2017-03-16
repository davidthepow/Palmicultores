/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import database.Estados;
import database.Municipios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ignacio Hernandez
 */
public class MunicipiosJpaController implements Serializable {

    public MunicipiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipios municipios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estados estadoId = municipios.getEstadoId();
            if (estadoId != null) {
                estadoId = em.getReference(estadoId.getClass(), estadoId.getId());
                municipios.setEstadoId(estadoId);
            }
            em.persist(municipios);
            if (estadoId != null) {
                estadoId.getMunicipiosCollection().add(municipios);
                estadoId = em.merge(estadoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipios municipios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipios persistentMunicipios = em.find(Municipios.class, municipios.getId());
            Estados estadoIdOld = persistentMunicipios.getEstadoId();
            Estados estadoIdNew = municipios.getEstadoId();
            if (estadoIdNew != null) {
                estadoIdNew = em.getReference(estadoIdNew.getClass(), estadoIdNew.getId());
                municipios.setEstadoId(estadoIdNew);
            }
            municipios = em.merge(municipios);
            if (estadoIdOld != null && !estadoIdOld.equals(estadoIdNew)) {
                estadoIdOld.getMunicipiosCollection().remove(municipios);
                estadoIdOld = em.merge(estadoIdOld);
            }
            if (estadoIdNew != null && !estadoIdNew.equals(estadoIdOld)) {
                estadoIdNew.getMunicipiosCollection().add(municipios);
                estadoIdNew = em.merge(estadoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipios.getId();
                if (findMunicipios(id) == null) {
                    throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipios municipios;
            try {
                municipios = em.getReference(Municipios.class, id);
                municipios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.", enfe);
            }
            Estados estadoId = municipios.getEstadoId();
            if (estadoId != null) {
                estadoId.getMunicipiosCollection().remove(municipios);
                estadoId = em.merge(estadoId);
            }
            em.remove(municipios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipios> findMunicipiosEntities() {
        return findMunicipiosEntities(true, -1, -1);
    }

    public List<Municipios> findMunicipiosEntities(int maxResults, int firstResult) {
        return findMunicipiosEntities(false, maxResults, firstResult);
    }

    private List<Municipios> findMunicipiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipios.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Municipios findMunicipios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipios.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipios> rt = cq.from(Municipios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
