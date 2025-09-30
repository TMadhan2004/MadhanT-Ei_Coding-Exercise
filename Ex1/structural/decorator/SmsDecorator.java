public class SmsDecorator extends DecoratorBase 
{
    public SmsDecorator(Notifier wrappee) 
    { 
        super(wrappee); 
    }
    @Override
    public void send(String user, String message) 
    {
        wrappee.send(user, message);
        System.out.println("SMS To: " + user + "  Msg: " + message);
    }
}
