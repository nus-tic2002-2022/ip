package com.calebjianhui.duke.taskmanager;

import java.util.Arrays;
import java.util.List;

/**
 * A Task class representing a generic task
 */
public abstract class Task {
    // Variables needed:
    protected boolean isDone;
    protected String description;

    /**
     * Task constructor
     *
     * @param isDone Whether the task is done
     * @param description Description of task
     **/
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Get the done status of the task
     *
     * @return Done status of task
     **/
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Gets the description of the task
     *
     * @return Description of task
     **/
    public abstract String getDescription();

    /**
     * Getter for type of task
     *
     * @return Task type
     **/
    public abstract String getType();

    /**
     * Setter for isDone
     *
     * @param isDone New Status
     **/
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Setter for description
     *
     * @param description New description
     **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns true if the task description contains the input keyword
     *
     * @param isCharacterSearch Whether to search by characters or word blocks
     * @param keyword Keyword to search for
     * @return If the description contains the keyword
     **/
    public boolean containsKeyword(boolean isCharacterSearch, String keyword) {
        if (isCharacterSearch) {
            return this.getDescription().toLowerCase().contains(keyword.toLowerCase());
        } else {
            List<String> descriptionWordList = Arrays.asList(getDescription().toLowerCase().split(" "));
            return descriptionWordList.contains(keyword.toLowerCase());
        }
    }

}
