package com.calebjianhui.duke.taskmanager;

/**
 * A task that represents a task that require a fixed duration of time
 **/
public class FixedDurationTask extends Task implements FixedDurationModule {
    // Command separator given by user (to separate description from time needed)
    public static final String COMMAND_SEPARATOR = " /needs ";
    // For UI purposes to differentiate type of task
    public static final String TYPE_INDICATOR = "F";

    // Variables needed:
    private String rawDuration;

    /**
     * FixedDurationTask constructor
     *
     * @param isDone Whether the task is done
     * @param description Description of task
     * @param duration Duration of time needed for the task
     **/
    public FixedDurationTask(boolean isDone, String description, String duration) {
        super(isDone, description);
        setDuration(duration);
    }

    /**
     * Getter for description
     *
     * @return Formatted Description of task
     **/
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
     *
     * @return Full description including date (as given by user)
     **/
    @Override
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + rawDuration;
    }

    /**
     * Getter for type of task
     *
     * @return Type of task
     **/
    public String getType() {
        return TYPE_INDICATOR;
    }

    /**
     * Sets the duration needed for the task
     *
     * @param duration Block of time that is needed for the task
     **/
    @Override
    public void setDuration(String duration) {
        this.rawDuration = duration;
    }

    /**
     * Gets the duration needed for the task
     *
     * @return Duration needed for the task
     **/
    @Override
    public String getDuration() {
        return rawDuration;
    }

}
