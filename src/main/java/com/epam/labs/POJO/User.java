package com.epam.labs.POJO;

import com.epam.labs.enums.Role;

/**
 * Class for describing User entity from database
 *
 * @author zemluk
 */
public class User {
    /**
     * Field for storing id of user entity
     */
    private int id;
    /**
     * Field for storing ID of user entity
     */
    private Role role;
    /**
     * Field for storing email of user entity
     */
    private String email;
    /**
     * Field for storing password of user entity
     */
    private String password;
    /**
     * Field for storing first name of user entity
     */
    private String firstName;
    /**
     * Field for storing last name of user entity
     */
    private String lastName;
    /**
     * Field for storing passport id of user entity
     */
    private String passportID;
    /**
     * Field for storing location of user entity
     */
    private String location;
    /**
     * Field for storing phone of user entity
     */
    private String phone;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor for full initialization of object
     *
     * @param id         ID field of user
     * @param role       Role field of user
     * @param email      Email field of user
     * @param password   Password field of user
     * @param firstName  First name field of user
     * @param lastName   Last Name field of user
     * @param passportID Passport ID field of user
     * @param location   Location field of user
     * @param phone      Phone field of user
     */
    public User(int id, Role role, String email, String password, String firstName, String lastName, String passportID, String location, String phone) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportID = passportID;
        this.location = location;
        this.phone = phone;
    }

    /**
     * Method for getting ID field of user entity
     *
     * @return ID field of user entity
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting ID field of user entity
     *
     * @param id ID field of user entity
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for getting role field of user entity
     *
     * @return Role field of user entity
     */
    public Role getIdRole() {
        return role;
    }

    /**
     * Method for setting role field of user entity
     *
     * @param role Role field of user entity
     */
    public void setIdRole(Role role) {
        this.role = role;
    }

    /**
     * Method for getting email field of user entity
     *
     * @return Email field of user entity
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method for setting email field of user entity
     *
     * @param email Email field of user entity
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method for getting password field of user entity
     *
     * @return Password field of user entity
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting password field of user entity
     *
     * @param password Password field of user entity
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for getting first name field of user entity
     *
     * @return First name field of user entity
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method for setting first name field of user entity
     *
     * @param firstName First name field of user entity
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method for getting last name field of user entity
     *
     * @return Last name field of user entity
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method for setting last name field of user entity
     *
     * @param lastName Last name field of user entity
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method for getting passport ID field of user entity
     *
     * @return Passport ID field of user entity
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Method for setting passport ID field of user entity
     *
     * @param passportID Passport ID field of user entity
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * Method for getting location field of user entity
     *
     * @return Location field of user entity
     */
    public String getLocation() {
        return location;
    }

    /**
     * Method for setting location field of user entity
     *
     * @param location Location field of user entity
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Method for getting phone of user entity
     *
     * @return Phone field of user entity
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method for setting phone field of user entity
     *
     * @param phone Phone field of user entity
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
