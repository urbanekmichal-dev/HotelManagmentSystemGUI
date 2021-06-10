package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

/**
 *Model class used for storing data of range.
 * @author Michał Urbanek
 * @version 1.0
 */
public class RangeOfValues {

    /**
     * Min value
     */
    private int min;
    /**
     * Max value
     */
    private int max;

    /**
     * constructor
     *
     * @param min minimum value of range
     * @param max maximum value of range
     */
    public RangeOfValues(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the value of the private field "min"
     *
     * @return integer containing the value of the private field min
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the value of the private field "max"
     *
     * @return integer containing the value of the private field max
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the value of the private field "min"
     *
     * @param min minimum value of range
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Set the value of the private field "max"
     *
     * @param max maximum value of range
     */
    public void setMax(int max) {
        this.max = max;
    }

}
