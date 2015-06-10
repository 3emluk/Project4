package com.epam.labs.servlets.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter for changing encoding of received data
 *
 * @author zemluk
 */
@WebFilter("/CharsetFilter")
public class CharsetFilter implements Filter {
    /**
     * Logger variable
     */
    private final Logger log = Logger.getLogger(getClass().getName());
    /**
     * Variable to save desired encoding
     */
    private String encoding;

    /**
     * Method for basic filter initialization
     *
     * @param config Filter configuration
     * @throws ServletException in case of servlet commands exceptions
     */
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    /**
     * Method for performing access filtration to protected resources(perform authentication)
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @param next     Chain which must checked
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding + "\"");
        log.info("Charset set to:" + encoding);
        next.doFilter(request, response);
    }

    /**
     * Method for destroying filter and closing all necessary resources
     */
    public void destroy() {
    }
}