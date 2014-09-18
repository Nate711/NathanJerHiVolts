package nathankau;

public class Mho extends GameObject {
	
	int px,py;
	
	Mho(int x, int y) {
		super(x,y);
	}
	public void updateGameSense(char[][] newGameMap) {
		updateGameMap(newGameMap);			
	}
	public void makeMove() {
		
	}
}
