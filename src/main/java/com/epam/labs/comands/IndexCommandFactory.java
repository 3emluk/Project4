package com.epam.labs.comands;

import org.apache.log4j.Logger;

/**
 * Class for implementing command factory for root servlet
 *
 * @author zemluk
 */
public class IndexCommandFactory {
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
        IndexManagement indexManagement = new IndexManagement();
        if (name != null) {
            switch (name) {
                case "/logIn":
                    return indexManagement.new LogInControllerCommand();
                case "/logInConcrete":
                    return indexManagement.new LogInHandlerCommand();
                case "/logOut":
                    return indexManagement.new LogOutCommand();
                case "/register":
                    return indexManagement.new RegisterControllerCommand();
                case "/registerConcrete":
                    return indexManagement.new RegisterHandlerCommand();
                default:
                    return indexManagement.new DefaultCommand();
            }
        } else {
            return indexManagement.new DefaultCommand();
        }
//        return null;
    }

    /**
     * Singleton instance
     */
    protected static IndexCommandFactory instance = new IndexCommandFactory();

    /**
     * Method for getting root factory instance
     *
     * @return existing root factory instance
     */
    public static IndexCommandFactory getInstance() {
        return instance;
    }

    /**
     * Private constructor for singleton pattern
     */
    protected IndexCommandFactory() {

    }
}
