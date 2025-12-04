package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject{
	public MetalWall(GameWorld game, Position pos) {
        super(game, pos);
    }

    public MetalWall() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "(" + pos.getCol() + "," + pos.getRow() + ")" + " MetalWall";
    }
    
    @Override
    public boolean isSolid() {
        return true; // Una pared siempre es sólida
    }

    @Override
    public boolean isExit() {
        return false; // No es una salida
    }

    @Override
    public String getIcon() {
        return Messages.METALWALL; // Representación visual de una pared
    }
    
    @Override
    public void update() {
    	// Wall no realiza ninguna acción en su actualización
    }
    
    @Override
    public void dead() {
    	
    }

	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		String name = getObjectNameFrom(line);
		if (!name.equalsIgnoreCase("M") && !name.equalsIgnoreCase("MetalWall")) {
			return null;
		}
		
		if (words.length != 2) {
		       throw new ObjectParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		Position pos;
	    try {
	        pos = getPositionFrom(line, game); // Asumiendo que existe un método parsePosition en Position
	    } catch (IllegalArgumentException e) {
	        throw new ObjectParseException("Invalid position format: " + words[0], e);
	    }

	    game.checkPositionInBounds(pos);
	    
		return new MetalWall(game, pos);
	}
}