package nathankau;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HiVoltsApplet extends Applet implements KeyListener {
	GameEngine hivolts;
	
	public void init() {
		setSize(600,600);
		hivolts = new GameEngine(600,600);
		addKeyListener(this);
		hivolts.init();
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
	      }
	      
	      hivolts.turn(c);
	      
	      repaint();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
}
