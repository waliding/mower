package com.wha.mower;

import java.util.ArrayList;

/**
 * The grass area class
 */
public class Area {

    private int height, width;
    private ArrayList<Mower> mowers = new ArrayList<Mower>();

    /**
     * Constructor
     */
    public Area() {
        this.height = 1;
        this.width = 1;
    }

    /**
     * Constructor
     *
     * @param height The height (should be positive)
     * @param width The width (should be positive)
     */
    public Area(int height, int width) {
        checkPositivity(height, width);
        this.height = height;
        this.width = width;
    }

    /**
     * Get height of the grass area
     *
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get width of the grass area
     *
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the mowers list
     *
     * @return The ArrayList of mowers
     */
    public ArrayList<Mower> getMowers() {
        return mowers;
    }

    /**
     * Set height and width of the grass area
     *
     * @param height The height (should be positive)
     * @param width The width (should be positive)
     * @throws IllegalArgumentException if height, width  not positive
     */
    public void setDim(int height, int width) {
        checkPositivity(height, width);
        this.height = height;
        this.width = width;

    }

    /**
     * Check height and width positive
     *
     * @param height The height (should be positive)
     * @param width The width (should be positive)
     * @throws IllegalArgumentException if height, width  not positive
     */
    public void checkPositivity(int height, int width) {
        if(height < 0 && width < 0)
            throw new IllegalArgumentException("argument height, width " +
                    "should be positive");
    }

    /**
     * Adding a Mower to the grass area
     *
     * @param mower The mower element to be added
     * @throws IllegalArgumentException if mower is null
     */
    public void addMower(Mower mower) {
        if(mower != null) {
            this.mowers.add(mower);
        } else {
            throw new IllegalArgumentException("mower argument is null," +
                                                             "cannot be added");
        }
    }

    /**
     * String representation of the Object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "Area :\n" +
                "height = " + height + "\n" +
                "width  = " + width  + "\n" +
                "Mowers = " + mowers;
    }
}