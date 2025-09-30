public class WindowsButton implements Button 
{
    private Runnable handler;
    @Override
    public void render() 
    {
        System.out.println("Windows Button: Submit");
    }
    @Override
    public void onClick(Runnable handler) 
    { 
        this.handler = handler; 
    }
    @Override
    public void click() 
    {
        System.out.println("Windows You clicked the Submit button.");
        if (handler != null) handler.run();
    }
}
