package duke.parser;

import java.util.ArrayList;
import java.util.List;

import static duke.parser.Tokenizer.tokenizer;

/**
 * This class will store the userInput that has been tokenized and contain methods for accessing the userInput value and size.
 * This class also contain fileContentParser for conversion of text file content and removing the template.
 */
public class Parser {

    protected ArrayList<String> userInput;

    /**
     * Constructor for Parser
     *
     * @param input User input from UI.
     */
    public Parser(String input) {
        this.userInput = tokenizer(input);
    }

    /**
     * Constructor for Parser
     */
    public Parser() {
        this.userInput = new ArrayList<String>();
    }

    /**
     * Returns List of String after converting text file content and removing template information.
     *
     * @param input File input from text file in String.
     * @return a list of Strings on text file content.
     */
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

    /**
     * Returns userInput variable in Parser class at specific index.
     *
     * @param index index of userInput to be accessed.
     * @return the Strings at specific index of userInput.
     */
    public String getUserInput(int index) {
        return this.userInput.get(index);
    }

    /**
     * Returns size of userInput variable in Parser class.
     *
     * @return the size of userInput.
     */
    public int getUserInputSize() {
        return this.userInput.size();
    }
}
