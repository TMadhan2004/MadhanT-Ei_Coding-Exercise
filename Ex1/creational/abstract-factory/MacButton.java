public class MacButton implements Button {
    private Runnable handler;
    @Override
    public void render() 
    {
        System.out.println("Mac Button: Submit");
    }
    @Override
    public void onClick(Runnable handler) 
    { 
        this.handler = handler; 
    }
    @Override
    public void click() {
        System.out.println("Mac You clicked the Submit button.");
        if (handler != null) handler.run();
    }
}
