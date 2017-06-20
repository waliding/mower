package com.wha.mower;

import java.awt.Point;

/**
 * The Position : (x, y) class
 */
public class Position extends Point {

    /**
     * Constructor
     */
    public Position() {
        super();
    }

    /**
     * Constructor
     *
     * @param x The x abscissa
     * @param y The y ordinate
     */
    public Position(int x, int y) {
        super(x, y);
    }

    /**
     * Add a Position coordinates
     *
     * @param p The Position object to add
     */
    public void addPosition(Position p) {
        this.x += p.getX();
        this.y += p.getY();
    }
}