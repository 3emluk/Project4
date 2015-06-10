package com.epam.labs.comands;

import com.epam.labs.POJO.Car;
import com.epam.labs.dao.CarDAO;
import com.epam.labs.dao.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for gathering car servlet commands
 *
 * @author zemluk
 */
public class CarManagement {
    /**
     * Attribute name in request with car entity id
     */
    public final String ID_VALUE = "id";
    /**
     * Main page of servlet
     */
    public final String SOURCE_PAGE = "/administrator/carManagement/";
    /**
     * Add car form location
     */
    public final String ADD_PAGE = "/carForm.jsp";
    /**
     * Modify car form location
     */
    public final String MODIFY_PAGE = "/carForm.jsp";

    /**
     * Inner class with default command implementation
     */
    public class DefaultCommand extends AbstractCommand {

        /**
         * Method override to perform default car command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing default car command");
            CarDAO carDAO = new CarDAO();
            request.setAttribute("cars", carDAO.getAll());
            request.getRequestDispatcher("/carManagement.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with delete command implementation
     */
    public class DeleteCommand extends AbstractCommand {

        /**
         * Method override to perform delete car command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing delete car command");
            int id = Integer.parseInt(request.getParameter(ID_VALUE));
            CarDAO carDAO = new CarDAO();
            carDAO.deleteEntity(id);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with create request command implementation
     */
    public class CreateControllerCommand extends AbstractCommand {

        /**
         * Method override to perform create car request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.info("Executing car creation command");
            request.setAttribute("action", "create");
            request.getRequestDispatcher(ADD_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with create command implementation
     */
    public class CreateHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform create car command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing car saving command");
            Car car = new Car();
            car.setModel(request.getParameter("model"));
            car.setManufacturer(request.getParameter("manufacturer"));
            car.setSign(request.getParameter("sign"));
            car.setPrice(Integer.parseInt(request.getParameter("price")));
            car.setIsAvaliable(Boolean.parseBoolean(request.getParameter("isAvaliable")));
            CarDAO userDAO = new CarDAO();
            userDAO.saveEntity(car);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with modify request command implementation
     */
    public class ModifyControllerCommand extends AbstractCommand {
        /**
         * Method override to perform modify car request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing car modifying command");
            CarDAO userDAO = new CarDAO();
            Car car = userDAO.getByID(Integer.parseInt((request.getParameter("id"))));
            request.setAttribute("car", car);
            request.setAttribute("action", "modify");
            request.getRequestDispatcher(MODIFY_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with modify command implementation
     */
    public class ModifyHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform modify car command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing car updating command");
            Car car = new Car();
            car.setId(Integer.parseInt(request.getParameter("id")));
            car.setModel(request.getParameter("model"));
            car.setManufacturer(request.getParameter("manufacturer"));
            car.setSign(request.getParameter("sign"));
            car.setPrice(Integer.parseInt(request.getParameter("price")));
            car.setIsAvaliable(Boolean.parseBoolean(request.getParameter("isAvaliable")));
            CarDAO userDAO = new CarDAO();
            userDAO.updateEntity(car);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

}
