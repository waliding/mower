package com.wha.mower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandInterpreter provide interpretation for the string command sequence
 */
public class CommandInterpreter {

    private final static Pattern headerPattern =
                                  Pattern.compile("^((\\d+) (\\d+))");
    private final static Pattern positionPattern =
                                  Pattern.compile("^((\\d+) (\\d+) ([NESW]))");
    private final static Pattern instructionPattern =
                                  Pattern.compile("^([DGA]+)");

    /**
     * Parse the whole input commands string
     * Important : It can return a
     *
     * @param instructions The string defining area dims, mowers and moves
     * @throws IOException if instruction has IO problem
     * @return Area The grass area mapped with correctly positioned mowers
     *         exit(-1) if IllegalArgumentException catched
     */
    public static Area toArea(String instructions) throws IOException,
                                                      IllegalArgumentException {
        int lineNum = 1;
        String line;
        Area area;
        Mower mower = new Mower();

        //Preparing the reader
        BufferedReader reader =
                new BufferedReader(new StringReader(instructions));

        //Reading first line
        String header = reader.readLine();

        //Initalizing the Area
        area = parseHeader(header);

            // Reading '\n' delimited string of command parameters
        while ((line = reader.readLine()) != null) {
              if (lineNum % 2 != 0) {
                  //position line case(Ex: 4 4 N)
                  mower = parsePositionLine(line);

              } else {
                  //instruction line case (Ex: GAGAGADA)
                  mower.move(parseInstructionLine(line), area);
                  area.addMower(mower);
              }

              lineNum++;
        }

        //Printing result
        System.out.println(area);

        return area;
    }

    /**
     * Parse the header string and map it to an Area object
     *
     * @param header The header string defining area dimenions(Ex: 5 5)
     * @throws IllegalArgumentException if header is badly formed or null
     * @return Area The grass area object
     */
    public static Area parseHeader(String header) {
        if (header != null) {
            Matcher matcher = headerPattern.matcher(header);

            if(matcher.find()){
                //Init the Area with read dimensions
                return new Area(Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)));
            }
        }

        throw new IllegalArgumentException("Argument : header is " +
                "not correctly formed");
    }

    /**
     * Parse the position string and map it to a Mower object
     *
     * @param position The initial position of the mower(Ex: 3 3 N)
     * @throws IllegalArgumentException if position is badly formed or null
     * @return Mower The mower object with the specified position
     */
    public static Mower parsePositionLine(String position) {
        if(position != null) {
            Matcher matcher = positionPattern.matcher(position);

            if(matcher.find()){
                return new Mower(Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Mower.ORIENTATIONS.get(matcher.group(4)));
            }
        }

        throw new IllegalArgumentException("Argument : position is " +
                                                "not correctly formed");
    }

    /**
     * Parse the command sequence string(do a simple Regex check)
     *
     * @param instruction The moves sequence of the mower(Ex: GAGADADAA)
     * @throws IllegalArgumentException if instruction is badly formed or null
     * @return String The sequence string
     */
    public static String parseInstructionLine(String instruction) {
        if(instruction != null) {
            Matcher matcher = instructionPattern.matcher(instruction);

            if(matcher.matches()){
                return instruction;
            }
        }

        throw new IllegalArgumentException("Argument : instruction is " +
                                                "not correctly formed");
    }
}