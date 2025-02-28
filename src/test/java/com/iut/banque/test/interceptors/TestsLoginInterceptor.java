package com.iut.banque.test.interceptors;

import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.interceptors.LoginInterceptor;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.junit.After;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestsLoginInterceptor {

    private LoginInterceptor interceptor;
    private ActionInvocation invocationMock;
    private ApplicationContext appContextMock;
    private ServletContext servletContextMock;
    private BanqueFacade banqueFacadeMock;
    private ActionContext actionContextMock;
    private MockedStatic<ServletActionContext> mockedStatic;

    @Before
    public void setUp() {
        interceptor = new LoginInterceptor();
        invocationMock = mock(ActionInvocation.class);
        appContextMock = mock(ApplicationContext.class);
        servletContextMock = mock(ServletContext.class);
        banqueFacadeMock = mock(BanqueFacade.class);
        actionContextMock = mock(ActionContext.class);

        mockedStatic = Mockito.mockStatic(ServletActionContext.class);
        mockedStatic.when(ServletActionContext::getServletContext).thenReturn(servletContextMock);

        when(servletContextMock.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT"))
                .thenReturn(appContextMock);
        when(appContextMock.getBean("banqueFacade")).thenReturn(banqueFacadeMock);

        when(invocationMock.getInvocationContext()).thenReturn(actionContextMock);
    }

    @Test
    public void testInterceptLoginAction() throws Exception {
        when(actionContextMock.getName()).thenReturn("controller.Connect.login");

        String result = interceptor.intercept(invocationMock);

        verify(invocationMock, times(1)).invoke();
        assertEquals(null, result);
    }

    @Test
    public void testInterceptRedirectToLoginWhenNotAuthenticated() throws Exception {
        when(actionContextMock.getName()).thenReturn("someSensitiveAction");
        when(banqueFacadeMock.getConnectedUser()).thenReturn(null);

        String result = interceptor.intercept(invocationMock);

        assertEquals("redirectToLogin", result);
    }

    @Test
    public void testInterceptSensitiveActionAsNonManager() throws Exception {
        Utilisateur nonManagerUser = mock(Utilisateur.class);
        when(actionContextMock.getName()).thenReturn("retourTableauDeBordManager");
        when(banqueFacadeMock.getConnectedUser()).thenReturn(nonManagerUser);

        String result = interceptor.intercept(invocationMock);

        assertEquals("redirectToLogin", result);
    }

    @Test
    public void testInterceptSensitiveActionAsManager() throws Exception {
        Gestionnaire managerUser = mock(Gestionnaire.class);
        when(actionContextMock.getName()).thenReturn("retourTableauDeBordManager");
        when(banqueFacadeMock.getConnectedUser()).thenReturn(managerUser);

        String result = interceptor.intercept(invocationMock);

        verify(invocationMock, times(1)).invoke();
        assertEquals(null, result);
    }

   @After 
    public void tearDown() {
        mockedStatic.close();
    }
}