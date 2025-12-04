package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	public Position(int col, int row) {
		this.row = row;
		this.col = col;
    }
	
	public void actPos(GameWorld game) {
	    game.positionToString(this.col, this.row);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) //es el único getClass permitido en la práctica
			return false;
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}
	
	public Position avanzaFila(){
		return new Position (col, row + 1);
	}
	
	public Position avanzaCol() {
		return new Position (col + 1, row);
	}
	
	public Position retrocedeFila() {
		return new Position (col, row - 1);
	}
	
	public Position retrocedeCol() {
		return new Position (col - 1, row);
	}
	
	public boolean limInferior() {
		return this.row >= 9;
	}
	
	public boolean FinDer() {
		return this.col > 9;
	}
	
	public boolean FinIzq() {
		return this.col <= 0;
	}
	
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
