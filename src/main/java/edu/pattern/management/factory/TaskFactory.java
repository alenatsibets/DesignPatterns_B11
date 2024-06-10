package edu.pattern.management.factory;

import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TaskFactory interface, realisation of Factory design pattern. It contains of a Logger and declares a creation method.
 */
public interface TaskFactory {
    /**
     * Logger (configuration is declared in log4j2.xml file)
     */
    Logger logger = LogManager.getLogger();

    /**
     * Method for creating a specific Task.
     * @return new Task
     */
    Task createTask(String name, String deadline, String personName, String description);
}