package duke.parser;

import duke.exception.DukeException;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import static duke.parser.Tokenizer.tokenizer;

/**
 * This class will store the userInput that has been tokenized and contain methods for accessing the userInput value and size.
 * This class also contain fileContentParser for conversion of text file content and removing the template.
 */
public class Parser {

    protected static ArrayList<String> userInput;

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
        return userInput.get(index);
    }

    /**
     * Returns size of userInput variable in Parser class.
     *
     * @return the size of userInput.
     */
    public int getUserInputSize() {
        return userInput.size();
    }

    /**
     * Returns LocalDateTime based on userInput variable in Parser class.
     *
     * @param index the starting index of userInput which contain information of dateTime.
     * @return dateTime information in LocalDateTime format.
     */
    public static LocalDateTime dateTimeParser(int index) throws DukeException {
        LocalDateTime output = null;
        try {
            String d1 = userInput.get(index);
            String t1 = userInput.get(index + 1);
            DateTimeFormatter formatter = null;
            if (d1.matches("(.*)-(.*)")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            } else {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            }
            output = LocalDateTime.parse(d1 + " " + t1, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("invalidDate");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("missingTime");
        }

        return output;
    }

    /**
     * Returns LocalDate based on userInput variable in Parser class.
     *
     * @param index the starting index of userInput which contain information of date.
     * @return date information in LocalDate format.
     */
    public static LocalDate dateParser(int index) throws DukeException {
        LocalDate output = null;
        try {
            String d1 = userInput.get(index);
            DateTimeFormatter formatter = null;
            if (d1.matches("(.*)-(.*)")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            } else {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
            output = LocalDate.parse(d1, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("invalidDate");
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < userInput.size(); i++) {
            output = output + "index at " + i + " : " + userInput.get(i) + "\n";
        }
        return output;
    }
}
