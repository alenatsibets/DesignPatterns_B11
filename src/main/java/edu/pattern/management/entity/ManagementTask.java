package edu.pattern.management.entity;

import static edu.pattern.management.entity.TaskStatus.IN_PROCESS;

public class ManagementTask extends Task {

    public ManagementTask(String name, String deadline, String personName, String description) {
        super(name, deadline, personName, description);
    }

    @Override
    public void process() {
        switch (this.status) {
            case TO_DO:
                this.setStatus(IN_PROCESS);
            case IN_PROCESS:
                System.out.println("ManagementTask is processed... ");
                this.finish();
                break;
            case DONE:
                System.out.println("This ManagementTask is completed.");
                break;
            default:
                System.out.println("Unknown state");
                break;
        }
    }
}