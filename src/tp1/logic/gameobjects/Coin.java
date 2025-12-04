package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameItem;
import tp1.logic.GameStatus;
import tp1.logic.GameWorld;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.Position;
import tp1.view.Messages;

public class Coin extends GameObject {

    public Coin(GameWorld game, Position pos) {
        super(game, pos);
    }

    // para la factoría
    public Coin() { }

    @Override
    public String toString() {
        return "(" + pos.getCol() + "," + pos.getRow() + ") Coin";
    }

    @Override public boolean isSolid()   { return false; }
    @Override public boolean isExit()    { return false; }
    @Override public String getIcon()    { return Messages.COIN; }
    @Override public void update()       { /* no hace nada por ciclo */ }
    @Override public void dead()         { /* no tiene lógica propia */ }

    @Override
    public GameObject parse(String line, GameWorld game)
            throws ObjectParseException, OffBoardException {
        String[] w = line.trim().split("\\s+");
        String name = getObjectNameFrom(line);
        if (!name.equalsIgnoreCase("Coin") && !name.equalsIgnoreCase("Moneda"))
            return null;
        if (w.length != 2)
            throw new ObjectParseException("Incorrect parameters for Coin");
        Position p = getPositionFrom(line, game);
        game.checkPositionInBounds(p);
        return new Coin(game, p);
    }

    @Override
    public boolean receiveInteraction(GameItem other) {
        // Solo nos importa cuando es un Lemming y está en la misma casilla
        if (other instanceof Lemming) {
            Lemming lem = (Lemming) other;
            if (lem.isInPosition(this.pos)) {
                // 1) marcamos esta moneda como muerta para que el contenedor la borre
                this.isAlive = false;
                // 2) incrementamos el contador de monedas en el juego
                //    (hacemos cast a GameStatus o a Game, porque GameWorld no lo expone)
                ((GameStatus) game).incrementCoins();
                return true;
            }
        }
        return false;
    }
}
