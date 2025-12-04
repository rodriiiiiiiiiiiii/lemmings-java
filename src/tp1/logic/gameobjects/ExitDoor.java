package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
	public ExitDoor(GameWorld game, Position position) {
	    super(game, position); // Si la clase padre maneja la posición
	    this.game = game;
	}

    public ExitDoor() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "(" + pos.getCol() + "," + pos.getRow() + ")" + " ExitDoor";    
	}
    
    @Override
    public boolean isSolid() {
        return false; // Una pared siempre es sólida
    }

    @Override
    public boolean isExit() {
        return true; // No es una salida
    }

    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR; // Representación visual de una pared
    }
    
    @Override
    public void update() {
    	// ExitDoor no realiza ninguna acción en su actualización
    }
    
    @Override
    public void dead() {
    	
    }

	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		String name = getObjectNameFrom(line);
		if (!name.equalsIgnoreCase("E") && !name.equalsIgnoreCase("ExitDoor")) {
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
	    
		return new ExitDoor(game, pos);
	}

}