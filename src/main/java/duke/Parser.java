package duke;

public class Parser {
    public static String formatFirstWord(String input){
        String[] inputArray = input.split(" ", 2);
        inputArray[0] = inputArray[0].toLowerCase();
        if(inputArray.length > 1){
            return inputArray[0] + " " + inputArray[1];
        }
        return inputArray[0];
    }
}
