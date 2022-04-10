package com.calebjianhui.duke.taskmanager;

public class FixedDurationTask extends Task implements FixedDurationModule {
    public static final String COMMAND_SEPARATOR = " /needs ";
    public static final String TYPE_INDICATOR = "F";

    private String rawDuration;

    public FixedDurationTask(boolean isDone, String description, String duration) {
        super(isDone, description);
        setDuration(duration);
    }

    /**
     * Getter for description
     *
     * @return Description of task
     * **/
    public String getDescription() {
        return description + " (needs: " + rawDuration + ")";
    }

    /**
     * Gets the description
     *
     * @param isFormatted Should the result be formatted nicely
     * @return Description of task
     **/
    @Override
    public String getDescription(boolean isFormatted) {
        if (isFormatted) {
            return getDescription();
        } else {
            return description;
        }
    }

    /**
     * Gets the raw description (as given by user)
     **/
    @Override
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + rawDuration;
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return TYPE_INDICATOR;
    }

    /**
     * Set the duration of the task
     * **/
    @Override
    public void setDuration(String duration) {
        this.rawDuration = duration;
    }

    /**
     * Get the duration of the task
     * **/
    @Override
    public String getDuration() {
        return rawDuration;
    }

}
