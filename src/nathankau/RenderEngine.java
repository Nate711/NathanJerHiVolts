/* Sources
 * http://www.wikihow.com/Use-Graphics-in-a-Java-Applet#Drawing_Images_sub
 * http://paleyontology.com/AP_CS/hivolts/
 */

package nathankau;
import java.applet.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class RenderEngine implements ImageObserver{
	// renderengine probably shouldn't know how to draw each gameobject
	int scale;
	Graphics g;
	
	int width,height;
	int cellSize;
	Image player, mho, fence;
	RenderEngine(int w,int h, Image player, Image mho, Image fence) {
		width = w;
		height = h;
		cellSize = Math.min(width,height)/12;
	}
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
	
	void renderPlayer(int x, int y, Graphics g) {
		g.drawImage(player,x*cellSize,y*cellSize,cellSize-2,cellSize-2,this);
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
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}