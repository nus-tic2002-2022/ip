import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeParser extends Parser {

    public static LocalDate date;

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.parse("2020-12-09");
        //LocalDate date2 = LocalDate.parse("2020-12-10");
        System.out.println(date1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
