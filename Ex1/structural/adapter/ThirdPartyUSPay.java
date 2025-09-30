public class ThirdPartyUSPay 
{
    public void chargeInUSD(double amountUsd) 
    {
        System.out.println("ThirdPartyUSPay Charging $" + String.format("%.2f", amountUsd) + " USD");
    }
}
