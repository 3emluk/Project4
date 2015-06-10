package com.epam.labs.comands;

import org.apache.log4j.Logger;

/**
 * Class for implementing command factory for user order management servlet
 *
 * @author zemluk
 */
public class UserOrderCommandFactory {
    /**
     * Method for getting specific command based on requested action
     *
     * @param name Requested action
     * @return Chosen command
     */
    public AbstractCommand getCommand(String name) {
        /**
         * Logger variable
         */
        final Logger log = Logger.getLogger(getClass().getName());
        log.info("Getting properly command for: " + name);
        UserOrderManagement userOrderManagement = new UserOrderManagement();
        if (name != null) {
            switch (name) {
                case "create":
                    return userOrderManagement.new CreateControllerCommand();
                case "createConcrete":
                    return userOrderManagement.new CreateHandlerCommand();
            }
        } else {
            return userOrderManagement.new DefaultCommand();
        }
        return null;
    }

    /**
     * Singleton instance
     */
    protected static UserOrderCommandFactory instance = new UserOrderCommandFactory();

    /**
     * Method for getting factory instance
     *
     * @return existing factory instance
     */
    public static UserOrderCommandFactory getInstance() {
        return instance;
    }

    /**
     * Private constructor for singleton pattern
     */
    protected UserOrderCommandFactory() {

    }
}
