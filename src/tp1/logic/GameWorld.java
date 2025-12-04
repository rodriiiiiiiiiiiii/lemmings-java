package tp1.logic;

import tp1.exceptions.OffBoardException;

public interface GameWorld {
    public boolean isInAir(Position pos);
    public boolean isInDoor(Position pos);
    public boolean isInWall(Position pos);
    
    public void incrementLemmingsDead();
	public void reduceLemmingsAlive();
	public void incrementCoins();
    
    public void lemmingArrived();
    public String positionToString(int col, int row);
	public boolean receiveInteraction(GameItem object);
	
	public void checkPositionInBounds(Position pos) throws OffBoardException;
}