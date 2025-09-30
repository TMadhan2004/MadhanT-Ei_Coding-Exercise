public class EmailNotifier implements Notifier 
{
    @Override
    public void send(String user, String message) 
    {
        System.out.println("Email To: " + user + "  Msg: " + message);
    }
}
