package com.wha.mower;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * The Position class test
 */
public class PositionTest {

    @Test
    public void addPosition_x_y_5_6() {
        Position p1 = new Position(4, 5);
        Position p2 = new Position(1, 1);
        p1.addPosition(p2);

        assertEquals(p1, new Position(5, 6));
    }
}
