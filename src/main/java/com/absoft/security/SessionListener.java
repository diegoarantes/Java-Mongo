package com.absoft.security;

import com.absoft.model.Usuario;
import com.absoft.repository.Usuarios;
import java.io.Serializable;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Diego Arantes
 */
public class SessionListener implements HttpSessionListener, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    private Usuario usuario;

    private HttpSession session;

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        //  System.out.println("Sessão Criada !!!!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        session = hse.getSession();
        usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            usuario = usuarios.findById(usuario.getId()); //Recupera a ultima versão dos dados do usuário

            //Retorna o usuário para offline
            usuario.setOnline(false);
            usuarios.save(usuario);

            //   System.out.println("Sessão Destruída !!!! (" + usuario.getUsuario() + ")");
        }

    }

}
