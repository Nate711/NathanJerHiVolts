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
	double cellSize;
	Image player, mho, fence;
	
	double imageSpacingFactor = .8; // the ratio of the width of the empty spaces between sprites to the width of the sprite
	
	RenderEngine(int w,int h, Image player, Image mho, Image fence) {
		width = w;
		height = h;
		cellSize = Math.min(width,height)/12.0;
	}
	/*
	RenderEngine(int w,int h) {
		width = w;
		height = h;
		cellSize = Math.min(width,height)/12;
	}
	*/
	public void setImages(Image p, Image m, Image f) {
		player = p;
		mho = m;
		fence = f;
	}
	
	void drawMho(int x, int y) {
		
	}
	void renderDeath(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0,0,width,height);
	}
	

	void renderScene(char[][] gameMap, Graphics g) {
		// gameMap is not matrix indexed, instead, the first index is the x value, the 2nd is the y value
		int sizeX = gameMap[0].length;
		int sizeY = gameMap.length;
		
		for(int i=0; i<sizeY; i++) { // i is x positions
			for(int j=0; j<sizeX; j++) { // j is y positions
				Image sprite = null;
				
				boolean empty = false;
				switch(gameMap[i][j]) {
					case 'm':
						sprite = mho;
						break;
					case 'p':
						sprite = player;
						break;
					case 'f':
						sprite = fence;
						break;
					case ' ':
						empty=true;
						break;
					default:
						break;
				}
				
				if(!empty) g.drawImage(sprite, (int)(i*cellSize), (int)(j*cellSize), 
						(int)(cellSize/(1+imageSpacingFactor)), (int)(cellSize/(1+imageSpacingFactor)), this);
				
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