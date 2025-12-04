package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameModelException;

public class SaveCommand extends NoParamsCommand {
	private static final String NAME = "Save"; // Nombre del comando
    private static final String SHORTCUT = "s"; // Atajo del comando
    private static final String DETAILS = "[s]ave <filename>"; // Detalles
    private static final String HELP = "Save the game configuration on to a text file <fileName>"; // Ayuda
    private String fileName;

    // Constructor
    public SaveCommand() {
    	super(NAME, SHORTCUT, DETAILS, HELP);
    }
    
    public SaveCommand(String filename) {
    	super(NAME, SHORTCUT, DETAILS, HELP);
    	this.fileName = filename;
    }

    // Método parse para procesar los comandos
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        if(!matchCommand(commandWords[0]) || commandWords.length != 2) {
        	return null;
        }
    	
    	if (matchCommand(commandWords[0]) && commandWords.length == 2) {
    		String file = commandWords[1];
            return new SaveCommand(file);
        }	
    	throw new CommandParseException("Incorrect number of parameters for save command.");
    }


	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
            // Intentar cargar el fichero de configuración
            game.save(fileName);
        } catch (GameModelException gme) {
            // En caso de error en la carga, mostrar el mensaje de error
            throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, gme);
        }		
	}
}