package Exceptions;

public class InputException extends Exception {
    String error;

    public InputException(String err) {
        this.error = err;
    }

    public void printError() {
        switch (this.error) {
            case "MissingItem":
                System.out.println("A task has to follow with an item");
            case "NoCommand":
                System.out.println("Invalid command, try again");
        }
    }
}
