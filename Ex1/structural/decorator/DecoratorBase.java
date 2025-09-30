public abstract class DecoratorBase implements Notifier 
{
    protected final Notifier wrappee;
    protected DecoratorBase(Notifier wrappee) 
    {
        if (wrappee == null) 
           throw new IllegalArgumentException("wrappee cannot be null");
        this.wrappee = wrappee;
    }
}
