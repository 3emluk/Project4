package com.epam.labs.POJO;

/**
 * Class for describing Car entity from database
 *
 * @author zemluk
 */
public class Car {
    /**
     * Field for storing id of car entity
     */
    private int id;
    /**
     * Field for storing model of car entity
     */
    private String model;
    /**
     * Field for storing manufacturer of car entity
     */
    private String manufacturer;
    /**
     * Field for storing sign of car entity
     */
    private String sign;
    /**
     * Field for storing price of car entity
     */
    private int price;
    /**
     * Field for storing isAvaliable of car entity
     */
    private boolean isAvaliable;

    /**
     * Default constructor
     */
    public Car() {
    }

    /**
     * Constructor for full initialization of object
     *
     * @param id           ID field of car
     * @param model        Model field of car
     * @param manufacturer Manufacturer field of car
     * @param sign         Sign field of car
     * @param price        Price field of car
     * @param isAvaliable  IsAvaliable field of car
     */
    public Car(int id, String model, String manufacturer, String sign, int price, boolean isAvaliable) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.sign = sign;
        this.price = price;
        this.isAvaliable = isAvaliable;
    }

    /**
     * Method for getting ID field of car entity
     *
     * @return ID field of car entity
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param id ID field of car entity
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for getting model field of car entity
     *
     * @return Model field of car entity
     */
    public String getModel() {
        return model;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param model Model field of car entity
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Method for getting manufacturer field of car entity
     *
     * @return Manufacturer field of car entity
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param manufacturer Manufacturer field of car entity
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Method for getting sign field of car entity
     *
     * @return Sign field of car entity
     */
    public String getSign() {
        return sign;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param sign Sign field of car entity
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * Method for getting price field of car entity
     *
     * @return Price field of car entity
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param price Price field of car entity
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Method for getting isAvaliable field of car entity
     *
     * @return isAvaliable field of car entity
     */
    public boolean isAvaliable() {
        return isAvaliable;
    }

    /**
     * Method for setting ID field of car entity
     *
     * @param isAvaliable isAvaliable field of car entity
     */
    public void setIsAvaliable(boolean isAvaliable) {
        this.isAvaliable = isAvaliable;
    }
}
