package duke.parser;

import java.util.ArrayList;

/**
 * Extended class of Parser with method that converts user input from String into an ArrayList.
 */
public class Tokenizer extends Parser {

    /**
     * Returns user input in ArrayList format after separating each individual word in String.
     *
     * @param input User Input obtain from UI.
     * @return ArrayList containing individual words from user input.
     */
    public static ArrayList<String> tokenizer(String input) {
        ArrayList<String> output = new ArrayList<String>();

        // iterate through input string
        for (int i = 0; i < input.length();) {
            String curWord = "";
            // capture current word separated by space character
            do {
                curWord = curWord + Character.toString(input.charAt(i));
                i++;
            }
            while (i < input.length() && input.charAt(i) != ' ');
            curWord = curWord.stripLeading();
            curWord = curWord.stripTrailing();
            output.add(curWord);
        }
        return output;
    }

}
