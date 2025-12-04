package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.GameWorld;
import tp1.logic.Game;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Wall;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Coin;
import tp1.logic.gameobjects.Pincho;
import tp1.logic.gameobjects.Volcan;
import tp1.logic.gameobjects.Trampolin;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 * Command to add a new GameObject at runtime.
 * Syntax: addObject <Type> <Row> <Col>
 */
public class AddObjectCommand extends Command {
    private static final String NAME     = "addObject";
    private static final String SHORTCUT = "ao";
    private static final String DETAILS  = "[ao]ddObject <Type> <Row> <Col>";
    private static final String HELP     = "Add a new object to the game at (Row,Col)";

    private final String type;
    private final Position pos;

    public AddObjectCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.type = null;
        this.pos  = null;
    }

    public AddObjectCommand(String type, Position pos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.type = type;
        this.pos  = pos;
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        if (!matchCommand(words[0])) return null;
        if (words.length != 4) {
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        }
        String t = words[1];
        // row: letter A->0, B->1...
        int row = words[2].charAt(0) - 'A';
        int col;
        try {
            col = Integer.parseInt(words[3]) - 1;
        } catch (NumberFormatException e) {
            throw new CommandParseException(Messages.INVALID_NUMBER.formatted(words[3]), e);
        }
        return new AddObjectCommand(t, new Position(col, row));
    }

    @Override
    public void execute(GameModel model, GameView view) throws CommandExecuteException {
        Game game = (Game) model;
        GameObject obj;
        GameWorld world = (GameWorld) game;
        switch (type.toLowerCase()) {
            case "wall":     obj = new Wall(world, pos);      break;
            case "metalwall":obj = new MetalWall(world, pos); break;
            case "exitdoor": obj = new ExitDoor(world, pos);  break;
            case "lemming":  obj = new Lemming(world, pos);   break;
            case "coin":     obj = new Coin(world, pos);      break;
            case "pincho":   obj = new Pincho(world, pos);    break;
            case "volcano":  obj = new Volcan(world, pos);   break;
            case "trampolin":obj = new Trampolin(world, pos);break;
            default:
                view.showError("Unknown object type: " + type);
                return;
        }
        // ensure Game has addObject(GameObject)
        game.addObject(obj);
        view.showGame();
    }
}
