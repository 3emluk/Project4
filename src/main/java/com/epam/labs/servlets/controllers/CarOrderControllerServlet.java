
package com.epam.labs.servlets.controllers;

import com.epam.labs.comands.AbstractCommand;
import com.epam.labs.comands.OrderCommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to perform order management for administrator user role
 *
 * @author zemluk
 */
@WebServlet("/administrator/orderManagement/*")
public class CarOrderControllerServlet extends HttpServlet {
    /**
     * Attribute which describes parameter name at request on which depends future command
     */
    public final static String COMMAND_PARAMETER = "action";

    /**
     * Method for processing both POST and GET requests by one command factory
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderCommandFactory factory = OrderCommandFactory.getInstance();
        AbstractCommand abstractCommand = factory.getCommand(request.getParameter(COMMAND_PARAMETER));
        abstractCommand.execute(request, response);
    }

    /**
     * Method for processing GET requests
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method for processing POST requests
     *
     * @param request  Request from servlet
     * @param response Response from servlet
     * @throws ServletException in case of servlet commands exceptions
     * @throws IOException      in case of failed to find specified page
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
