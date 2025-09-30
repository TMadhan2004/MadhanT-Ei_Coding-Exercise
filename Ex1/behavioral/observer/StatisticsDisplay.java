public class StatisticsDisplay implements Observer 
{
    private float maxTemp = Float.NEGATIVE_INFINITY;
    private float minTemp = Float.POSITIVE_INFINITY;
    private float totalTemp = 0f;
    private int numReadings = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) 
    {
        if (temperature > maxTemp) 
           maxTemp = temperature;
        if (temperature < minTemp) 
           minTemp = temperature;
        totalTemp += temperature;
        numReadings++;
        float avg = totalTemp / numReadings;
        System.out.println(String.format("Stats - min: %.1f°C, max: %.1f°C, avg: %.1f°C", minTemp, maxTemp, avg));
    }
}
