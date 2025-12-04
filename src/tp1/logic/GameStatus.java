package tp1.logic;

public interface GameStatus {
	public int getCycle();
	public int numLemmingsInBoard();
	public int numLemmingsDead();
	public int numLemmingsExit();
	public int numLemmingsToWin();
	public boolean playerWins();
	public boolean playerLooses();
	public void incrementCoins();
}