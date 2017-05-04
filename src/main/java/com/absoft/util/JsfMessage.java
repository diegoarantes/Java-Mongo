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

    FacesContext context = FacesContext.getCurrentInstance();

    public void info(String titulo, String msg) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg));
    }

    public void erro(String titulo, String msg) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, msg));
    }

    public void advertencia(String titulo, String msg) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, msg));
    }
}
