package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameItem;

public class Wall extends GameObject{
	public Wall(GameWorld game, Position pos) {
        super(game, pos);
    }

    public Wall() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
    	return "(" + pos.getCol() + "," + pos.getRow() + ")" + " Wall";
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
        return Messages.WALL; // Representación visual de una pared
    }
    
    @Override
    public void update() {
    	// Wall no realiza ninguna acción en su actualización
    }
    
    @Override
    public void dead() {
 
    }
    
    @Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}

    public void matarWall() {
    	isAlive = false;
    }

	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		String name = getObjectNameFrom(line);
		if (!name.equalsIgnoreCase("W") && !name.equalsIgnoreCase("Wall")) {
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
	    
		return new Wall(game, pos);
	}
}
