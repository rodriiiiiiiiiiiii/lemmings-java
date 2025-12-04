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
public class RemoveObjectCommand extends Command {
    private static final String NAME     = "removeObject";
    private static final String SHORTCUT = "ro";
    private static final String DETAILS  = "[ro]bject  <Row> <Col>";
    private static final String HELP     = "Remove an object to the game at (Row,Col)";

    private final Position pos;

    public RemoveObjectCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.pos  = null;
    }

    public RemoveObjectCommand(Position pos) {
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
            throw new CommandParseException(Messages.INVALID_NUMBER.formatted(words[3]), e);
        }
        return new RemoveObjectCommand(new Position(col, row));
    }

   
    @Override
    public void execute(GameModel model, GameView view) throws CommandExecuteException {
        Game game = (Game) model;
        try {
            game.removeObjectsAt(pos);
            view.showGame();
        } catch (Exception e) {
            throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
        }
    }
}
