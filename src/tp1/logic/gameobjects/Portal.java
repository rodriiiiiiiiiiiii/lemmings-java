// -------------------------------------------------
//  tp1/logic/gameobjects/Portal.java
// -------------------------------------------------
package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;

public class Portal extends GameObject {

    private final Position destino;

    public Portal(GameWorld game, Position origen, Position destino) {
        super(game, origen);
        this.destino = destino;
    }
    /** Constructor vacío para la fábrica */
    public Portal() { super(null, null); destino = null; }

    @Override
    public String toString() {
        return "(" + pos.getCol() + "," + pos.getRow() + ") Portal→(" 
               + destino.getCol() + "," + destino.getRow() + ")";
    }

    @Override public boolean isSolid()   { return false; }
    @Override public boolean isExit()    { return false; }
    @Override public String getIcon()    { return "🌀"; /* o Messages.PORTAL */ }
    @Override public void update()       { }
    @Override public void dead()         { }

    @Override
    public GameObject parse(String line, GameWorld game)
            throws ObjectParseException, OffBoardException {
        String[] parts = line.trim().split("\\s+");
        // esperamos: "(x,y) Portal (x2,y2)"
        if (parts.length != 3) return null;
        if (!parts[1].equalsIgnoreCase("Portal")) return null;

        // parse origen (usa el helper de GameObject)
        Position origen = getPositionFrom(line, game);

        // parse destino a mano:
        Position dest = parsePositionToken(parts[2], game);

        return new Portal(game, origen, dest);
    }

    private Position parsePositionToken(String token, GameWorld game)
            throws ObjectParseException, OffBoardException {
        try {
            // token = "(col,row)"
            String inside = token.substring(1, token.length() - 1);
            String[] cs = inside.split(",");
            int col = Integer.parseInt(cs[0].trim());
            int row = Integer.parseInt(cs[1].trim());
            Position p = new Position(col, row);
            game.checkPositionInBounds(p);
            return p;
        } catch (Exception e) {
            throw new ObjectParseException("Invalid position: " + token, e);
        }
    }

    @Override
    public boolean receiveInteraction(GameItem other) {
        if (!(other instanceof Lemming)) return false;
        Lemming lem = (Lemming) other;
        // si el lemming está exactamente sobre el portal...
        if (lem.isInPosition(this.pos)) {
            // lo movemos al destino
            lem.setPosition(destino);
            return true;
        }
        return false;
    }
}

