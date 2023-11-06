package fr.efrei.apptrack.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ApprentiSessionBean {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("APPRENTRACK_PU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private Query requete;

    public List<Apprenti> getTousLesApprentis(){
        requete = entityManager.createNamedQuery("recupererTousLesApprentis");
        return  requete.getResultList();
    }

    public Apprenti getApprentiParId(int id) {
        requete = entityManager.createNamedQuery("recupererUnApprenti" );
        requete.setParameter("id", id);
        return (Apprenti) requete.getSingleResult();
    }

    public void supprimerApprenti(Apprenti apprentiASupprimer) {
        entityManager.getTransaction().begin();
        entityManager.remove(apprentiASupprimer);
        entityManager.getTransaction().commit();
    }

    public void modifierApprenti(Apprenti apprentiAModifier) {
        entityManager.getTransaction().begin();
        entityManager.merge(apprentiAModifier);
        entityManager.getTransaction().commit();
    }

    public void ajouterApprenti(Apprenti nouvelApprenti) {
        entityManager.getTransaction().begin();
        entityManager.persist(nouvelApprenti);
        entityManager.getTransaction().commit();
    }
}