package com.wha.mower;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * The Position class test
 */
public class AreaTest {

    Area area = new Area();

    @Test
    public void setDim_height_width_1_3() {
        area.setDim(1, 3);

        assertEquals(area.getHeight(), 1);
        assertEquals(area.getWidth(), 3);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void setDim_height_width_neg_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("positive");

        area.setDim(-1, -3);
    }

    @Test
    public void addMower_0_0_N() {
        Mower m = new Mower();
        area.addMower(m);

        assertEquals(m, area.getMowers().get(0));
    }

    @Test
    public void addMower_null() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("null");

        area.addMower(null);
    }
}
