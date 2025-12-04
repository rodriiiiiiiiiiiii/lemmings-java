package tp1.control.commands;

import java.util.Arrays;
import java.util.List;
import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			new UpdateCommand(),
	        new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new SetRoleCommand(),
			new LoadCommand(),
			new SaveCommand(),
			new AdvanceCommand(),
			new AddObjectCommand(),
			new RemoveObjectCommand(),
			new spaunLemmingCommand(),
			new StatsCommand()
	);

	
	public static Command parse(String[] commandWords) throws CommandParseException{	
		for (Command c: availableCommands) { //recorre todos los comandos
			Command parsedCommand = c.parse(commandWords); //se parsea el input
			if (parsedCommand != null) {
				return parsedCommand; //se devuelve el comando
			}
		
		}
		
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) {
			commands.append(c.helpText()); // Añade el texto de ayuda del comando
		}
		
		return commands.toString(); //transforma los mensajes de ayuda a string
	}

}
