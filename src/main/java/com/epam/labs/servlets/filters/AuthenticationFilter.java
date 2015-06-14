package com.epam.labs.servlets.filters;

import com.epam.labs.dao.DBException;
import com.epam.labs.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Filter for performing authentication process
 *
 * @author zemluk
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

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
        log.info("AuthenticationFilter initialized");
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
        URI uri = null;
        try {
            uri = new URI(req.getRequestURI());
        } catch (URISyntaxException e) {
            log.info("Wrong url");
        }
        String path = uri.getPath();
        log.info("Requested Resource::" + uri);
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            log.info("Unauthorized access request to: " + path);
            res.sendRedirect("/");
            return;
        } else {
            if (session != null) {
                UserDAO userDAO = UserDAO.getInstance();
                int idUser = (int) session.getAttribute("user");
                int idRole = 0;
                try {
                    idRole = userDAO.getUserRoleByID(idUser);
                } catch (DBException e) {
                    log.error("User with " + idUser + " change id or removed");
                    res.sendRedirect("/logOut");
                    return;
                }
                if (idRole == 1 && (path.equals("/administrator/orderManagement/")
                        || path.equals("/administrator/userManagement/")
                        || path.equals("/administrator/carManagement/"))) {

                    chain.doFilter(request, response);
                    return;
                }
                if (idRole == 2 && (path.equals("/user/customerOrders/"))) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    log.info("Unauthorized access request user: " + idUser);
                    res.sendRedirect("/");
                    return;
                }
            }
        }
    }

    /**
     * Method for destroying filter and closing all necessary resources
     */
    public void destroy() {
        //close any resources here
    }

}
