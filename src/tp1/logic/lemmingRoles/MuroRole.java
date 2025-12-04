package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;

/**
 * Rol “Muro”: el lemming se queda fijo y los otros
 * lémings que intenten pasar por encima cambian de dirección.
 */
public class MuroRole extends AbstractRole {

    private static final String NAME     = "Muro";
    private static final String SHORTCUT = "MR";
    private static final String HELP     = "[M]uro: actúa como pared";

    @Override
    public void start(Lemming lemming) {
        // Nada especial al asignar
    }

    @Override
    public void play(Lemming lemming) {
        // El muro-no camina ni cae
    }

    @Override
    public String getIcon(Lemming lemming) {
        return "#";  // o cualquier símbolo que uses para muro-lléming
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String helpText() {
        return "    " + HELP + System.lineSeparator();
    }

    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase(NAME) || input.equalsIgnoreCase(SHORTCUT);
    }

    /**
     * Cuando otro Lemming interacciona con este “muro”,
     * invertimos su dirección.
     * @param other  el lemming que choca
     * @param self   el lemming con MuroRole
     */
    
    
    @Override
    public boolean receiveInteraction(GameItem other, Lemming self) {
        if (!(other instanceof Lemming)) return false;
        Lemming mover = (Lemming) other;

        // en lugar de self.pos, usamos self.getPosition()
        Position muroPos = self.getPosition();

        // si el otro léming "entra" en la casilla del muro...
        if (mover.isInPosition(muroPos)) {
            // invertimos su dirección
            Direction dir = mover.getDirection();
            mover.setDirection(
                dir == Direction.LEFT  ? Direction.RIGHT :
                dir == Direction.RIGHT ? Direction.LEFT  :
                dir
            );
            return true;
        }
        return false;
    }

}
