package com.epam.labs.dao;

import com.epam.labs.POJO.CarOrder;
import com.epam.labs.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement all necessary methods to database access for order entity
 *
 * @author zemluk
 */
public class CarOrderDAO extends AbstractDAO<CarOrder> {
    /**
     * ID field location at prepared statement
     */
    private static final int ID_ORDER = 1;


    /**
     * ID_CAR field location at prepared statement for save/update
     */
    private static final int ID_CAR = 1;

    /**
     * START_DATE field location at prepared statement for save/update
     */
    private static final int START_DATE = 2;

    /**
     * DURATION field location at prepared statement for save/update
     */
    private static final int DURATION = 3;

    /**
     * END_DATE field location at prepared statement for save/update
     */
    private static final int END_DATE = 4;

    /**
     * ID_USER field location at prepared statement for save/update
     */
    private static final int ID_USER = 5;

    /**
     * IS_CONFIRMED field location at prepared statement for save/update
     */
    private static final int IS_CONFIRMED = 6;

    /**
     * COMMENT field location at prepared statement for save/update
     */
    private static final int COMMENT = 7;

    /**
     * CHARGES field location at prepared statement for save/update
     */
    private static final int CHARGES = 8;

    /**
     * RENT_PRICE field location at prepared statement for save/update
     */
    private static final int RENT_PRICE = 9;

    /**
     * FULL_PRICE field location at prepared statement for save/update
     */
    private static final int FULL_PRICE = 10;

    /**
     * Query string for get all records
     */
    private static final String GET_ORDERS =
            "SELECT * FROM ( " +
                    "SELECT id, car_order_number, id_car, start_date, duration, end_date, id_user, is_confirmed,rent_price, charges, full_price, coment " +
                    "FROM car_order) a " +
                    "ORDER BY id";

    /**
     * Query string to save order entity
     */
    private static final String SAVE_ORDER =
            "INSERT INTO" +
                    " CAR_ORDER (id_car, start_date, duration, end_date, id_user, is_confirmed, coment, charges, rent_price, full_price) " +
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";
    /**
     * Query string to get order entity by id
     */
    private static final String GET_ORDER_BY_ID =
            "SELECT * FROM ( " +
                    "SELECT id, car_order_number, id_car, start_date, duration, end_date, id_user, is_confirmed, charges, rent_price, full_price, coment " +
                    "FROM car_order) a " +
                    "WHERE id = ?";

    /**
     * Query string to get order entity for specified user
     */
    private static final String GET_ORDERS_BY_USER =
            "SELECT * FROM ( " +
                    "SELECT id, car_order_number, id_car, start_date, duration, end_date, id_user, is_confirmed, charges, rent_price, full_price, coment " +
                    "FROM car_order) a " +
                    "WHERE id_user = ?";

    /**
     * Query string to delete order entity by id
     */
    private static final String DELETE_ORDER = "DELETE FROM car_order WHERE id = ? ";
    /**
     * Query string to update order entity
     */
    private static final String UPDATE_ORDER = "UPDATE car_order " +
            "SET  id_car = ?, start_date =?, duration = ?, end_date = ?, id_user = ?, is_confirmed = ?, coment = ?, charges=? " +
            "WHERE id = ? ";

    /**
     * Query string to confirm specified order entity
     */
    private static final String CONFIRM_ORDER = "UPDATE car_order " +
            "SET is_confirmed = 1 " +
            "WHERE id = ? ";

    /**
     * Method for saving order entity to database
     *
     * @param entity Order entity
     * @return ID of saved order entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public int saveEntity(CarOrder entity) throws DBException {
        int id = -1;
        try (Connection conn = ConnectionPool.getConnection()) {
            try (PreparedStatement prStmt = conn.prepareStatement(SAVE_ORDER, new String[]{"id"})) {
                prStmt.setInt(ID_CAR, entity.getIdCar());
                prStmt.setDate(START_DATE, new java.sql.Date(entity.getStartDate().getTime()));
                prStmt.setInt(DURATION, entity.getDuration());
                if (entity.getEndDate() == null) {
                    prStmt.setNull(END_DATE, java.sql.Types.DATE);
                } else {
                    prStmt.setDate(END_DATE, new java.sql.Date(entity.getEndDate().getTime()));
                }
                prStmt.setInt(ID_USER, entity.getIdUser());
                prStmt.setInt(IS_CONFIRMED, (entity.isConfirmed()) ? 1 : 0);
                prStmt.setString(COMMENT, entity.getComment());
                prStmt.setInt(CHARGES, entity.getCharges());
                prStmt.setInt(RENT_PRICE, 0);
                prStmt.setInt(FULL_PRICE, 0);
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
     * Method for update order entity(if exists)
     *
     * @param entity Specified order entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void updateEntity(CarOrder entity) throws DBException {
        try (Connection conn = ConnectionPool.getConnection()) {
            try (PreparedStatement prStmt = conn.prepareStatement(UPDATE_ORDER)) {
            prStmt.setInt(ID_CAR, entity.getIdCar());
            prStmt.setDate(START_DATE, new java.sql.Date(entity.getStartDate().getTime()));
            prStmt.setInt(DURATION, entity.getDuration());
            prStmt.setInt(DURATION, entity.getDuration());
            if (entity.getEndDate() == null) {
                prStmt.setNull(END_DATE, java.sql.Types.DATE);
            } else {
                prStmt.setDate(END_DATE, new java.sql.Date(entity.getEndDate().getTime()));
            }
            prStmt.setInt(ID_USER, entity.getIdUser());
            prStmt.setInt(IS_CONFIRMED, (entity.isConfirmed()) ? 1 : 0);
            prStmt.setString(COMMENT, entity.getComment());
            prStmt.setInt(CHARGES, entity.getCharges());
            prStmt.setInt(9, entity.getId());
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
     * Method for delete order entity by ID from database
     *
     * @param id ID of order entity which must be deleted
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void deleteEntity(int id) throws DBException {
            try (Connection conn = ConnectionPool.getConnection()) {
                try (
             PreparedStatement statement = conn.prepareStatement(DELETE_ORDER)) {
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
     * Method for getting order entity by ID from database
     *
     * @param id Specified ID of order entity
     * @return Desired order entity with requested ID
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public CarOrder getByID(int id) throws DBException {
        CarOrder carOrder = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_ORDER_BY_ID)) {
            prStmt.setInt(ID_CAR, id);
            ResultSet rs = prStmt.executeQuery();
            if (rs.next()) {
                carOrder = new CarOrder();
                setPOJOFields(rs, carOrder);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no car orders in the DB", e);
            throw new DBException("There are no car orders  in the DB");
        } finally {
            return carOrder;
        }

    }


    /**
     * Method for getting all records of order entity type
     *
     * @return List with order entities
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public List<CarOrder> getAll() throws DBException {
        List<CarOrder> carOrderList = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_ORDERS)) {
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                CarOrder carOrder = new CarOrder();
                setPOJOFields(rs, carOrder);
                carOrderList.add(carOrder);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no orders in the DB", e);
            throw new DBException("There are no orders in the DB");
        } finally {
            return carOrderList;
        }
    }


    /**
     * Method for getting records of order entity type for specified user id
     *
     * @param id ID of user for orders
     * @return List with order entities
     * @throws DBException if some database exceptions occurred
     */
    public List<CarOrder> getOrdersForUser(int id) throws DBException {
        List<CarOrder> carOrderList = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_ORDERS_BY_USER)) {
            prStmt.setInt(ID_ORDER, id);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                CarOrder carOrder = new CarOrder();
                setPOJOFields(rs, carOrder);
                carOrderList.add(carOrder);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no orders for user in the DB", e);
            throw new DBException("There are no orders for user in the DB");
        } finally {
            return carOrderList;
        }

    }


    /**
     * Method for update IS_CONFIRM field to true for specified order(if exsists)
     *
     * @param id ID of order entity
     * @throws DBException if some database exceptions occurred
     */
    public void confirmOrder(int id) throws DBException {
            try (Connection conn = ConnectionPool.getConnection()) {
                try (
             PreparedStatement prStmt = conn.prepareStatement(CONFIRM_ORDER)) {
            prStmt.setInt(ID_ORDER, id);
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
     * Method for initialize  order entity fields
     *
     * @param rs       Received ResultSet
     * @param carOrder Entity which must be filled
     * @throws SQLException if wrong field mapping
     */
    private void setPOJOFields(ResultSet rs, CarOrder carOrder) throws SQLException {
        carOrder.setId(rs.getInt(DBParams.COLUMN_ID));
        carOrder.setCarOrderNumber(rs.getString(DBParams.ORDER_CAR_ORDER_NUMBER));
        carOrder.setIdCar(rs.getInt(DBParams.ORDER_ID_CAR));
        carOrder.setStartDate(rs.getDate(DBParams.ORDER_START_DATE));
        carOrder.setDuration(rs.getInt(DBParams.ORDER_DURATION));
        carOrder.setEndDate(rs.getDate(DBParams.ORDER_END_DATE));
        carOrder.setIdUser(rs.getInt(DBParams.ORDER_ID_USER));
        carOrder.setIsConfirmed(rs.getBoolean(DBParams.ORDER_IS_CONFIRMED));
        carOrder.setComment(rs.getString(DBParams.ORDER_COMMENT));
        carOrder.setRentPrice(rs.getInt(DBParams.ORDER_RENT_PRICE));
        carOrder.setCharges(rs.getInt(DBParams.ORDER_CHARGES));
        carOrder.setFullPrice(rs.getInt(DBParams.ORDER_FULL_PRICE));
    }

}
