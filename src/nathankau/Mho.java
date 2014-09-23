package nathankau;

/*
 * Rules:
 * if(mho is directly vertical or horizontal of player) move one square directly towards player
 * else{
 * 		if(horizontal and vertical distance are equal) move diagonal to empty square
 * 		else if(horizontal distance > vertical distance) move horizontally to empty square
 * 		else if(vertical > horizontal) move vertically to empty
 * }
 */
public class Mho extends GameObject {
	
	int px,py;
	
	Mho(int x, int y) {
		super(x,y);
	}
	public void updateGameSense(char[][] newGameMap, Player p) {
		updateGameMap(newGameMap);
		px = p.getX();
		py = p.getY();
	}
	
	public void makeMove() {
		int x_distance;
		int y_distance;
		
		if(x_coor - px > 0) x_distance = x_coor = px;
		else x_distance = px - x_coor;
		
		if(y_coor - py > 0) y_distance = y_coor = py;
		else y_distance = py - y_coor;
		
		
		if(px == x_coor){
			if(y_coor > py) y_coor--;
			else if(y_coor < py) y_coor++;
		}else if(py == y_coor){
			if(x_coor > px) x_coor--;
			else if(x_coor < px) x_coor++;
		}else{
			if(x_distance > y_distance){
				if(y_coor > py) y_coor--;
				else if(y_coor < py) y_coor++;
			}
			else if(y_distance > x_distance){
				if(x_coor > px) x_coor--;
				else if(x_coor < px) x_coor++;
			}
			else{
				if(x_coor > px) x_coor--;
				else if(x_coor < px) x_coor++;
				
				if(y_coor > py) y_coor--;
				else if(y_coor < py) y_coor++;
			}
		}
	}
}
