package com.calebjianhui.duke.taskmanager;

/**
 * Interface for all task that require a fixed duration of time
 **/
public interface FixedDurationModule {

    /**
     * Sets the duration needed for the task
     *
     * @param durationBlock Block of time that is needed for the task
     **/
    void setDuration(String durationBlock);

    /**
     * Gets the duration needed for the task
     *
     * @return Duration needed for the task
     **/
    String getDuration();

    /**
     * Gets the raw description (as given by user)
     *
     * @return Full description including date (as given by user)
     **/
    String getRawDescription();

    /**
     * Gets the description
     *
     * @param isFormatted Should the result be formatted nicely
     * @return Description of task
     **/
    String getDescription(boolean isFormatted);
}
