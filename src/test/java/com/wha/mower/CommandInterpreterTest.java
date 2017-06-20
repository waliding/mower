package com.wha.mower;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

/**
 * The Position class test
 */
public class CommandInterpreterTest {

    @Test
    public void toArea_interview_dataset() throws IOException,
                                                       IllegalArgumentException{

        //String representing the input File
        String s =  "5 5\n"       +
                    "1 2 N\n"     +
                    "GAGAGAGAA\n" +
                    "3 3 E\n"     +
                    "AADAADADDA";

        Area area = CommandInterpreter.toArea(s);

        Mower m1 = area.getMowers().get(0);
        Mower m2 = area.getMowers().get(1);


        assertEquals(5, area.getHeight());
        assertEquals(5, area.getWidth());
        assertEquals("Mower(1, 3, N)", m1.toString());
        assertEquals("Mower(5, 1, E)", m2.toString());
    }

    @Test
    public void parseHeader_height_width_1_5() {
        String s = "1 5\n";
        Area area = CommandInterpreter.parseHeader(s);

        assertEquals(area.getHeight(), 1);
        assertEquals(area.getWidth(), 5);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseHeader_height_width_exception() {
        String s = "1 \n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parseHeader(s);
    }

    @Test
    public void parseHeader_null_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parseHeader(null);
    }

    @Test
    public void parseHeader_height_width_neg_exception() {
        String s = "1 -1\n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parseHeader(s);
    }

    @Test
    public void parsePositionLine_x_y_direction_1_4_N() {
        String s = "1 4 N\n";
        Mower mower = CommandInterpreter.parsePositionLine(s);

        assertEquals(mower.toString(), "Mower(1, 4, N)");
    }

    @Test
    public void parsePositionLine_x_y_direction_wrong_orientation_exception() {
        String s = "1 A\n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parsePositionLine(s);
    }

    @Test
    public void parsePositionLine_direction_wrong_orientation_exception() {
        String s = "1 4 A\n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parsePositionLine(s);
    }

    @Test
    public void parsePositionLine_x_y_direction_neg_orientation_exception() {
        String s = "1 -1 N\n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parsePositionLine(s);
    }

    @Test
    public void parsePositionLine_null_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parsePositionLine(null);
    }

    @Test
    public void parseInstructionLine_GADA() {
        String s = "GADA";
        assertEquals(CommandInterpreter.parseInstructionLine(s), "GADA");
    }

    @Test
    public void parseInstructionLine_bad_sequence_exception() {
        String s = "GADA\n";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parseInstructionLine(s);
    }

    @Test
    public void parseInstructionLine_null_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("not correctly formed");

        CommandInterpreter.parseInstructionLine(null);
    }
}
