package com.epam.labs.comands;

import com.epam.labs.POJO.User;
import com.epam.labs.dao.DBException;
import com.epam.labs.dao.UserDAO;
import com.epam.labs.enums.Role;
import com.epam.labs.utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class for gathering root servlet commands
 */
public class IndexManagement {
    /**
     * Main page of servlet
     */
    public final String SOURCE_PAGE = "/";

    /**
     * Inner class with default command implementation
     */
    public class DefaultCommand extends AbstractCommand {
        /**
         * Method override to perform default root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing index default command");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with log in request command implementation
     */
    public class LogInControllerCommand extends AbstractCommand {
        /**
         * Method override to perform log in request root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing index log in command");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with log in command implementation
     */
    public class LogInHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform log in root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.info("Executing index authorize command");
            String email = request.getParameter("email");
            String password = request.getParameter("passwd");
            UserDAO userDAO = new UserDAO();
            HashUtil hashUtil = new HashUtil();
            password = hashUtil.getHash(password);
            User user = null;
            try {
                user = userDAO.findUser(email, password);
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getId());
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", Integer.toString(user.getId()));
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                response.sendRedirect(SOURCE_PAGE);
            } catch (DBException e) {
                request.setAttribute("loginError", "Your login or password are invalid");
                request.getRequestDispatcher("/logIn").forward(request, response);
                e.printStackTrace();
            }

        }
    }

    /**
     * Inner class with log out command implementation
     */
    public class LogOutCommand extends AbstractCommand {
        /**
         * Method override to perform log out root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing index log out command");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        System.out.println("JSESSIONID=" + cookie.getValue());
                        break;
                    }
                }
            }
            //invalidate the session if exists
            HttpSession session = request.getSession(false);
            System.out.println("User=" + session.getAttribute("user"));
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(SOURCE_PAGE);

        }
    }

    /**
     * Inner class with register request command implementation
     */
    public class RegisterControllerCommand extends AbstractCommand {
        /**
         * Method override to perform register request root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing index register redirect command");
            request.getRequestDispatcher("/userForm.jsp").forward(request, response);
        }
    }

    /**
     * Inner class with register command implementation
     */
    public class RegisterHandlerCommand extends AbstractCommand {
        /**
         * Method override to perform register root command
         *
         * @param request  Request from servlet
         * @param response Response from servlet
         * @throws ServletException
         * @throws IOException
         * @throws DBException
         */
        @Override
        protected void doOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
            log.info("Executing index user registration command");
            User user = new User();
            user.setIdRole(Role.CUSTOMER);
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassportID(request.getParameter("passportID"));
            user.setLocation(request.getParameter("location"));
            user.setPhone(request.getParameter("phone"));
            String password = request.getParameter("password");
            HashUtil hashUtil = new HashUtil();
            user.setPassword(hashUtil.getHash(password));

            UserDAO userDAO = new UserDAO();
            userDAO.saveEntity(user);
            response.sendRedirect(SOURCE_PAGE);
        }
    }


}
