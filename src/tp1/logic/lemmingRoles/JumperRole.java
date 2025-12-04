package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.Position;
import tp1.view.Messages;

public class JumperRole extends AbstractRole {

    private int contador;

    @Override
    public void start(Lemming lemming) {
        contador = 0;
    }

    @Override
    public void play(Lemming lemming) {
        contador++;
        if (contador % 3 == 0 && !lemming.isInAir()) {
        	lemming.bounce();
        	lemming.reestablecerCaida();
        } 
        else {
        	lemming.reestablecerCaida();
        	lemming.walkOrFall();
        }

    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_JUMPER;
    }

    @Override
    public String getName() {
        return "Jumper";
    }

    @Override
    public String helpText() {
        return "\t[J]umper: salta cada 3 ciclos\n";
    }

    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Jumper") ||
               input.equalsIgnoreCase("JMP");
    }
}
