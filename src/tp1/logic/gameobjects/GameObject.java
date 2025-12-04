package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Direction;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public abstract class GameObject implements GameItem {
	protected Position pos;
	protected boolean isAlive;
	protected boolean enteredDoor = false;
	protected GameWorld game;
	
    public GameObject() {
    	
    }
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {	
		return this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean enteredDoor() {
		return enteredDoor;
	}
	
	public boolean sePuedeExcavar() {
		return true;
	}
	
	public boolean sePuedeAsignar(LemmingRole role) {
		return false;
	}
	
	public abstract String toString();
	
	public abstract boolean isSolid();
	
	public abstract boolean isExit();
	
	public abstract void update();
	
	public abstract String getIcon();
	
	public abstract void dead();
	
	@Override
    public boolean receiveInteraction(GameItem other) {
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        return false;
    }

    @Override
    public boolean interactWith(MetalWall wall) {
    	return false;
    }

    public abstract GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException;
    
    protected static Position getPositionFrom(String line, GameWorld game) throws ObjectParseException, OffBoardException {
        try {
            String[] parts = line.split("\\s+");
            String positionPart = parts[0];
            String[] coords = positionPart.substring(1, positionPart.length() - 1).split(",");
            int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            Position pos = new Position(row, col);
            game.checkPositionInBounds(pos); // Valida si está dentro del tablero
            return pos;
        } catch (Exception e) {
            throw new ObjectParseException("Invalid position format: " + line, e);
        }
    }

    protected static String getObjectNameFrom(String line) throws ObjectParseException {
        try {
            String[] parts = line.split("\\s+");
            return parts[1].trim();
        } catch (Exception e) {
            throw new ObjectParseException("Object type not found in line: " + line, e);
        }
    }

    protected static Direction getLemmingDirectionFrom(String line) throws ObjectParseException {
        try {
            String[] parts = line.split("\\s+");
            Direction d = Direction.valueOf(parts[2].toUpperCase());
            return d;
        } catch (Exception e) {
            throw new ObjectParseException("Invalid direction for Lemming in line: " + line, e);
        }
    }

    protected static int getLemmingHeightFrom(String line) throws ObjectParseException {
        try {
            String[] parts = line.split("\\s+");
            return Integer.parseInt(parts[3]);
        } catch (Exception e) {
            throw new ObjectParseException("Invalid height for Lemming in line: " + line, e);
        }
    }

    protected static LemmingRole getLemmingRoleFrom(String line) throws ObjectParseException {
        try {
            String[] parts = line.split("\\s+");
            return LemmingRoleFactory.parse(parts[4].trim());
        } catch (Exception e) {
            throw new ObjectParseException("Invalid role for Lemming in line: " + line, e);
        }
    }
 // dentro de GameObject.java
    public void kill() {
        this.isAlive = false;
    }
    

}
