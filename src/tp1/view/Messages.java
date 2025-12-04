package tp1.view;

import tp1.util.MyStringUtils;

public class Messages {
	public static final String VERSION = "2.2";

	public static final String GAME_NAME = "Lemmings";

	public static final String USAGE = "Usage: %s [<level>]".formatted(GAME_NAME);

	public static final String WELCOME = String.format("%s %s%n", GAME_NAME, VERSION);

	public static final String LEVEL_NOT_A_NUMBER = "The level must be a number";
	public static final String INVALID_LEVEL_NUMBER = "Not valid level number";

	public static final String LEVEL_NOT_A_NUMBER_ERROR = String.format("%s: %%s", LEVEL_NOT_A_NUMBER);

	public static final String PROMPT = "Command > ";

	public static final String DEBUG = "[DEBUG] Executing: %s%n";
	public static final String ERROR = "[ERROR] Error: %s%n";
	
	// GAME STATUS
	public static final String NUMBER_OF_CYCLES = "Number of cycles: %s";
	public static final String NUM_LEMMINGS = "Lemmings in board: %s";
	public static final String DEAD_LEMMINGS = "Dead lemmings: %s";
	public static final String EXIT_LEMMINGS = "Lemmings exit door: %s ┃%s";

	// GAME END MESSAGE
	public static final String GAME_OVER = "Game over";
	public static final String PLAYER_QUITS = "Player leaves the game";
	public static final String PLAYER_WINS = "Player wins!";
	public static final String PLAYER_LOOSES = "Player loses...";

	// Position format
	public static final String POSITION = "(%s,%s)";

	// Other
	public static final String SPACE = " ";
	public static final String TAB = "   ";
	public static final String LINE_SEPARATOR = System.lineSeparator();
	public static final String LINE = "%s" + LINE_SEPARATOR;
	public static final String LINE_TAB = TAB + LINE;
	public static final String LINE_2TABS = TAB + LINE_TAB;

//Commands
	// Errores de factorías:
	public static final String UNKNOWN_COMMAND = "Unknown command: %s";
	public static final String UNKNOWN_GAME_OBJECT = "Unknown game object: %s";
	public static final String UNKNOWN_ROLE = "Unknown role: %s";
	// Errores de commandos:
	public static final String COMMAND_PARAMETERS_MISSING = "Missing parameters";
	public static final String COMMAND_INCORRECT_PARAMETER_NUMBER = "Incorrect parameter number";
	// Errores de ficheros:
	public static final String FILE_NOT_FOUND = "File not found: \"%s\"";
	public static final String READ_ERROR = "Undetermined error reading file \"%s\"";
	// Errores de parseo de otras cosas:
	public static final String INVALID_NUMBER = "Invalid number: %s";
	public static final String INVALID_POSITION = "Invalid position: %s";
	public static final String INVALID_COMMAND = "Invalid command: %s";
	public static final String INVALID_DIRECTION = "Invalid direction: %s";
	public static final String INVALID_HEIGHT = "Invalid height: %s";
	public static final String INVALID_GAME_STATUS = "Invalid game status";
	public static final String INVALID_INIT_CONF = "Invalid init configuration because %s";
	public static final String ERROR_INVALID_INIT_CONF = "Unknown invalid init configuration";
	public static final String ERROR_PARSING_GAME_OBJECT = "Not a correct game object because: %s";

	
	public static final String HELP_AVAILABLE_COMMANDS = "Available commands:";
	public static final String COMMAND_HELP_TEXT = "%s: %s";

	@Deprecated
	/* @formatter:off */
	public static final String[] HELP_LINES = new String[] { "Available commands:",
			"[n]one | \"\": skips cycle",
			"[h]elp: print this help message",
			"[r]eset [nLevel]: start the nLevel game or the level 1 if not nLevel",
			"[e]xit: end the execution of the game"};
	/* @formatter:on */
	@Deprecated
	public static final String HELP = String.join(LINE_SEPARATOR+"   ", HELP_LINES) + LINE_SEPARATOR;


	// UPDATE (NONE)
	public static final String COMMAND_UPDATE_NAME = "none";
	public static final String COMMAND_UPDATE_SHORTCUT = "n";
	public static final String COMMAND_UPDATE_DETAILS = "[n]one";
	public static final String COMMAND_UPDATE_HELP = "user does not perform any action";
	
	// EXIT
	public static final String COMMAND_EXIT_NAME = "exit";
	public static final String COMMAND_EXIT_SHORTCUT = "e";
	public static final String COMMAND_EXIT_DETAILS = "[e]xit";
	public static final String COMMAND_EXIT_HELP = "exits the game";
	
	// HELP
	public static final String COMMAND_HELP_NAME = "help";
	public static final String COMMAND_HELP_SHORTCUT = "h";
	public static final String COMMAND_HELP_DETAILS = "[h]elp";
	public static final String COMMAND_HELP_HELP = "print this help message";
	
//Roles
	public static final String WALKER_ROL_SHORTCUT = "W";
	public static final String WALKER_ROL_NAME = "Walker";
	public static final String WALKER_ROL_HELP = "[W]alker: Lemming that walks";
	public static final String DOWNCAVER_ROLE_NAME = "Downcaver"; 
    public static final String DOWNCAVER_ROLE_SHORTCUT = "DC"; 
    public static final String DOWNCAVER_ROLE_DETAILS = "	[D]own[C]aver: Lemming caves downwards"; 
    public static final String DOWNCAVER_ROLE_HELP = "[D]own[C]aver: Lemming caves downwards";
    public static final String PARACHUTER_ROLE_NAME = "Parachuter"; 
    public static final String PARACHUTER_ROLE_SHORTCUT = "P"; 
    public static final String PARACHUTER_ROLE_HELP = "[P]arachuter: Lemming falls with a parachute"; 
	public static final String MURO_ROLE_NAME = "MuroRole"; 
    public static final String MURO_SHORTCUT = "MR"; 
    public static final String MURO_ROLE_DETAILS = "	[M]uro[R]ole: Lemming is a wall"; 
    public static final String MURO_ROLE_HELP = "[M]uro[R]ole: Lemming is a wall";
    public static final String NO_LEMMING_IN_POS = "SetRoleCommand error (Incorrect position or no object in that position admits that role)";


//Symbols
	public static final String EMPTY = "";
	public static final String METALWALL = MyStringUtils.repeat("X",ConsoleView.CELL_SIZE);
	public static final String WALL = MyStringUtils.repeat("▓",ConsoleView.CELL_SIZE);
	public static final String EXIT_DOOR = "🚪";
	public static final String LEMMING_RIGHT = "B";
	public static final String LEMMING_LEFT = "ᗺ";
	public static final String LEMMING_PARACHUTE = "🪂";
	public static final String LEMMING_DOWN_CAVER = "´･ω･`";
	public static final String SPIKE = "A";
	public static final String VOLCAN = "🌋";
	public static final String COIN = "O";
	public static final String TRAMPOLIN = "🛝";
	public static final String PORTAL = "🌀";
	public static final String LEMMING_BOMBER = "💣";
	public static final String LEMMING_JUMPER = "🦘";
	public static final String LEMMING_CLIMBER = "🧗";

	
//Exceptions
	public static final String ERROR_FILE_PARSE = "Invalid file %s configuration";
	public static final String ERROR_COMMAND_PARSE = "Command parse problem";
	public static final String ERROR_COMMAND_EXECUTE = "Command execute problem";
	public static final String POS_OFF_BOARD = "Position %s off the board";
	public static final String ROLE_ASIGNED = "Role %s assigned to lemming in position %s.";
	public static final String ROLE_FAILED = "Failed to assign role %s to lemming in position %s.";
	public static final String INVALID_FORMAT = "Invalid format of: %s";
}
