package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;

import java.time.LocalDateTime;

public interface DateModule {
    /**
     * Get the current date structure of the task
     *
     * @return Pair<TaskDateStructure, LocalDateTime> of Task
     * **/
    Pair<TaskDateStructure, LocalDateTime> getDateStructure();

    /**
     * Sets the date for the task
     * **/
    void setDate(String newDate);

    /**
     * Gets the date for the task
     *
     * @param isFormatted Whether to return the formatted date
     * **/
    String getDate(boolean isFormatted);

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
