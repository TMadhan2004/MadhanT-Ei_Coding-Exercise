public class CurrentConditionsDisplay implements Observer 
{
    @Override
    public void update(float temperature, float humidity, float pressure) 
    {
        System.out.println(String.format("Current conditions: %.1f°C, %.1f%% humidity, %.1f hPa", temperature, humidity, pressure));
    }
}
