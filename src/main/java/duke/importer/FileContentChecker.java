package duke.importer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class FileContentChecker {

    protected static String line;

    public static void lineChecker (String input) throws ImportErrorException {

        line = input;
        try{
        formatChecker();
        taskChecker();
        markChecker();
        prepositionChecker();
        } catch (ImportErrorException i){
            throw i;
        }
    }

    private static void formatChecker() throws  ImportErrorException{

        if(line.charAt(0) != '[' || line.charAt(2) != ']' || line.charAt(3) != '[' || line.charAt(5) != ']') {
            System.out.println("☹ OOPS!!! Error importing this line: " + line);
            throw new ImportErrorException("format");
        }
    }

    private static void taskChecker() throws ImportErrorException{
        if(line.charAt(1) == 'T' || line.charAt(1) == 'D' || line.charAt(1) == 'E') {
        }else{
            System.out.println("☹ OOPS!!! Error importing this line: " + line);
            throw new ImportErrorException("task");
        }
    }

    private static void markChecker() throws ImportErrorException{
        if(line.charAt(4) == ' ' || line.charAt(4) == 'X'){
        }else{
            System.out.println("☹ OOPS!!! Error importing this fileInput: " + line);
            throw new ImportErrorException("mark");
        }
    }

    private static void prepositionChecker() throws ImportErrorException{
        if(line.charAt(1) == 'T'){
        }else if(line.charAt(1) == 'D'){
            try{
                byChecker();
            }catch (ImportErrorException i1){
                throw i1;
            }
        }else if(line.charAt(1) == 'E'){
            try {
                atChecker();
            }catch (ImportErrorException i2){
                throw i2;
            }
        }
    }

    private static void byChecker() throws ImportErrorException{
        if(line.charAt(1) == 'D' && line.contains("(by:") && line.charAt(line.length()-1) == ')'){
            String dt = line.substring(line.indexOf("(by:")+4, line.length()-1);
            try{
                dateTimeChecker(dt);
            }catch (ImportErrorException p){
                throw p;
            }
        }else{
            throw new ImportErrorException("by");
        }
    }
    private static void atChecker() throws ImportErrorException{
        if(line.charAt(1) == 'E' && line.contains("(at:") && line.charAt(line.length()-1) == ')'){
            String dt = line.substring(line.indexOf("(at:")+4, line.length()-1);
            try{
                dateTimeChecker(dt);
            }catch (ImportErrorException p){
                throw p;
            }
        }else{
            throw new ImportErrorException("at");
        }
    }
    private static void dateTimeChecker(String dt) throws ImportErrorException{
        final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDateTime dateTime = LocalDateTime.parse(dt.trim(), DATE_TIME_FORMAT);
        }catch(DateTimeParseException p){
            throw new ImportErrorException("dateTime");
        }
    }
}
