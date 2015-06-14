package com.epam.labs.comands;

import com.epam.labs.POJO.Car;
import com.epam.labs.POJO.CarOrder;
import com.epam.labs.POJO.CarOrderView;
import com.epam.labs.POJO.User;
import com.epam.labs.dao.CarDAO;
import com.epam.labs.dao.CarOrderDAO;
import com.epam.labs.dao.DBException;
import com.epam.labs.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class for gathering order servlet commands
 *
 * @author zemluk
 */
public class OrderManagement {
    /**
     * Attribute name in request with order entity id
     */
    public final String ID_VALUE = "id";
    /**
     * Main page of servlet
     */
    public final String SOURCE_PAGE = "/administrator/orderManagement/";
    /**
     * Add order form location
     */
    public final String ADD_PAGE = "/orderForm.jsp";
    /**
     * Modify order form location
     */
    public final String MODIFY_PAGE = "/orderForm.jsp";

    /**
     * Inner class with default command implementation
     */
    public class DefaultCommand extends AbstractCommand {
        /**
         * Method override to perform default order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders default command");
            CarOrderDAO carOrderDAO = CarOrderDAO.getInstance();
            CarDAO carDAO = CarDAO.getInstance();
            UserDAO userDAO = UserDAO.getInstance();
            ArrayList<CarOrder> tmp = (ArrayList<CarOrder>) carOrderDAO.getAll();
            ArrayList<CarOrderView> viewList = new ArrayList<>(tmp.size());
            for (CarOrder co : tmp) {
                viewList.add(new CarOrderView(co, userDAO.getByID(co.getIdUser()), carDAO.getByID(co.getIdCar())));
            }
            request.setAttribute("orders", viewList);
            request.getRequestDispatcher("/orderManagement.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with delete command implementation
     */
    public class DeleteCommand extends AbstractCommand {

        /**
         * Method override to perform delete order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders delete command");
            int id = Integer.parseInt(request.getParameter(ID_VALUE));
            CarOrderDAO carOrderDAO = CarOrderDAO.getInstance();
            carOrderDAO.deleteEntity(id);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with create request command implementation
     */
    public class CreateControllerCommand extends AbstractCommand {

        /**
         * Method override to perform create order request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders create command");
            CarDAO carDAO = CarDAO.getInstance();
            UserDAO userDAO = UserDAO.getInstance();
            request.setAttribute("cars", carDAO.getAllAvaliable());
            request.setAttribute("users", userDAO.getAll());
            request.setAttribute("action", "create");
            request.getRequestDispatcher(ADD_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with create command implementation
     */
    public class CreateHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform create order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders save command");
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
            carOrder.setIdUser(Integer.parseInt(request.getParameter("idUser")));
            carOrder.setIsConfirmed(false);
            carOrder.setComment(request.getParameter("comment"));
            carOrder.setCharges(Integer.parseInt(request.getParameter("charges")));
            CarOrderDAO userDAO = CarOrderDAO.getInstance();
            userDAO.saveEntity(carOrder);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with modify request command implementation
     */
    public class ModifyControllerCommand extends AbstractCommand {
        /**
         * Method override to perform modify order request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders modify command");
            CarOrderDAO carOrderDAO = CarOrderDAO.getInstance();
            CarDAO carDAO = CarDAO.getInstance();
            UserDAO userDAO = UserDAO.getInstance();
            CarOrder carOrder = carOrderDAO.getByID(Integer.parseInt((request.getParameter("id"))));
            Car car = carDAO.getByID(carOrder.getIdCar());
            User user = userDAO.getByID(carOrder.getIdUser());
            request.setAttribute("order", carOrder);
            request.setAttribute("user", user);
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
         * Method override to perform modify order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders update command");
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
            carOrder.setId(Integer.parseInt(request.getParameter("id")));
            carOrder.setIdCar(Integer.parseInt(request.getParameter("idCar")));
            carOrder.setStartDate(startDate);
            carOrder.setDuration(Integer.parseInt(request.getParameter("duration")));
            carOrder.setEndDate(endDate);
            carOrder.setIdUser(Integer.parseInt(request.getParameter("idUser")));
            carOrder.setIsConfirmed(Boolean.parseBoolean(request.getParameter("isConfirmed")));
            carOrder.setComment(request.getParameter("comment"));
            carOrder.setCharges(Integer.parseInt(request.getParameter("charges")));
            CarOrderDAO userDAO = CarOrderDAO.getInstance();
            userDAO.updateEntity(carOrder);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with confirm command implementation
     */
    public class ConfirmCommand extends AbstractCommand {
        /**
         * Method override to perform confirm order command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing admin orders confirm order command");
            int id = Integer.parseInt(request.getParameter(ID_VALUE));
            CarOrderDAO carOrderDAO = CarOrderDAO.getInstance();
            CarDAO carDAO = CarDAO.getInstance();
            CarOrder carOrder = carOrderDAO.getByID(id);
            if (carDAO.checkAvaliability(carOrderDAO.getByID(id).getIdCar())) {
                carOrderDAO.confirmOrder(id);
            } else {
                throw new DBException("Car is not avaliable");
            }
            response.sendRedirect(SOURCE_PAGE);
        }
    }

}
