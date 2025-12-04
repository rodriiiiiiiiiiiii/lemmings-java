package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Direction;

public class Lemming extends GameObject {
	private int MAX_FALL = 2;
	private LemmingRole role;
	private Direction dir = Direction.RIGHT;
	private int casillasCaida;
	
	public Lemming(GameWorld game, Position pos) {//constructor con parámetros
		super(game, pos);
		this.role = new WalkerRole();
	}

	public Lemming() {
		// TODO Auto-generated constructor stub
	}

	public Lemming(GameWorld game, Position position, Direction direction, int height, LemmingRole role) {
		super(game, position); // Llama al constructor de la clase base si almacena la posición
        this.dir = direction; // Establecer la dirección inicial
        casillasCaida = height; // Establecer la altura
        this.role = role; // Establecer el rol del lemming
    }

	public Direction getDirection() {
		   return this.dir;
	}
	
	public void disableRol() {
		this.role = new WalkerRole(); //por defecto se establece walkerRole
	}
	
	public void reestablecerCaida(){ //se usa para el rol parachute
		this.casillasCaida = 0;
	}
	
	public boolean isInAir() { //está en el aire
		return game.isInAir(pos);
	}
	
	public void excavar() { 
		Position caida = pos.avanzaFila(); //Nueva posicion del lemming cuando caiga
		pos = caida;
		pos.actPos(this.game); // Actualizamos la posición en el tablero
		this.casillasCaida++;
	}
	
	public void caer() {
		Position caida = pos.avanzaFila(); //Nueva posicion del lemming cuando caiga
		pos = caida;
		pos.actPos(this.game); // Actualizamos la posición en el tablero
		this.casillasCaida++;
		
	}
	
	public void walkOrFall() {
		if(game.isInDoor(pos)) {// Si está en exit door se elimina
			game.lemmingArrived();
			this.isAlive = false;
			this.enteredDoor = true;
		}
		else { 
			if(game.isInAir(pos)) { //Si el lemming esta en el aire
				Position caida = pos.avanzaFila(); //Nueva posicion del lemming cuando caiga
				pos = caida;
				pos.actPos(this.game); // Actualizamos la posición en el tablero
				this.casillasCaida++;
			}
			else { 
				if(dir == Direction.RIGHT) {
			
					Position movPos = pos.avanzaCol();						
		            if(movPos.FinDer()|| game.isInWall(movPos)) {
							this.dir = Direction.LEFT;
					}
				    else {
				    	this.pos = movPos; //Actualizamos la posicion del lemming
				    	this.pos.actPos(this.game); // Actualizamos en el tablero
				    }
				}
				else if (this.dir == Direction.LEFT) { // Si la dirección del lemming es hacia la izquierda
				    Position movPos = pos.retrocedeCol(); // Nueva posición del lemming (da un paso a la izquierda)
				    
				    // Si al dar el paso, el lemming choca con el extremo izquierdo del tablero o contra un muro, cambiará su dirección hacia la derecha
				    if (pos.FinIzq() || game.isInWall(movPos)) {
				        this.dir = Direction.RIGHT; // Cambia la dirección a la derecha
				    }
				    else {
				        pos = movPos; // Actualizamos la posición del lemming
				        pos.actPos(this.game); // Actualizamos en el tablero
				    }
				}

				
			}
		}
	}
	
	@Override
	public String getIcon() {
		return role.getIcon(this);
	}
	
	@Override
	public String toString() {
		return "(" + pos.getCol() + "," + pos.getRow() + ")" + " Lemming" + " " + dir + " " + casillasCaida + " " + role.getName();
	}
	
	// 1.1: Override de isSolid() para reflejar el rol Muro
	@Override
	public boolean isSolid() {
	    return role instanceof tp1.logic.lemmingRoles.MuroRole;
	}

	// 1.2: Necesitamos poder cambiar la dirección desde MuroRole:
	public void setDirection(Direction newDir) {
	    this.dir = newDir;
	}


    @Override
    public boolean isExit() {
        return false; // No es una salida
    }
    
    @Override
    public void update() {
    	comprobarInteracciones();
    	if (isAlive()) { // Solo actualizamos si el lemming está vivo.
            role.play(this); // Delegamos el comportamiento actual al rol (WalkerRole).
        }
    }
    
    @Override
    public void dead() {
    	Position nextPos = pos.avanzaFila();
    	 if (game.isInWall(nextPos)) {
 	        if (casillasCaida > MAX_FALL) { //si la caída es mayor de 2 bloques
 	        	isAlive = false; 
 	        	game.incrementLemmingsDead();
 	 	    	game.reduceLemmingsAlive();
 	        } else {
 	            casillasCaida = 0;
 	        }
 	    }
         // Si el lemming sale por debajo del tablero, también muere 
 	    else if (pos.limInferior()) {
 	    	isAlive = false;
 	    	game.incrementLemmingsDead();
 	    	game.reduceLemmingsAlive();
 	    }
    }
    
    @Override
	public boolean sePuedeAsignar(LemmingRole newRole) {
		if(newRole != null) { //que el rol se haya parseado correctamente
			this.role = newRole;
			role.start(this); //cada start depende del rol, aunque la mayoría son vacíos
			return true;
		}
		return false;
	}
	//comprueba todas las interacciones con todos los objetos del juego
	public boolean comprobarInteracciones() {
		return game.receiveInteraction(this);
	}

    @Override
    public boolean receiveInteraction(GameItem other) {
        return this.role.receiveInteraction(other, this);
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        return this.role.interactWith(lemming, this);
    }
    //comprueba si está encima de un objeto solido
    public boolean estaEncima(GameObject other) {
    	return other.isInPosition(pos.avanzaFila());
    }
    public boolean estaAlLadoDer(GameObject other) {
    	return other.isInPosition(pos.avanzaCol());
    }
    public boolean estaAlLadoIzq(GameObject other) {
    	return other.isInPosition(pos.retrocedeCol());
    }
    
    @Override
    public boolean interactWith(Wall wall) {
        return this.role.interactWith(wall, this);
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        return this.role.interactWith(door, this);
    }

    @Override
    public boolean interactWith(MetalWall metalWall) {
        return this.role.interactWith(metalWall, this);
    }
    


	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		String name = getObjectNameFrom(line);
		if (!name.equalsIgnoreCase("L") && !name.equalsIgnoreCase("Lemming")) {
			return null;
		}
		
		
		if (words.length != 5) {
		       throw new ObjectParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		//Obtener la posicion
		Position pos;
	    try {
	        pos = getPositionFrom(line, game); // Asumiendo que existe un método parsePosition en Position
	    } catch (IllegalArgumentException e) {
	        throw new ObjectParseException("Invalid position format: " + words[0], e);
	    }
	    
	    
	    game.checkPositionInBounds(pos);

	    // 3. Obtener la dirección
	    Direction dir;
	    dir = getLemmingDirectionFrom(line);
	    try {
	        if (dir != Direction.RIGHT &&  dir != Direction.LEFT) {
	            throw new IllegalArgumentException("Invalid lemming direction");
	        }
	    } catch (IllegalArgumentException e) {
	        throw new ObjectParseException("Invalid lemming direction: " + dir, e);
	    }
	    
	    
	    // 4. Obtener la altura
	    int height;
	    try {
	        height = getLemmingHeightFrom(line);
	        if (height < 0) {
	            throw new ObjectParseException("Height must be non-negative: " + words[3]);
	        }
	    } catch (NumberFormatException e) {
	        throw new ObjectParseException("Invalid height format: " + words[3], e);
	    }
	    
	    
	    // 5. Obtener el rol
	    LemmingRole role;
	    try {
	        role = getLemmingRoleFrom(line); // Asumiendo un método parseRole en LemmingRole
	    } catch (IllegalArgumentException e) {
	        throw new ObjectParseException("Invalid lemming role: " + words[4], e);
	    }
	    
	    
	    // Si todos los datos son válidos, crear un nuevo objeto Lemming
	    return new Lemming(game, pos, dir, height, role);
	}
	
	
	public void Saltar(Position pos) {
		Position salto = pos.retrocedeFila(); //Nueva posicion del lemming cuando caiga
		salto = pos.retrocedeFila();
		pos = salto;
		pos.actPos(this.game);
	}
	
	/**
	 * Mueve al lemming dos casillas hacia arriba (si cabe) para simular el rebote.
	 */
	public void bounce() {
	    // calculamos la posición dos filas arriba
	    Position up1 = pos.retrocedeFila();
	    Position up2 = up1.retrocedeFila();
	    Position upgo;
	    if (this.getDirection() == Direction.RIGHT) {
	    	upgo = up2.avanzaCol();
	    }
	    else {
	    	upgo = up2.retrocedeCol();
	    }
	    
	    try {
	        // validamos que no salimos del tablero
	        game.checkPositionInBounds(upgo);
	        // actualizamos posición
	        this.pos = upgo;
	        // reflectamos el cambio en la vista
	        this.pos.actPos((GameWorld)game);
	    } catch (OffBoardException e) {
	        // si rebota fuera, lo dejamos en up1
	        this.pos = up1;
	        this.pos.actPos((GameWorld)game);
	    }
	}
	
	// dentro de tp1.logic.gameobjects.Lemming

	/** Permite que Portal haga `lem.setPosition(destino)` */
	public void setPosition(Position nueva) {
	    this.pos = nueva;
	}

	/** Útil si quieres leer la posición actual */
	public Position getPosition() {
	    return this.pos;
	}
	// dentro de Lemming.java

	/** Permite a un role suicidarse correctamente */
	public void die() {
	    // marcamos como muerto y actualizamos contadores
	    this.kill();                     // de GameObject
	    game.incrementLemmingsDead();    // game es GameWorld
	    game.reduceLemmingsAlive();
	}

	/** Para que BomberRole obtenga el Game real y llame a destroyWallAt(...) */
	public tp1.logic.Game getGame() {
	    return (tp1.logic.Game) this.game;
	}



}