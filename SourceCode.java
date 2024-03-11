import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Locker {
    private String studentId;
    private String contactNumber;
    private long startTime;

    public Locker(String studentId, String contactNumber, long startTime) {
        this.studentId = studentId;
        this.contactNumber = contactNumber;
        this.startTime = startTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public long getStartTime() {
        return startTime;
    }
}

public class LibraryLockerManagementSystem {
    private static Map<Integer, Locker> lockers = new HashMap<>();
    private static final long OVERTIME_LIMIT = 120000; // 2 minutes in milliseconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Locker");
            System.out.println("2. Release Locker");
            System.out.println("3. Check Total Available Lockers");
            System.out.println("4. Check Total Booked Lockers");
            System.out.println("5. Check Overtime Lockers");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    bookLocker(scanner);
                    break;
                case 2:
                    releaseLocker(scanner);
                    break;
                case 3:
                    checkTotalAvailableLockers();
                    break;
                case 4:
                    checkTotalBookedLockers();
                    break;
                case 5:
                    checkOvertimeLockers();
                    break;
                case 6:
                    System.out.println("Exiting the Library Locker Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookLocker(Scanner scanner) {
        System.out.print("\n\nEnter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();

        int lockerNumber = getAvailableLocker();
        long startTime = System.currentTimeMillis();

        Locker locker = new Locker(studentId, contactNumber, startTime);
        lockers.put(lockerNumber, locker);

        System.out.println("\nLocker Number " + lockerNumber + " Assigned Successfully\n\n");
    }

    private static void releaseLocker(Scanner scanner) {
        System.out.print("\n\nEnter Locker Number to release: ");
        int lockerNumber = scanner.nextInt();

        if (lockers.containsKey(lockerNumber)) {
            Locker locker = lockers.get(lockerNumber);
            long elapsedTime = System.currentTimeMillis() - locker.getStartTime();

            System.out.println("\nLocker Number " + lockerNumber + " has been released successfully.");
            System.out.println("Student ID: " + locker.getStudentId());
            System.out.println("Booking Time: " + elapsedTime / 1000 + " seconds");

            if (elapsedTime > OVERTIME_LIMIT) {
                System.out.println("\nWARNING: Locker has done overtime!\n\n");
            }

            lockers.remove(lockerNumber);
        } else {
            System.out.println("\nLocker not found. Please enter a valid Locker Number.");
        }
    }

    private static void checkTotalAvailableLockers() {
        int totalAvailableLockers = 100 - lockers.size(); // Assuming there are 100 lockers in total
        System.out.println("\n\nTotal Available Lockers: " + totalAvailableLockers);
        System.out.println("\nAvailable lockers are: " + getAvailableLockerNumbers());
    }

    private static void checkTotalBookedLockers() {
        System.out.println("\n\nTotal Booked Lockers: " + lockers.size());
        System.out.println("\nBooked lockers are: " + getBookedLockerNumbers());
    }

    private static void checkOvertimeLockers() {
        System.out.println("\n\nTotal Overtime Lockers: " + getOvertimeLockerCount());
        System.out.println("\nLocker Details -");

        for (Map.Entry<Integer, Locker> entry : lockers.entrySet()) {
            long elapsedTime = System.currentTimeMillis() - entry.getValue().getStartTime();
            if (elapsedTime > OVERTIME_LIMIT) {
                System.out.println(entry.getKey() + " - " + entry.getValue().getStudentId() + " - " + entry.getValue().getContactNumber());
            }
        }
    }

    private static String getAvailableLockerNumbers() {
        StringBuilder availableLockers = new StringBuilder();
        for (int i = 1; i <= 100; i++) { // Assuming there are 100 lockers in total
            if (!lockers.containsKey(i)) {
                availableLockers.append(i).append(", ");
            }
        }
        return availableLockers.toString().replaceAll(", $", ""); // Remove the trailing comma and space
    }

    private static String getBookedLockerNumbers() {
        StringBuilder bookedLockers = new StringBuilder();
        for (int i = 1; i <= 100; i++) { // Assuming there are 100 lockers in total
            if (lockers.containsKey(i)) {
                bookedLockers.append(i).append(", ");
            }
        }
        return bookedLockers.toString().replaceAll(", $", ""); // Remove the trailing comma and space
    }

    private static int getOvertimeLockerCount() {
        int overtimeLockerCount = 0;
        for (Map.Entry<Integer, Locker> entry : lockers.entrySet()) {
            long elapsedTime = System.currentTimeMillis() - entry.getValue().getStartTime();
            if (elapsedTime > OVERTIME_LIMIT) {
                overtimeLockerCount++;
            }
        }
        return overtimeLockerCount;
    }

    private static int getAvailableLocker() {
        for (int i = 1; i <= 100; i++) { // Assuming there are 100 lockers in total
            if (!lockers.containsKey(i)) {
                return i;
            }
        }
        return -1; // No available lockers
    }
}
