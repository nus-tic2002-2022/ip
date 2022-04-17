package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UserInput {
    protected Command command;
    protected Category category;
    protected LocalDate day;
    protected String time;
    protected StringBuilder item;

    public UserInput() {
        this.time = "";
        this.item = new StringBuilder();
    }

    /**
     * Return parsed user input
     *
     * @param tokens of the user input splitted by empty spaces " "
     * @return parsed user input
     */
    public UserInput parseInput(String[] tokens) throws DateTimeParseException {
        try {
            for (int i = 0; i < tokens.length; i++) {
                switch (tokens[i]) {
                case "todo":
                case "deadline":
                case "event":
                    this.category = Category.valueOf(tokens[i].toUpperCase());
                    break;
                case "/at":
                    if (tokens.length > 2) {
                        this.time = tokens[i + 2];
                    }
                case "/by":
                    if (tokens.length > 1) {
                        this.day = LocalDate.parse(tokens[i + 1]);
                    }
                    return this;
                case "no":
                case "list":
                    this.command = Command.valueOf(tokens[i].toUpperCase());
                    return this;
                case "mark":
                case "unmark":
                case "delete":
                case "find":
                    this.command = Command.valueOf(tokens[i].toUpperCase());
                    if (tokens.length > 1) {
                        this.item.append(tokens[i + 1]);
                    }
                    return this;
                default:
                    this.item.append(tokens[i]).append(" ");
                }
            }
        } catch (DateTimeParseException e) {
            throw e;
        }

        return this;
    }

    protected enum Command {
        LIST, MARK, UNMARK, NO, DELETE, FIND
    }

    protected enum Category {
        TODO, DEADLINE, EVENT
    }
}
