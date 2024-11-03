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
import logica_De_Negocio.entidades.Enofilo;
import persistencia.exceptions.NonexistentEntityException;

public class EnofiloJpaController implements Serializable {

    public EnofiloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EnofiloJpaController(){
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enofilo enofilo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enofilo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enofilo enofilo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enofilo = em.merge(enofilo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = enofilo.getId();
                if (findEnofilo(id) == null) {
                    throw new NonexistentEntityException("The enofilo with id " + id + " no longer exists.");
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
            Enofilo enofilo;
            try {
                enofilo = em.getReference(Enofilo.class, id);
                enofilo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enofilo with id " + id + " no longer exists.", enfe);
            }
            em.remove(enofilo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enofilo> findEnofiloEntities() {
        return findEnofiloEntities(true, -1, -1);
    }

    public List<Enofilo> findEnofiloEntities(int maxResults, int firstResult) {
        return findEnofiloEntities(false, maxResults, firstResult);
    }

    private List<Enofilo> findEnofiloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enofilo.class));
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

    public Enofilo findEnofilo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enofilo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnofiloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enofilo> rt = cq.from(Enofilo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
