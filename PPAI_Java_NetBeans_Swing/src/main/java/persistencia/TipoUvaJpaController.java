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

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //Solo para el MOCK de datos
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

    public List<TipoUva> findTipoUvaEntities() {
        return findTipoUvaEntities(true, -1, -1);
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

    //Solo para el MOCK de datos
    public TipoUva findTipoUva(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoUva.class, id);
        } finally {
            em.close();
        }
    }
}
