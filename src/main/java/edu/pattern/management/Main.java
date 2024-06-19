package edu.pattern.management;

import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.exception.TaskException;
import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.factory.impl.*;
import edu.pattern.management.strategy.Context;
import edu.pattern.management.strategy.impl.DefaultStrategy;
import edu.pattern.management.strategy.impl.TwiceDebugStrategy;
import edu.pattern.management.strategy.impl.UsualStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is created as a client to processed project functionality.
 */

public class Main {
    /**
     * Logger (configuration is declared in log4j2.xml file).
     */
    static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        TaskFactory factory = new GeneralTaskFactory();

        Task bugTask = null;
        try {
            bugTask = factory.createTask("task/bugTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read from file");
        }
        Task bugTask2 = null;
        try {
            bugTask2 = factory.createTask("task/bugTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read from file");
        }
        Task featureTask = null;
        try {
            featureTask = factory.createTask("task/featureTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read from file");
        }
        Task managementTask = null;
        try {
            managementTask = factory.createTask("task/managementTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read from file");
        }
        CompositeTask compositeTask = null;
        try {
            compositeTask = (CompositeTask) factory.createTask("task/compositeTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read from file");
        }

        Context defualtContext = new Context(new DefaultStrategy());
        Context usualContext = new Context(new UsualStrategy());
        Context twiceDebugContext = new Context(new TwiceDebugStrategy());
        if (compositeTask != null) {
            compositeTask.addTask(managementTask);
            compositeTask.addTask(featureTask);
            compositeTask.addTask(bugTask);
            System.out.println();
            defualtContext.executeStrategy(compositeTask);
            System.out.println();
            usualContext.executeStrategy(compositeTask);
            System.out.println();
        }

        if (bugTask != null) {
            defualtContext.executeStrategy(bugTask);
            System.out.println();
            twiceDebugContext.executeStrategy(bugTask2);
        }

        if (featureTask != null) {
            defualtContext.executeStrategy(featureTask);
        }

        if (managementTask != null) {
            defualtContext.executeStrategy(managementTask);
        }
    }
}