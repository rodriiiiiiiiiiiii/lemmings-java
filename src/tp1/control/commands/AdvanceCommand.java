package tp1.control.commands;

import tp1.control.commands.Command;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;


public class AdvanceCommand extends Command {
    private static final String NAME = "advance";
    private static final String SHORTCUT = "a";
    private static final String DETAILS = "[a]dvance <n>";
    private static final String HELP = "Advance the game n cycles";

    private final int cycles;

    public AdvanceCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.cycles = 1;
    }

    public AdvanceCommand(int cycles) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.cycles = cycles;
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        if (!matchCommand(words[0])) return null;

        if (words.length == 1) {
            return new AdvanceCommand(1);
        } else if (words.length == 2) {
            try {
                int n = Integer.parseInt(words[1]);
                if (n < 1) {
                    throw new CommandParseException("Number of cycles must be positive");
                }
                return new AdvanceCommand(n);
            } catch (NumberFormatException e) {
                throw new CommandParseException(Messages.INVALID_NUMBER.formatted(words[1]), e);
            }
        }
        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        for (int i = 0; i < cycles; i++) {
            game.update();
        }
        view.showGame();
    }
}
