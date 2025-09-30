import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Astronaut Daily Schedule Organizer\n");

        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConflictObserver());
        manager.addObserver(new LoggingObserver());

        TaskFactory factory = new TaskFactory();
        ConsoleController controller = new ConsoleController(manager, factory);

        try (Scanner sc = new Scanner(System.in)) {
            boolean continueSession;
            do {
                printMenu();
                System.out.print("Enter: ");
                String choice = sc.nextLine().trim();
                System.out.println();
                continueSession = controller.handle(choice, sc);
            } while (continueSession);
        }
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1) Add Task");
        System.out.println("2) Remove Task");
        System.out.println("3) View Tasks (sorted)");
        System.out.println("4) Edit Task");
        System.out.println("5) Mark Task Completed");
        System.out.println("6) View Tasks by Priority");
        System.out.println("0) Exit");
    }
}
