public class Ceo extends BaseApprover 
{
    @Override
    public void approve(ExpenseRequest request) 
    {
        System.out.println("CEO approved: " + request);
    }
}
