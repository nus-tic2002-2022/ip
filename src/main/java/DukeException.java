public class DukeException extends Exception {

    /**
     * This exception occurs that whether input command was recognized.
     * If there is no input or wrong input, the default message will pop out.
     */

    public DukeException(String msg) {
        if(msg =="") msg="☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println (msg);
    }
}
