package com.wha.mower;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class MowerTest {

    Area area = new Area(5, 5);

    @Test
    public void mower_x_y_orientation_1_2_EAST() {
        Mower m = new Mower(1, 2, Mower.EAST);

        assertEquals("Mower(1, 2, E)", m.toString());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void mower_x_y_orientation_neg() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("should be positive");

        new Mower(-1, -2, Mower.EAST);
    }

    @Test
    public void mower_x_y_1_2_orientation_bad() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("orientation should be");

        new Mower(1, 2, 5);
    }

    @Test
    public void move_x_y_orientation_1_3_NORTH() {
        String s = "DAGGAGAGAGAGAD";
        Mower m = new Mower(1, 3, Mower.NORTH);
        m.move(s, area);

        assertEquals("Mower(1, 3, N)", m.toString());
    }

    @Test
    public void move_x_y_orientation_1_2_NORTH_bad_char_K() {
        String s = "KDAGGAGAGAGAGAD";
        Mower m = new Mower(1, 2, Mower.NORTH);
        m.move(s, area);

        assertEquals("Mower(1, 2, N)", m.toString());
    }

    @Test
    public void move_x_y_orientation_1_2_NORTH_area_null_exception() {
        String s = "DAGGAGAGAGAGA";
        Mower m = new Mower(1, 2, Mower.NORTH);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("null");

        m.move(s, null);
    }

    @Test
    public void move_x_y_orientation_1_2_NORTH_instruction_null_exception() {
        Mower m = new Mower(1, 2, Mower.NORTH);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("null");

        m.move(null, area);
    }
}
