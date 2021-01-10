/** 
 * Level select screen.
 * Clicking on a level sets the current level
 * in LevelData.
 */

package com.neet.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.entity.Bubble;
import com.neet.entity.GameButton;
import com.neet.entity.Star;
import com.neet.handlers.GameData;
import com.neet.handlers.GameStateManager;
import com.neet.handlers.ImageLoader;
import com.neet.handlers.JukeBox;
import com.neet.handlers.Keys;
import com.neet.handlers.LevelData;
import com.neet.handlers.Mouse;
import com.neet.main.Game;
import com.neet.main.GamePanel;

public class LevelSelectState extends GameState {
	
	// bg image
	private BufferedImage bg;
	
	// bubbles
	private ArrayList<Bubble> bubbles;
	private int bubbleTimer;
	
	// buttons
	private GameButton[] buttons;
	private int currentChoice;
	
	// stars
	private Star[][] stars;
	
	// font
	private Font titleFont;
	private Font font;
	
	// fading
	private int fadeInTimer;
	private int fadeInDelay;
	private int fadeOutTimer;
	private int fadeOutDelay;
	private int alpha;
	private int nextState;
	
	public LevelSelectState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		// load save file
		GameData.load();
		
		// get bg image
		bg = ImageLoader.BG;
		
		// font
		titleFont = LevelData.SC_FONT.deriveFont(46f);
		font = LevelData.SC_FONT.deriveFont(28f);
		
		// set up buttons
		buttons = new GameButton[LevelData.MAX_LEVELS];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new GameButton(50, 116 * i + 124 + 18);
			buttons[i].setFont(font);
			buttons[i].setType(GameButton.LEFT);
			String s = LevelData.getLevel(i * 3);
			s = s.substring(0, s.indexOf("   "));
			s += "  :  " + GameData.getScore(i) + " SCORE";
			buttons[i].setText(s);
			if(i >= 3) buttons[i].setActive(false);
		}
		
		// get stars
		stars = new Star[LevelData.MAX_LEVELS][3];
		int[] info = LevelData.getStars();
		for(int i = 0; i < stars.length; i++) {
			for(int j = 0; j < stars[0].length; j++) {
				if(j < info[i]) {
					stars[i][j] = new Star(480 + 40 * j, 114 * i + 125, true);
					// stars[i][j] = new Star(480 + 40 * j, 114 + 35 * i, true);
				}
				else {
					stars[i][j] = new Star(480 + 40 * j, 114 * i + 125, false);
					// stars[i][j] = new Star(480 + 40 * j, 114 + 35 * i, false);
				}
			}
		}
		
		// show mouse cursor
		Game.setCursor(Game.VISIBLE);
		
		// fade timer
		fadeInTimer = 0;
		fadeInDelay = 60;
		fadeOutTimer = -1;
		fadeOutDelay = 60;
		
		// music
		if(!JukeBox.isPlaying("menumusic")) {
			JukeBox.loop("menumusic", 3000, JukeBox.getFrames("menumusic") - 3000);
		}
		JukeBox.stop("bgmusic1");
		
		// bubbles
		bubbles = new ArrayList<Bubble>();
		bubbleTimer = 0;
		
	}
	
	public void update() {
		
		// check game input
		handleInput();
		
		// check buttons for hover
		for(int i = 0; i < buttons.length; i++) {
			if(currentChoice == i) {
				buttons[i].setHover(true);
			}
			else {
				buttons[i].setHover(false);
			}
		}
		
		// update stars
		for(int i = 0; i < stars.length; i++) {
			for(int j = 0; j < stars[0].length; j++) {
				stars[i][j].update();
			}
		}
		
		// update fade
		if(fadeInTimer >= 0) {
			fadeInTimer++;
			alpha = (int) (255.0 * fadeInTimer / fadeInDelay);
			if(fadeInTimer == fadeInDelay) {
				fadeInTimer = -1;
			}
		}
		if(fadeOutTimer >= 0) {
			fadeOutTimer++;
			alpha = (int) (255.0 * fadeOutTimer / fadeOutDelay);
			if(fadeOutTimer == fadeOutDelay) {
				gsm.setState(nextState);
			}
		}
		if(alpha < 0) alpha = 0;
		if(alpha > 255) alpha = 255;
		
		// bubbles
		bubbleTimer++;
		if(bubbleTimer == 60) {
			bubbles.add(new Bubble(Math.random() * 540 - 100, Math.random() * 100 + 480));
			bubbleTimer = 0;
		}
		for(int i = 0; i < bubbles.size(); i++) {
			if(bubbles.get(i).update()) {
				bubbles.remove(i);
				i--;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		g.drawImage(bg, 0, 0, null);
		
		// draw bubbles
		for(int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).draw(g);
		}

		// draw info
		g.setColor(Color.white);
		g.drawString("ESC TO RETURN", 10, 590);
		
		// draw title
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("L E V E L   S E L E C T", 100, 80);
		g.setStroke(LevelData.STROKE_2);
		g.drawLine(-5, 90, 800, 90);
		
		// draw buttons
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].draw(g);
		}
		
		// draw stars
		for(int i = 0; i < stars.length; i++) {
			for(int j = 0; j < stars[0].length; j++) {
				stars[i][j].draw(g);
			}
		}
		
		// draw fade
		if(fadeInTimer >= 0) {
			g.setColor(new Color(255, 255, 255, 255 - alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		if(fadeOutTimer >= 0) {
			g.setColor(new Color(255, 255, 255, alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
	}
	
	private void select() {
		if(fadeOutTimer != -1) return;
		if(currentChoice >= 0 && currentChoice < buttons.length && fadeOutTimer == -1) {
			if(!buttons[currentChoice].isActive()) return;
			//LevelData.setLevel(LevelData.getList(currentChoice * 4));
			LevelData.setLevel(LevelData.getList(currentChoice * 3 + 0));
			nextState = GameStateManager.LEVEL_INFO_STATE;
			fadeInTimer = -1;
			fadeOutTimer = 0;
			GameData.resetScore();
			JukeBox.play("menuclick");
		}
	}
	
	public void handleInput() {
		
		// pressed escape, go back to main menu
		if(Keys.isPressed(Keys.ESCAPE)) {
			nextState = GameStateManager.MENU_STATE;
			fadeInTimer = -1;
			fadeOutTimer = 0;
		}
		
		// mouse clicked, check if clicked on button
		if(Mouse.isPressed()) {
			select();
		}
		
		// check if mouse is hovering over buttons
		boolean hit = false;
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].isHovering(Mouse.x, Mouse.y)) {
				currentChoice = i;
				hit = true;
				break;
			}
		}
		if(!hit) currentChoice = -1;	
	}
}
