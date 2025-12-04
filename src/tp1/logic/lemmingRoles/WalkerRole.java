package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.GameItem;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole {
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static String LEFT_ICON = Messages.LEMMING_LEFT;
	private static String RIGHT_ICON = Messages.LEMMING_RIGHT;
	
	public void start(Lemming lemming) {
		
	}
	
	public void play(Lemming lemming) {;	
		lemming.walkOrFall();
	}

	public String getHelp() {
		return HELP;
	}
	   
	public String helpText() {
		return "		" + Messages.WALKER_ROL_HELP + System.lineSeparator();
	}

	@Override
	public String getIcon(Lemming lemming) {
		String resultado = null;
		 Direction dir = lemming.getDirection(); //depende del sentido del movimiento se devuelve un icon u otro
		if(dir == Direction.RIGHT) {
			resultado = RIGHT_ICON;
		}
		else if(dir == Direction.LEFT) {
			resultado = LEFT_ICON;
		}
		return resultado;
	}
	
	@Override
	 public boolean canParse(String input) {
		 return input.equalsIgnoreCase("Walker") || input.equalsIgnoreCase("W");
	 }
	
	@Override
	public String getName() {
		return NAME;
	}

}
