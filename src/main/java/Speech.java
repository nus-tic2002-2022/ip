public class Speech {

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
}
