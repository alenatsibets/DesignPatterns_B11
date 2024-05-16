package edu.pattern.management.entity;

import static edu.pattern.management.entity.TaskStatus.IN_PROCESS;

public class BugTask extends Task {

    public BugTask(String name, String deadline, String personName, String description) {
        super(name, deadline, personName, description);
    }

    @Override
    public void process() {
        switch (this.status) {
            case TO_DO:
                this.setStatus(IN_PROCESS);
            case IN_PROCESS:
                System.out.println("BugTask is processed... ");
                this.finish();
                break;
            case DONE:
                System.out.println("This BugTask is completed.");
                break;
            default:
                System.out.println("Unknown state");
                break;
        }
    }
}