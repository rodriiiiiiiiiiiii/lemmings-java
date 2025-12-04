package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	private static final String NAME = Messages.DOWNCAVER_ROLE_NAME;
    private static final String SHORTCUT = Messages.DOWNCAVER_ROLE_SHORTCUT;
    private static final String DETAILS = Messages.DOWNCAVER_ROLE_DETAILS;
    private static final String HELP = Messages.DOWNCAVER_ROLE_HELP;
	private boolean hasCaved;	

	public DownCaverRole() {//constructor sin parámetros
		this(false);
	}
	
	private DownCaverRole(boolean hasCaved) {//constructor con parámetros
		this.hasCaved = hasCaved;
	}
	
	public void start(Lemming lemming) {
		if(lemming.isInAir()) { //si el rol se le asigna a un lemming en aire se le quita automáticamente
			lemming.disableRol();
		}
	}

	public void play(Lemming lemming) {
		if(hasCaved) { //si ha excavado una wall, no metalWall 
			lemming.excavar();
			//no se reestablece las casillas de caída a 0 porque si debajo de una pared hay dos huecos vacíos
			//y otra pared, son 3 casillas de caída y estaríamos diciendo erróneamente que el lemming está vivo.
			hasCaved = false;
		}
		else {
			lemming.disableRol();
	    	lemming.update(); //se mueve como un lemming tipo walker en ese turno
		}
	}
	
	public boolean interactWith(Wall wall, Lemming lemming) {
		boolean interacts = lemming.estaEncima(wall); //si hay wall justo debajo, la excava
		if(interacts) {
			hasCaved = true;
			wall.matarWall();// isAlive = false solo para esa wall
		}
		return interacts;
	}
	
	@Override
	 public boolean canParse(String input) {
		 return input.equalsIgnoreCase("DownCaver") || input.equalsIgnoreCase("DC");
	 }
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public String getIcon(Lemming lemming) {
        return Messages.LEMMING_DOWN_CAVER;
	}

	@Override
	public String helpText() {
		return "		" + Messages.DOWNCAVER_ROLE_HELP + System.lineSeparator();
	}

}