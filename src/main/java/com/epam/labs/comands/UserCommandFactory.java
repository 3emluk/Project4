package com.epam.labs.comands;

import org.apache.log4j.Logger;

/**
 * Class for implementing command factory for user management servlet
 *
 * @author zemluk
 */
public class UserCommandFactory {
    /**
     * Method for getting specific command based on requested action
     *
     * @param name Requested action
     * @return Chosen command
     */
    public AbstractCommand getCommand(String name) {
        /**
         * Logger variable
         */final Logger log = Logger.getLogger(getClass().getName());
        log.info("Getting properly command for: " + name);
        UserManagement userManagement = new UserManagement();
        if (name != null) {
            switch (name) {
                case "delete":
                    return userManagement.new DeleteCommand();
                case "create":
                    return userManagement.new CreateControllerCommand();
                case "createConcrete":
                    return userManagement.new CreateHandlerCommand();
                case "modify":
                    return userManagement.new ModifyControllerCommand();
                case "modifyConcrete":
                    return userManagement.new ModifyHandlerCommand();
            }
        } else {
            return userManagement.new DefaultCommand();
        }
        return null;
    }

    /**
     * Singleton instance
     */
    protected static UserCommandFactory instance = new UserCommandFactory();

    /**
     * Method for getting factory instance
     *
     * @return existing factory instance
     */
    public static UserCommandFactory getInstance() {
        return instance;
    }

    /**
     * Private constructor for singleton pattern
     */
    protected UserCommandFactory() {

    }
}
