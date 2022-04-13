public class Speech {

    public static void emptylist(){
        int empty = (int)Math.floor((Math.random() * 7) + 1);
        switch (empty) {
            case 1:
                System.out.println("Sir, May I remind you that you have no tasks on hand. Maybe you will like to add some, Sir?");
                break;
            case 2:
                System.out.println("Maybe you have a concussion Sir? There are no tasks to this list");
                break;
            case 3:
                System.out.println("Sir, your last task was completed and there are no outstanding on this list.");
                break;
            case 4:
                System.out.println("Grats Sir, there are no outstanding tasks on this list, maybe a dinner with Miss Potts?");
                break;
            case 5:
                System.out.println("Sir, there are no tasks on hand");
                break;
            case 6:
                System.out.println("Nothing on this list, Sir");
                break;
            case 7:
                System.out.println("Sir, you are on this rare occasion that the list is empty.");
                break;
        }
    }

    public static void introduction(){
        int hello = (int)Math.floor((Math.random() * 7) + 1);
        switch (hello) {
            case 1:
                System.out.println("Welcome Back Sir, Jarvis at your assistance");
                break;
            case 2:
                System.out.println("Good to see you alive, Sir");
                break;
            case 3:
                System.out.println("Back from Saving the world, Sir?");
                break;
            case 4:
                System.out.println("Good Day Sir, The weather in Malibu is 72 degrees with scattered clouds. The surf conditions are fair with waist to shoulder highlines, high tide will be at 10:52 a.m.");
                break;
            case 5:
                System.out.println("Working on a secret project, are we, Sir?");
                break;
            case 6:
                System.out.println("As always sir, a great pleasure watching you work");
                break;
            case 7:
                System.out.println("Welcome back, sir. I've also prepared a safety briefing for you to entirely ignore");
                break;
        }
    }

    public static void farewell(){
        int byebye = (int)Math.floor((Math.random() * 7) + 1);
        switch (byebye) {
            case 1:
                System.out.println("Out Saving the world with the Avengers again Sir?");
                break;
            case 2:
                System.out.println("Have a good day Sir");
                break;
            case 3:
                System.out.println("Have you forgotten your Ironman Suit?");
                break;
            case 4:
                System.out.println("Maybe take the subway instead of flying Sir");
                break;
            case 5:
                System.out.println("Out to get your Cheeseburger Sir?");
                break;
            case 6:
                System.out.println("The world should thank you for your service Sir");
                break;
            case 7:
                System.out.println("Of Course Sir, it is my pleasure serving you");
                break;
        }
    }

    public static void todospeech(){
        int todorandomizer = (int)Math.floor((Math.random() * 7) + 1);
        switch (todorandomizer) {
            case 1:
                System.out.println("Sir, I have added this task to the list");
                break;
            case 2:
                System.out.println("Getting busier Sir? I have included this item to the list");
                break;
            case 3:
                System.out.println("Sir, this task has been added to the list. Do you still have time to save the world?");
                break;
            case 4:
                System.out.println("Understood Sir, this is added to the list");
                break;
            case 5:
                System.out.println("Included this into the list Sir, do you still have time to have date night with Miss Potts?");
                break;
            case 6:
                System.out.println("I have included this into your list. Hope you have time to rest after completing the tasks, Sir.");
                break;
            case 7:
                System.out.println("Of Course Sir, it is my pleasure serving you");
                break;
        }
    }
}
