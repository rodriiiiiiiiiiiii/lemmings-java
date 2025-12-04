package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.Position;
import tp1.view.Messages;

public class BomberRole extends AbstractRole {

    private int contador;
    private static final int TIMER = 3;

    @Override
    public void start(Lemming lemming) {
        contador = 0;
    }

    @Override
    public void play(Lemming lemming) {
        contador++;
        if (contador < TIMER) 
            return;   // aún no explota

        // 1) destruye muros en las 8 casillas alrededor
        Position pos = lemming.getPosition();
        for (int dc = -1; dc <= 1; dc++) {
            for (int dr = -1; dr <= 1; dr++) {
                if (dc == 0 && dr == 0) continue;
                Position target = new Position(
                    pos.getCol() + dc,
                    pos.getRow() + dr
                );
                try {
                    lemming.getGame().destroyWallAt(target);
                } catch (Exception e) {
                    // fuera de bordes o sin muro: lo ignoramos
                }
            }
        }

        // 2) el bomber se suicida
        lemming.die();
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_BOMBER; // define "💣" en Messages
    }

    @Override
    public String getName() {
        return "Bomber";
    }

    @Override
    public String helpText() {
        return "\t[B]omber: explota tras 3 ciclos\n";
    }

    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Bomber") ||
               input.equalsIgnoreCase("BMB");
    }
}
