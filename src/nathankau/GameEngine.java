package nathankau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GameEngine {
	Player p1;
	ArrayList<Fence> staticFences = new ArrayList<Fence>();
	ArrayList<Fence> activeFences = new ArrayList<Fence>();
	ArrayList<Mho> mhos = new ArrayList<Mho>();
	
	int activeBoardSize = 10;
	int boardSize = activeBoardSize+2;
	int numMhos = 10;
	int numActiveFences=10;
	
	int width,height;

	RenderEngine hivoltsRenderer;
	
	Image player,fence,mho;
	
	boolean gameOn = true;
	
	GameEngine(int w,int h) {
		width=w;
		height=h;
		hivoltsRenderer = new RenderEngine(width,height,player,mho,fence);
	}
	
	public void setImages(Image p, Image m, Image f) {
		hivoltsRenderer.setImages(p, m, f);
	}
	public void restart() {
		
		init();
		gameOn = true;
	}
	
	public void gameOver() {
		gameOn = false;
		System.out.println("YOU DIED");
	}
	public void turn(char key) {
		char oldMap[][] = getMap();
		
		if(key == 'j') {
			boolean foundSpot = false;
			while(!foundSpot) {
				int x = (int)(Math.random()*activeBoardSize);
				int y = (int)(Math.random()*activeBoardSize);
				if(oldMap[x][y] == ' ' || oldMap[x][y] == 'm') {
					p1.jump(x, y);
					foundSpot = true;
				}
			}
		}
		else {
			p1.makeMove(key);
		}
		
		
		if(oldMap[p1.getX()][p1.getY()] == 'f' || oldMap[p1.getX()][p1.getY()] == 'm') {
			p1.die();
			gameOver();
			return;
		}
		
		for(Iterator<Mho> iterator = mhos.iterator(); iterator.hasNext();) {
			oldMap = getMap();
			
			Mho mho = iterator.next();
			mho.updateGameSense(oldMap, p1);
			mho.makeMove();
			
			// Kill the player if the mho moves onto them
			if(mho.getX() == p1.getX() && mho.getY() == p1.getY()) {
				p1.die();
				gameOver();
				return;
			}
			
			
			// Kill the Mho if it's on a fence
			if(oldMap[mho.getX()][mho.getY()] == 'f') {
				mho.die();
				iterator.remove();
			}
			
		}
		// mhos make moves
		// who dies?
	}
	
	public void debugInit() {
		staticFences.clear();
		activeFences.clear();
		mhos.clear();
		
		p1 = new Player(0,0);
		activeFences.add(new Fence(4,0));
		mhos.add(new Mho(6,0));
	}
	
	public void init() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i=0; i<activeBoardSize; i++) {
			for(int j=0; j<activeBoardSize;j++) {
				positions.add(i*activeBoardSize+j);
			}
		}
		Collections.shuffle(positions);
		
		staticFences.clear();
		activeFences.clear();
		mhos.clear();
		
		int i=0;
		for(; i<numMhos;i++) {
			mhos.add(new Mho(positions.get(i)/activeBoardSize + 1, positions.get(i)%activeBoardSize + 1));
		}
		
		int i0 = i;
		for(; i<numActiveFences+i0; i++) {
			activeFences.add(new Fence(positions.get(i)/activeBoardSize + 1, positions.get(i)%activeBoardSize + 1));
			
		}
		
		for(int j=0; j<boardSize-1;j ++) {
			staticFences.add(new Fence(j,0));
			staticFences.add(new Fence(j+1,boardSize-1));
			staticFences.add(new Fence(0,j+1));
			staticFences.add(new Fence(boardSize-1,j));
		}
		p1 = new Player(positions.get(i)/activeBoardSize+1, positions.get(i)%activeBoardSize+1);
	}
	
	public void render(Graphics g) {
		if(gameOn) hivoltsRenderer.renderScene(getMap(),g);
		else {
			hivoltsRenderer.renderDeath(g);
		}
	}
	
	char[][] getMap() {
		char[][] map = new char[activeBoardSize+2][activeBoardSize+2];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = ' ';
			}
		}
		
		for(Fence f:staticFences) {
			map[f.getX()][f.getY()] = 'f';
		}
		for(Fence f:activeFences) {
			map[f.getX()][f.getY()] = 'f';
		}
		for(Mho m:mhos) {
			map[m.getX()][m.getY()] = 'm';
		}
		map[p1.getX()][p1.getY()] = 'p';
		
		return map;
	}
}
