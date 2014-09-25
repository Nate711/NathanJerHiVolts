package nathankau;

public abstract class GameObject {
	int x_coor,y_coor;
	boolean alive = true;
	
	char gameMap[][] = new char[12][12]; // 'p' = player, 'm' = mho, 'f' = fence, 'e' = empty
	
	GameObject(int x, int y) {
		x_coor = x;
		y_coor = y;
	}
	void die() {
		alive = false;
	}
	void makeMove() {}
	void updateGameMap(char[][] newGameMap) {
		for(int i=0; i<gameMap.length; i++) {
			for(int j=0; j<gameMap[0].length; j++) {
				gameMap[i][j] = newGameMap[i][j];
			}
		}
	}
	
	int getX() { return x_coor;}
	int getY() {return y_coor;}	
}
