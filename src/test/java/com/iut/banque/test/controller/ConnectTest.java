import com.iut.banque.constants.LoginConstants;
import com.iut.banque.controller.Connect;
import com.iut.banque.exceptions.IllegalFormatException; 
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectTest {

    @Mock
    private BanqueFacade banqueFacade;

    @InjectMocks
    private Connect connect;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginAsAdmin() throws IllegalFormatException { 
        Gestionnaire admin = new Gestionnaire("Admin", "User", "123 Admin Goat", true, "admin.user", "admin123");

        when(banqueFacade.tryLogin("admin.user", "admin123")).thenReturn(LoginConstants.MANAGER_IS_CONNECTED);
        when(banqueFacade.getConnectedUser()).thenReturn(admin);

        connect.setUserCde("admin.user");
        connect.setUserPwd("admin123");

        String result = connect.login();

        assertEquals("SUCCESSMANAGER", result, "L'utilisateur devrait être connecté en tant qu'admin");

        Utilisateur connectedUser = connect.getConnectedUser();
        assertTrue(connectedUser instanceof Gestionnaire, "L'utilisateur connecté devrait être un admin");
    }

    @Test
    public void testLoginFailure() throws IllegalFormatException { 
        when(banqueFacade.tryLogin("wrong.user", "wrong.pwd")).thenReturn(LoginConstants.LOGIN_FAILED);

        connect.setUserCde("wrong.user");
        connect.setUserPwd("wrong.pwd");

        String result = connect.login();

        assertEquals("ERROR", result, "La connexion devrait échouer avec des mauvais identifiants");
    }

    @Test
    public void testLogout() {
        doNothing().when(banqueFacade).logout();

        String result = connect.logout();

        assertEquals("SUCCESS", result, "La déconnexion devrait réussir");

        verify(banqueFacade, times(1)).logout();
    }
}