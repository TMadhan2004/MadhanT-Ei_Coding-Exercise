public class MacTextField implements TextField {
    private String text = "";
    @Override
    public void render() 
    { 
        System.out.println("Mac Rendering TextField: " + text); 
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
