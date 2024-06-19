package edu.pattern.management.strategy.impl;

import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.chain.impl.CompositeTaskHandler;
import edu.pattern.management.chain.impl.FeatureTaskHandler;
import edu.pattern.management.chain.impl.ManagementTaskHandler;
import edu.pattern.management.entity.*;
import edu.pattern.management.strategy.TaskProcessingStrategy;

/**
 * DefaultStrategy class is a realisation of a Strategy design pattern. It is used for handling a single Task.
 */
public class DefaultStrategy implements TaskProcessingStrategy {
    /**
     * Method for calling corresponding TaskHandler to process single Task.
     *
     * @param task task to handle
     */
    @Override
    public void processTask(Task task) {
        switch (task) {
            case BugTask bugTask:
                BugTaskHandler bugHandler = new BugTaskHandler(null);
                bugHandler.handleTask(bugTask);
                break;
            case FeatureTask featureTask:
                FeatureTaskHandler featureHandler = new FeatureTaskHandler(null);
                featureHandler.handleTask(featureTask);
                break;
            case ManagementTask managementTask:
                ManagementTaskHandler managementHandler = new ManagementTaskHandler(null);
                managementHandler.handleTask(managementTask);
                break;
            case CompositeTask compositeTask:
                CompositeTaskHandler compositeTaskHandler = new CompositeTaskHandler(null);
                compositeTaskHandler.handleTask(compositeTask);
                break;
            default:
                System.out.println("Unknown task type");
                break;
        }
    }
}