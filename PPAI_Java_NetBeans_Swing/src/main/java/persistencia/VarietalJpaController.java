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
import logica_de_negocio.entidades.Varietal;
import persistencia.exceptions.NonexistentEntityException;

public class VarietalJpaController implements Serializable {

    public VarietalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public VarietalJpaController() {
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Varietal varietal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(varietal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Varietal varietal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            varietal = em.merge(varietal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = varietal.getId();
                if (findVarietal(id) == null) {
                    throw new NonexistentEntityException("The varietal with id " + id + " no longer exists.");
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
            Varietal varietal;
            try {
                varietal = em.getReference(Varietal.class, id);
                varietal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The varietal with id " + id + " no longer exists.", enfe);
            }
            em.remove(varietal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Varietal> findVarietalEntities() {
        return findVarietalEntities(true, -1, -1);
    }

    public List<Varietal> findVarietalEntities(int maxResults, int firstResult) {
        return findVarietalEntities(false, maxResults, firstResult);
    }

    private List<Varietal> findVarietalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Varietal.class));
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

    public Varietal findVarietal(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Varietal.class, id);
        } finally {
            em.close();
        }
    }

    public int getVarietalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Varietal> rt = cq.from(Varietal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
