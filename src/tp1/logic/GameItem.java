package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Pincho;
import tp1.logic.gameobjects.Wall;

public interface GameItem {
    public boolean receiveInteraction(GameItem other);

    public boolean interactWith(Lemming lemming);

    public boolean interactWith(Wall wall);
    
    public boolean interactWith(ExitDoor door);

    public boolean interactWith(MetalWall metalWall);
}