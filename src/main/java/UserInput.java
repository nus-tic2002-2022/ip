import Exceptions.InputException;

public class UserInput {

    protected String command;
    protected String category;
    protected String day;
    protected String time;
    protected StringBuilder item;

    public UserInput() {
        this.command = "";
        this.category = "";
        this.day = "";
        this.time = "";
        this.item = new StringBuilder();
    }

    public UserInput parseInput(String[] tokens) throws InputException {
        for (int i = 0; i < tokens.length; i++) {
            if (this.category.equals("") && (tokens[i].equals("todo") || tokens[i].equals("deadline") || tokens[i].equals("event"))) {
                this.category = tokens[i];
                continue;
            }

            if (tokens[i].equals("/by")) {
                this.day = tokens[i + 1];
                break;
            }

            if (tokens[i].equals("/at")) {
                this.day = tokens[i + 1];
                this.time = tokens[i + 2];
                break;
            }

            if (this.command.equals("") && (tokens[i].equals("list") || tokens[i].equals("mark") || tokens[i].equals("unmark") || tokens[i].equals("no"))) {
                this.command = tokens[i];
                if (this.command.equals("no")) {
                    return this;
                }
                if (this.command.equals("mark") || this.command.equals("unmark")) {
                    this.item.append(tokens[i + 1]);
                    return this;
                }
                continue;
            }
            if (!this.category.equals("")) {
                this.item.append(tokens[i]).append(" ");
            }

        }
        return this;
    }
}
