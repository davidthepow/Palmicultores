/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import database.Estados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import database.Municipios;
import java.util.ArrayList;
import java.util.Collection;
import database.Localidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ignacio Hernandez
 */
public class EstadosJpaController implements Serializable {

    public EstadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estados estados) {
        if (estados.getMunicipiosCollection() == null) {
            estados.setMunicipiosCollection(new ArrayList<Municipios>());
        }
        if (estados.getLocalidadesCollection() == null) {
            estados.setLocalidadesCollection(new ArrayList<Localidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Municipios> attachedMunicipiosCollection = new ArrayList<Municipios>();
            for (Municipios municipiosCollectionMunicipiosToAttach : estados.getMunicipiosCollection()) {
                municipiosCollectionMunicipiosToAttach = em.getReference(municipiosCollectionMunicipiosToAttach.getClass(), municipiosCollectionMunicipiosToAttach.getId());
                attachedMunicipiosCollection.add(municipiosCollectionMunicipiosToAttach);
            }
            estados.setMunicipiosCollection(attachedMunicipiosCollection);
            Collection<Localidades> attachedLocalidadesCollection = new ArrayList<Localidades>();
            for (Localidades localidadesCollectionLocalidadesToAttach : estados.getLocalidadesCollection()) {
                localidadesCollectionLocalidadesToAttach = em.getReference(localidadesCollectionLocalidadesToAttach.getClass(), localidadesCollectionLocalidadesToAttach.getId());
                attachedLocalidadesCollection.add(localidadesCollectionLocalidadesToAttach);
            }
            estados.setLocalidadesCollection(attachedLocalidadesCollection);
            em.persist(estados);
            for (Municipios municipiosCollectionMunicipios : estados.getMunicipiosCollection()) {
                Estados oldEstadoIdOfMunicipiosCollectionMunicipios = municipiosCollectionMunicipios.getEstadoId();
                municipiosCollectionMunicipios.setEstadoId(estados);
                municipiosCollectionMunicipios = em.merge(municipiosCollectionMunicipios);
                if (oldEstadoIdOfMunicipiosCollectionMunicipios != null) {
                    oldEstadoIdOfMunicipiosCollectionMunicipios.getMunicipiosCollection().remove(municipiosCollectionMunicipios);
                    oldEstadoIdOfMunicipiosCollectionMunicipios = em.merge(oldEstadoIdOfMunicipiosCollectionMunicipios);
                }
            }
            for (Localidades localidadesCollectionLocalidades : estados.getLocalidadesCollection()) {
                Estados oldEstadoIdOfLocalidadesCollectionLocalidades = localidadesCollectionLocalidades.getEstadoId();
                localidadesCollectionLocalidades.setEstadoId(estados);
                localidadesCollectionLocalidades = em.merge(localidadesCollectionLocalidades);
                if (oldEstadoIdOfLocalidadesCollectionLocalidades != null) {
                    oldEstadoIdOfLocalidadesCollectionLocalidades.getLocalidadesCollection().remove(localidadesCollectionLocalidades);
                    oldEstadoIdOfLocalidadesCollectionLocalidades = em.merge(oldEstadoIdOfLocalidadesCollectionLocalidades);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estados estados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estados persistentEstados = em.find(Estados.class, estados.getId());
            Collection<Municipios> municipiosCollectionOld = persistentEstados.getMunicipiosCollection();
            Collection<Municipios> municipiosCollectionNew = estados.getMunicipiosCollection();
            Collection<Localidades> localidadesCollectionOld = persistentEstados.getLocalidadesCollection();
            Collection<Localidades> localidadesCollectionNew = estados.getLocalidadesCollection();
            List<String> illegalOrphanMessages = null;
            for (Municipios municipiosCollectionOldMunicipios : municipiosCollectionOld) {
                if (!municipiosCollectionNew.contains(municipiosCollectionOldMunicipios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Municipios " + municipiosCollectionOldMunicipios + " since its estadoId field is not nullable.");
                }
            }
            for (Localidades localidadesCollectionOldLocalidades : localidadesCollectionOld) {
                if (!localidadesCollectionNew.contains(localidadesCollectionOldLocalidades)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Localidades " + localidadesCollectionOldLocalidades + " since its estadoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Municipios> attachedMunicipiosCollectionNew = new ArrayList<Municipios>();
            for (Municipios municipiosCollectionNewMunicipiosToAttach : municipiosCollectionNew) {
                municipiosCollectionNewMunicipiosToAttach = em.getReference(municipiosCollectionNewMunicipiosToAttach.getClass(), municipiosCollectionNewMunicipiosToAttach.getId());
                attachedMunicipiosCollectionNew.add(municipiosCollectionNewMunicipiosToAttach);
            }
            municipiosCollectionNew = attachedMunicipiosCollectionNew;
            estados.setMunicipiosCollection(municipiosCollectionNew);
            Collection<Localidades> attachedLocalidadesCollectionNew = new ArrayList<Localidades>();
            for (Localidades localidadesCollectionNewLocalidadesToAttach : localidadesCollectionNew) {
                localidadesCollectionNewLocalidadesToAttach = em.getReference(localidadesCollectionNewLocalidadesToAttach.getClass(), localidadesCollectionNewLocalidadesToAttach.getId());
                attachedLocalidadesCollectionNew.add(localidadesCollectionNewLocalidadesToAttach);
            }
            localidadesCollectionNew = attachedLocalidadesCollectionNew;
            estados.setLocalidadesCollection(localidadesCollectionNew);
            estados = em.merge(estados);
            for (Municipios municipiosCollectionNewMunicipios : municipiosCollectionNew) {
                if (!municipiosCollectionOld.contains(municipiosCollectionNewMunicipios)) {
                    Estados oldEstadoIdOfMunicipiosCollectionNewMunicipios = municipiosCollectionNewMunicipios.getEstadoId();
                    municipiosCollectionNewMunicipios.setEstadoId(estados);
                    municipiosCollectionNewMunicipios = em.merge(municipiosCollectionNewMunicipios);
                    if (oldEstadoIdOfMunicipiosCollectionNewMunicipios != null && !oldEstadoIdOfMunicipiosCollectionNewMunicipios.equals(estados)) {
                        oldEstadoIdOfMunicipiosCollectionNewMunicipios.getMunicipiosCollection().remove(municipiosCollectionNewMunicipios);
                        oldEstadoIdOfMunicipiosCollectionNewMunicipios = em.merge(oldEstadoIdOfMunicipiosCollectionNewMunicipios);
                    }
                }
            }
            for (Localidades localidadesCollectionNewLocalidades : localidadesCollectionNew) {
                if (!localidadesCollectionOld.contains(localidadesCollectionNewLocalidades)) {
                    Estados oldEstadoIdOfLocalidadesCollectionNewLocalidades = localidadesCollectionNewLocalidades.getEstadoId();
                    localidadesCollectionNewLocalidades.setEstadoId(estados);
                    localidadesCollectionNewLocalidades = em.merge(localidadesCollectionNewLocalidades);
                    if (oldEstadoIdOfLocalidadesCollectionNewLocalidades != null && !oldEstadoIdOfLocalidadesCollectionNewLocalidades.equals(estados)) {
                        oldEstadoIdOfLocalidadesCollectionNewLocalidades.getLocalidadesCollection().remove(localidadesCollectionNewLocalidades);
                        oldEstadoIdOfLocalidadesCollectionNewLocalidades = em.merge(oldEstadoIdOfLocalidadesCollectionNewLocalidades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estados.getId();
                if (findEstados(id) == null) {
                    throw new NonexistentEntityException("The estados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estados estados;
            try {
                estados = em.getReference(Estados.class, id);
                estados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estados with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Municipios> municipiosCollectionOrphanCheck = estados.getMunicipiosCollection();
            for (Municipios municipiosCollectionOrphanCheckMunicipios : municipiosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estados (" + estados + ") cannot be destroyed since the Municipios " + municipiosCollectionOrphanCheckMunicipios + " in its municipiosCollection field has a non-nullable estadoId field.");
            }
            Collection<Localidades> localidadesCollectionOrphanCheck = estados.getLocalidadesCollection();
            for (Localidades localidadesCollectionOrphanCheckLocalidades : localidadesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estados (" + estados + ") cannot be destroyed since the Localidades " + localidadesCollectionOrphanCheckLocalidades + " in its localidadesCollection field has a non-nullable estadoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estados> findEstadosEntities() {
        return findEstadosEntities(true, -1, -1);
    }

    public List<Estados> findEstadosEntities(int maxResults, int firstResult) {
        return findEstadosEntities(false, maxResults, firstResult);
    }

    private List<Estados> findEstadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estados.class));
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

    public Estados findEstados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estados> rt = cq.from(Estados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
