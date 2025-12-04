package tp1.logic.gameobjects;

import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class Pincho extends GameObject {

    public Pincho(GameWorld game, Position pos) {
        super(game, pos);
    }

    public Pincho() { /* para la factoría */ }

    @Override
    public String toString() {
        return "(" + pos.getCol() + "," + pos.getRow() + ") Spike";
    }

    @Override
    public boolean isSolid() {
        return true;    // bloquea el movimiento (el Lemming “se posa” sobre él)
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getIcon() {
        return Messages.SPIKE;
    }

    @Override
    public void update() {
        // No hace nada cada ciclo
    }

    @Override
    public void dead() {
        // Nunca “muere” el pincho
    }

    @Override
    public boolean receiveInteraction(GameItem other) {
        if (other instanceof Lemming) {
            Lemming lem = (Lemming) other;
            // Sólo mata si el Lemming está justo encima:
            Position encima = this.pos.retrocedeFila(); 
            if (lem.isInPosition(encima)) {
                // Marcamos muerte y actualizamos contadores
                lem.isAlive = false;
                game.incrementLemmingsDead();
                game.reduceLemmingsAlive();
                return true;
            }
        }
        return false;
    }

    /** Para que la factoría lo reconozca en el fichero de nivel */
    @Override
    public GameObject parse(String line, GameWorld game)
            throws tp1.exceptions.ObjectParseException, tp1.exceptions.OffBoardException {
        String[] w = line.trim().split("\\s+");
        String name = getObjectNameFrom(line);
        if (!name.equalsIgnoreCase("S") && !name.equalsIgnoreCase("Spike"))
            return null;
        if (w.length != 2)
            throw new tp1.exceptions.ObjectParseException("Incorrect parameter count for Spike");
        Position p = getPositionFrom(line, game);
        game.checkPositionInBounds(p);
        return new Pincho(game, p);
    }

}
