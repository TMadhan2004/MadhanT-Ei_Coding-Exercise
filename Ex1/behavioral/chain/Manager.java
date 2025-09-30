public class Manager extends BaseApprover 
{
    private final double limit = 500.0;

    @Override
    public void approve(ExpenseRequest request) 
    {
        if (request.getAmount() <= limit) 
        {
            System.out.println("Manager approved: " + request);
        } 
        else 
        {
            passToNext(request);
        }
    }
}
