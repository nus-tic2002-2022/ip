package duke.parser;

import java.util.ArrayList;
import java.util.List;

import static duke.parser.Tokenizer.tokenizer;

public class Parser {

    protected ArrayList<String> userInput;

    public Parser(String input) {
        this.userInput = tokenizer(input);
    }

    public Parser() {
        this.userInput = new ArrayList<String>();
    }

    public static List<String> fileContentParser(String input) {
        List<String> output = new ArrayList<String>();

        // iterate through input string
        for (int i = 0; i < input.length();) {
            String cur_word = "";
            // capture current word separated by space character
            while (i < input.length() && input.charAt(i) != '|') {
                cur_word = cur_word + Character.toString(input.charAt(i));
                i++;
            }
            cur_word = cur_word.stripLeading();
            cur_word = cur_word.stripTrailing();
            if (!cur_word.equalsIgnoreCase("") && !cur_word.equalsIgnoreCase("type")
                    && !cur_word.equalsIgnoreCase("status") && !cur_word.equalsIgnoreCase("task")
                    && !cur_word.equalsIgnoreCase("deadline/duration")) {
                output.add(cur_word);
            }
            i++;
        }
        return output;
    }

    public String getUserInput(int index) {
        return this.userInput.get(index);
    }
    public int getUserInputSize() {
        return this.userInput.size();
    }
}
