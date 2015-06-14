package com.epam.labs.dao;

import com.epam.labs.POJO.User;
import com.epam.labs.enums.Role;
import com.epam.labs.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement all necessary methods to database access for car entity
 *
 * @author zemluk
 */
public class UserDAO extends AbstractDAO<User> {

    /**
     * Instance of singleton object
     */
    private static UserDAO UserDAO;

    /**
     * ID field location at prepared statement
     */
    private static final int ID_USER = 1;

    /**
     * EMAIL field location at prepared statement for save/update
     */
    private static final int EMAIL = 1;
    /**
     * PASS field location at prepared statement for save/update
     */
    private static final int PASS = 2;
    /**
     * PASSPORT field location at prepared statement for save/update
     */
    private static final int PASSPORT = 3;
    /**
     * LOCATION field location at prepared statement for save/update
     */
    private static final int LOCATION = 4;
    /**
     * PHONE field location at prepared statement for save/update
     */
    private static final int PHONE = 5;
    /**
     * USER field location at prepared statement for save/update
     */
    private static final int USER = 6;

    /**
     * Query string for get all records
     */
    private static final String GET_USERS =
            "SELECT * FROM ( " +
                    "SELECT id, id_role, email, password, first_name, last_name, passport_id, location, phone " +
                    "FROM my_user) a " +
                    "ORDER BY last_name";

    /**
     * Query string to save user entity
     */
    private static final String SAVE_USER =
            "INSERT INTO" +
                    " MY_USER (id_role, email, password, first_name, last_name, passport_id, location, phone)" +
                    " VALUES (?,?,?,?,?,?,?,?)";
    /**
     * Query string to get user entity by id
     */
    private static final String GET_USERS_BY_ID =
            "SELECT * FROM ( " +
                    "SELECT id, id_role, email, password, first_name, last_name, passport_id, location, phone " +
                    "FROM my_user) a " +
                    "WHERE id = ?";
    /**
     * Query string to delete user entity by id
     */
    private static final String DELETE_USER = "DELETE FROM my_user WHERE id = ?";
    /**
     * Query string to update user entity
     */
    private static final String UPDATE_USER = "UPDATE my_user " +
            "SET email = ?, password = ?, passport_id = ?, location = ?, phone = ? " +
            "WHERE id = ? ";

    /**
     * Query string to find user entity by login credentials
     */
    private static final String FIND_USER = "SELECT id, id_role, email, password, first_name, last_name, passport_id, location, phone " +
            "FROM my_user WHERE email = ? AND password = ?";

    /**
     * Query string to get user role by his ID
     */
    private static final String GET_USER_ROLE_BY_ID = "SELECT id_role " +
            "FROM my_user WHERE id = ?";


    /**
     * Private constructor for singleton realization
     */
    private UserDAO() {}

    /**
     * Method for returning singleton instance
     * @return DAO instance
     */
    public static UserDAO getInstance() {
        if (UserDAO == null) {
            UserDAO = new UserDAO();
        }
        return UserDAO;
    }

    /**
     * Method for saving user entity to database
     *
     * @param entity Car entity
     * @return ID of saved user entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public int saveEntity(User entity) throws DBException {
        int id = -1;
        try (Connection conn = ConnectionPool.getConnection()) {
            try (PreparedStatement prStmt = conn.prepareStatement(SAVE_USER, new String[]{"id"})) {
            prStmt.setInt(1, entity.getIdRole().getRoleId());
            prStmt.setString(2, entity.getEmail());
            prStmt.setString(3, entity.getPassword());
            prStmt.setString(4, entity.getFirstName());
            prStmt.setString(5, entity.getLastName());
            prStmt.setString(6, entity.getPassportID());
            prStmt.setString(7, entity.getLocation());
            prStmt.setString(8, entity.getPhone());
            prStmt.executeUpdate();
            ResultSet rs = prStmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
            }
            conn.commit();
            log.info("Transaction is being committed");
            } catch (SQLException e) {
                conn.rollback();
                log.error("SQLException", e);
                log.info("Transaction is being rolled back");
                throw new DBException(e.getMessage());
            } finally {
                return id;
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
            log.info("Connection error");
            throw new DBException(e.getMessage());
        }
    }

    /**
     * Method for update user entity(if exists)
     *
     * @param entity Specified user entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void updateEntity(User entity) throws DBException {
            try (Connection conn = ConnectionPool.getConnection()) {
                try (PreparedStatement prStmt = conn.prepareStatement(UPDATE_USER)) {

            prStmt.setString(EMAIL, entity.getEmail());
            prStmt.setString(PASS, entity.getPassword());
            prStmt.setString(PASSPORT, entity.getPassportID());
            prStmt.setString(LOCATION, entity.getLocation());
            prStmt.setString(PHONE, entity.getPhone());
            prStmt.setInt(USER, entity.getId());
            prStmt.executeUpdate();
            conn.commit();
            log.info("Transaction is being committed");
                } catch (SQLException e) {
                    conn.rollback();
                    log.error("SQLException", e);
                    log.info("Transaction is being rolled back");
                    throw new DBException(e.getMessage());
                }
            } catch (SQLException e) {
                log.error("SQLException", e);
                log.info("Connection error");
                throw new DBException(e.getMessage());
            }
    }

    /**
     * Method for delete user entity by ID from database
     *
     * @param id ID of user entity which must be deleted
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void deleteEntity(int id) throws DBException {
                try (Connection conn = ConnectionPool.getConnection()) {
                    try ( PreparedStatement statement = conn.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            conn.commit();
            log.info("Transaction is being committed");
                    } catch (SQLException e) {
                        conn.rollback();
                        log.error("SQLException", e);
                        log.info("Transaction is being rolled back");
                        throw new DBException(e.getMessage());
                    }
                } catch (SQLException e) {
                    log.error("SQLException", e);
                    log.info("Connection error");
                    throw new DBException(e.getMessage());
                }
    }

    /**
     * Method for getting user entity by ID from database
     *
     * @param id Specified ID of user entity
     * @return Desired user entity with requested ID
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public User getByID(int id) throws DBException {
        User user = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_USERS_BY_ID)) {
            prStmt.setInt(ID_USER, id);
            ResultSet rs = prStmt.executeQuery();
            rs.next();
            user = new User();
            setPOJOFields(rs, user);
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("Can`t find user with " + id + ": ", e);
            throw new DBException("There are no such user in the DB");
        }
        return user;
    }

    /**
     * Method for getting all records of user entity type
     *
     * @return List with user entities
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public List<User> getAll() throws DBException {
        List<User> userList = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_USERS)) {
            log.info("executing query");
            log.info(prStmt);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                setPOJOFields(rs, user);
                userList.add(user);
                log.info("User: " + user.getFirstName());
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no users in the DB", e);
            throw new DBException("There are no uses in the DB");
        }
        return userList;
    }

    /**
     * Method for getting user entity by email and password combination from database
     *
     * @param email    email of user
     * @param password password of user
     * @return Desired user entity with requested email and password
     * @throws DBException if some database exceptions occurred
     */

    public User findUser(String email, String password) throws DBException {
        User user = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(FIND_USER)) {
            prStmt.setString(1, email);
            prStmt.setString(2, password);
            ResultSet rs = prStmt.executeQuery();
            rs.next();
            user = new User();
            setPOJOFields(rs, user);
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("Can`t find user with " + email + ", " + password + ": ", e);
            throw new DBException("There are no such user in the DB");
        }
        return user;
    }

    /**
     * Method for getting user role by user ID from database
     *
     * @param id ID of desired user
     * @return Role value of specified user
     * @throws DBException if some database exceptions occurred
     */
    public int getUserRoleByID(int id) throws DBException {
        int idRole = 0;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_USER_ROLE_BY_ID)) {
            prStmt.setInt(ID_USER, id);
            ResultSet rs = prStmt.executeQuery();
            rs.next();
            idRole = rs.getInt(DBParams.USER_ROLE);
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("Can`t find user with " + id + ": ", e);
            throw new DBException("There are no such user in the DB");
        }
        return idRole;
    }

    /**
     * Method for initialize  user entity fields
     *
     * @param rs   Received ResultSet
     * @param user Entity which must be filled
     * @throws SQLException if wrong field mapping
     */
    private void setPOJOFields(ResultSet rs, User user) throws SQLException {
        user.setId(rs.getInt(DBParams.COLUMN_ID));
        user.setIdRole(Role.values()[rs.getInt(DBParams.USER_ROLE) - 1]);
        user.setEmail(rs.getString(DBParams.USER_EMAIL));
        user.setPassword(rs.getString(DBParams.USER_PASS));
        user.setFirstName(rs.getString(DBParams.USER_FIRST_NAME));
        user.setLastName(rs.getString(DBParams.USER_LAST_NAME));
        user.setPassportID(rs.getString(DBParams.USER_PASSPORT));
        user.setLocation(rs.getString(DBParams.USER_LOCATION));
        user.setPhone(rs.getString(DBParams.USER_PHONE));
    }

}
