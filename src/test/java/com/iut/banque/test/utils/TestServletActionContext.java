package com.iut.banque.test.utils;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletContext;


public class TestServletActionContext {
    private static ServletContext servletContext;

    public static void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }
}

