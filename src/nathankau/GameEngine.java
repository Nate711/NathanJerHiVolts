package nathankau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class GameEngine {
	Player p1;
	ArrayList<Fence> staticFences = new ArrayList<Fence>();
	ArrayList<Fence> activeFences = new ArrayList<Fence>();
	ArrayList<Mho> mhos = new ArrayList<Mho>();
	
	int activeBoardSize = 10;
	int boardSize = activeBoardSize+2;
	int numMhos = 10;
	int numActiveFences=20;
	
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
		staticFences.clear();
		activeFences.clear();
		mhos.clear();
		init();
		gameOn = true;
	}
	
	public void gameOver() {
		gameOn = false;
		System.out.println("YOU DIED");
	}
	public void turn(char key) {
		char oldMap[][] = getMap();
		p1.makeMove(key);
		if(oldMap[p1.getX()][p1.getY()] == 'f' || oldMap[p1.getX()][p1.getY()] == 'm') {
			p1.die();
			gameOver();
		}
		
		for(Mho mho: mhos) {
			oldMap = getMap();
			mho.updateGameSense(oldMap);
			mho.makeMove();
			
			
			if(oldMap[mho.getX()][mho.getY()] == 'p') {
				p1.die();
				gameOver();
			}
			
			if(oldMap[mho.getX()][mho.getY()] == 'f') {
				mho.die();
			}
		}
		// mhos make moves
		// who dies?
	}
	
	
	public void init() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i=0; i<activeBoardSize; i++) {
			for(int j=0; j<activeBoardSize;j++) {
				positions.add(i*activeBoardSize+j);
			}
		}
		Collections.shuffle(positions);
		
		
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
