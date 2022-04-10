package com.calebjianhui.duke.taskmanager;

import java.util.Arrays;
import java.util.List;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Getter for isDone
     * **/
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Getter for description
     * **/
    public abstract String getDescription();

    /**
     * Getter for type of task
     * **/
    public abstract String getType();

    /**
     * Setter for isDone
     *
     * @param isDone New Status
     * **/
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Setter for description
     *
     * @param description New description
     * **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Check if the description contains the keyword
     *
     * @param isCharacterSearch Whether to search by characters or word blocks
     * @param keyword Keyword to search for
     * @return If the description contains the keyword
     * **/
    public boolean containsKeyword(boolean isCharacterSearch, String keyword) {
        if (isCharacterSearch) {
            return this.getDescription().toLowerCase().contains(keyword.toLowerCase());
        } else {
            List<String> descriptionWordList = Arrays.asList(getDescription().toLowerCase().split(" "));
            return descriptionWordList.contains(keyword.toLowerCase());
        }
    }

}
