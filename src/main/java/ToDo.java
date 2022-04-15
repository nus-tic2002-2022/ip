import java.util.Arrays;
import java.util.Scanner;


public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

