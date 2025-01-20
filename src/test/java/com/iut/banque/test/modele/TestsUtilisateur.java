package com.iut.banque.test.modele;

import com.iut.banque.modele.Utilisateur;
import com.iut.banque.exceptions.IllegalFormatException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsUtilisateur {

    @Test
    public void testConstructorWithParameters() throws IllegalFormatException {
        Utilisateur utilisateur = new Utilisateur("Dupont", "Jean", "123 Rue de Paris", true, "user01", "password123") {
        };

        assertEquals("Dupont", utilisateur.getNom());
        assertEquals("Jean", utilisateur.getPrenom());
        assertEquals("123 Rue de Paris", utilisateur.getAdresse());
        assertTrue(utilisateur.isMale());
        assertEquals("user01", utilisateur.getUserId());
        assertEquals("password123", utilisateur.getUserPwd());
    }

    @Test
    public void testConstructorWithoutParameters() {
        Utilisateur utilisateur = new Utilisateur() {
        };
        assertNotNull("Utilisateur instance should not be null", utilisateur);
    }

    @Test
    public void testSetAndGetNom() {
        Utilisateur utilisateur = new Utilisateur() {
        };
        utilisateur.setNom("Dupont");
        assertEquals("Dupont", utilisateur.getNom());
    }

    @Test
    public void testSetAndGetPrenom() {
        Utilisateur utilisateur = new Utilisateur() {
        };
        utilisateur.setPrenom("Jean");
        assertEquals("Jean", utilisateur.getPrenom());
    }

    @Test
    public void testToString() {
        Utilisateur utilisateur = new Utilisateur("Dupont", "Jean", "123 Rue de Paris", true, "user01", "password123") {
        };
        String expected = "Utilisateur [userId=user01, nom=Dupont, prenom=Jean, adresse=123 Rue de Paris, male=true, userPwd=password123]";
        assertEquals(expected, utilisateur.toString());
    }
}
