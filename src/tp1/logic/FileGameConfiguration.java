package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileGameConfiguration implements GameConfiguration{
	private int cycle;
    private int lemmingsInBoard;
    private int lemmingsDead;
    private int lemmingsExit;
    private int lemmingsToWin;
    private GameObjectContainer gameObjects;
	
    public static final GameConfiguration NONE = new FileGameConfiguration();
    
    private FileGameConfiguration() {
        // Implementa la configuración estándar por defecto si es necesario
    }
    
    public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
    	try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        	
        	String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new GameLoadException("File is empty or does not contain valid game configuration.");
            }
        	
            String[] state = firstLine.trim().split(" ");
            if (state.length != 5) 
            	throw new GameLoadException("Invalid game state format in file.");
            try {
	            // Parsear el estado del juego
	            this.cycle = Integer.parseInt(state[0]);
	            this.lemmingsInBoard = Integer.parseInt(state[1]);
	            this.lemmingsDead = Integer.parseInt(state[2]);
	            this.lemmingsExit = Integer.parseInt(state[3]);
	            this.lemmingsToWin = Integer.parseInt(state[4]);

            } catch (NumberFormatException nfe) {
            	throw new GameLoadException(Messages.ERROR_FILE_PARSE.formatted(fileName), nfe);
            }
            
            // Inicializar el contenedor de objetos del juego
            gameObjects = new GameObjectContainer();

            // Leer los objetos del juego
            String line;
            while ((line = reader.readLine()) != null) {
            	
                GameObject gameObject = GameObjectFactory.parse(line, game);
                gameObjects.addObject(gameObject);
                
            }

        } catch (FileNotFoundException fnfe) {
        	System.out.println("fnfe");
            throw new GameLoadException(Messages.ERROR_FILE_PARSE.formatted(fileName), fnfe);
            
        } catch (ObjectParseException ope) {
        	System.out.println("ope");
            throw new GameLoadException(Messages.ERROR_FILE_PARSE.formatted(fileName), ope);
        	
        } catch (OffBoardException obe) {
        	System.out.println("obe");
            throw new GameLoadException(Messages.ERROR_FILE_PARSE.formatted(fileName), obe);
            
        } catch (IOException ioe) {
        	System.out.println("ioe");
        	throw new GameLoadException(Messages.ERROR_FILE_PARSE.formatted(fileName), ioe);
        }
    }
    
	@Override
    public int getCycle() {
        return cycle;
    }

    @Override
    public int numLemmingsInBoard() {
        return lemmingsInBoard;
    }

    @Override
    public int numLemmingsDead() {
        return lemmingsDead;
    }

    @Override
    public int numLemmingsExit() {
        return lemmingsExit;
    }

    @Override
    public int numLemmingToWin() {
        return lemmingsToWin;
    }

    @Override
    public GameObjectContainer getGameObjects() {
        return gameObjects;
    }
}
