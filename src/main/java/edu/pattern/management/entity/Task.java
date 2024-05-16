package edu.pattern.management.entity;

import java.util.Objects;

import static edu.pattern.management.entity.TaskStatus.DONE;

public abstract class Task {
    public String name;
    public TaskStatus status;
    public String deadline;
    public String personName;
    public String description;

    public Task(String name, String deadline, String personName, String description) {
        this.name = name;
        this.status = TaskStatus.TO_DO;
        this.deadline = deadline;
        this.personName = personName;
        this.description = description;
    }

    public abstract void process();

    public boolean isCompleted() {
        return this.status == DONE;
    }

    public void finish() {
        if (!this.isCompleted()) {
            this.setStatus(DONE);
        }
        System.out.println(this.status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public TaskStatus getStatus() {
        return status;
    }

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