package com.absoft.service;

import com.absoft.model.Usuario;
import com.absoft.repository.Usuarios;
import com.absoft.util.ConverterSHA1;
import com.absoft.util.JsfMessage;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego Arantes
 */
@RequestScoped
public class UsuarioService {

    @Inject
    private Usuarios usuarios;

    @Inject
    private JsfMessage message;

    public boolean login(String user, String pass) {

        //Verifica Usuario e senha na Base
        Usuario usu = usuarios.verificarUsuario(user, ConverterSHA1.cipher(pass));

        if (usu != null && usu.isAtivo()) { //Se usuário for diferente de Nulo e está ativo
            //Adiciona o usuário logado na sessão
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usu);

            //Seta que o usuário está online
            usu.setOnline(true);

            //Seta o IP
            usu.setIpUltimoLogon(retornaIP());

            //Seta a data e hora
            usu.setHoraUltimoLogon(new Date());

            usuarios.save(usu);

            //Registra o Log
            //logService.registraLogin(usu, "Login efetuado. IP: " + retornaIP());
            return true;

        } else if (usu != null && !usu.isAtivo()) {//Se usuário for diferente de Nulo e está Inativo 
            message.advertencia(null, "Usuário Inativo!");
            return false;
        } else {
            message.erro(null, "Usuário ou senha inválidos!");
            return false;
        }

    }

    public void logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
    }

    public Usuario usuarioLogado() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public boolean isSenhaPadrao(String senha) {
        return senha.equals("mudar.123");
    }

    private String retornaIP() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getHeader("X_FORWARDED_FOR");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }

}
