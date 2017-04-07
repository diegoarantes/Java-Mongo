package com.absoft.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsfRedirectStrategy {

    /**
     * Redirects the response to the supplied URL.
     * <p>
     * If <tt>contextRelative</tt> is set, the redirect value will be the value
     * after the request context path. Note that this will result in the loss of
     * protocol information (HTTP or HTTPS), so will cause problems if a
     * redirect is being performed to change to HTTPS, for example.
     */
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String redirectUrl = url;
        redirectUrl = response.encodeRedirectURL(redirectUrl);

        //we should redirect using ajax response if the case warrants
        boolean ajaxRedirect = request.getHeader("faces-request") != null
                && request.getHeader("faces-request").toLowerCase().indexOf("ajax") > -1;

        if (ajaxRedirect) {
            String ajaxRedirectXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                                   + "<partial-response><redirect url=\"" + redirectUrl + "\"></redirect></partial-response>";
            response.setContentType("text/xml");
            response.getWriter().write(ajaxRedirectXml);
        } else {
            response.sendRedirect(redirectUrl);
        }

    }

}
