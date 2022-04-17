package duke.utils;

import duke.task.DateFunctions;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;


public class SearchDate {
    /**
     * Determines whether the user input query is valid or not.
     * If it is, then Duke will display tasks with the corresponding day or month.
     *
     * @param userInput user input should be something like "search sunday" or "search december".
     * @see duke.task.DateFunctions#daySearch(DayOfWeek)
     * @see duke.task.DateFunctions#monthSearch(Month)
     */
    protected static void searchDate (String userInput){
        String date = "";
        try {
            date = userInput.substring(userInput.indexOf(" ")).trim();
        }catch(StringIndexOutOfBoundsException s) {
            System.out.println("Sorry, what did you want me to search? Please include the Day of the week or Month.\n");
        }
            if (date.contains("day")) {
                try {
                    DayOfWeek day = findDay(date);
                    DateFunctions.daySearch(day);
                } catch (DateTimeException dte) {
                    System.out.println("Oh no! :( I'm not sure what that meant, it didn't seem like a valid day.");
                    System.out.println("You may try typing \"days\" to see the list of valid day.\n");
                }
            } else {
                try {
                    Month month = findMonth(date);
                    DateFunctions.monthSearch(month);
                } catch (DateTimeException dte) {
                    System.out.println("Oh no! :( I'm not sure what that meant, it didn't seem like a valid month.");
                    System.out.println("You may try typing \"months\" to see the list of valid month.\n");
                }
            }

    }

    private static DayOfWeek findDay (String day) throws DateTimeException {
        switch (day) {
            case "monday":
                return DayOfWeek.MONDAY;
            case "tuesday":
                return DayOfWeek.TUESDAY;
            case "wednesday":
                return DayOfWeek.WEDNESDAY;
            case "thursday":
                return DayOfWeek.THURSDAY;
            case "friday":
                return DayOfWeek.FRIDAY;
            case "saturday":
                return DayOfWeek.SATURDAY;
            case "sunday":
                return DayOfWeek.SUNDAY;
            default:
                throw new DateTimeException("");
        }
    }
    private static Month findMonth (String month) throws DateTimeException{
        switch(month){
            case "january":
                return Month.JANUARY;
            case "february":
                return Month.FEBRUARY;
            case "march":
                return Month.MARCH;
            case "april":
                return Month.APRIL;
            case "may":
                return Month.MAY;
            case "june":
                return Month.JUNE;
            case "july":
                return Month.JULY;
            case "august":
                return Month.AUGUST;
            case "september":
                return Month.SEPTEMBER;
            case "october":
                return Month.OCTOBER;
            case "november":
                return Month.NOVEMBER;
            case "december":
                return Month.DECEMBER;
            default:
                throw new DateTimeException("");
        }
    }
}
