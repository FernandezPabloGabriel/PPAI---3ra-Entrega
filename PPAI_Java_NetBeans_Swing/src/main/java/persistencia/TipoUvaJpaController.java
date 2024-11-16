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
import logica_de_negocio.entidades.TipoUva;
import persistencia.exceptions.NonexistentEntityException;

public class TipoUvaJpaController implements Serializable {

    public TipoUvaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TipoUvaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoUva tipoUva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoUva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoUva tipoUva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoUva = em.merge(tipoUva);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = tipoUva.getId();
                if (findTipoUva(id) == null) {
                    throw new NonexistentEntityException("The tipoUva with id " + id + " no longer exists.");
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
            TipoUva tipoUva;
            try {
                tipoUva = em.getReference(TipoUva.class, id);
                tipoUva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoUva with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoUva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoUva> findTipoUvaEntities() {
        return findTipoUvaEntities(true, -1, -1);
    }

    public List<TipoUva> findTipoUvaEntities(int maxResults, int firstResult) {
        return findTipoUvaEntities(false, maxResults, firstResult);
    }

    private List<TipoUva> findTipoUvaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoUva.class));
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

    public TipoUva findTipoUva(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoUva.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoUvaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoUva> rt = cq.from(TipoUva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
