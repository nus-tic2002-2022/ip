package duke;

import duke.exceptions.InputException;

public class UserInput {
    protected Command command;
    protected Category category;
    protected String day;
    protected String time;
    protected StringBuilder item;

    public UserInput() {
        this.day = "";
        this.time = "";
        this.item = new StringBuilder();
    }

    public UserInput parseInput(String[] tokens) throws InputException {
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
                        this.day = tokens[i + 1];
                    }
                    break;
                case "no":
                case "list":
                    this.command = Command.valueOf(tokens[i].toUpperCase());
                    return this;
                case "mark":
                case "unmark":
                case "delete":
                    this.command = Command.valueOf(tokens[i].toUpperCase());
                    if (tokens.length > 1) {
                        this.item.append(tokens[i + 1]);
                    }
                    return this;
                default:
                    this.item.append(tokens[i]).append(" ");
            }
        }
        return this;
    }

    protected enum Command {
        LIST, MARK, UNMARK, NO, DELETE
    }

    protected enum Category {
        TODO, DEADLINE, EVENT
    }
}
