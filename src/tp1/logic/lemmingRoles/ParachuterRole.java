package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {
	private static final String NAME = Messages.PARACHUTER_ROLE_NAME;
	
    @Override
    public void start(Lemming lemming) {
        // No hay lógica inicial específica para el paracaídas.
    }

    @Override
    public void play(Lemming lemming) {
    	lemming.reestablecerCaida();
    	if (lemming.isInAir()) {
            lemming.caer(); // Cae suavemente con el paracaídas.
            if (!lemming.isInAir()) {
                lemming.disableRol(); // Al aterrizar, vuelve a ser Walker.
            }
        } else {
            lemming.disableRol(); // Si está en el suelo, vuelve a ser Walker.
        }
    }
    
    @Override
	 public boolean canParse(String input) {
		 return input.equalsIgnoreCase("Parachuter") || input.equalsIgnoreCase("P");
	 }
    
    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_PARACHUTE;
    }
    
    @Override
	public String getName() {
		return NAME;
	}
    
    @Override
    public String helpText() {
    	return "		" + Messages.PARACHUTER_ROLE_HELP + System.lineSeparator();
    }
}