public class Main 
{
    public static void main(String[] args)
     {
        System.out.println("Decorator Pattern Demo (multi-channel notifications)\n");
        Notifier alt = new PushDecorator(new SmsDecorator(new EmailNotifier()));
        alt.send("user2@example.com", "Your order has been shipped.");

        // Notifier test = new SmsDecorator(new PushDecorator(new EmailNotifier()));
        // test.send("user2@example.com", "Message sent");
    }
}
