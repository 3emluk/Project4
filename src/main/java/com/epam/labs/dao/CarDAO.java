package com.epam.labs.dao;

import com.epam.labs.POJO.Car;
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
public class CarDAO extends AbstractDAO<Car> {

    /**
     * ID field location at prepared statement
     */
    private static final int ID_CAR = 1;

    /**
     * MODEL field location at prepared statement for save/update
     */
    private static final int MODEL = 1;
    /**
     * MANUFACTURER field location at prepared statement for save/update
     */
    private static final int MANUFACTURER = 2;
    /**
     * SIGN field location at prepared statement for save/update
     */
    private static final int SIGN = 3;
    /**
     * PRICE field location at prepared statement for save/update
     */
    private static final int PRICE = 4;
    /**
     * IS_AVALIABLE field location at prepared statement for save/update
     */
    private static final int IS_AVALIABLE = 5;
    /**
     * ID_CAR for UPDATE field location at prepared statement for save/update
     */
    private static final int ID_CAR_UPDATE = 6;

    /**
     * Query string for get all records
     */
    private static final String GET_CARS =
            "SELECT * FROM ( " +
                    "SELECT id, model, manufacturer, sign, price, is_avaliable " +
                    "FROM car) a " +
                    "ORDER BY model";

    private static final String CHECK_CAR_AVAILABILITY =
            "SELECT is_avaliable " +
                    "FROM car " +
                    "WHERE id = ?";


    /**
     * Query string to save car entity
     */
    private static final String SAVE_CAR =
            "INSERT INTO" +
                    " CAR (model, manufacturer, sign, price, is_avaliable)" +
                    " VALUES (?,?,?,?,?)";
    /**
     * Query string to get car entity by id
     */
    private static final String GET_CAR_BY_ID =
            "SELECT * FROM ( " +
                    "SELECT id, model, manufacturer, sign, price, is_avaliable " +
                    "FROM car) a " +
                    "WHERE id = ?";

    /**
     * Query string to delete car entity by id
     */
    private static final String DELETE_CAR = "DELETE FROM car WHERE id = ? ";
    /**
     * Query string to update car entity
     */
    private static final String UPDATE_CAR = "UPDATE car " +
            "SET model = ?, manufacturer = ?, sign = ?, price = ?, is_avaliable = ? " +
            "WHERE id = ? ";
    /**
     * Query string to get all avaliable car entities
     */
    private static final String GET_AVALIABLE_CARS =
            "SELECT * FROM ( " +
                    "SELECT id, model, manufacturer, sign, price, is_avaliable " +
                    "FROM car) a " +
                    "WHERE is_avaliable = 1";


    /**
     * Method for saving car entity to database
     *
     * @param entity Car entity
     * @return ID of saved car entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public int saveEntity(Car entity) throws DBException {
        int id = -1;
        try (Connection conn = ConnectionPool.getConnection()) {
            try ( PreparedStatement prStmt = conn.prepareStatement(SAVE_CAR, new String[]{"id"})) {
            prStmt.setString(MODEL, entity.getModel());
            prStmt.setString(MANUFACTURER, entity.getManufacturer());
            prStmt.setString(SIGN, entity.getSign());
            prStmt.setInt(PRICE, entity.getPrice());
            prStmt.setInt(IS_AVALIABLE, (entity.isAvaliable()) ? 1 : 0);
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
     * Method for update car entity(if exists)
     *
     * @param entity Specified car entity
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void updateEntity(Car entity) throws DBException {
            try (Connection conn = ConnectionPool.getConnection()) {
                try (PreparedStatement prStmt = conn.prepareStatement(UPDATE_CAR)) {

            prStmt.setString(MODEL, entity.getModel());
            prStmt.setString(MANUFACTURER, entity.getManufacturer());
            prStmt.setString(SIGN, entity.getSign());
            prStmt.setInt(PRICE, entity.getPrice());
            prStmt.setInt(IS_AVALIABLE, (entity.isAvaliable()) ? 1 : 0);
            prStmt.setInt(ID_CAR_UPDATE, entity.getId());
            prStmt.executeUpdate();
            conn.commit();
            log.info("Transaction is being committed");
                } catch (SQLException e) {
                    conn.rollback();
                    log.error("SQLException", e);
                    log.info("Transaction is being rolled back");
                    throw new DBException(e.getMessage());
//                } finally {
//                    return id;
                }
            } catch (SQLException e) {
                log.error("SQLException", e);
                log.info("Connection error");
                throw new DBException(e.getMessage());
            }
    }

    /**
     * Method for delete car entity by ID from database
     *
     * @param id ID of car entity which must be deleted
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public void deleteEntity(int id) throws DBException {
                try (Connection conn = ConnectionPool.getConnection()) {
                    try (PreparedStatement statement = conn.prepareStatement(DELETE_CAR)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            conn.commit();
            log.info("Transaction is being committed");
                    } catch (SQLException e) {
                        conn.rollback();
                        log.error("SQLException", e);
                        log.info("Transaction is being rolled back");
                        throw new DBException(e.getMessage());
//                    } finally {
//                        return id;
                    }
                } catch (SQLException e) {
                    log.error("SQLException", e);
                    log.info("Connection error");
                    throw new DBException(e.getMessage());
                }
    }

    /**
     * Method for getting car entity by ID from database
     *
     * @param id Specified ID of car entity
     * @return Desired car entity with requested ID
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public Car getByID(int id) throws DBException {
        Car car = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_CAR_BY_ID)) {
            prStmt.setInt(ID_CAR, id);
            ResultSet rs = prStmt.executeQuery();
            if (rs.next()) {
                car = new Car();
                setPOJOFields(rs, car);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("Can`t find car with " + id + ": ", e);
            throw new DBException("There are no such car in the DB");
        } finally {
            return car;
        }

    }

    /**
     * Method for getting all records of car entity type
     *
     * @return List with car entities
     * @throws DBException if some database exceptions occurred
     */
    @Override
    public List<Car> getAll() throws DBException {
        List<Car> carList = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_CARS)) {
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                setPOJOFields(rs, car);
                carList.add(car);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no cars in the DB", e);
            throw new DBException("There are no cars in the DB");
        } finally {
            return carList;
        }
    }


    /**
     * Method for checking avaliability of car entity by ID from database
     *
     * @param id Specified ID of car entity
     * @return Availability value
     * @throws DBException if some database exceptions occurred
     */
    public boolean checkAvaliability(int id) throws DBException {
        boolean result = false;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(CHECK_CAR_AVAILABILITY)) {
            prStmt.setInt(ID_CAR, id);
            ResultSet rs = prStmt.executeQuery();
            if (rs.next()) {
                result = rs.getBoolean(DBParams.CAR_IS_AVALIABLE);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("Can`t find car with " + id + ": ", e);
            throw new DBException("There are no such car in the DB");
        } finally {
            return result;
        }

    }

    /**
     * Method for getting all records of car entity type which are avaliable to work with
     *
     * @return List with specified car entities
     * @throws DBException if some database exceptions occurred
     */
    public List<Car> getAllAvaliable() throws DBException {
        List<Car> carList = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement prStmt = conn.prepareStatement(GET_AVALIABLE_CARS)) {
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                setPOJOFields(rs, car);
                carList.add(car);
            }
        } catch (SQLException e) {
            log.error("SQLException ", e);
            throw new DBException(e.getMessage());
        } catch (NullPointerException e) {
            log.error("NullPointerException.  There are no avaliable cars in the DB", e);
            throw new DBException("There are no avaliable cars in the DB");
        } finally {
            return carList;
        }
    }

    /**
     * Method for initialize  car entity fields
     *
     * @param rs  Received ResultSet
     * @param car Entity which must be filled
     * @throws SQLException if wrong field mapping
     */
    private void setPOJOFields(ResultSet rs, Car car) throws SQLException {
        car.setId(rs.getInt(DBParams.COLUMN_ID));
        car.setModel(rs.getString(DBParams.CAR_MODEL));
        car.setManufacturer(rs.getString(DBParams.CAR_MANUFACTURER));
        car.setSign(rs.getString(DBParams.CAR_SIGN));
        car.setPrice(rs.getInt(DBParams.CAR_PRICE));
        car.setIsAvaliable(rs.getBoolean(DBParams.CAR_IS_AVALIABLE));
    }

}
