public interface Approver 
{
    Approver setNext(Approver next);
    void approve(ExpenseRequest request);
}
