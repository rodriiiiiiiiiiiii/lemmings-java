package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.lemmingRoles.*;

public class GameObjectContainer {
	private List<GameObject> objects;
	Lemming lemming;

	public GameObjectContainer() { //se inicializa la lista
		objects = new ArrayList<GameObject>();
	}
	
	
	public GameObjectContainer(GameObjectContainer gameObjects) {
		this.objects = new ArrayList<>(gameObjects.objects);
	}

	// Only one add method (polymorphism)
	public void addObject(GameObject object) {
		if (object != null) {
			objects.add(object);
		}
	}
	
	private void removeDead(Game game) {  
		for(int i =  objects.size() - 1; i >= 0; i-- ) {
	    	if(!this.objects.get(i).isAlive()) { //esto incluye tanto lemmings como walls
	    		objects.remove(i); // por ello no se llama a incrementLemmingsDead() ni reduceLemmingsAlive()
	    	}
	    }
		
	}
	
	public String getPositionString(Position pos) {
        StringBuilder sb = new StringBuilder();
        for (GameObject obj : objects) {
        	if(obj.isInPosition(pos)) {
        		sb.append(obj.getIcon());
        	}
        }
        return sb.toString();
    }
	
	public String objectsToString() {
		StringBuilder sb = new StringBuilder();
		for (GameObject obj: objects) {
			sb.append(obj.toString()).append("\n");
		}
		return sb.toString();
	}
	
	public boolean isSolidAt(Position pos) {
	    for (GameObject obj : objects) {
	        if (obj.isSolid() && obj.isInPosition(pos)) { //que se cumpla que sea sólido y esté en dicha posición
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean isAtExitDoor(Position pos) {
		boolean encontrada = false;
		int i=0;
		while(i< objects.size() && !encontrada) { //se busca en el Gameobjects
			if(this.objects.get(i).isInPosition(pos) && this.objects.get(i).isExit()) { //que se cumpla que sea exitdoor y esté en dicha posición
				encontrada = true;
			}
			else {
				i++;
			}
		}
		return encontrada;
	}
	
	public boolean isAtWall(Position pos) { 
		boolean encontrada = false;
		int i=0;
		while(i< objects.size() && !encontrada) { //se busca en el Gameobjects
			if(this.objects.get(i).isInPosition(pos) && this.objects.get(i).isSolid()) { //que se cumpla que sea wall y esté en dicha posición
				encontrada = true;
			}
			else {
				i++;
			}
		}
		return encontrada;
	}	
	
	public void update(Game game) {
		for(int i=0; i< this.objects.size(); i++) { 
    		objects.get(i).dead(); //se actualiza el isAlive si hiciera falta
    		objects.get(i).update();
    	}
    	this.removeDead(game); //se hace un remove de los objetos con isAlive = false
	}
	
	public boolean checksInteractions(GameItem gameObj) {
		for (GameObject obj : objects) {
			if (obj.receiveInteraction(gameObj)) { //interacciones entre los objetos
                return true; 
            }
        }
		return false;
	}
	
	public boolean asignarRoleAlLemming(LemmingRole role, Position pos) {
		for (GameObject obj : objects) {
			if (obj.isInPosition(pos) // Busca el Lemming en la posición
					&& obj.sePuedeAsignar(role)) { // Llama al método de asignación de rol
                return true; 
            }
        }
		return false;
	}
	
	/**
	 * Marca como muertas (isAlive=false) todas las paredes
	 * (Wall o MetalWall) en la posición dada.
	 */
	// dentro de GameObjectContainer.java
	public void destroyWallsAt(Position pos) {
	    for (GameObject obj : objects) {
	        if (obj.isInPosition(pos) &&
	           (obj instanceof Wall || obj instanceof MetalWall)) {
	            obj.kill();            // ← en lugar de obj.isAlive = false
	        }
	    }
	}
	
	public void removeObjectsAt(Position pos) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            GameObject obj = objects.get(i);
            if (obj.isInPosition(pos)) {
                obj.kill();   // usa el método público kill() en GameObject
            }
        }
    }


	
}
