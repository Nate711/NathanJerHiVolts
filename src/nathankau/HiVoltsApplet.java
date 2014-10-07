package nathankau;

/* Nathan Kau, Jeremy Samos
 * Period 6
 * 
 * This program recreates the 1970s arcade game HiVolts using authentic graphics, and includes the
 * essential elements of the game, which include the player, mhos, and fences.
 * 
 * The program consists of seven classes: HiVoltsApplet.java, GameObject.java, GameEngine.java,
 * Mho.java, Player.java, Fence.java, and RenderEngine.java. The program also uses three image files 
 * for the game sprites: fence.png, mho.png, and player.png.
 * 
 * GameObject.java - This class is the superclass for all game objects, which include the Player, the Mhos, 
 * 					 and the Fences. The class contains the methods die(), makeMove(), updateGameMap(),
 * 					 getX(), and getY(), all of which are inherited by the subclasses.
 * 
 * Player.java - This class is a subclass of GameObject and is responsible for handling the movement and 
 * 			     the state (alive or dead) of the player. The class uses a switch statement to determine
 *               the key inputs that move the player in a certain direction.
 * 
 * Fence.java - The Fence class represents each instance of a fence. The only component of this class
 * 			    is the constructor, in which the x and y coordinates are defined for the fence.
 * 
 * Mho.java -  The Mho class is responsible for handling the death and, most importantly, the behavior of 
 * 			   each Mho. This class is where the rules of the game that determine the next move for
 * 		       each Mho are defined. 
 * 
 * GameEnigine.java -  This class is where the game board is initialized and where the actions for each
 * 					   turn are defined. In the turn() method, the player's move is handled, as well as 
 * 					   each Mho's movement. Therefore, this method also determines whether a Mho or the 
 * 					   the player is killed or survives each turn.
 * 
 * RenderEngine.java - This class handles the drawing of the game board and all of the sprites. The class
 * 					   implements the interface ImageObserver, which is required in order to draw image
 * 					   files onto the screen.
 * 
 * 
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class HiVoltsApplet extends Applet implements KeyListener {
	GameEngine hivolts;
	
	Image mho;
	Image player;
	Image fence;
	
	public void init() {
		try {
			mho = ImageIO.read(new URL(getCodeBase(), "nathankau\\mho.png"));
			player = ImageIO.read(new URL(getCodeBase(), "nathankau\\player.png"));
			fence = ImageIO.read(new URL(getCodeBase(), "nathankau\\fence.png"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(800,950);
		
		
		setBackground(Color.black);
		hivolts = new GameEngine(getWidth(),getHeight());
		hivolts.setImages(player,mho,fence);
		
		addKeyListener(this);
		hivolts.init();
		//hivolts.debugInit();
	}
	public void paint(Graphics gr) {
		hivolts.render(gr);

	}
	
	public void keyTyped( KeyEvent e ) {
	      char c = e.getKeyChar();
	      if ( c != KeyEvent.CHAR_UNDEFINED ) {
	         e.consume();
	         System.out.println(c);
	      }

	      if (c == 'r') {
	    	  hivolts.restart();

	    	  repaint();
	      }
	      
	      String moves = "qweasdzxcj";
	      
	      if(moves.indexOf(c) == -1) {
	    	  return;
	      }
	  
	      
	      hivolts.turn(c);
	      
	      repaint();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
}