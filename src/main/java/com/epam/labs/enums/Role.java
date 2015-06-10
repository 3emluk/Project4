package com.epam.labs.enums;

/**
 * Role enum represents users possible roles.
 *
 * @author zemluk
 */
public enum Role {
    ADMINISTRATOR(1), CUSTOMER(2);

    /**
     * Role id value in the DB.
     */
    private int id;

    /**
     * Method returns role id value.
     *
     * @return id value, int.
     */
    public int getRoleId() {
        return id;
    }

    /**
     * Constructor.
     *
     * @param id, int.
     */
    private Role(int id) {
        this.id = id;
    }

    /**
     * Method for returning string value of enum
     *
     * @return String value
     */
    @Override
    public String toString() {
        return String.valueOf(id);
    }

    /**
     * Method for getting name of current enum field
     *
     * @return String name of current enum
     */
    public String getName() {
        return name();
    }

}
