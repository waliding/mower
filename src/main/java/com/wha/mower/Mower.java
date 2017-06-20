package com.wha.mower;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * The mower class
 */
public class Mower {

    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
    static final int TOTAL_CARDINALS = 4;

    //To avoid if else conditions
    static final DualHashBidiMap<String, Integer> ORIENTATIONS =
            new DualHashBidiMap<String, Integer>();
    static {
        ORIENTATIONS.put("N", NORTH);
        ORIENTATIONS.put("E", EAST);
        ORIENTATIONS.put("S", SOUTH);
        ORIENTATIONS.put("W", WEST);
    }

    //To avoid if else conditions
    private static final Position[] ROTATIONS =
            {
                    new Position(0,1),
                    new Position(1,0),
                    new Position(0,-1),
                    new Position(-1,0),
                    new Position(0,1)
            };

    private Position position;
    private int orientation;

    /**
     * Constructor
     */
    public Mower() {
        position = new Position(0, 0);
        orientation = NORTH;
    }

    /**
     * Constructor
     *
     * @param x The x abscissa
     * @param y The y ordinate
     * @param orientation The int corresponding to the cardinal point
     * @throws IllegalArgumentException if orientation not in cardinal interval
     * @throws IllegalArgumentException if x, y  not positive
     */
    public Mower(int x, int y, int orientation) {
        //Checking the orientation interval
        checkOrientation(orientation);

        //Checking positivity
        checkPositive(x, y);

        position = new Position(x, y);
        this.orientation = orientation;
    }

    /**
     * Checking if orientation is in [0, 3]
     *
     * @param orientation The integer representing one of the four directions
     * @throws IllegalArgumentException if orientation not in cardinal interval
     */
    private void checkOrientation(int orientation) {
        if(orientation < NORTH || orientation > WEST) {
            throw new IllegalArgumentException("orientation should be " +
                    "Mower.NORTH, Mower.EAST, Mower.SOUTH, Mower.WEST " +
                    "| given : " + orientation);
        }
    }

    /**
     * Checking if x, y are positive
     *
     * @param x The integer abscissa
     * @param y The integer ordinate
     * @throws IllegalArgumentException if x, y  not positive
     */
    private void checkPositive(int x, int y) {
        if(x < 0 && y < 0) {
            throw new IllegalArgumentException("arguments x, y " +
                                                          "should be positive");
        }
    }

    /**
     * Get the Position object
     *
     * @return The Position object containing x and y
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Move the mower in the grass area(inside ONLY)
     *
     * @param instruction The command sequence(Ex: DADAGAGAA)
     * @param area The grass area
     * @throws IllegalArgumentException if area or instruction is null
     */
    public void move(String instruction, Area area) {

        if(area != null && instruction != null) {
            Position nextPos = new Position();

            for(int i = 0; i < instruction.length(); i++) {
                switch(instruction.charAt(i)) {
                    case 'G' :
                        orientation = (orientation - 1 + TOTAL_CARDINALS)
                                % TOTAL_CARDINALS;
                        break;
                    case 'D' :
                        orientation = (orientation + 1 + TOTAL_CARDINALS)
                                % TOTAL_CARDINALS;
                        break;
                    case 'A' :
                        //Determining next move
                        Position moveVector = ROTATIONS[orientation];

                        //Next position should look like this
                        nextPos.setLocation((int)position.getX(),
                                (int)position.getY());
                        nextPos.addPosition(moveVector);

                        //Next position x and y
                        int posX = (int)nextPos.getX();
                        int posY = (int)nextPos.getY();

                        //Move inside area ONLY
                        if(posX >= 0 && posY >= 0
                                && posX <= area.getWidth()
                                && posY <= area.getHeight()) {

                            position.addPosition(moveVector);
                        }
                        break;
                    default:
                        //DO nothing if bad Char
                        //We could add a log here
                        break;
                }
            }
        } else {
            throw new IllegalArgumentException("area or instruction " +
                                                            "argument is null");
        }
    }

    /**
     * String representation of the Object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "Mower(" + (int)position.getX() +
                ", " + (int)position.getY() + ", "
                + ORIENTATIONS.getKey(orientation) + ")";
    }
}