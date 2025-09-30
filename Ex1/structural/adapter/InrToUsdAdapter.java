public class InrToUsdAdapter implements PaymentProcessor 
{
    private final ThirdPartyUSPay usPay;
    private final double inrToUsdRate;

    public InrToUsdAdapter(ThirdPartyUSPay usPay, double inrToUsdRate) 
    {
        if (usPay == null) 
            throw new IllegalArgumentException("usPay cannot be null");
        if (inrToUsdRate <= 0) 
            throw new IllegalArgumentException("rate must be positive");
        this.usPay = usPay;
        this.inrToUsdRate = inrToUsdRate;
    }

    @Override
    public void pay(double amountRupees) 
    {
        if (amountRupees <= 0) 
        {
            System.out.println("Adapter Invalid amount in INR: " + amountRupees);
            return;
        }
        double usd = amountRupees * inrToUsdRate;
        System.out.println("Adapter Converting ₹" + String.format("%.2f", amountRupees) + " to $" + String.format("%.2f", usd) + " at rate " + inrToUsdRate);
        usPay.chargeInUSD(usd);
    }
}
