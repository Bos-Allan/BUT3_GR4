package com.iut.banque.test.modele;

import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.exceptions.IllegalFormatException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsGestionnaire {

    @Test
    public void testConstructorWithParameters() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "123 Rue de Paris", true, "manager01", "password123");

        assertEquals("Dupont", gestionnaire.getNom());
        assertEquals("Jean", gestionnaire.getPrenom());
        assertEquals("123 Rue de Paris", gestionnaire.getAdresse());
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
        new Gestionnaire("Dupont", "Jean", "123 Rue de Paris", true, "", "password123");
    }

    @Test
    public void testToString() throws IllegalFormatException {
        Gestionnaire gestionnaire = new Gestionnaire("Dupont", "Jean", "123 Rue de Paris", true, "manager01", "password123");
        String expected = "Gestionnaire [nom=Dupont, prenom=Jean, adresse=123 Rue de Paris, male=true, userId=manager01, userPwd=password123]";
        assertEquals(expected, gestionnaire.toString());
    }
}
