package com.epam.labs.dao;

/**
 * Class for saving database fields as constants
 *
 * @author zemluk
 */
class DBParams {
    static final String COLUMN_ID = "id";

    static final String USER_ROLE = "id_role";
    static final String USER_EMAIL = "email";
    static final String USER_PASS = "password";
    static final String USER_FIRST_NAME = "first_name";
    static final String USER_LAST_NAME = "last_name";
    static final String USER_PASSPORT = "passport_id";
    static final String USER_LOCATION = "location";
    static final String USER_PHONE = "phone";

    static final String CAR_MODEL = "model";
    static final String CAR_MANUFACTURER = "manufacturer";
    static final String CAR_SIGN = "sign";
    static final String CAR_PRICE = "price";
    static final String CAR_IS_AVALIABLE = "is_avaliable";

    static final String ORDER_CAR_ORDER_NUMBER = "car_order_number";
    static final String ORDER_ID_CAR = "id_car";
    static final String ORDER_START_DATE = "start_date";
    static final String ORDER_DURATION = "duration";
    static final String ORDER_END_DATE = "end_date";
    static final String ORDER_ID_USER = "id_user";
    static final String ORDER_RENT_PRICE = "rent_price";
    static final String ORDER_CHARGES = "charges";
    static final String ORDER_FULL_PRICE = "full_price";
    static final String ORDER_IS_CONFIRMED = "is_confirmed";
    static final String ORDER_COMMENT = "coment";
}
