package au.com.medibank.factory;

import au.com.medibank.exception.FitbitsException;
import au.com.medibank.instruction.Instruction;
import au.com.medibank.instruction.Move;
import au.com.medibank.instruction.TurnLeft;
import au.com.medibank.instruction.TurnRight;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class InstructionFactory {

    private static final Instruction move = new Move();
    private static final Instruction turnLeft = new TurnLeft();
    private static final Instruction turnRight = new TurnRight();

    public static List<Instruction> createInstructions(String instructionLineRaw) {
        if ((instructionLineRaw == null) || (instructionLineRaw.length() == 0)) {
            throw new FitbitsException("Input Trainee line can not be an empty string or NULL");
        }

        List<Instruction> result = new ArrayList<Instruction>();
        String instructionLine = instructionLineRaw.replaceAll("\\s", "");

        for (int i = 0; i < instructionLine.length(); i++) {
            switch (instructionLine.charAt(i)) {
                case 'M':
                    result.add(move);
                    break;
                case 'L':
                    result.add(turnLeft);
                    break;
                case 'R':
                    result.add(turnRight);
                    break;
                default:
                    throw new FitbitsException("Invalid character " + instructionLine.charAt(i) + " supplied as an instruction");
            }
        }

        return result;

    }
}
