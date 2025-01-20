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

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation inv) throws Exception {
        ActionContext context = inv.getInvocationContext();
        String actionName = context.getName();
        System.out.println("Interceptor invoked for action: " + actionName);

        // Actions accessibles sans vérification
        if (actionName.equalsIgnoreCase("redirectionLogin") || actionName.equalsIgnoreCase("controller.Connect.login")) {
            return inv.invoke();
        }

        // Récupérer l'utilisateur connecté via BanqueFacade
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
        BanqueFacade banque = (BanqueFacade) appContext.getBean("banqueFacade");
        Utilisateur user = banque.getConnectedUser();

        // Vérifier si l'utilisateur est connecté
        if (user == null) {
            System.out.println("User is not authenticated, redirecting to login.");
            return "redirectToLogin";
        }

        // Vérifier si l'utilisateur est un gestionnaire pour les actions sensibles
        if (isSensitiveAction(actionName) && !(user instanceof Gestionnaire)) {
            System.out.println("Access denied: User is not a manager.");
            return "redirectToLogin";
        }

        System.out.println("User is authenticated and authorized.");
        return inv.invoke();
    }

    // Méthode pour identifier les actions sensibles nécessitant un gestionnaire
    private boolean isSensitiveAction(String actionName) {
        return actionName.equalsIgnoreCase("retourTableauDeBordManager") ||
                actionName.equalsIgnoreCase("listeCompteManager") ||
                actionName.equalsIgnoreCase("urlAjoutUtilisateur");
    }
}
