package com.epam.labs.comands;

import org.apache.log4j.Logger;

/**
 * Class for implementing command factory for car management servlet
 *
 * @author zemluk
 */
public class CarCommandFactory {
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
        CarManagement carManagement = new CarManagement();

        if (name != null) {
            switch (name) {
                case "delete":
                    return carManagement.new DeleteCommand();
                case "create":
                    return carManagement.new CreateControllerCommand();
                case "createConcrete":
                    return carManagement.new CreateHandlerCommand();
                case "modify":
                    return carManagement.new ModifyControllerCommand();
                case "modifyConcrete":
                    return carManagement.new ModifyHandlerCommand();
            }
        } else {
            return carManagement.new DefaultCommand();
        }
        return null;
    }

    /**
     * Singleton instance
     */
    private static CarCommandFactory instance = new CarCommandFactory();

    /**
     * Method for getting factory instance
     *
     * @return existing factory instance
     */
    public static CarCommandFactory getInstance() {
        return instance;
    }

    /**
     * Private constructor for singleton pattern
     */
    private CarCommandFactory() {
    }
}
