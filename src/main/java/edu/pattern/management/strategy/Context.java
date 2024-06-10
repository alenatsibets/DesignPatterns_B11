package edu.pattern.management.strategy;

import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Context class for realisation the Strategy design pattern. It contains of a Logger and declares the chosen Strategy.
 */
public class Context {
    /**
     * Logger (configuration is declared in log4j2.xml file)
     */
    static Logger logger = LogManager.getLogger();
    /**
     * The chosen Strategy
     */
    private TaskProcessingStrategy strategy;

    /**
     * Constructor for declaring the chosen Strategy.
     * @param strategy chosen Strategy
     */
    public Context(TaskProcessingStrategy strategy) {
        logger.info("Strategy is: " + strategy.getClass().getName());
        this.strategy = strategy;
    }

    /**
     * Method for executing the Strategy.
     * @param task task to handle
     */
    public void executeStrategy(Task task) {
        logger.info("Executing strategy: " + strategy.getClass().getName());
        strategy.processTask(task);
    }

    /**
     * Method for setting the Strategy.
     */
    public void setStrategy(TaskProcessingStrategy strategy) {
        logger.info("Strategy is: " + strategy.getClass().getName());
        this.strategy = strategy;
    }
}