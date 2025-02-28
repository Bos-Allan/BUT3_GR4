package com.iut.banque.test.modele;

import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.exceptions.IllegalFormatException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsGestionnaire {

    @Test
    public void testGetEtSetNom() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");
        gestionnaire.setNom("Durand");
        assertEquals("Durand", gestionnaire.getNom());
    }

    @Test
    public void testGetEtSetPrenom() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");
        gestionnaire.setPrenom("Paul");
        assertEquals("Paul", gestionnaire.getPrenom());
    }

    @Test
    public void testGetEtSetAdresse() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");
        gestionnaire.setAdresse("4 Rue la vie");
        assertEquals("4 Rue la vie", gestionnaire.getAdresse());
    }

    @Test
    public void testIsEtSetMale() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");
        gestionnaire.setMale(false);
        assertFalse(gestionnaire.isMale());
    }

    @Test
    public void testConstructorWithParameters() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");

        assertEquals("Dupont", gestionnaire.getNom());
        assertEquals("Jean", gestionnaire.getPrenom());
        assertEquals("4 Rue la vie", gestionnaire.getAdresse());
        assertTrue(gestionnaire.isMale());
        assertEquals("manager01", gestionnaire.getUserId());
        assertEquals("password123", gestionnaire.getUserPwd());
    }

    @Test
    public void testConstructorWithoutParameters() {
        Gestionnaire gestionnaire = new Gestionnaire();
        assertNotNull("Gestionnaire instance should not be null", gestionnaire);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyUsrId() throws IllegalFormatException {
        new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "", "password123");
    }

    @Test
    public void testToString() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "4 Rue la vie", true, "manager01", "password123");
        String expected = "Gestionnaire [nom=Dupont, prenom=Jean, adresse=4 Rue la vie, male=true, userId=manager01, userPwd=password123]";
        assertEquals(expected, gestionnaire.toString());
    }
}
