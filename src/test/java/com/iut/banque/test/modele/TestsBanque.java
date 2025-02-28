package com.iut.banque.test.modele;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.modele.Banque;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.Gestionnaire;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestsBanque {

    private Banque banque;
    @Mock
    private Compte compteMock;

    @Mock
    private CompteAvecDecouvert compteDecouvertMock;

    @Mock
    private Client clientMock;

    @Mock
    private Gestionnaire gestionnaireMock;

    @Before
    public void setUp() {

        banque = new Banque();
        compteMock = mock(Compte.class);
        compteDecouvertMock = mock(CompteAvecDecouvert.class);
        clientMock = mock(Client.class);

    }

    @Test
    public void testSetAndGetClients() {
        Map<String, Client> clients = new HashMap<>();
        clients.put("client1", clientMock);

        banque.setClients(clients);
        assertEquals("The clients map should match", clients, banque.getClients());
    }

    @Test
    public void testSetAndGetGestionnaires() {
        Map<String, Gestionnaire> gestionnaires = new HashMap<>();
        gestionnaires.put("gestionnaire1", gestionnaireMock);

        banque.setGestionnaires(gestionnaires);
        assertEquals("The gestionnaires map should match", gestionnaires, banque.getGestionnaires());
    }

    @Test
    public void testSetAndGetAccounts() {
        Map<String, Compte> accounts = new HashMap<>();
        accounts.put("account1", compteMock);

        banque.setAccounts(accounts);
        assertEquals("The accounts map should match", accounts, banque.getAccounts());
    }

    @Test
    public void testDebiterCompte() throws InsufficientFundsException, IllegalFormatException {
        banque.debiter(compteMock, 100.0);

        verify(compteMock, times(1)).debiter(100.0);
    }

    @Test
    public void testDebiterCompteFondsInsuffisants() throws InsufficientFundsException, IllegalFormatException {
        doThrow(new InsufficientFundsException()).when(compteMock).debiter(100.0);

        try {
            banque.debiter(compteMock, 100.0);
            fail("InsufficientFundsException should have been thrown");
        } catch (InsufficientFundsException e) {
            //
        }
    }

    @Test
    public void testCrediterCompte() throws IllegalFormatException {
        banque.crediter(compteMock, 100.0);

        verify(compteMock, times(1)).crediter(100.0);
    }

    @Test
    public void testCrediterCompteDecouvert() throws IllegalFormatException {
        banque.crediter(compteDecouvertMock,  100.0);

        verify(compteDecouvertMock, times(1)).crediter(100.0);
    }

    @Test
    public void testDeleteUser(){
        Map<String, Client> clients = new HashMap<>();
        clients.put("client1", clientMock);
        banque.setClients(clients);

        banque.deleteUser("client1");
        assertFalse(banque.getClients().containsKey("client1"));
    }

    @Test
    public void testChangeDecouvert() throws IllegalFormatException, IllegalOperationException {
        banque.changeDecouvert(compteDecouvertMock,1000.0);
        verify(compteDecouvertMock, times(1)).setDecouverAutorise(1000.0);
    }


}
