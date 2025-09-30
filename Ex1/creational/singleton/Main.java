public class Main 
{
    public static void main(String[] args)
     {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("info", "Application started.");
        logger2.log("warning", "Low memory warning.");
        logger1.log("error", "Unexpected error occurred!");

        if (logger1 == logger2) 
        {
            System.out.println("\nBoth logger1 and logger2 are the SAME instance.");
        }
    }
}
