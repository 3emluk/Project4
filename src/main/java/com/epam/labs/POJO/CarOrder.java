package com.epam.labs.POJO;

import java.util.Date;

/**
 * Class for describing Car Order entity from database
 *
 * @author zemluk
 */
public class CarOrder {
    /**
     * Field for storing ID of car order entity
     */
    private int id;
    /**
     * Field for storing car order number of car order entity
     */
    private String carOrderNumber;
    /**
     * Field for storing car id of car order entity
     */
    private int idCar;
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
     * Field for storing user id of car order entity
     */
    private int idUser;
    /**
     * Field for storing rent price of car order entity
     */
    private int rentPrice;
    /**
     * Field for storing charges of car order entity
     */
    private int charges;
    /**
     * Field for storing full rent price of car order entity
     */
    private int fullPrice;
    /**
     * Field for storing confirmation value of car order entity
     */
    private boolean isConfirmed;
    /**
     * Field for storing comment of car order entity
     */
    private String comment;

    /**
     * Default constructor
     */
    public CarOrder() {
    }

    /**
     * Constructor for full initialization of object
     *
     * @param id             ID field of car order
     * @param carOrderNumber Car order number field of car order
     * @param idCar          Car id field of car order
     * @param startDate      Start date field of car order
     * @param duration       Duration field of car order
     * @param endDate        End date field of car order
     * @param idUser         User id field of car order
     * @param rentPrice      Rent price field of car order
     * @param charges        Charges field of car order
     * @param fullPrice      Full price field of car order
     * @param isConfirmed    Is confirmed field of car order
     * @param comment        Comment field of car order
     */
    public CarOrder(int id, String carOrderNumber, int idCar, Date startDate, int duration, Date endDate, int idUser, int rentPrice, int charges, int fullPrice, boolean isConfirmed, String comment) {
        this.id = id;
        this.carOrderNumber = carOrderNumber;
        this.idCar = idCar;
        this.startDate = startDate;
        this.duration = duration;
        this.endDate = endDate;
        this.idUser = idUser;
        this.rentPrice = rentPrice;
        this.charges = charges;
        this.fullPrice = fullPrice;
        this.isConfirmed = isConfirmed;
        this.comment = comment;
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
     * Method for getting car id field of car order entity
     *
     * @return Car id field of car order entity
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * Method for setting car id field of car order entity
     *
     * @param idCar Car id field of car order entit
     */
    public void setIdCar(int idCar) {
        this.idCar = idCar;
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
     * Method for getting user id field of car order entity
     *
     * @return User id field of car order entity
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Method for setting user id field of car order entity
     *
     * @param idUser User id field of car order entity
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

