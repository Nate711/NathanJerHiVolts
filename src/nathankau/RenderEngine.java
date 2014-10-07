/* Sources
 * http://www.wikihow.com/Use-Graphics-in-a-Java-Applet#Drawing_Images_sub
 * http://paleyontology.com/AP_CS/hivolts/
 */

package nathankau;
import java.applet.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;

public class RenderEngine implements ImageObserver{
	// renderengine probably shouldn't know how to draw each gameobject
	int scale;
	Graphics g;
	
	int width,height;
	
	int sideBuffer = 60;
	int topBuffer = 60;
	int bottomBuffer = 100;
	
	int activeWidth, activeHeight;
	
	double cellSize;
	Image player, mho, fence;
	
	double imageSpacingFactor = .7; // the ratio of the width of the empty spaces between sprites to the width of the sprite
	
	int total=0,won=0;
	
	Font BIG = new Font("OCR A Extended", Font.PLAIN,50);
	Font MEDIUM = new Font("OCR A Extended", Font.PLAIN,30);
	Font SMALL = new Font("OCR A Extended", Font.PLAIN,24);
	
	Color THEORANGE = new Color(255,144,0);
	
	RenderEngine(int w,int h, Image player, Image mho, Image fence) {
		width = w;
		height = h;
		activeWidth = width-2*sideBuffer;
		activeHeight = height-topBuffer-bottomBuffer;
		
		cellSize = activeWidth/(11.0+imageSpacingFactor);
		// there's a border on the right/bottom because the image doesn't take up the entire screen, must fix
	}

	public void setImages(Image p, Image m, Image f) {
		player = p;
		mho = m;
		fence = f;
	}

	void renderDeath(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,width,height);
		
		g.setColor(THEORANGE);
		
		g.setFont(BIG);
		g.drawString("YOU DIED", width/2-120, height/2);
		
		g.setFont(MEDIUM);
		
		g.drawString("PRESS R TO RESTART", width/2-140, height/2+100);
		total++;
	}
	void renderWin(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		g.setColor(THEORANGE);
		
		g.setFont(BIG);
		
		
		g.drawString("YOU WIN", width/2-120, height/2);
		
		g.setFont(MEDIUM);
		
		
		g.drawString("PRESS R TO RESTART", width/2-140, height/2+100);
		total++;
		won++;
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
				
				if(!empty) g.drawImage(sprite, (int)(i*cellSize)+sideBuffer, (int)(j*cellSize)+topBuffer, 
						(int)(cellSize*imageSpacingFactor), (int)(cellSize*imageSpacingFactor), this);
				
				//System.out.print(gameMap[i][j] + " ");
			}
			//System.out.println();
		}
		String winsOutOf=" wins out of ";
		if(won == 1) {
			winsOutOf = " win out of ";
		}

		String gamesWon = won + winsOutOf + (total) + " games. You've won " + Math.round((double)(won*100)/(total)) + "% of games.";
		
		g.setColor(new Color(255,144,0));
		Font font = new Font("OCR A Extended", Font.PLAIN,24);
		g.setFont(font);
		g.drawString(gamesWon, sideBuffer, 800);
		
	}
	
	void setGamesWonTotalt(int won, int lost) {
		this.won = won;
		this.total = lost;
	}
	
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}