package com.absoft.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class JsfMessage {

    public static void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
    }

    public static void erro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
    }

    public static void advertencia(String msg) {
        FacesContext.getCurrentInstance().addMessage("",
                new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));
    }
}
