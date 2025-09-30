public class WindowsTextField implements TextField 
{
    private String text = "";
    @Override
    public void render() 
    { 
        System.out.println("Windows Rendering TextField: " + text); 
    }
    @Override
    public void setText(String text) 
    { 
        this.text = text; 
    }
    @Override
    public String getText() 
    { 
        return text; 
    }
}
