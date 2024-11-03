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
import logica_De_Negocio.entidades.Vino;
import persistencia.exceptions.NonexistentEntityException;

public class VinoJpaController implements Serializable {

    public VinoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public VinoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vino vino) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vino);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vino vino) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vino = em.merge(vino);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = vino.getId();
                if (findVino(id) == null) {
                    throw new NonexistentEntityException("The vino with id " + id + " no longer exists.");
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
            Vino vino;
            try {
                vino = em.getReference(Vino.class, id);
                vino.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vino with id " + id + " no longer exists.", enfe);
            }
            em.remove(vino);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vino> findVinoEntities() {
        return findVinoEntities(true, -1, -1);
    }

    public List<Vino> findVinoEntities(int maxResults, int firstResult) {
        return findVinoEntities(false, maxResults, firstResult);
    }

    private List<Vino> findVinoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vino.class));
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

    public Vino findVino(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vino.class, id);
        } finally {
            em.close();
        }
    }

    public int getVinoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vino> rt = cq.from(Vino.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
