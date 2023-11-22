/**
 * Represents a driver with a name and associated route area.
 * Implements the Comparable interface for sorting purposes based on different criteria.
 */
public class Driver implements Comparable<Driver> {
    // The driver's name
    private String name;
    // The area of the driver's route
    private double area;
    // Variable to determine the comparison criteria: 1 for area, 2 for name, etc.
    private int comparisonVariable;

    /**
     * Constructs a Driver with the specified name.
     * @param name The driver's name.
     */
    public Driver(String name) {
        this.name = name;
    }

    /**
     * Returns the driver's name.
     * @return The name of the driver.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the driver's name.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the area of the driver's route.
     * @return The route area.
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets the area of the driver's route.
     * @param area The area to set.
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Returns the current comparison criteria. 
     * @return 1 for area comparison, 2 for name comparison, etc.
     */
    public int getComparisonVariable() {
        return comparisonVariable;
    }

    /**
     * Sets the comparison criteria for this driver.
     * @param comparisonVariable 1 for area, 2 for name, etc.
     */
    public void setComparisonVariable(int comparisonVariable) {
        this.comparisonVariable = comparisonVariable;
    }

    /**
     * Compares this driver to another driver based on the set comparison criteria.
     * @param other The other driver to compare to.
     * @return A negative integer, zero, or a positive integer as this driver 
     * is less than, equal to, or greater than the other driver.
     */
    @Override
    public int compareTo(Driver other) {
        if (comparisonVariable == 1) {
            return Double.compare(this.area, other.area);
        } else if (comparisonVariable == 2) {
            return this.name.compareTo(other.name);
        }
        // Default to comparing by name if no valid comparisonVariable is set
        return this.name.compareTo(other.name);
    }

    /**
     * Returns a string representation of the driver.
     * @return The driver's name followed by a tab and their route area (rounded to 2 decimal places).
     */
    @Override
    public String toString() {
        return name + "\t" + String.format("%.2f", area);
    }
}
