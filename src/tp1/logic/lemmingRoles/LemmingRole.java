package tp1.logic.lemmingRoles;

import tp1.logic.GameItem;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;

public interface LemmingRole {
    public void start(Lemming lemming); // Código al asignar el rol.
    public void play(Lemming lemming);  // Comportamiento en cada ciclo.
    public String getIcon(Lemming lemming); // Representación visual del rol.
    public String getName();
    public String helpText();
    
    public boolean receiveInteraction(GameItem other, Lemming lemming);
   
    public boolean interactWith(Lemming receiver, Lemming lemming);
     
    public boolean interactWith(Wall wall, Lemming lemming);
   
    public boolean interactWith(ExitDoor door, Lemming lemming);
    
    public boolean interactWith(MetalWall metalWall, Lemming lemming);
    
    boolean canParse(String input);
   
}