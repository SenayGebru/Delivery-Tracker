/**
 * This project updates the original program so it can be expanded for more than 20 drivers and additional delivery points. 
 * Furthermore, it searches and sorts the data accordingly into linkedlists.
 * @author Senay Gebru 
 * NETID: STG230001
 * Date:10/20/2023
 */    
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        // Prompting the user for file names.
        String driverFilename = getFileName(scanner, "Enter the name of the file containing driver routes: ");
        String commandsFilename = getFileName(scanner, "Enter the name of the file containing search and sort commands: ");
        
        LinkedList<Driver> driverList = new LinkedList<>();
        
        // Populates the linked list with name of the drivers from the provided file.
        readDriverFile(driverFilename, driverList);
        
        // Process the commands from the provided command file.
        processCommandsFile(commandsFilename, driverList);
        
        // Display the final driver list.
        displayDriverList(driverList);
        
        scanner.close();
    }

    /**
     * Prompt user for a filename.
     * @param scanner Scanner object to read user input.
     * @param prompt The message to display to the user.
     * @return The entered filename.
     */
    public static String getFileName(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Reads drivers and their routes from a file and populates the provided linked list.
     * @param filename The name of the file to read.
     * @param driverList The linked list to populate with Driver objects.
     */
    public static void readDriverFile(String filename, LinkedList<Driver> driverList) throws IOException {
        Scanner fileScanner = new Scanner(new File(filename));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split("[ ,]+");
            if (parts.length >= 2) {
                String driverName = parts[0];
                Driver driver = new Driver(driverName);
                List<Point> driverCoordinates = new ArrayList<>();
                for (int i = 1; i < parts.length; i += 2) {
                    double x = Double.parseDouble(parts[i]);
                    double y = Double.parseDouble(parts[i + 1]);
                    driverCoordinates.add(new Point(x, y));
                }
                // Check if the first and last coordinates match
                Point firstPoint = driverCoordinates.get(0);
                Point lastPoint = driverCoordinates.get(driverCoordinates.size() - 1);
                if (firstPoint.getX() == lastPoint.getX() && firstPoint.getY() == lastPoint.getY()) {
                    double area = calculateArea(driverCoordinates);
                    driver.setArea(area);
                    driverList.add(driver);
                }
                
                

            }
            
        }
        fileScanner.close();
        
    }
    

    /**
     * Processes the commands file to sort and search the driver list.
     * @param filename The name of the command file.
     * @param driverList The linked list of drivers.
     */
    public static void processCommandsFile(String filename, LinkedList<Driver> driverList) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    if ("sort".equalsIgnoreCase(parts[0]) && parts.length >= 3) {
                        // Handle sorting commands
                        if ("name".equalsIgnoreCase(parts[1])) {
                            if ("asc".equalsIgnoreCase(parts[2])) {
                                driverList.sortByName("asc");
                            } else if ("dsc".equalsIgnoreCase(parts[2])) {
                                driverList.sortByName("dsc");
                            }
                        } else if ("area".equalsIgnoreCase(parts[1])) {
                            if ("asc".equalsIgnoreCase(parts[2])) {
                                driverList.sortByArea("asc");
                            } else if ("dsc".equalsIgnoreCase(parts[2])) {
                                driverList.sortByArea("dsc");
                            }
                        }
                    } else {
                        // If not a SORT command, attempt to search the driver list by name
                        String nameToSearch = parts[0];
                        Driver foundDriver = driverList.searchByName(nameToSearch);
                        if (foundDriver == null) {
                            System.out.println(nameToSearch + " not found");
                        } else {
                            System.out.println(foundDriver.getArea());
                        }
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Command file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in commands.");
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred while processing commands.");
            e.printStackTrace();
        }
    }
    

    /**
     * Search the linked list for drivers based on the given query.
     * The query can be either a name or an area.
     * @param driverList The linked list of drivers.
     * @param query The search query.
     */
    public static void searchDriverList(LinkedList<Driver> driverList, String query) {
        boolean found = false;
        Node<Driver> current = driverList.getHead();
        while (current != null) {
            Driver driver = current.getPayload();
            if (driver.getName().equalsIgnoreCase(query) || String.format("%.2f", driver.getArea()).equals(query)) {
                System.out.println(driver.getName() + "\\t" + String.format("%.2f", driver.getArea()));
                found = true;
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println(query + " not found");
        }
    }

    /**
     * Displays the names and areas of all drivers in the linked list.
     * @param driverList The linked list of drivers.
     */
    public static void displayDriverList(LinkedList<Driver> driverList) {
        Node<Driver> current = driverList.getHead();
        while (current != null) {
            System.out.println(current.getPayload());
            current = current.getNext();
        }
    }

    /**
     * Represents a 2D point with x and y coordinates.
     */
    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    /**
     * Calculates the area covered by a set of points.
     * Assumes the points define a polygon.
     * @param points The list of points.
     * @return The calculated area.
     */
    public static double calculateArea(List<Point> points) {
        double area = 0.0;
        int j = points.size() - 1;
        for (int i = 0; i < points.size(); i++) {
            area += (points.get(j).getX() + points.get(i).getX()) * (points.get(j).getY() - points.get(i).getY());
            j = i;
        }
        return 0.5 * Math.abs(area);
        
    }
}
