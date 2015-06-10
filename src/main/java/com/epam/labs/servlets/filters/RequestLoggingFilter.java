package com.epam.labs.servlets.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

/**
 * Filter for performing authentication process
 *
 * @author zemluk
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {

    /**
     * Logger variable
     */
    private final Logger log = Logger.getLogger(getClass().getName());
    /**
     * Variable to save local content
     */
    private ServletContext context;

    /**
     * Method for basic filter initialization
     *
     * @param fConfig Filter configuration
     * @throws ServletException in case of servlet commands exceptions
     */
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        log.info("RequestLoggingFilter initialized");
    }

    /**
     * Method for performing access filtration to protected resources(perform authentication)
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @param chain    Chain which must checked
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Enumeration<String> params = req.getParameterNames();
        URI uri = null;
        try {
            uri = new URI(req.getRequestURI());
        } catch (URISyntaxException e) {
            log.info("Wrong url");
        }
        String path = uri.getPath();
        log.info("Requested Resource::" + uri);
        if ((path.endsWith("jsp") || path.endsWith("html"))) {
            log.info("Access denied: " + path);
            res.sendRedirect("/");
            return;
        }
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            log.info(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Method for destroying filter and closing all necessary resources
     */
    public void destroy() {
    }

}
