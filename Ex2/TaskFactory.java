import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TaskFactory 
{
    private static final List<DateTimeFormatter> FMTS = Arrays.asList(
        DateTimeFormatter.ofPattern("HH:mm"),
        DateTimeFormatter.ofPattern("H:mm")
    );

    public Task create(String description, String startHHmm, String endHHmm, String priorityStr) 
    {
        List<String> errors = new ArrayList<>();

        if (description == null || description.trim().isEmpty()) {
            errors.add("Description is required");
        }

        LocalTime start = tryParseTime(startHHmm);
        LocalTime end = tryParseTime(endHHmm);
        if (start == null || end == null) {
            errors.add("Invalid time format.");
        }

        Priority priority = null;
        try {
            priority = Priority.fromString(priorityStr);
        } catch (IllegalArgumentException ex) {
            errors.add("Invalid priority.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Error: " + String.join(" ", errors));
        }

        return new Task(description.trim(), start, end, priority);
    }

    private LocalTime tryParseTime(String s) {
        String in = s == null ? null : s.trim();
        for (DateTimeFormatter fmt : FMTS) {
            try {
                return LocalTime.parse(in, fmt);
            } catch (DateTimeParseException ignore) { }
        }
        return null;
    }
}
