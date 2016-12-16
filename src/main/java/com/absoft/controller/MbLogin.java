package com.absoft.controller;

import com.absoft.service.UsuarioService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class MbLogin implements Serializable {

    @Inject
    private UsuarioService service;

    @Getter
    @Setter
    private String usuario;

    @Getter
    @Setter
    private String senha;

    String pagina;

    public MbLogin() {
        pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("p");
    }

    public String efetuarLogin() {
        if (service.login(usuario, senha)) {
            // atualizarPermissoes();
            if (service.isSenhaPadrao(senha)) { //Verifica se o usuário está usando a senha padrão
                return "/app/usuario/perfil?faces-redirect=true&defaultPass=true";

            } else if ((pagina == null) || (pagina.equals("/"))) {
                return "/app/dashboard?faces-redirect=true";
            } else {
                return pagina + "?faces-redirect=true";
            }

        } else {
            return "";
        }

    }

    /**
     * Efetua logout do usuário do sistema
     *
     * @return retorna para página de login
     */
    public String efetuarLogout() {
        service.logout();
        return "/pub/login?faces-redirect=true";
    }

}
