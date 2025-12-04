package tp1.logic.lemmingRoles;

import tp1.logic.GameItem;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole {
    public AbstractRole() {
    	
    }

    @Override
    public boolean receiveInteraction(GameItem other, Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(Lemming receiver, Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(Wall wall, Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door, Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(MetalWall wall, Lemming lemming) {
    	return false;
    }

}