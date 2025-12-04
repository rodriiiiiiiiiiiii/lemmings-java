package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameItem;
import tp1.logic.GameStatus;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Volcan extends GameObject{
	public Volcan(GameWorld game, Position pos) {
        super(game, pos);
    }

    public Volcan() { /* para la factoría */ }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + pos.getCol() + "," + pos.getRow() + ") Volcan";
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isExit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return Messages.VOLCAN;
	}

	@Override
	public void dead() {
		// TODO Auto-generated method stub
		
	}
	
	 @Override
	    public GameObject parse(String line, GameWorld game)
	            throws ObjectParseException, OffBoardException {
	        String[] w = line.trim().split("\\s+");
	        String name = getObjectNameFrom(line);
	        if (!name.equalsIgnoreCase("Volcano")) return null;
	        if (w.length != 2)
	            throw new ObjectParseException("Incorrect parameters for Volcano");
	        Position p = getPositionFrom(line, game);
	        game.checkPositionInBounds(p);
	        return new Volcan(game, p);
	    }

	    // Aquí matamos al lemming sólo si:
	    //  - es un Lemming
	    //  - está justo encima de nosotros
	    //  - el número de ciclo es múltiplo de 3
	 @Override
	 public boolean receiveInteraction(GameItem other) {
	     if (!(other instanceof Lemming)) 
	         return false;

	     Lemming lem = (Lemming) other;
	     // posición “encima” del volcán
	     Position encima = this.pos.retrocedeFila();
	     if (!lem.isInPosition(encima)) 
	         return false;

	     // ¡ojo! cast a GameStatus para poder llamar a getCycle()
	     int cicloActual = ((GameStatus) game).getCycle();
	     if (cicloActual % 3 == 0) {
	         // mata sólo a este lemming
	         lem.isAlive = false;
	         game.incrementLemmingsDead();
	         game.reduceLemmingsAlive();
	         return true;
	     }
	     return false;
	 }


}
