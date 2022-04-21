/**
 * Creates a Todo object. Todo object is inherited from Task object.
 */

import java.util.Arrays;
import java.util.Scanner;


public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    public ToDo(String description, boolean isDone){
        super(description);
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

