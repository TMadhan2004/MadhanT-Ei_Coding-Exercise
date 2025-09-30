public class ExpenseRequest 
{
    private final String purpose;
    private final double amount;

    public ExpenseRequest(String purpose, double amount) 
    {
        this.purpose = purpose;
        this.amount = amount;
    }

    public String getPurpose() 
    { 
        return purpose; 
    }
    public double getAmount() 
    { 
        return amount; 
    }

    @Override
    public String toString() 
    {
        return String.format("Expense[%.2f] - %s", amount, purpose);
    }
}
