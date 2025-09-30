public class Main 
{
    public static void main(String[] args) 
    {
        Approver manager = new Manager();
        Approver director = new Director();
        Approver ceo = new Ceo();
        manager.setNext(director).setNext(ceo);

        ExpenseRequest r1 = new ExpenseRequest("Team lunch", 250);
        ExpenseRequest r2 = new ExpenseRequest("Conference tickets", 1200);
        ExpenseRequest r3 = new ExpenseRequest("New laptops", 12000);

        manager.approve(r1);
        manager.approve(r2);
        manager.approve(r3);
    }
}
