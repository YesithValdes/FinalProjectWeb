/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa;

import entities.AuthorEditorial;
import entities.AuthorEditorialPK;
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
public class AuthorEditorialJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public AuthorEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuthorEditorial authorEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(authorEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuthorEditorial authorEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            authorEditorial = em.merge(authorEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AuthorEditorialPK id = authorEditorial.getAuthorEditorialPK();
                if (findAuthorEditorial(id) == null) {
                    throw new NonexistentEntityException("The AuthorEditorial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AuthorEditorialPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuthorEditorial authorEditorial;
            try {
                authorEditorial = em.getReference(AuthorEditorial.class, id);
                authorEditorial.getAuthorEditorialPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The AuthorEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(authorEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuthorEditorial> findAuthorEditorialEntities() {
        return findAuthorEditorialEntities(true, -1, -1);
    }

    public List<AuthorEditorial> findAuthorEditorialEntities(int maxResults, int firstResult) {
        return findAuthorEditorialEntities(false, maxResults, firstResult);
    }

    private List<AuthorEditorial> findAuthorEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuthorEditorial.class));
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

    public AuthorEditorial findAuthorEditorial(AuthorEditorialPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuthorEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthorEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuthorEditorial> rt = cq.from(AuthorEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
