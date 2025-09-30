public abstract class BaseApprover implements Approver 
{
    protected Approver next;

    @Override
    public Approver setNext(Approver next) 
    {
        this.next = next;
        return next;
    }

    protected void passToNext(ExpenseRequest request) 
    {
        if (next != null) 
        {
            next.approve(request);
        } 
        else 
        {
            System.out.println("No approver available for: " + request);
        }
    }
}
