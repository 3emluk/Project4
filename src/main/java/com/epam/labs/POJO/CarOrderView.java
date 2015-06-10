package com.epam.labs.POJO;

import java.util.Date;

/**
 * Class for describing Car Order entity at view
 *
 * @author zemluk
 */
public class CarOrderView {

    /**
     * Field for storing ID of car order entity
     */
    private int id;
    /**
     * Field for storing car order number of car order entity
     */
    private String carOrderNumber;
    /**
     * Field for storing manufacturer of car entity
     */
    private String carManufacturer;
    /**
     * Field for storing model of car entity
     */
    private String carModel;
    /**
     * Field for storing sign of car entity
     */
    private String carSign;
    /**
     * Field for storing price of car entity
     */
    private int carPrice;
    /**
     * Field for storing start date of car order entity
     */
    private Date startDate;
    /**
     * Field for storing duration of car order entity
     */
    private int duration;
    /**
     * Field for storing end date of car order entity
     */
    private Date endDate;
    /**
     * Field for storing first name of user entity
     */
    private String userFirstName;
    /**
     * Field for storing last name of user entity
     */
    private String userLastName;
    /**
     * Field for storing passport ID of user entity
     */
    private String userPassportID;
    /**
     * Field for storing location of user entity
     */
    private String userLocation;
    /**
     * Field for storing email of user entity
     */
    private String userEmail;
    /**
     * Field for storing phone of user entity
     */
    private String userPhone;
    /**
     * Field for storing rent price of car order entity
     */
    private int rentPrice;
    /**
     * Field for storing charges of car order entity
     */
    private int charges;
    /**
     * Field for storing full price of car order entity
     */
    private int fullPrice;
    /**
     * Field for storing is confirmed of car order entity
     */
    private boolean isConfirmed;
    /**
     * Field for storing comment of car order entity
     */
    private String comment;

    /**
     * Default constructor
     */
    public CarOrderView() {
    }

    /**
     * Constructor for full initialization of object
     *
     * @param co Car order entity
     * @param u  User entity
     * @param c  Car entity
     */
    public CarOrderView(CarOrder co, User u, Car c) {
        this.id = co.getId();
        this.carOrderNumber = co.getCarOrderNumber();
        this.carManufacturer = c.getManufacturer();
        this.carModel = c.getModel();
        this.carSign = c.getSign();
        this.carPrice = c.getPrice();
        this.startDate = co.getStartDate();
        this.duration = co.getDuration();
        this.endDate = co.getEndDate();
        this.userFirstName = u.getFirstName();
        this.userLastName = u.getLastName();
        this.userPassportID = u.getPassportID();
        this.userLocation = u.getLocation();
        this.userEmail = u.getEmail();
        this.userPhone = u.getPhone();
        this.rentPrice = co.getRentPrice();
        this.charges = co.getCharges();
        this.fullPrice = co.getFullPrice();
        this.isConfirmed = co.isConfirmed();
        this.comment = co.getComment();
    }


    /**
     * Method for getting ID field of car order entity
     *
     * @return ID field of car order entity
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting ID field of car order entity
     *
     * @param id ID field of car order entity
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for getting car order number field of car order entity
     *
     * @return Car order number field of car order entity
     */
    public String getCarOrderNumber() {
        return carOrderNumber;
    }

    /**
     * Method for setting car order number field of car order entity
     *
     * @param carOrderNumber Car order number field of car order entity
     */
    public void setCarOrderNumber(String carOrderNumber) {
        this.carOrderNumber = carOrderNumber;
    }

    /**
     * Method for getting manufacturer field of car entity
     *
     * @return Manufacturer field of car entity
     */
    public String getCarManufacturer() {
        return carManufacturer;
    }

    /**
     * Method for setting manufacturer field of car entity
     *
     * @param carManufacturer Manufacturer field of car entity
     */
    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    /**
     * Method for getting model field of car entity
     *
     * @return Model field of car entity
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Method for setting model field of car entity
     *
     * @param carModel Model field of car entity
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * Method for getting sign field of car entity
     *
     * @return Sign field of car entity
     */
    public String getCarSign() {
        return carSign;
    }

    /**
     * Method for setting sign field of car entity
     *
     * @param carSign Sign field of car entity
     */
    public void setCarSign(String carSign) {
        this.carSign = carSign;
    }

    /**
     * Method for getting price field of car entity
     *
     * @return Price field of car entity
     */
    public int getCarPrice() {
        return carPrice;
    }

    /**
     * Method for setting price field of car entity
     *
     * @param carPrice Price field of car entity
     */
    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    /**
     * Method for getting start date field of car order entity
     *
     * @return Start date field of car order entity
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Method for setting start date field of car order entity
     *
     * @param startDate Start date field of car order entity
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Method for getting duration field of car order entity
     *
     * @return Duration field of car order entity
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method for setting duration field of car order entity
     *
     * @param duration Duration field of car order entity
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Method for getting end date field of car order entity
     *
     * @return End date field of car order entity
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Method for setting end date field of car order entity
     *
     * @param endDate End date field of car order entity
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Method for getting first name field of user entity
     *
     * @return First name field of user entity
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Method for setting first name field of user entity
     *
     * @param userFirstName First name field of user entity
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Method for getting last name field of user entity
     *
     * @return Last name field of user entity
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Method for setting last name field of user entity
     *
     * @param userLastName Last name field of user entity
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Method for getting passport ID field of user entity
     *
     * @return Passport ID field of user entity
     */
    public String getUserPassportID() {
        return userPassportID;
    }

    /**
     * Method for setting passport ID field of user entity
     *
     * @param userPassportID Passport ID field of user entity
     */
    public void setUserPassportID(String userPassportID) {
        this.userPassportID = userPassportID;
    }

    /**
     * Method for getting location field of user entity
     *
     * @return Location field of user entity
     */
    public String getUserLocation() {
        return userLocation;
    }

    /**
     * Method for setting location field of user entity
     *
     * @param userLocation Location field of user entity
     */
    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    /**
     * Method for getting email field of user entity
     *
     * @return Email field of user entity
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Method for setting email field of user entity
     *
     * @param userEmailID Email field of user entity
     */
    public void setUserEmail(String userEmailID) {
        this.userEmail = userEmailID;
    }

    /**
     * Method for getting phone field of user entity
     *
     * @return Phone field of user entity
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * Method for setting phone field of user entity
     *
     * @param userPhone Phone field of user entity
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * Method for getting is confirmed field of car order entity
     *
     * @return Is confirmed field of car order entity
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Method for setting is confirmed field of car order entity
     *
     * @param isConfirmed Is confirmed field of car order entity
     */
    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    /**
     * Method for getting rent price field of car order entity
     *
     * @return Rent price field of car order entity
     */
    public int getRentPrice() {
        return rentPrice;
    }

    /**
     * Method for setting rent price field of car order entity
     *
     * @param rentPrice Rent price field of car order entity
     */
    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    /**
     * Method for getting charges field of car order entity
     *
     * @return Charges field of car order entity
     */
    public int getCharges() {
        return charges;
    }

    /**
     * Method for setting charges field of car order entity
     *
     * @param charges Charges field of car order entity
     */
    public void setCharges(int charges) {
        this.charges = charges;
    }

    /**
     * Method for getting full price field of car order entity
     *
     * @return Full price field of car order entity
     */
    public int getFullPrice() {
        return fullPrice;
    }

    /**
     * Method for setting full price field of car order entity
     *
     * @param fullPrice Full price field of car order entity
     */
    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

    /**
     * Method for getting comment field of car order entity
     *
     * @return Comment field of car order entity
     */
    public String getComment() {
        return comment;
    }

    /**
     * Method for setting comment field of car order entity
     *
     * @param comment Comment field of car order entity
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}

