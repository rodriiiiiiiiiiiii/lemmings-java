package tp1.logic;

public interface GameConfiguration {
	  // Métodos para obtener el estado del juego
    int getCycle();
    int numLemmingsInBoard();
    int numLemmingsDead();
    int numLemmingsExit();
    int numLemmingToWin();

    // Método para obtener el contenedor de objetos del juego
    GameObjectContainer getGameObjects();
}
