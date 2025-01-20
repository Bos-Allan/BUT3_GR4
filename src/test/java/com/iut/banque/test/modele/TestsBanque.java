package com.iut.banque.test.modele;

import com.iut.banque.modele.Banque;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.Gestionnaire;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestsBanque {

    private Banque banque;

    @Before
    public void setUp() {
        banque = new Banque();
    }

    @Test
    public void testSetAndGetClients() {
        Map<String, Client> clients = new HashMap<>();
        Client client = new Client();
        clients.put("client1", client);

        banque.setClients(clients);
        assertEquals("The clients map should match", clients, banque.getClients());
    }

    @Test
    public void testSetAndGetGestionnaires() {
        Map<String, Gestionnaire> gestionnaires = new HashMap<>();
        Gestionnaire gestionnaire = new Gestionnaire();
        gestionnaires.put("gestionnaire1", gestionnaire);

        banque.setGestionnaires(gestionnaires);
        assertEquals("The gestionnaires map should match", gestionnaires, banque.getGestionnaires());
    }

    @Test
    public void testSetAndGetAccounts() {
        Map<String, Compte> accounts = new HashMap<>();
        Compte compte = new CompteAvecDecouvert(); // Utiliser une classe concrète
        accounts.put("account1", compte);

        banque.setAccounts(accounts);
        assertEquals("The accounts map should match", accounts, banque.getAccounts());
    }

}
