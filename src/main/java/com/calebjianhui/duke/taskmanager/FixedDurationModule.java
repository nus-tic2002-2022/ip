package com.calebjianhui.duke.taskmanager;

public interface FixedDurationModule {

    /**
     * Sets the duration for the task
     * **/
    void setDuration(String durationBlock);

    /**
     * Gets the duration for the task
     * **/
    String getDuration();

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
