package edu.pattern.management.factory;

import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface TaskFactory {
    Logger logger = LogManager.getLogger();
    Task createTask(String name, String deadline, String personName, String description);
}