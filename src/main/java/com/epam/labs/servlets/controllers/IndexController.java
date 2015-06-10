package com.epam.labs.servlets.controllers;

import com.epam.labs.comands.AbstractCommand;
import com.epam.labs.comands.IndexCommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Servlet to perform basic root actions without any user role
 *
 * @author zemluk
 */
@WebServlet("/")
public class IndexController extends HttpServlet {
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
        IndexCommandFactory factory = IndexCommandFactory.getInstance();
        URI uri = null;
        try {
            uri = new URI(request.getRequestURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String path = uri.getPath();
        AbstractCommand abstractCommand = factory.getCommand(path);
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
