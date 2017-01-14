package au.com.medibank.factory;

import au.com.medibank.exception.FitbitsException;
import au.com.medibank.instruction.Instruction;
import au.com.medibank.instruction.Move;
import au.com.medibank.instruction.TurnLeft;
import au.com.medibank.instruction.TurnRight;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 */
public class InstructionFactoryTest {
    @Test
    public void testCreateInstructionsFromLine() {
        String instructionLineRaw = " MLRMRL"; // Move, Left, Right, Move, Right, Left
        List<Instruction> instructions = InstructionFactory.createInstructions(instructionLineRaw);
        assertNotNull(instructions);
        assertEquals(6, instructions.size());
        int i = 0;
        assertTrue(instructions.get(i++) instanceof Move);
        assertTrue(instructions.get(i++) instanceof TurnLeft);
        assertTrue(instructions.get(i++) instanceof TurnRight);
        assertTrue(instructions.get(i++) instanceof Move);
        assertTrue(instructions.get(i++) instanceof TurnRight);
        assertTrue(instructions.get(i++) instanceof TurnLeft);
    }

    @Test(expected = FitbitsException.class)
    public void testCreateInstructionsFailsWhenLineNull() {
        String instructionLineRaw = null;
        InstructionFactory.createInstructions(instructionLineRaw);
    }

    @Test(expected = FitbitsException.class)
    public void testCreateInstructionsFailsWhenLineEmpty() {
        String instructionLineRaw = "";
        List<Instruction> instructions = InstructionFactory.createInstructions(instructionLineRaw);
    }

    @Test(expected = FitbitsException.class)
    public void testCreateInstructionsFailsWhenInvalidCharacter() {
        String instructionLineRaw = "MXRL"; //That X won't work
        InstructionFactory.createInstructions(instructionLineRaw);
    }
}
