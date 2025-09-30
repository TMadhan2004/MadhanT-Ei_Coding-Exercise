import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger 
{
    private static volatile Logger instance;

    private Logger() {}

    public static Logger getInstance() 
    {
        if (instance == null) {
            synchronized (Logger.class) 
            {
                if (instance == null) 
                {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String level, String message)
    {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[" + timestamp + "] " + level.toUpperCase() + ": " + message);
    }
}
