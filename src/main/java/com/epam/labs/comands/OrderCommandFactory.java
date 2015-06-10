package com.epam.labs.comands;

import org.apache.log4j.Logger;

/**
 * Class for implementing command factory for order management servlet
 *
 * @author zemluk
 */
public class OrderCommandFactory {
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
        OrderManagement orderManagement = new OrderManagement();
        if (name != null) {
            switch (name) {
                case "delete":
                    return orderManagement.new DeleteCommand();
                case "create":
                    return orderManagement.new CreateControllerCommand();
                case "createConcrete":
                    return orderManagement.new CreateHandlerCommand();
                case "modify":
                    return orderManagement.new ModifyControllerCommand();
                case "modifyConcrete":
                    return orderManagement.new ModifyHandlerCommand();
                case "confirm":
                    return orderManagement.new ConfirmCommand();

            }
        } else {
            return orderManagement.new DefaultCommand();
        }
        return null;

    }

    /**
     * Singleton instance
     */
    protected static OrderCommandFactory instance = new OrderCommandFactory();

    /**
     * Method for getting factory instance
     *
     * @return existing factory instance
     */
    public static OrderCommandFactory getInstance() {
        return instance;
    }

    /**
     * Private constructor for singleton pattern
     */
    protected OrderCommandFactory() {

    }
}
