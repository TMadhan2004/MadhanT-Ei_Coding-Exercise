import java.util.ArrayList;
import java.util.List;

public class WeatherData 
{
    private final List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    public void addObserver(Observer o) 
    {
        if (o != null) observers.add(o);
    }
    public void removeObserver(Observer o) 
    {
        observers.remove(o);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) 
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    private void notifyObservers() 
    {
        for (Observer o : observers) 
        {
            o.update(temperature, humidity, pressure);
        }
    }
}
