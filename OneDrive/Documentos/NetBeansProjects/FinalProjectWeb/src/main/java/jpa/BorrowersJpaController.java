/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa;

import entities.Borrowers;
import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alexis
 */
public class BorrowersJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public BorrowersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Borrowers borrower) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(borrower);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Borrowers borrower) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            borrower = em.merge(borrower);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = borrower.getBorrowerId();
                if (findBorrower(id) == null) {
                    throw new NonexistentEntityException("The borrower with id " + id + " no longer exists.");
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
            Borrowers borrower;
            try {
                borrower = em.getReference(Borrowers.class, id);
                borrower.getBorrowerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The borrower with id " + id + " no longer exists.", enfe);
            }
            em.remove(borrower);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Borrowers> findBorrowersEntities() {
        return findBorrowersEntities(true, -1, -1);
    }

    public List<Borrowers> findBorrowersEntities(int maxResults, int firstResult) {
        return findBorrowersEntities(false, maxResults, firstResult);
    }

    private List<Borrowers> findBorrowersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Borrowers.class));
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

    public Borrowers findBorrower(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Borrowers.class, id);
        } finally {
            em.close();
        }
    }

    public int getBorrowersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Borrowers> rt = cq.from(Borrowers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
