package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;

public class ResetCommand extends NoParamsCommand {

	private static final String NAME = "reset";
	private static final String SHORTCUT = "r";
	private static final String DETAILS= "[r]eset";
	private static final String HELP = "Resets the game to its initial state.";
	

    public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public void execute(GameModel game, GameView view) {
		game.reset();  // Reiniciamos el juego con el mismo nivel
        view.showGame();  // Creamos una nueva vista para el nuevo juego
        view.showMessage("Game reset at level " + game.getLevel()); //Indica el nivel al que se reinicia
	}

}