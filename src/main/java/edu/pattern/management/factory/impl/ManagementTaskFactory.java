package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.ManagementTask;
import edu.pattern.management.factory.TaskFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagementTaskFactory implements TaskFactory {
    static Logger logger = LogManager.getLogger();
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating Management Task");
        return new ManagementTask(name, deadline, personName, description);
    }
}