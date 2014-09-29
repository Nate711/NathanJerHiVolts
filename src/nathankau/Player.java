package nathankau;

public class Player extends GameObject {
	Player(int x, int y) {
		super(x,y);
	}
	public void updateGameSense(char[][] newGameMap) {
		updateGameMap(newGameMap);
	}
	public void die() {
		alive = false;
	}
	
	public void jump(int x, int y) {
		x_coor = x;
		y_coor = y;
	}
	
	public void makeMove() {}
	public void makeMove(char c) {
		switch(c) {
		case 'a':
			x_coor -= 1;
			break;
		case 'd':
			x_coor += 1;
			break;
		case 'w':
			y_coor -= 1;
			break;
		case 'x':
			y_coor += 1;
			break;
		case 's':
			break;
		case 'q':
			y_coor -= 1;
			x_coor -=1;
			break;
		case 'e':
			y_coor -= 1;
			x_coor += 1;
			break;
		case 'c':
			x_coor += 1;
			y_coor += 1;
			break;
		case 'z':
			x_coor -= 1;
			y_coor += 1;
			break;
		default:
			break;
		}
	}
}
