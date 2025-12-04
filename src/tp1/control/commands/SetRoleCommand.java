package tp1.control.commands;

import tp1.exceptions.OffBoardException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.RoleParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public class SetRoleCommand extends NoParamsCommand {
	private static final String NAME = "setRole"; // Nombre del comando
    private static final String SHORTCUT = "sr"; // Atajo del comando
    private static final String DETAILS = "[s]et[R]ole <ROLE> <ROW> <COL>"; // Detalles
    private static final String HELP = "Sets the lemming in position (ROW,COL) to role ROLE."; // Ayuda
    
    private LemmingRole roleName;
    private Position pos; // Nombre del rol a aplicar    
    
    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }
    
    public SetRoleCommand(LemmingRole roleName, Position pos) {
    	super(NAME, SHORTCUT, DETAILS, HELP);
    	this.roleName = roleName;
    	this.pos = pos;
    }
    
    public String helpText() {
    	return super.helpText() + LemmingRoleFactory.roleHelp();
    }
    
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
    	if(matchCommand(commandWords[0]))  {
    		
    		if(commandWords.length == 4) {
    			
				try {
					roleName = LemmingRoleFactory.parse(commandWords[1]); //se parsea el rol
					
					try {
						int row = commandWords[2].charAt(0) - 'A'; //transforma los indices char a un número
						int col = Integer.parseInt(commandWords[3]) - 1; //se resta 1 para ajustar con los indices
						
			        	Position posicion = new Position(col, row);
						return new SetRoleCommand(roleName, posicion); //se va al constructor con parámetros
						
					} catch (NumberFormatException nfe){
	                    throw new CommandParseException(Messages.ERROR_COMMAND_EXECUTE, nfe);
	                }
					
		        }
				
				catch (RoleParseException rpe) {
		     		throw new CommandParseException(Messages.ERROR_COMMAND_EXECUTE, rpe);
				}
				
			}
    		throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

    	}
        return null;
    }
    
    @Override
    public void execute(GameModel game, GameView gameview) throws CommandExecuteException{
    	 try {

    	     boolean result = game.asignarRoleAlLemming(roleName, this.pos); // Intentar asignar el rol
    	     if(!result) {
     	        throw new CommandExecuteException(Messages.NO_LEMMING_IN_POS);
    	     }
    	     
    	     // Si todo va bien, mostrar mensaje de éxito
    	     gameview.showMessage(Messages.ROLE_ASIGNED.formatted(roleName.getName(), pos.toString()));
    	    
    	 	} catch (OffBoardException obe) {
    	    	throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, obe);
    	    } 
    }
}
