package com.iut.banque.interceptors;

import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.modele.Utilisateur;
import com.iut.banque.modele.Gestionnaire;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LoginInterceptor implements Interceptor {

    // Actions accessibles sans vérification
    private static final String LOGIN_ACTION = "controller.Connect.login";
    private static final String REDIRECT_LOGIN_ACTION = "redirectionLogin";

    // Actions sensibles nécessitant un gestionnaire
    private static final String ACTION_MANAGER_DASHBOARD = "retourTableauDeBordManager";
    private static final String ACTION_LIST_ACCOUNTS = "listeCompteManager";
    private static final String ACTION_ADD_USER = "urlAjoutUtilisateur";

    @Override
    public void destroy() {
        // Aucun nettoyage spécifique requis
    }

    @Override
    public void init() {
        // Aucune initialisation spécifique requise
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext context = invocation.getInvocationContext();
        String actionName = context.getName();
        System.out.println("Interceptor invoked for action: " + actionName);

        // Vérifier si l'action est publique
        if (isPublicAction(actionName)) {
            return invocation.invoke();
        }

        // Récupérer BanqueFacade depuis le contexte Spring
        BanqueFacade banque;
        try {
            ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
            banque = (BanqueFacade) appContext.getBean("banqueFacade");
        } catch (Exception e) {
            System.err.println("Error retrieving BanqueFacade from Spring context: " + e.getMessage());
            return "redirectToLogin";
        }

        // Vérifier si l'utilisateur est connecté
        Utilisateur user = banque.getConnectedUser();
        if (user == null) {
            System.out.println("User is not authenticated, redirecting to login.");
            return "redirectToLogin";
        }

        // Vérifier si l'utilisateur est autorisé pour des actions sensibles
        if (isSensitiveAction(actionName) && !(user instanceof Gestionnaire)) {
            System.out.println("Access denied: User is not a manager.");
            return "redirectToLogin";
        }

        System.out.println("User is authenticated and authorized for action: " + actionName);
        return invocation.invoke();
    }

    /**
     * Vérifie si une action est publique (accessible sans authentification).
     */
    private boolean isPublicAction(String actionName) {
        return LOGIN_ACTION.equalsIgnoreCase(actionName) || REDIRECT_LOGIN_ACTION.equalsIgnoreCase(actionName);
    }

    /**
     * Vérifie si une action est sensible et nécessite un gestionnaire.
     */
    private boolean isSensitiveAction(String actionName) {
        return ACTION_MANAGER_DASHBOARD.equalsIgnoreCase(actionName) ||
                ACTION_LIST_ACCOUNTS.equalsIgnoreCase(actionName) ||
                ACTION_ADD_USER.equalsIgnoreCase(actionName);
    }
}
