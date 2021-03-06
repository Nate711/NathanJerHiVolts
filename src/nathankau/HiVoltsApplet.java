package nathankau;

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
	         //System.out.println(c);
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