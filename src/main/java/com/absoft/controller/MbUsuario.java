package com.absoft.controller;

import com.absoft.repository.Usuarios;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego Arantes
 */
@Named
@ViewScoped
public class MbUsuario implements Serializable {
    @Inject Usuarios usuarios;
    
    
}
