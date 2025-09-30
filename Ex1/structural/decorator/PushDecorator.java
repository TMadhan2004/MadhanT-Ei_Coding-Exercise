public class PushDecorator extends DecoratorBase
{
    public PushDecorator(Notifier wrappee) 
    { 
        super(wrappee); 
    }
    @Override
    public void send(String user, String message) 
    {
        wrappee.send(user, message);
        System.out.println("Push To: " + user + "  Msg: " + message);
    }
}
