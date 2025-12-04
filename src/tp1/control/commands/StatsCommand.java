package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.GameStatus;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;

/**
 * Command to display current game statistics.
 * Syntax: stats
 */
public class StatsCommand extends NoParamsCommand {
    private static final String NAME     = "stats";
    private static final String SHORTCUT = "st";
    private static final String DETAILS  = "[st]ats";
    private static final String HELP     = "Show current game statistics";

    public StatsCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public void execute(GameModel model, GameView view) throws CommandExecuteException {
        // GameStatus para acceder a estadísticas generales
        GameStatus status = (GameStatus) model;
        view.showMessage(Messages.NUMBER_OF_CYCLES.formatted(status.getCycle()));
        view.showMessage(Messages.NUM_LEMMINGS.formatted(status.numLemmingsInBoard()));
        view.showMessage(Messages.DEAD_LEMMINGS.formatted(status.numLemmingsDead()));
        view.showMessage(Messages.EXIT_LEMMINGS.formatted(status.numLemmingsExit(), status.numLemmingsToWin()));
        // Si el juego implementa monedas
        if (model instanceof Game) {
            int coins = ((Game) model).getCoinsCollected();
            view.showMessage("Coins collected: " + coins);
        }
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        // NoParamsCommand.parse ya cubre shortcut/no params
        return super.parse(words);
    }
}
