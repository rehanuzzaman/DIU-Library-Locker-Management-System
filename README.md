**DIU Library LockerManagement System**

1. Introduction:
The Library Locker Management System is a Java-based application designed to efficiently manage lockers within a library. It allows users to book, release, and monitor lockers, providing essential information such as availability, booking status, and overtime warnings.

2. Features:

Book Locker:
Users can reserve lockers by entering their student ID, contact number, and the system automatically assigns an available locker, recording the start time of the booking.

Release Locker:
Users can release a booked locker by entering the corresponding locker number. The system calculates the booking duration and provides a warning if the locker exceeds the allowed overtime limit.

Check Total Available Lockers:
Displays the total number of available lockers and their corresponding numbers.

Check Total Booked Lockers:
Shows the total number of currently booked lockers and their corresponding numbers.

Check Overtime Lockers:
Identifies and displays lockers that have exceeded the allowed booking duration, along with student details.

Exit:
Allows the user to gracefully exit the system.

3. Implementation Details:

The system uses a Locker class to encapsulate information about each locker, such as student ID, contact number, and booking start time.
A HashMap is employed to store and manage locker objects, using locker numbers as keys.
Overtime is defined as exceeding a specified time limit (2 minutes in this case), and relevant warnings are provided.
4. Usage:

Upon running the program, the user is presented with a menu to choose from various options.
The system continuously runs until the user chooses to exit.
5. How to Run:

Compile the Java file: javac LibraryLockerManagementSystem.java
Run the compiled program: java LibraryLockerManagementSystem
6. Assumptions:

The system assumes a fixed number of lockers (100 in this case) for simplicity.
Time is measured in milliseconds, and the overtime limit is set to 2 minutes.
7. Future Improvements:

Integration with a database for persistent data storage.
Enhanced user interface for better user experience.
Additional features such as locker customization and user authentication.
