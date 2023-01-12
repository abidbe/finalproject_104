/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.finalproject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ws.c.finalproject.exceptions.NonexistentEntityException;
import ws.c.finalproject.exceptions.PreexistingEntityException;

/**
 *
 * @author asus
 */
public class KtpJpaController implements Serializable {

    public KtpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ws.c_finalproject_jar_0.0.1-SNAPSHOTPU");
    
    public KtpJpaController(){}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ktp ktp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ktp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKtp(ktp.getId()) != null) {
                throw new PreexistingEntityException("Ktp " + ktp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ktp ktp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ktp = em.merge(ktp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ktp.getId();
                if (findKtp(id) == null) {
                    throw new NonexistentEntityException("The ktp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ktp ktp;
            try {
                ktp = em.getReference(Ktp.class, id);
                ktp.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ktp with id " + id + " no longer exists.", enfe);
            }
            em.remove(ktp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ktp> findKtpEntities() {
        return findKtpEntities(true, -1, -1);
    }

    public List<Ktp> findKtpEntities(int maxResults, int firstResult) {
        return findKtpEntities(false, maxResults, firstResult);
    }

    private List<Ktp> findKtpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ktp.class));
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

    public Ktp findKtp(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ktp.class, id);
        } finally {
            em.close();
        }
    }

    public int getKtpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ktp> rt = cq.from(Ktp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
