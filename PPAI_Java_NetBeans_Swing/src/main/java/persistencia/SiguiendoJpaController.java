package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import logica_de_negocio.entidades.Siguiendo;
import persistencia.exceptions.NonexistentEntityException;

public class SiguiendoJpaController implements Serializable {

    public SiguiendoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SiguiendoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Siguiendo siguiendo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(siguiendo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Siguiendo siguiendo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            siguiendo = em.merge(siguiendo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = siguiendo.getId();
                if (findSiguiendo(id) == null) {
                    throw new NonexistentEntityException("The siguiendo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Siguiendo siguiendo;
            try {
                siguiendo = em.getReference(Siguiendo.class, id);
                siguiendo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The siguiendo with id " + id + " no longer exists.", enfe);
            }
            em.remove(siguiendo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Siguiendo> findSiguiendoEntities() {
        return findSiguiendoEntities(true, -1, -1);
    }

    public List<Siguiendo> findSiguiendoEntities(int maxResults, int firstResult) {
        return findSiguiendoEntities(false, maxResults, firstResult);
    }

    private List<Siguiendo> findSiguiendoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Siguiendo.class));
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

    public Siguiendo findSiguiendo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Siguiendo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSiguiendoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Siguiendo> rt = cq.from(Siguiendo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
