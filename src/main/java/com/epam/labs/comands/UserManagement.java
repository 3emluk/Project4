package com.epam.labs.comands;

import com.epam.labs.POJO.User;
import com.epam.labs.dao.DBException;
import com.epam.labs.dao.UserDAO;
import com.epam.labs.enums.Role;
import com.epam.labs.utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for gathering user servlet commands
 *
 * @author zemluk
 */
public class UserManagement {
    /**
     * Attribute name in request with user entity id
     */
    public final String ID_VALUE = "id";
    /**
     * Main page of servlet
     */
    public final String SOURCE_PAGE = "/administrator/userManagement/";
    /**
     * Add user form location
     */
    public final String ADD_PAGE = "/userForm.jsp";
    /**
     * Modify user form location
     */
    public final String MODIFY_PAGE = "/userForm.jsp";

    /**
     * Inner class with default command implementation
     */
    public class DefaultCommand extends AbstractCommand {
        /**
         * Method override to perform default user command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user default command");
            UserDAO userDAO = UserDAO.getInstance();
            request.setAttribute("users", userDAO.getAll());
            request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with delete command implementation
     */
    public class DeleteCommand extends AbstractCommand {
        /**
         * Method override to perform delete user command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user delete command");
            int id = Integer.parseInt(request.getParameter(ID_VALUE));
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.deleteEntity(id);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with create request command implementation
     */
    public class CreateControllerCommand extends AbstractCommand {
        /**
         * Method override to perform create user request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user create command");
            request.setAttribute("action", "create");
            request.getRequestDispatcher(ADD_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with create command implementation
     */
    public class CreateHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform create user command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user save command");
            User user = new User();
            int tmp = Integer.parseInt(request.getParameter("role"));
            if (tmp == 1) {
                user.setIdRole(Role.ADMINISTRATOR);
            } else {
                user.setIdRole(Role.CUSTOMER);
            }
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassportID(request.getParameter("passportID"));
            user.setLocation(request.getParameter("location"));
            user.setPhone(request.getParameter("phone"));
            String password = request.getParameter("password");
            HashUtil hashUtil = new HashUtil();
            user.setPassword(hashUtil.getHash(password));
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.saveEntity(user);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

    /**
     * Inner class with modify request command implementation
     */
    public class ModifyControllerCommand extends AbstractCommand {
        /**
         * Method override to perform modify user request command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user modify request command");
            UserDAO userDAO = UserDAO.getInstance();
            User user = userDAO.getByID(Integer.parseInt((request.getParameter("id"))));
            request.setAttribute("user", user);
            request.setAttribute("action", "modify");
            request.getRequestDispatcher(MODIFY_PAGE).forward(request, response);
        }
    }

    /**
     * Inner class with modify command implementation
     */
    public class ModifyHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform modify user command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing user update command");
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));

            int tmp = Integer.parseInt(request.getParameter("role"));
            if (tmp == 1) {
                user.setIdRole(Role.ADMINISTRATOR);
            } else {
                user.setIdRole(Role.CUSTOMER);
            }
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassportID(request.getParameter("passportID"));
            user.setLocation(request.getParameter("location"));
            user.setPhone(request.getParameter("phone"));
            String password = request.getParameter("password");
            HashUtil hashUtil = new HashUtil();
            user.setPassword(hashUtil.getHash(password));
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.updateEntity(user);
            response.sendRedirect(SOURCE_PAGE);
        }
    }

}
