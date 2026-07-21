package com.herotozero.dao;

import com.herotozero.entity.EmissionEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

@ApplicationScoped
public class EmissionDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("herotozero PU");

    private EntityManager em() {
        return emf.createEntityManager();
    }

   
    public List<EmissionEntry> findAllApproved() {
        EntityManager em = em();
        try {
            return em.createQuery(
                "SELECT e FROM EmissionEntry e WHERE e.approved = true ORDER BY e.country",
                EmissionEntry.class).getResultList();
        } finally {
            em.close();
        }
    }

   
    public EmissionEntry findLatestByCountryCode(String countryCode) {
        EntityManager em = em();
        try {
            List<EmissionEntry> results = em.createQuery(
                "SELECT e FROM EmissionEntry e WHERE e.approved = true " +
                "AND UPPER(e.countryCode) = UPPER(:code) ORDER BY e.year DESC",
                EmissionEntry.class)
                .setParameter("code", countryCode)
                .setMaxResults(1)
                .getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public List<EmissionEntry> findAllPending() {
        EntityManager em = em();
        try {
            return em.createQuery(
                "SELECT e FROM EmissionEntry e WHERE e.approved = false ORDER BY e.country",
                EmissionEntry.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void approve(Long id) {
        EntityManager em = em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            EmissionEntry entry = em.find(EmissionEntry.class, id);
            entry.setApproved(true);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Fehler beim Genehmigen", e);
        } finally {
            em.close();
        }
    }

    public void save(EmissionEntry entry) {
        EntityManager em = em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entry);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Fehler beim Speichern", e);
        } finally {
            em.close();
        }
    }
}
