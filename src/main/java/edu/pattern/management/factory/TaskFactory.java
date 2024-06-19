package edu.pattern.management.factory;

import edu.pattern.management.entity.Task;
import edu.pattern.management.exception.TaskException;
import edu.pattern.management.reader.TaskFileReader;
import edu.pattern.management.reader.impl.TaskFileReaderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskFactory interface, realisation of Factory design pattern. It contains of a Logger and declares a creation method.
 */
public abstract class TaskFactory {
    /**
     * Logger (configuration is declared in log4j2.xml file).
     */
    public Logger logger = LogManager.getLogger();

    /**
     * Method for creating a specific Task with atomic arguments.
     *
     * @return new Task
     */
    public abstract Task createTask(String name, String deadline, String personName, String description);

    /**
     * Method for creating a specific Task with arguments as a List.
     *
     * @return new Task
     */
    public Task createTask(List<String> parameters) throws TaskException {
        if (parameters == null || parameters.isEmpty() || parameters.size() < 4) {
            logger.error("Not enough parameters");
            throw new TaskException("Not enough parameters");
        }
        return createTask(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
    }

    /**
     * Method for creating a specific Task with reading parameters from the file.
     *
     * @return new Task
     */
    public Task createTask(String file) throws TaskException {
        List<String> parameters = new ArrayList<>();
        TaskFileReader reader = new TaskFileReaderImpl();
        try {
            parameters = reader.parseTaskParameters(file);
        } catch (TaskException e) {
            logger.error("cannot create Task from file", e);
        }
        return createTask(parameters);
    }
}