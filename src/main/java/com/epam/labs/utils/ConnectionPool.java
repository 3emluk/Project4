package com.epam.labs.utils;

import oracle.jdbc.pool.OracleDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//import javax.sql.DataSource;

/**
 * Class for implementing Connection Pool. Initializes pool and provides connections.
 *
 * @author zemluk
 */
public class ConnectionPool {
    /**
     * Logger for server logs.
     */
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    /**
     * Represents Oracle DB Data Source
     */
    private static OracleDataSource ds;
    private static Connection conn;

    /**Initializes named data source
     * */
    static {
        String resourceName = "db.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
            ds = new OracleDataSource();
            ds.setURL(props.getProperty("oracle_db_url"));
            ds.setUser(props.getProperty("oracle_db_username"));
            ds.setPassword(props.getProperty("oracle_db_password"));

        } catch (IOException e) {
            log.error("Can't find database properties file");
        } catch (SQLException e) {
            log.error("Can't create desired datasource");
        }


//            String url = "jdbc:oracle:thin:@localhost:1521:XE";
//            ds = new OracleDataSource();
//            ds.setURL(url);

//            DataSource ds= new Connection();
//            ds.setDriverType("thin");
//            ds.setServerName("localhost");
//            ds.setPortNumber(1521);
//            ds.setDatabaseName("XE"); // Oracle SID
//            ds.setUser("Herong");
//            ds.setPassword("TopSecret");
//
//            ds = (DataSource) ic.lookup("jdbc/RemoteOracle");
//            ds=new
//        } catch (NamingException ex) {
//            log.error("NamingException during DataSource lookup. ", ex);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        }
    }

    /**
     * Returns connection from connection pool.
     * Sets connection auto commit to false.
     *
     * @return Connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String username = "project4";
            String password = "project4";
//        Connection conn= DriverManager.getConnection(url, username, password);
//            conn = ds.getConnection(username,password);
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            log.info("Created connection");
        }
        return conn;
    }

}