package edu.pattern.management.strategy;

import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Context {
    static Logger logger = LogManager.getLogger();
    private TaskProcessingStrategy strategy;

    public Context(TaskProcessingStrategy strategy) {
        logger.info("Strategy is: " + strategy.getClass().getName());
        this.strategy = strategy;
    }

    public void executeStrategy(Task task) {
        logger.info("Executing strategy: " + strategy.getClass().getName());
        strategy.processTask(task);
    }

    public void setStrategy(TaskProcessingStrategy strategy) {
        logger.info("Strategy is: " + strategy.getClass().getName());
        this.strategy = strategy;
    }
}