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
import logica_De_Negocio.entidades.Maridaje;
import persistencia.exceptions.NonexistentEntityException;

public class MaridajeJpaController implements Serializable {

    public MaridajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MaridajeJpaController() {
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maridaje maridaje) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maridaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maridaje maridaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maridaje = em.merge(maridaje);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = maridaje.getId();
                if (findMaridaje(id) == null) {
                    throw new NonexistentEntityException("The maridaje with id " + id + " no longer exists.");
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
            Maridaje maridaje;
            try {
                maridaje = em.getReference(Maridaje.class, id);
                maridaje.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maridaje with id " + id + " no longer exists.", enfe);
            }
            em.remove(maridaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maridaje> findMaridajeEntities() {
        return findMaridajeEntities(true, -1, -1);
    }

    public List<Maridaje> findMaridajeEntities(int maxResults, int firstResult) {
        return findMaridajeEntities(false, maxResults, firstResult);
    }

    private List<Maridaje> findMaridajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maridaje.class));
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

    public Maridaje findMaridaje(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maridaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaridajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maridaje> rt = cq.from(Maridaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
