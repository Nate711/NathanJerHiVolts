package nathankau;
import java.applet.*;
import java.awt.*;

public class RenderEngine {
	// renderengine probably shouldn't know how to draw each gameobject
	int scale;
	Graphics g;
	
	int width,height;
	int cellSize;
	RenderEngine(int w,int h) {
		width = w;
		height = h;
		cellSize = Math.min(width,height)/12;
	}
	void drawMho(int x, int y) {
		
	}
	void renderDeath(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,width,height);
	}
	void renderScene(char[][] gameMap, Graphics g) {
		// gameMap is not matrix indexed, instead, the first index is the x value, the 2nd is the y value
		int sizeX = gameMap[0].length;
		int sizeY = gameMap.length;
		
		for(int i=0; i<sizeY; i++) { // i is x positions
			for(int j=0; j<sizeX; j++) { // j is y positions
				Color c;
				switch(gameMap[i][j]) {
					case 'm':
						c=Color.red;
						break;
					case 'p':
						c=Color.white;
						break;
					case 'f':
						c=Color.black;
						break;
					case ' ':
						c=Color.gray;
						break;
					default:
						c = Color.yellow;
						break;
				}
				g.setColor(c);
				g.fillRect(i*cellSize, j*cellSize, cellSize-1, cellSize-1);
				
				System.out.print(gameMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}