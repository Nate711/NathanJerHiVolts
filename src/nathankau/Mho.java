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
		if(!alive) return;
		int x_distance;
		int x_direction;
		
		int y_distance;
		int y_direction;
		
		// option 0 = directly hor/vert
		// option 1 = diag
		// option 2 = best vert/hor
		// option 3 = worst vert/hor
		// option 4 = stay put
		
		x_direction = x_coor>=px ? -1 : 1; // x_direction = -1 if the player is on the left, 1 if on the right
		y_direction = y_coor>=py ? -1 : 1; // y_direction = -1 if the player is below, 1 if the player is above
		
		x_distance = (px-x_coor)*x_direction; // ie, x_coor = 10, px = 5, x_direction = -1    ====>  x_distance = 5
		y_distance = (py-y_coor)*y_direction;

		// check for option 0
		if(px == x_coor){  //if the mho is directly horizontal or vertical
			y_coor +=y_direction;
			return;
		}else if(py == y_coor){
			x_coor+=x_direction;
			return;
		}
		
		// check for option 1 -- the diagonal move
		boolean isDiagFenceFree = gameMap[x_coor+x_direction][y_coor+y_direction] != 'f'; // is there a fence at the diagonal?
		boolean isDiagMhoFree =  gameMap[x_coor+x_direction][y_coor+y_direction] != 'm'; // is there a mho at the diag?
		
		if(isDiagFenceFree && isDiagMhoFree) { // make the diag move if there isn't a fence
			x_coor+=x_direction;
			y_coor+=y_direction;
			return;
		}
		// invariant: the diagonal was not possible because a fence was there
		
		// check for option 2 and 3 -- the square move
		boolean isHorzGreater = x_distance >= y_distance;
		
		boolean isHorzFenceFree = gameMap[x_coor+x_direction][y_coor] != 'f';
		boolean isVertFenceFree = gameMap[x_coor][y_coor+y_direction] != 'f';
		
		boolean isHorzMhoFree = gameMap[x_coor+x_direction][y_coor] != 'm';
		boolean isVertMhoFree = gameMap[x_coor][y_coor+y_direction] != 'm';
		
		if(isHorzGreater) { // vert distance is more
			if(isHorzFenceFree && isHorzMhoFree) {
				x_coor+=x_direction;
				return;
			}
		} else { // horz distance is more
			if(isVertFenceFree && isVertMhoFree) {
				y_coor+=y_direction;
				return;
			}
		}
		
		// invariant: the diagonal was a no-go, the best square move was a no-go
		if(isVertFenceFree && isVertMhoFree) {
			y_coor+=y_direction;
			return;
		}
		if(isHorzFenceFree && isHorzMhoFree) {
			x_coor+=x_direction;
			return;
		}
		// invariant: both the diagonals and the square moves have fences -- invariant ID = 01
		// check that invariant
		if((isDiagFenceFree&&isDiagMhoFree) || (isHorzFenceFree&&isHorzMhoFree) || (isVertFenceFree&&isVertMhoFree)) {
			System.out.println("Invariant 01 was not true. Ay yaaa");
		}
		
		// time to move onto fences, oh noo
		// every single option had a fence OR a mho
		if(isDiagMhoFree) { // make the diag move if there isn't a mho
			x_coor+=x_direction;
			y_coor+=y_direction;
			return;
		}
		// check for a non-mho space, it doesn't matter which one it tries first since it's going to die
		if(isVertMhoFree) {
			y_coor+=y_direction;
			return;
		}
		if(isHorzMhoFree) {
			x_coor+=x_direction;
			return;
		}
		
		// invariant: both the diagonals and the square moves have mhos -- invariant ID = 02
		if(isDiagMhoFree || isHorzMhoFree || isVertMhoFree) {
			System.out.println("Invariant 02 was not true. Ay yaaa");
		}
		
		System.out.println("Mho Stayed Put");
		// got to stay put!!!!!
		return;
	}

}
