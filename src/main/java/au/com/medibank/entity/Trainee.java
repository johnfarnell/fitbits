package au.com.medibank.entity;

import au.com.medibank.instruction.Instruction;

import java.util.List;

/**
 */
public class Trainee {

    private Position position;
    private Heading heading;

    private String description;

    private List<Instruction> instructions;

    public Trainee(Position position, Heading heading) {
        this.position = position;
        this.heading = heading;
    }

    public void executeInstructions(Pitch pitch) {
        instructions.forEach((Instruction instruction) -> {
            PositionHeading newPositionHeading = instruction.execute(pitch, this);
            //update the position and heading
            setPosition(newPositionHeading.getPosition());
            setHeading(newPositionHeading.getHeading());
        });
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public int getPositionX() {
        return getPosition().getX();
    }

    public int getPositionY() {
        return getPosition().getY();
    }

    public Position getPosition() {
        return position;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Trainee " + description + System.getProperty("line.separator"));
        sb.append("Heading " + heading + System.getProperty("line.separator"));
        sb.append("Position " + position);
        sb.append(System.getProperty("line.separator"));

        return sb.toString();

    }
}
