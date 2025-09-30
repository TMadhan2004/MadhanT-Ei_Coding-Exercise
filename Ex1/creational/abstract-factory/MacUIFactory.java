public class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() 
    { 
        return new MacButton(); 
    }
    @Override
    public TextField createTextField() 
    { 
        return new MacTextField(); 
    }
}
