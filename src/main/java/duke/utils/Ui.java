package duke.utils;

/**
 * Displays useful things on the screen
 */
public class Ui {

    public Ui() {
        showWelcome();
    }

    private void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * List the days of the week
     * monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    protected static void showDays(){
        System.out.println("Check out the list of valid days below: ");
        System.out.println("monday\ntuesday\nwednesday\nthursday\nfriday\nsaturday\nsunday");
    }

    /**
     * List the months of the year
     * january, february, march, april, may, june, july, august, september,october, november, december
     */
    protected static void showMonths(){
        System.out.println("Check out the list of valid months below: ");
        System.out.println("january\nfebruary\nmarch\napril\nmay\njune");
        System.out.println("july\naugust\nseptember\noctober\nnovember\ndecember");
    }
}
