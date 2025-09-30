public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Adapter Pattern Demo (INR to USD payment)\n");

        ThirdPartyUSPay gateway = new ThirdPartyUSPay();
        PaymentProcessor processor = new InrToUsdAdapter(gateway, 0.012);

        processor.pay(1000);   
        processor.pay(0);     
        processor.pay(259.75); 
    }
}
