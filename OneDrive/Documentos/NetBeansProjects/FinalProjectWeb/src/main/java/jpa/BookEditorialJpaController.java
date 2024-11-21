/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa;

import entities.BookEditorial;
import entities.BookEditorialPK;
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
public class BookEditorialJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public BookEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BookEditorial bookEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bookEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BookEditorial bookEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bookEditorial = em.merge(bookEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BookEditorialPK id = bookEditorial.getBookEditorialPK();
                if (findBookEditorial(id) == null) {
                    throw new NonexistentEntityException("The BookEditorial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BookEditorialPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BookEditorial bookEditorial;
            try {
                bookEditorial = em.getReference(BookEditorial.class, id);
                bookEditorial.getBookEditorialPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The BookEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(bookEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BookEditorial> findBookEditorialEntities() {
        return findBookEditorialEntities(true, -1, -1);
    }

    public List<BookEditorial> findBookEditorialEntities(int maxResults, int firstResult) {
        return findBookEditorialEntities(false, maxResults, firstResult);
    }

    private List<BookEditorial> findBookEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookEditorial.class));
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

    public BookEditorial findBookEditorial(BookEditorialPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookEditorial> rt = cq.from(BookEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
