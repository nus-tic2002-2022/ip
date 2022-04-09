package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskDateStructure;

public interface DateModule {
    /**
     * Get the current date structure of the task
     *
     * @return TaskDateStructure of Task
     * **/
    TaskDateStructure getDateStructure();

    /**
     * Sets the date for the task
     * **/
    void setDate(String newDate);

    /**
     * Gets the date for the task
     * **/
    String getDate();

    /**
     * Gets the raw description (as given by user)
     * **/
    String getRawDescription();

    /**
     * Gets the description
     *
     * @param isFormatted Should the result be formatted nicely
     * **/
    String getDescription(boolean isFormatted);
}
