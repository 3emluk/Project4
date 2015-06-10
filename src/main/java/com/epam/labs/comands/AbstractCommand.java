package com.epam.labs.comands;

import com.epam.labs.dao.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for command pattern basic implementation
 *
 * @author zemluk
 */
public abstract class AbstractCommand {
    /**
     * Logger variable
     */
    protected final Logger log = Logger.getLogger(getClass().getName());
    /**
     * Attribute name with error message
     */
    public final String ERROR_MESSAGE = "msg";
    /**
     * Location of error page
     */
    public final String ERROR_PAGE = "/error.jsp";

    /**
     * Method for executing specified command
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            doOperation(request, response);
        } catch (DBException dbE) {
            request.setAttribute(ERROR_MESSAGE, dbE);
            try {
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                log.error("DB exception caught :" + dbE);
            } catch (ServletException | IOException e) {
                log.error("Error forwarding to error page :" + e);
            }
        } catch (Exception ex) {
            request.setAttribute(ERROR_MESSAGE, ex);
            try {
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                log.error("Serious problem occurred :" + ex);
            } catch (ServletException | IOException e) {
                log.error("Error forwarding to error page :" + e);
            }

        }

    }

    /**
     * Method for specified command injection
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     * @throws DBException      in case database exceptions
     */
    protected abstract void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException;

}
