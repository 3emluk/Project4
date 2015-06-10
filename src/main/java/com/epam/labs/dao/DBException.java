package com.epam.labs.dao;

import java.sql.SQLException;

/**
 * Replaces SQLException and other exceptions during work with database
 *
 * @author zemluk
 */
public class DBException extends SQLException {
    public DBException(String message) {
        super(message);
    }
}
