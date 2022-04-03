import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+) (?<arguments>.*\\S+)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        System.out.println("------------testing------------");
        System.out.println(commandWord);
        System.out.println(arguments);
        System.out.println("-----end test-----");


        return new IncorrectCommand();


    }

}
