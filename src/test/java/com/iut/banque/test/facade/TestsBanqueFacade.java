package com.iut.banque.test.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.iut.banque.constants.LoginConstants;
import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.exceptions.TechnicalException;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.facade.BanqueManager;
import com.iut.banque.facade.LoginManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestsBanqueFacade {

    @Mock
    private LoginManager loginManager;

    @Mock
    private BanqueManager banqueManager;

    @Mock
    private Utilisateur utilisateur;

    @Mock
    private Compte compte;

    @Mock
    private CompteAvecDecouvert compteAvecDecouvert;

    @Mock
    private Client client;

    @Mock
    private Gestionnaire gestionnaire;

    @InjectMocks
    private BanqueFacade banqueFacade;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void tryLoginTestAdmin() {
        when(loginManager.tryLogin("admin", "password")).thenReturn(LoginConstants.MANAGER_IS_CONNECTED);

        int result = banqueFacade.tryLogin("admin", "password");
        assertEquals(LoginConstants.MANAGER_IS_CONNECTED, result);
        verify(banqueManager).loadAllClients();
    }

    @Test
    public void tryLoginTestUser() {
        when(loginManager.tryLogin("user", "password")).thenReturn(LoginConstants.USER_IS_CONNECTED);

        int result = banqueFacade.tryLogin("user", "password");
        assertEquals(LoginConstants.USER_IS_CONNECTED, result);
        verify(banqueManager, never()).loadAllClients();
    }

    @Test
    public void tryLoginTestFailed() {
        when(loginManager.tryLogin("user", "password")).thenReturn(LoginConstants.LOGIN_FAILED);

        int result = banqueFacade.tryLogin("user", "password");
        assertEquals(LoginConstants.LOGIN_FAILED, result);
        verify(banqueManager, never()).loadAllClients();
    }

    @Test
    public void crediterTest() throws IllegalFormatException {
        banqueFacade.crediter(compte, 1000);
        verify(banqueManager).crediter(compte, 1000);
    }

    @Test
    public void debiterTest() throws InsufficientFundsException, IllegalFormatException {
        banqueFacade.debiter(compte, 1000);
        verify(banqueManager).debiter(compte, 1000);
    }

    @Test
    public void debiterTestPlusQuePossible() throws InsufficientFundsException, IllegalFormatException {
        doThrow(new InsufficientFundsException()).when(banqueManager).debiter(compte, 1000);
        try {
            banqueFacade.debiter(compte, 1000);
            fail();
        } catch (InsufficientFundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void debiterTestMontantNegatif() throws InsufficientFundsException, IllegalFormatException {
        doThrow(new IllegalFormatException()).when(banqueManager).debiter(compte, -1000);
        try {
            banqueFacade.debiter(compte, -1000);
            fail();
        } catch (IllegalFormatException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void logoutTest() {
        banqueFacade.logout();
        verify(loginManager).logout();
    }

    @Test
    public void createAccountTest() throws TechnicalException, IllegalFormatException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.createAccount("123", client);
        verify(banqueManager).createAccount("123", client);
    }

    @Test
    public void createAccountAvecDecouvertTest() throws TechnicalException, IllegalFormatException, IllegalOperationException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.createAccount("123", client, 1000.0);
        verify(banqueManager).createAccount("123", client, 1000.0);
    }

    @Test
    public void createAccountAvecDecouvertNegatifTest() throws TechnicalException, IllegalFormatException, IllegalOperationException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        doThrow(new IllegalOperationException()).when(banqueManager).createAccount("123", client, -1000.0);
        try {
            banqueFacade.createAccount("123", client, -1000.0);
            fail();
        } catch (IllegalOperationException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void deleteAccountTest() throws IllegalOperationException, TechnicalException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.deleteAccount(compte);
        verify(banqueManager).deleteAccount(compte);
    }

    @Test
    public void deleteAccountSoldeNonNulTest() throws IllegalOperationException, TechnicalException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        doThrow(new IllegalOperationException()).when(banqueManager).deleteAccount(compte);
        try {
            banqueFacade.deleteAccount(compte);
            fail();
        } catch (IllegalOperationException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void createManagerTest() throws TechnicalException, IllegalArgumentException, IllegalFormatException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.createManager("123", "password", "nom", "prenom", "adresse", true);
        verify(banqueManager).createManager("123", "password", "nom", "prenom", "adresse", true);
    }

    @Test
    public void createClientTest() throws TechnicalException, IllegalOperationException, IllegalFormatException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.createClient("123", "password", "nom", "prenom", "adresse", true, "numClient");
        verify(banqueManager).createClient("123", "password", "nom", "prenom", "adresse", true, "numClient");
    }

    @Test
    public void deleteUserTest() throws IllegalOperationException, TechnicalException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.deleteUser(utilisateur);
        verify(banqueManager).deleteUser(utilisateur);
    }

    @Test
    public void loadClientsTest() {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.loadClients();
        verify(banqueManager).loadAllClients();
    }

    @Test
    public void getCompteTest() {
        when(banqueManager.getAccountById("123")).thenReturn(compte);
        Compte result = banqueFacade.getCompte("123");
        assertEquals(compte, result);
    }

    @Test
    public void testChangeDecouvert() throws IllegalFormatException, IllegalOperationException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        banqueFacade.changeDecouvert(compteAvecDecouvert, 500.0);
        verify(banqueManager).changeDecouvert(compteAvecDecouvert, 500.0);
    }

    @Test
    public void testChangeDecouvertNegatif() throws IllegalFormatException, IllegalOperationException {
        when(loginManager.getConnectedUser()).thenReturn(gestionnaire);
        doThrow(new IllegalOperationException()).when(banqueManager).changeDecouvert(compteAvecDecouvert, -500.0);
        try {
            banqueFacade.changeDecouvert(compteAvecDecouvert, -500.0);
            fail();
        } catch (IllegalOperationException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getConnectedUserTest() {
        when(loginManager.getConnectedUser()).thenReturn(utilisateur);
        Utilisateur result = banqueFacade.getConnectedUser();
        assertEquals(utilisateur, result);
    }
}