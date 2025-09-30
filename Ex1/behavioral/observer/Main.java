public class Main 
{
    public static void main(String[] args) 
    {
        WeatherData data = new WeatherData();
        data.addObserver(new CurrentConditionsDisplay());
        data.addObserver(new StatisticsDisplay());

        data.setMeasurements(29.4f, 65f, 1012.3f);
        data.setMeasurements(30.1f, 60f, 1011.8f);
        data.setMeasurements(27.8f, 70f, 1010.5f);
    }
}
