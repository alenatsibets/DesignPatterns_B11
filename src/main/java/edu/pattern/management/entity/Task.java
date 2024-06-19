package edu.pattern.management.entity;

import java.util.Objects;

import static edu.pattern.management.entity.TaskStatus.DONE;

/**
 * Task entity as an abstract class.
 */

public abstract class Task {
    /**
     * Short Task designation.
     */
    public String name;
    /**
     * Task status. There are tree possible variants: TO_DO, IN_PROGRESS, DONE.
     */
    public TaskStatus status;
    /**
     * Time by which the Task must be completed.
     */
    public String deadline;
    /**
     * Person who is responsible for the Task.
     */
    public String personName;
    /**
     * Description of what must be done.
     */
    public String description;

    /**
     * Constructor method for creating a Task. Initially status is set as TO_DO.
     */
    public Task(String name, String deadline, String personName, String description) {
        this.name = name;
        this.status = TaskStatus.TO_DO;
        this.deadline = deadline;
        this.personName = personName;
        this.description = description;
    }

    /**
     * Method for processing the Task. Corresponding inscription is displayed.
     */
    public abstract void process();

    /**
     * Method for checking whether the status of the Task is DONE.
     *
     * @return boolean value
     */
    public boolean isCompleted() {
        return this.status == DONE;
    }

    /**
     * Method for setting DONE status to the Task. It is used inside the process() method.
     */
    public void finish() {
        if (!this.isCompleted()) {
            this.setStatus(DONE);
        }
        System.out.println(this.status);
    }

    /**
     * Method for getting name of the Task.
     *
     * @return existing name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting new name to the Task.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for getting description of the Task.
     *
     * @return existing description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method for setting new description to the Task.
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method for getting deadline of the Task.
     *
     * @return existing deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Method for setting new deadline to the Task.
     *
     * @param deadline new deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Method for getting name of responsible person of the Task.
     *
     * @return existing person name
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Method for setting new name of responsible person of the Task.
     *
     * @param personName new person name
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * Method for getting status of the Task.
     *
     * @return existing status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Method for setting new status to the Task.
     *
     * @param status new status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!Objects.equals(name, task.name)) return false;
        if (!Objects.equals(description, task.description)) return false;
        if (!Objects.equals(deadline, task.deadline)) return false;
        return Objects.equals(personName, task.personName);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", personName='" + personName + '\'' +
                '}';
    }
}