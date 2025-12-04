package tp1.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.gameobjects.Pincho;
import tp1.logic.gameobjects.Portal;
import tp1.logic.gameobjects.Volcan;
import tp1.logic.gameobjects.Trampolin;
import tp1.logic.gameobjects.Coin;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;

public class Game implements GameWorld, GameStatus, GameModel, GameItem{

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

	private boolean Finished;
	private GameObjectContainer objects;
    private int nLevel;
	private int cycle;
	private int lemmingsAlive;
    private int lemmingsDead;
    private int lemmingsExited;
    private int lemmingsToWin;
    private int coinsCollected = 0;
	
    private GameConfiguration conf = FileGameConfiguration.NONE;
    private String FileName;
    
	public Game(int nLevel) { //se inicializan algunos valores
		this.Finished = false;
		this.nLevel = nLevel;
		cycle = 0;
        lemmingsDead = 0;
        lemmingsExited = 0;
        objects = new GameObjectContainer();
        this.loadLevel(nLevel);
	}

	private void loadLevel(int nLevel) { //se selecciona el nivel
		 switch (nLevel) {
	        case 1:
	            loadLevel1();
	            break;
	        case 2:
	            loadLevel2();
	            break;
	        default:
	            loadLevel1();
	        }
    }
	
    private void loadLevel1() {//las variables lemmignsToWin y lemmingsAlive dependen del nivel
     	this.lemmingsToWin = 3;
	    this.lemmingsAlive = 4;
        objects.addObject(new Lemming(this, new Position(6 ,0)));
        objects.addObject(new Lemming(this, new Position(9 ,0)));
        objects.addObject(new Lemming(this, new Position(2 ,3)));
        objects.addObject(new Lemming(this, new Position(3 ,3)));
        //objects.addObject(new Volcan(this, new Position(4 ,4)));
        objects.addObject(new Wall(this, new Position(0,9)));
        objects.addObject(new Wall(this, new Position(1,9)));
        objects.addObject(new Wall(this, new Position(2,4)));
        objects.addObject(new Wall(this, new Position(3,4)));
        objects.addObject(new Wall(this, new Position(4,4)));
        objects.addObject(new Wall(this, new Position(3,5)));
        objects.addObject(new Wall(this, new Position(5,6)));
        //objects.addObject(new Wall(this, new Position(4,3)));
        objects.addObject(new MetalWall(this, new Position(4, 6)));
        objects.addObject(new Wall(this, new Position(6,6)));
        objects.addObject(new Wall(this, new Position(7,6)));
        objects.addObject(new Wall(this, new Position(7,5)));
        objects.addObject(new Wall(this, new Position(8,1)));
        objects.addObject(new Wall(this, new Position(9,1)));
        objects.addObject(new Wall(this, new Position(8,8)));
        objects.addObject(new Wall(this, new Position(8,9)));
        objects.addObject(new Wall(this, new Position(9,9)));
        objects.addObject(new MetalWall(this, new Position(3, 6)));
        objects.addObject(new ExitDoor (this, new Position(4, 5)));
        objects.addObject(new Coin (this, new Position(5, 5)));
        objects.addObject(new Coin (this, new Position(6, 5)));
        objects.addObject(new Coin (this, new Position(4, 5)));
        //objects.addObject(new Portal(this, new Position(4,3), new Position(9,8)));
        //objects.addObject(new Portal(this, new Position(9,8), new Position(4,3)));
        try {
            this.asignarRoleAlLemming(new ParachuterRole(), new Position(6, 0));
        } catch (OffBoardException e) {
        	
        }
    	
    }

    private void loadLevel2(){
    	this.lemmingsToWin = 4;
	    this.lemmingsAlive = 5;
    	objects.addObject(new Lemming(this, new Position(9, 0)));
    	objects.addObject(new Lemming(this, new Position(3, 3)));
    	objects.addObject(new Lemming(this, new Position(5, 3)));
    	objects.addObject(new Lemming(this, new Position(4, 5)));
    	objects.addObject(new Lemming(this, new Position(0, 8)));
    	
    	objects.addObject(new Wall(this, new Position(2, 4)));
    	objects.addObject(new Wall(this, new Position(3, 4)));
    	objects.addObject(new Wall(this, new Position(4, 4)));
    	objects.addObject(new Wall(this, new Position(5, 4)));
    	objects.addObject(new Wall(this, new Position(4, 6)));
    	objects.addObject(new Wall(this, new Position(5, 6)));
    	objects.addObject(new Wall(this, new Position(6, 6)));
    	objects.addObject(new Wall(this, new Position(7, 6)));
    	objects.addObject(new Wall(this, new Position(7, 5)));
    	objects.addObject(new Wall(this, new Position(8, 8)));
    	objects.addObject(new Wall(this, new Position(0, 9)));
    	objects.addObject(new Wall(this, new Position(1, 9)));
    	objects.addObject(new Wall(this, new Position(2, 9)));
    	objects.addObject(new Wall(this, new Position(8, 1)));
    	objects.addObject(new Wall(this, new Position(9, 1)));
    	objects.addObject(new Wall(this, new Position(8, 9)));
    	objects.addObject(new Wall(this, new Position(9, 9)));
    	
    	objects.addObject(new ExitDoor(this, new Position(4, 5)));
    }
    
    public void load(String fileName) throws GameLoadException {
    	this.FileName = fileName;
        // Usar FileGameConfiguration para cargar la configuración
        conf = new FileGameConfiguration(fileName, this);
        this.cycle = conf.getCycle();
	    this.lemmingsAlive = conf.numLemmingsInBoard();
	    this.lemmingsDead = conf.numLemmingsDead();
	    this.lemmingsExited = conf.numLemmingsExit();
	    this.lemmingsToWin = conf.numLemmingToWin();
	    this.objects = new GameObjectContainer(conf.getGameObjects());
    }
    
    public void save(String filename) throws GameModelException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            // Escribir el estado del juego usando el método toString()
	            writer.write(this.toString());
	            
	            System.out.println("Game saved successfully to " + filename);
	            this.FileName = filename;
        } catch (IOException e) {
            throw new GameModelException("Error writing to file: " + filename, e);
        }
    }
	
// GameStatus methods	
    @Override
    public int getLevel() {
		return this.nLevel;
	}
    
    @Override
	public int getCycle() {
		return cycle;
	}
    
    @Override
	public int numLemmingsInBoard() {
    	return lemmingsAlive;
	}

	@Override
	public int numLemmingsDead() {
		return lemmingsDead;
	}

	@Override
	public int numLemmingsExit() {
		return lemmingsExited;
	}

	@Override
	public int numLemmingsToWin() {
		 return lemmingsToWin;
	}
	
	@Override
	public String positionToString(int col, int row) {
		 Position positionToCheck = new Position(col, row);
		    
		 // Llamar al contenedor para obtener el string de los objetos en esa posición
		 return objects.getPositionString(positionToCheck);
	}

	@Override
	public boolean playerWins() {
		return (lemmingsExited >= lemmingsToWin);
	}

	@Override
	public boolean playerLooses() {
		return (numLemmingsInBoard() == 0 && lemmingsExited < lemmingsToWin);
	}
	
	@Override
	public void incrementLemmingsDead() { //Añade un lemming al número de lemmings que han muerto
        lemmingsDead++;
	}	

	@Override
	public void reduceLemmingsAlive(){
		lemmingsAlive--;
	}
	public void incrementLemmingsAlive(){
		lemmingsAlive++;
	}

// GameModel methods
	@Override
	public boolean isFinished() { //involucra exit, win y loose
		return (Finished || playerWins() || playerLooses());
	}
	
	@Override
	public void update() {
		cycle++;
        objects.update(this);  // Delegar en GameObjectContainer
	}
	
	@Override
	public void reset() { //reinicia los valores del gameinit
		 if (conf == FileGameConfiguration.NONE) {
			this.Finished = false;
			this.cycle = 0;
	        this.lemmingsDead = 0;
	        this.lemmingsExited = 0;
	        objects = new GameObjectContainer();
	        loadLevel(this.nLevel);
		 }
		 else {
			 try {
				 load(FileName);
			 } catch (GameLoadException gle) {
				 
			 }
		 }
	}
	
	@Override
	public void exit() {
		Finished = true;
	}
	
// GameWorld methods (callbacks)
	@Override
	public boolean isInAir(Position pos) {
		return !objects.isSolidAt(pos.avanzaFila());
    }
	
	@Override
	public boolean isInDoor(Position pos) {
		return objects.isAtExitDoor(pos);
	}

	@Override
	public boolean isInWall(Position pos) {
		return objects.isAtWall(pos);
	}

		
	@Override
	public void lemmingArrived() {
		lemmingsExited++;
		reduceLemmingsAlive();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cycle).append(" ")
	      .append(lemmingsAlive).append(" ")
	      .append(lemmingsDead).append(" ")
	      .append(lemmingsExited).append(" ")
	      .append(lemmingsToWin).append("\n");
		
		sb.append(objects.objectsToString());
		return sb.toString();
    }
	
	public void checkPositionInBounds(Position pos) throws OffBoardException {
	    if (pos.getRow() < 0 || pos.getRow() >= DIM_X || 
	        pos.getCol() < 0 || pos.getCol() >= DIM_Y) {
	        throw new OffBoardException(Messages.POS_OFF_BOARD.formatted(pos.toString()));
	    }
	}
	
	//llama al contenedor para asignar al lemming un rol
		public boolean asignarRoleAlLemming(LemmingRole role, Position posicion) throws OffBoardException{
			checkPositionInBounds(posicion);
			
			return this.objects.asignarRoleAlLemming(role, posicion);
		}
		
		@Override
		public boolean receiveInteraction(GameItem object) {
			return this.objects.checksInteractions(object);
		}

		@Override
		public boolean interactWith(Lemming lemming) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean interactWith(Wall wall) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean interactWith(ExitDoor door) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean interactWith(MetalWall metalWall) {
			// TODO Auto-generated method stub
			return false;
		}

		//@Override
		public boolean receiveInteraction(Lemming lemming) {
			return this.objects.checksInteractions(lemming);
		}
		
	    public int getCoinsCollected() {
	        return coinsCollected;
	    }

		@Override
		public void incrementCoins() {
			coinsCollected++;
			
		}
		// dentro de Game.java
		public void destroyWallAt(Position pos) {
		    objects.destroyWallsAt(pos);
		}
		/** Permite añadir objetos dinámicamente */
		public void addObject(GameObject obj) {
		    this.objects.addObject(obj);
		}
		public void removeObjectsAt(Position pos) {
		       objects.removeObjectsAt(pos);
		}



}