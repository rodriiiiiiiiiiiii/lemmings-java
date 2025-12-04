package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.view.Messages;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.ObjectParseException;

import java.util.Arrays;
import java.util.List;


public class GameObjectFactory {
	private static final List<GameObject> availableObjects = Arrays.asList(
		    new Wall(),
		    new MetalWall(),
		    new ExitDoor(),
		    new Lemming(),
		    new Coin(),
		    new Pincho(),
		    new Volcan(),
		    new Trampolin(),
		    new Portal()    // ← registramos el Portal
		);


	
    public static GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
        for (GameObject gameObject: availableObjects) {
        		GameObject go = gameObject.parse(line, game);
        		if (go != null)
        			return go;
        }
        throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted(line));
    }

}