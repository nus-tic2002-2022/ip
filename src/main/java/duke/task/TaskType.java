package duke.task;

import duke.exception.DukeException;

public enum TaskType {

    EVENT ("event","E"),
    DEADLINE ("deadline", "D"),
    TODO ("todo","T");

    private final String commandText;
    private final String commandCode;

    TaskType (String commandText, String commandCode) {
        this.commandText = commandText;
        this.commandCode = commandCode;
    }

    public String getTaskTypeCode() { return commandCode; }

    /**
     * Convert string to TaskType
     * @param s TaskType in String
     * @return TaskType
     * @throws DukeException
     */
    public static TaskType stringToTaskType(String s) throws DukeException {

        boolean commandCodeMatched;
        boolean commandTextMatched;

        for (TaskType t : values()) {
            commandCodeMatched = t.commandCode.equals(s.trim().toUpperCase());
            commandTextMatched = t.commandText.equals(s.trim().toLowerCase());

            if (commandCodeMatched || commandTextMatched) {
                return t;
            }
        }

        throw new DukeException(DukeException.INVALID_TASK_TYPE_FILE+ "[" + s + "] /!\\");
    }
}
