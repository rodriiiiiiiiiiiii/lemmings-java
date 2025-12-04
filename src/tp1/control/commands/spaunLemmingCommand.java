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
public class spaunLemmingCommand extends Command {
    private static final String NAME     = "spawnLemming";
    private static final String SHORTCUT = "sl";
    private static final String DETAILS  = "[sl]emming <Row> <Col>";
    private static final String HELP     = "Add a new Lemming to the game at (Row,Col)";

    private final Position pos;

    public spaunLemmingCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.pos  = null;
    }

    public spaunLemmingCommand(Position pos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.pos  = pos;
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        if (!matchCommand(words[0])) return null;
        if (words.length != 3) {
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        }
        // row: letter A->0, B->1...
        int row = words[1].charAt(0) - 'A';
        int col;
        try {
            col = Integer.parseInt(words[2]) - 1;
        } catch (NumberFormatException e) {
            throw new CommandParseException(Messages.INVALID_NUMBER.formatted(words[2]), e);
        }
        return new spaunLemmingCommand(new Position(col, row));
    }

    @Override
    public void execute(GameModel model, GameView view) throws CommandExecuteException {
        Game game = (Game) model;
        GameObject obj;
        GameWorld world = (GameWorld) game;
        obj = new Lemming(world, pos);
        game.incrementLemmingsAlive();
        game.addObject(obj);
        view.showGame();
    }
}
