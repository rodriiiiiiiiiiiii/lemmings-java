package tp1.logic;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {
	public void load(String fileName) throws GameLoadException;
	public void save(String filename) throws GameModelException;
	public int getLevel();
	public boolean isFinished();
	public void update();
	public void reset();
	public void exit();
	public boolean asignarRoleAlLemming(LemmingRole role, Position posicion) throws OffBoardException;
}