package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.GameWorld;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.lemmingRoles.AbstractRole;
import tp1.view.Messages;
import tp1.logic.Position;

public class ClimberRole extends AbstractRole {

    @Override
    public void start(Lemming lemming) {
        // Nada especial al asignar rol
    }

    @Override
    public void play(Lemming lemming) {
        // 1) Calculamos la casilla justo delante (según dirección)
        Position pos   = lemming.getPosition();
        Direction dir  = lemming.getDirection();
        Position frente = dir == Direction.RIGHT
            ? pos.avanzaCol()
            : pos.retrocedeCol();

        // 2) Si hay un muro ahí...
        GameWorld world = (GameWorld) lemming.getGame();
        if ( world.isInWall(frente) ) {
            // 3) Intentamos subir: una fila arriba de ese muro
            Position arriba = frente.retrocedeFila();
            // Sólo subimos si no hay otro muro encima
            if (!world.isInWall(arriba) && !world.isInAir(arriba)) {
                lemming.setPosition(arriba);
            }
            else {
                // si no cabe, invertimos dirección
                lemming.setDirection(
                    dir == Direction.RIGHT 
                      ? Direction.LEFT 
                      : Direction.RIGHT
                );
            }
        }
        else {
            // si no hay muro delante, comportamiento normal
            lemming.walkOrFall();
        }
    }

    @Override
    public String getName() {
        return "Climber";
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_CLIMBER;  // ya definido en Messages, p.ej. 🧗
    }

    @Override
    public String helpText() {
        return "\t[CR] Climber: escala muros si puede, si no invierte dirección\n";
    }

    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Climber") 
            || input.equalsIgnoreCase("CR");
    }
}
