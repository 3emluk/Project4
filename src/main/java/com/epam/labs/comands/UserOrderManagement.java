package com.epam.labs.comands;

import com.epam.labs.POJO.CarOrder;
import com.epam.labs.POJO.CarOrderView;
import com.epam.labs.dao.CarDAO;
import com.epam.labs.dao.CarOrderDAO;
import com.epam.labs.dao.DBException;
import com.epam.labs.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class for gathering user order servlet commands
 *
 * @author zemluk
 */
public class UserOrderManagement {
    /**
     * Main page of servlet
     */
    public final String SOURCE_PAGE = "/user/customerOrders/";
    /**
     * Add user order form location
     */
    public final String ADD_PAGE = "/orderForm.jsp";

    /**
     * Inner class with default command implementation
     */
    public class DefaultCommand extends AbstractCommand {
        /**
         * Method override to perform delete user order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user order default command");
            CarOrderDAO carOrderDAO = new CarOrderDAO();
            CarDAO carDAO = new CarDAO();
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession(false);
            ArrayList<CarOrder> tmp = (ArrayList<CarOrder>) carOrderDAO.getOrdersForUser((int) session.getAttribute("user"));
            ArrayList<CarOrderView> viewList = new ArrayList<>(tmp.size());
            for (CarOrder co : tmp) {
                viewList.add(new CarOrderView(co, userDAO.getByID(co.getIdUser()), carDAO.getByID(co.getIdCar())));
            }
            request.setAttribute("orders", viewList);
            request.getRequestDispatcher("/orderManagement.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with create request command implementation
     */
    public class CreateControllerCommand extends AbstractCommand {
        /**
         * Method override to perform create user order request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user order create command");
            CarDAO carDAO = new CarDAO();
            UserDAO userDAO = new UserDAO();
            request.setAttribute("cars", carDAO.getAllAvaliable());
            request.setAttribute("action", "create");
            request.getRequestDispatcher(ADD_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with create command implementation
     */
    public class CreateHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform create user order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user order save command");
            CarOrder carOrder = new CarOrder();
            Date startDate = null;
            Date endDate = null;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                startDate = format.parse(request.getParameter("startDate"));
                endDate = format.parse(request.getParameter("endDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            carOrder.setIdCar(Integer.parseInt(request.getParameter("idCar")));
            carOrder.setStartDate(startDate);
            carOrder.setDuration(Integer.parseInt(request.getParameter("duration")));
            carOrder.setEndDate(endDate);
            HttpSession session = request.getSession(false);
            carOrder.setIdUser((int) session.getAttribute("user"));
            carOrder.setIsConfirmed(false);
            carOrder.setComment(request.getParameter("comment"));
            CarOrderDAO userDAO = new CarOrderDAO();
            userDAO.saveEntity(carOrder);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

}
