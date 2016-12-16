package com.absoft.security;

import com.absoft.model.Usuario;
import com.absoft.repository.Usuarios;
import com.absoft.util.JsfRedirectStrategy;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego Arantes
 */
public class LoginFilter implements Filter {

    @Inject
    private Usuarios usuarios;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Verifica se a sessão não expirou, se sim volta para a página de login
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        //Verifica se tem usuário na sessão
        if (session != null && !session.isNew() && session.getAttribute("user") != null) {
            // usuarioConectado(request, response); //Verifica se o usuário foi desconectado
            chain.doFilter(request, response);
        } else {
            //Retorna para a página de login
            new JsfRedirectStrategy().sendRedirect((HttpServletRequest) request, (HttpServletResponse) response,
                    "/login?p=" + retornaPaginaSolicitada(request));
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private String retornaPaginaSolicitada(ServletRequest request) {
        String pagina = ((HttpServletRequest) request).getRequestURI();
        return pagina;
    }

    //Algorithmo para desconectar usuário automaticamente
    private void usuarioConectado(ServletRequest request, ServletResponse response) throws IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            usuario = (Usuario) usuarios.findById(usuario.getId()); //Retorna os ultimos dados do usuario pelo ID

            System.out.println(usuario.getNome());
            if (!usuario.isOnline()) {

                //Invalida a Sessão
                session.invalidate();
            }
        }
    }
}
