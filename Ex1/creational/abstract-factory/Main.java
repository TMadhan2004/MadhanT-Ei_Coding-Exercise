public class Main {
    public static void main(String[] args) 
    {
        System.out.println("Windows Theme");
        UIFactory winFactory = new WindowsUIFactory();
        TextField wtf = winFactory.createTextField();
        System.out.println("Typing username: WindowsUser");
        wtf.setText("WindowsUser");
        wtf.render();
        Button wb = winFactory.createButton();
        wb.onClick(() -> System.out.println("Windows Submitting form for user: " + wtf.getText()));
        wb.render();
        wb.click();

        System.out.println();
        System.out.println("Mac Theme");
        UIFactory macFactory = new MacUIFactory();
        TextField mtf = macFactory.createTextField();
        System.out.println("Typing username: MacUser");
        mtf.setText("MacUser");
        mtf.render();
        Button mb = macFactory.createButton();
        mb.onClick(() -> System.out.println("Mac Submitting form for user: " + mtf.getText()));
        mb.render();
        mb.click();
    }
}
