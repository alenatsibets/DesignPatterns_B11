package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.BugTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BugTaskFactory implements TaskFactory {
    static Logger logger = LogManager.getLogger();
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating BugTask");
        return new BugTask(name, deadline, personName, description);
    }
}