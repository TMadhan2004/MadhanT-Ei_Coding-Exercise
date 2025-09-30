public class Director extends BaseApprover 
{
    private final double limit = 5_000.0;

    @Override
    public void approve(ExpenseRequest request) 
    {
        if (request.getAmount() <= limit) 
        {
            System.out.println("Director approved: " + request);
        } 
        else 
        {
            passToNext(request);
        }
    }
}
