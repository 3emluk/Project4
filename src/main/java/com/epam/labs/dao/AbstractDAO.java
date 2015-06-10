package com.epam.labs.dao;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Abstract class to describe all necessary methods for specified entity DAO
 *
 * @param <T> Specified entity type
 * @author zemluk
 */
public abstract class AbstractDAO<T> {
    /**
     * Logger variable
     */
    protected final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Abstract method for saving specified entity to database
     *
     * @param entity Specified entity
     * @return ID of saved entity
     * @throws DBException if some database exceptions occurred
     */
    public abstract int saveEntity(T entity) throws DBException;

    /**
     * Abstract method for update specified entity(if exists)
     *
     * @param entity Specified entity
     * @throws DBException if some database exceptions occurred
     */
    public abstract void updateEntity(T entity) throws DBException;

    /**
     * Abstract method for delete specified entity by ID from database
     *
     * @param id ID of entity which must be deleted
     * @throws DBException if some database exceptions occurred
     */
    public abstract void deleteEntity(int id) throws DBException;

    /**
     * Abstract method for getting specified entity by ID from database
     *
     * @param id Specified ID of entity
     * @return Desired entity with requested ID
     * @throws DBException if some database exceptions occurred
     */
    public abstract T getByID(int id) throws DBException;

    /**
     * Abstract method for getting all records of specified entity type
     *
     * @return List with specified entities
     * @throws DBException if some database exceptions occurred
     */
    public abstract List<T> getAll() throws DBException;

}
