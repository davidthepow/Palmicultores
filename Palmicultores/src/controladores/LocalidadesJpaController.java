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
import database.Localidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ignacio Hernandez
 */
public class LocalidadesJpaController implements Serializable {

    public LocalidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidades localidades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estados estadoId = localidades.getEstadoId();
            if (estadoId != null) {
                estadoId = em.getReference(estadoId.getClass(), estadoId.getId());
                localidades.setEstadoId(estadoId);
            }
            em.persist(localidades);
            if (estadoId != null) {
                estadoId.getLocalidadesCollection().add(localidades);
                estadoId = em.merge(estadoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidades localidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidades persistentLocalidades = em.find(Localidades.class, localidades.getId());
            Estados estadoIdOld = persistentLocalidades.getEstadoId();
            Estados estadoIdNew = localidades.getEstadoId();
            if (estadoIdNew != null) {
                estadoIdNew = em.getReference(estadoIdNew.getClass(), estadoIdNew.getId());
                localidades.setEstadoId(estadoIdNew);
            }
            localidades = em.merge(localidades);
            if (estadoIdOld != null && !estadoIdOld.equals(estadoIdNew)) {
                estadoIdOld.getLocalidadesCollection().remove(localidades);
                estadoIdOld = em.merge(estadoIdOld);
            }
            if (estadoIdNew != null && !estadoIdNew.equals(estadoIdOld)) {
                estadoIdNew.getLocalidadesCollection().add(localidades);
                estadoIdNew = em.merge(estadoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = localidades.getId();
                if (findLocalidades(id) == null) {
                    throw new NonexistentEntityException("The localidades with id " + id + " no longer exists.");
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
            Localidades localidades;
            try {
                localidades = em.getReference(Localidades.class, id);
                localidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidades with id " + id + " no longer exists.", enfe);
            }
            Estados estadoId = localidades.getEstadoId();
            if (estadoId != null) {
                estadoId.getLocalidadesCollection().remove(localidades);
                estadoId = em.merge(estadoId);
            }
            em.remove(localidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidades> findLocalidadesEntities() {
        return findLocalidadesEntities(true, -1, -1);
    }

    public List<Localidades> findLocalidadesEntities(int maxResults, int firstResult) {
        return findLocalidadesEntities(false, maxResults, firstResult);
    }

    private List<Localidades> findLocalidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidades.class));
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

    public Localidades findLocalidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidades> rt = cq.from(Localidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
