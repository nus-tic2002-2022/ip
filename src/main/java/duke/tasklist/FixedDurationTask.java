package duke.tasklist;

/**
 * This enum links predefined task to a fixed duration.
 */
public enum FixedDurationTask {
    HAVE_LUNCH("Have Lunch", 1),
    READ_BOOK("Read Book", 2),
    DO_ASSIGNMENT("Do Assignment", 3),
    WORKOUT("Workout", 4),
    ATTEND_LESSON("Attend Lesson", 5);

    private final String fixedTask;
    private final Integer fixedDuration;

    /**
     * Constructor for FixedDurationTask
     *
     * @param task description of task
     * @param duration fixed duration of task
     */
    FixedDurationTask(String task, Integer duration) {
        this.fixedTask = task;
        this.fixedDuration = duration;
    }

    /**
     * Returns fixedTask of FixedDurationTask.
     *
     * @return fixedTask of FixedDurationTask in String format.
     */
    public String getFixedTask() {
        return fixedTask;
    }

    /**
     * Returns fixedDuration of FixedDurationTask.
     *
     * @return fixedDuration of FixedDurationTask in Integer format.
     */
    public Integer getFixedDuration() {
        return fixedDuration;
    }

    /**
     * Returns fixedDuration of FixedDurationTask matching the task description provided.
     *
     * @param task description of task to search fixed duration
     * @return fixedDuration of FixedDurationTask in Integer format.
     */
    public static Integer findFixedDuration(String task) {
        for (FixedDurationTask fixedDurationTask : values()) {
            if (task.equalsIgnoreCase(fixedDurationTask.getFixedTask())) {
                return fixedDurationTask.getFixedDuration();
            }
        }
        return null;
    }
}
