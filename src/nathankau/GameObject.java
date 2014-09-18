package nathankau;

public abstract class GameObject {
	int x_coor,y_coor;
	boolean alive;
	
	char gameMap[][] = new char[12][12]; // 'p' = player, 'm' = mho, 'f' = fence, 'e' = empty
	
	GameObject(int x, int y) {
		x_coor = x;
		y_coor = y;
	}
	GameObject() {
		
	}
	void die() {
		alive = false;
	}
	
	void updateGameMap(char[][] newGameMap) {
		for(int i=0; i<gameMap.length; i++) {
			for(int j=0; j<gameMap[0].length; j++) {
				gameMap[i][j] = newGameMap[i][j];
			}
		}
	}
	
	int getX() { return x_coor;}
	int getY() {return y_coor;}
	
	abstract void updateGameSense(char[][] newGameMap); // get the game, map, figure out where the player is, etc
	abstract void makeMove(); // calculate the appropriate move and make it
}
