/**
 * This class contains all the level information
 * and which level is currently being played.
 * Level information includes:
 * 	- player start position
 * 	- goal position
 * 	- number of hits allowed
 * 	- any GameObjects
 * 	- level title
 * 	- description of the level
 */

package com.neet.handlers;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.neet.entity.Bouncer;
import com.neet.entity.Goal;
import com.neet.entity.Hole;
import com.neet.entity.Player;
import com.neet.entity.Spawner;

public class LevelData {
	
	private static String currentLevel;
	
	private static HashMap<String, Point> playerList;
	private static HashMap<String, Point> goalList;
	private static HashMap<String, Integer> limitList;
	private static HashMap<String, ArrayList<Bouncer>> bouncerList;
	private static HashMap<String, ArrayList<Hole>> holeList;
	private static HashMap<String, ArrayList<Spawner>> spawnerList;
	
	private static ArrayList<String> levelList;
	private static HashMap<String, String[]> levelDescriptions;
	private static int levelIndex;
	
	public static final int MAX_LEVELS = 3;
	public static final String LEVEL1_1 = "EASY   1";
	public static final String LEVEL1_2 = "EASY   2";
	public static final String LEVEL1_3 = "EASY   3";
	public static final String LEVEL2_1 = "MEDIUM   1";
	public static final String LEVEL2_2 = "MEDIUM   2";
	public static final String LEVEL2_3 = "MEDIUM   3";
	public static final String LEVEL3_1 = "HARD   1";
	public static final String LEVEL3_2 = "HARD   2";
	public static final String LEVEL3_3 = "HARD   3";
	
	public static final BasicStroke STROKE_1 = new BasicStroke(1);
	public static final BasicStroke STROKE_2 = new BasicStroke(2);
	public static final BasicStroke STROKE_3 = new BasicStroke(3);
	
	public static Font SC_FONT;
	public static Font LEVEL_INFO_FONT;
	
	public static void init() {
		
		try {
			SC_FONT = Font.createFont(
					Font.TRUETYPE_FONT,
					LevelData.class.getResourceAsStream("/fonts/Ustulo.ttf"));
			LEVEL_INFO_FONT = new Font("Arial", Font.BOLD, 25);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		levelList = new ArrayList<String>();
		levelList.add(LEVEL1_1);
		levelList.add(LEVEL1_2);
		levelList.add(LEVEL1_3);
		levelList.add(LEVEL2_1);
		levelList.add(LEVEL2_2);
		levelList.add(LEVEL2_3);
		levelList.add(LEVEL3_1);
		levelList.add(LEVEL3_2);
		levelList.add(LEVEL3_3);
		
		playerList = new HashMap<String, Point>();
		goalList = new HashMap<String, Point>();
		limitList = new HashMap<String, Integer>();
		bouncerList = new HashMap<String, ArrayList<Bouncer>>();
		holeList = new HashMap<String, ArrayList<Hole>>();
		spawnerList = new HashMap<String, ArrayList<Spawner>>();
		levelDescriptions = new HashMap<String, String[]>();
		
		ArrayList<Bouncer> bouncers;
		ArrayList<Hole> holes;
		ArrayList<Spawner> spawners;
		String[] descriptions;
		
		// easy 1
		playerList.put(LEVEL1_1, new Point(100, 100));
		goalList.put(LEVEL1_1, new Point(500, 380));
		limitList.put(LEVEL1_1, 14);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(100, 400, 200, 200));
		bouncers.add(new Bouncer(240, 100, 120, 120));
		bouncers.add(new Bouncer(340, 340, 170, 170));
		bouncers.add(new Bouncer(470, 180, 140, 140));
		bouncers.add(new Bouncer(770, 50, 300, 300));
		bouncers.add(new Bouncer(790, 590, 500, 500));
		bouncerList.put(LEVEL1_1, bouncers);
		
		// easy 2
		playerList.put(LEVEL1_2, new Point(80, 80));
		goalList.put(LEVEL1_2, new Point(550, 410));
		limitList.put(LEVEL1_2, 23);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				bouncers.add(new Bouncer(160 + 100 * i, 160 + 160 * j, 70, 70));
			}
		}
		bouncers.add(new Bouncer(60, 160, 70, 70));
		bouncers.add(new Bouncer(760, 320, 70, 70));
		bouncers.add(new Bouncer(160, 420, 70, 70));
		bouncerList.put(LEVEL1_2, bouncers);
		
		// easy 3
		playerList.put(LEVEL1_3, new Point(70, 100));
		goalList.put(LEVEL1_3, new Point(500, 400));
		limitList.put(LEVEL1_3, 9);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(80, 280, 150, 150));
		bouncers.add(new Bouncer(270, 400, 130, 130));
		bouncers.add(new Bouncer(400, 80, 150, 150));
		bouncers.add(new Bouncer(560, 280, 150, 150));
		bouncers.add(new Bouncer(700, 30, 400, 400));
		bouncerList.put(LEVEL1_3, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(200, 100, 150, 150));
		holes.add(new Hole(340, 270, 150, 150));
		holes.add(new Hole(270, 550, 300, 300));
		holes.add(new Hole(730, 570, 450, 450));
		holeList.put(LEVEL1_3, holes);
		
		
		// medium 1
		playerList.put(LEVEL2_1, new Point(105, 112));
		goalList.put(LEVEL2_1, new Point(416, 240));
		limitList.put(LEVEL2_1, 24);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 7; j++) {
				if((j == 1 && i > 0 && i < 9) ||
					(i == 8 && j > 0 && j < 6) ||
					(j == 5 && i > 0 && i < 9) ||
					(i == 1 && j == 4) ||
					(j == 3 && i > 0 && i < 7) ||
					(j == 0) ||
					(j == 1)) continue;
				bouncers.add(new Bouncer(32 + 64 * i, 48 + 64 * j, 64, 64));
			}
		}
		bouncerList.put(LEVEL2_1, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(730, 20, 400, 400));
		holeList.put(LEVEL2_1, holes);
		
		
		// medium 2
		playerList.put(LEVEL2_2, new Point(560, 80));
		goalList.put(LEVEL2_2, new Point(560, 400));
		limitList.put(LEVEL2_2, 20);
		descriptions = new String[1];
		descriptions[0] = "- timed: 15 sec";
		levelDescriptions.put(LEVEL2_2, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 8; i++) {
			bouncers.add(new Bouncer(760 - 80 * i, 240, 80, 80));
		}
		bouncers.add(new Bouncer(200, 320, 80, 80));
		bouncers.add(new Bouncer(200, 400, 80, 80));
		bouncers.add(new Bouncer(320, 240, 20, 20, 3, 1));
		bouncers.add(new Bouncer(320, 240, 20, 20, -1, -3));
		bouncers.add(new Bouncer(320, 240, 20, 20, 2, -2));
		bouncers.add(new Bouncer(320, 240, 20, 20, -2, 2));
		bouncerList.put(LEVEL2_2, bouncers);


		// medium 3
		playerList.put(LEVEL2_3, new Point(100, 100));
		goalList.put(LEVEL2_3, new Point(560, 400));
		limitList.put(LEVEL2_3, 10);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(80, 240, 100, 100));
		bouncers.add(new Bouncer(210, 60, 100, 100));
		bouncers.add(new Bouncer(330, 100, 100, 100));
		bouncers.add(new Bouncer(270, 300, 100, 100));
		bouncers.add(new Bouncer(410, 390, 130, 120));
		bouncers.add(new Bouncer(550, 250, 130, 120));
		bouncers.add(new Bouncer(130, 550, 400, 400));
		bouncerList.put(LEVEL2_3, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(160, 140, 100, 100));
		holes.add(new Hole(300, 180, 100, 100));
		holes.add(new Hole(640, 480, 250, 250));
		holes.add(new Hole(600, 100, 300, 300));
		holeList.put(LEVEL2_3, holes);


		// hard 1
		playerList.put(LEVEL3_1, new Point(80, 80));
		goalList.put(LEVEL3_1, new Point(560, 400));
		limitList.put(LEVEL3_1, 20);
		descriptions = new String[2];
		descriptions[0] = "- pull down";
		descriptions[1] = "- timed: 30 sec";
		levelDescriptions.put(LEVEL3_1, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 1; i < 6; i++) {
			for(int j = 0; j < 12; j++) {
				if((i + j) % 2 == 0) continue;
				bouncers.add(new Bouncer(80 * j, 80 * i, 30, 30));
			}
		}
		bouncerList.put(LEVEL3_1, bouncers);
		holes = new ArrayList<Hole>();
		for(int i = 0; i < 3; i++) {
			holes.add(new Hole(80 + 160 * i, 400, 130, 130));
		}
		holes.add(new Hole(80 + 640, 400, 130, 130));
		holeList.put(LEVEL3_1, holes);


		// hard 2
		playerList.put(LEVEL3_2, new Point(80, 300));
		goalList.put(LEVEL3_2, new Point(720, 300));
		limitList.put(LEVEL3_2, 20);
		spawners = new ArrayList<Spawner>();
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(400, 300, 100, 100));
		bouncerList.put(LEVEL3_2, bouncers);
		spawners.add(new Spawner(80, 80, 80, 80, 60, 2));
		spawners.add(new Spawner(80, 520, 80, 80, 60, 2));
		spawners.add(new Spawner(720, 80, 80, 80, 60, 2));
		spawners.add(new Spawner(720, 520, 80, 80, 60, 2));
		spawnerList.put(LEVEL3_2, spawners);

		
		// hard 3
		playerList.put(LEVEL3_3, new Point(150, 300));
		goalList.put(LEVEL3_3, new Point(700, 300));
		limitList.put(LEVEL3_3, 1);
		descriptions = new String[3];
		descriptions[0] = "- frictionless";
		descriptions[1] = "- timed: 15 sec";
		descriptions[2] = "- one chance";
		levelDescriptions.put(LEVEL3_3, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(400, 300, 100, 100));
		bouncerList.put(LEVEL3_3, bouncers);
		
	}
	
	public static void setLevel(String s) {
		currentLevel = s;
		for(int i = 0; i < levelList.size(); i++) {
			if(levelList.get(i).equals(s)) {
				levelIndex = i;
				break;
			}
		}
	}
	
	public static String getLevel() {
		return currentLevel;
	}
	
	public static String getLevel(int i) {
		if(i >= levelList.size()) return "--   ";
		return levelList.get(i);
	}
	
	public static int getLevelIndex() {
		for(int i = 0; i < levelList.size(); i++) {
			if(levelList.get(i).equals(currentLevel)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void nextLevel() {
		if(levelIndex < levelList.size() - 1) {
			levelIndex++;
		}
		currentLevel = levelList.get(levelIndex);
	}
	
	public static String getList(int i) {
		return levelList.get(i);
	}
	
	public static ArrayList<Bouncer> getBouncers() {
		if(bouncerList.get(currentLevel) == null) return new ArrayList<Bouncer>();
		return bouncerList.get(currentLevel);
	}
	
	public static ArrayList<Hole> getHoles() {
		if(holeList.get(currentLevel) == null) return new ArrayList<Hole>();
		return holeList.get(currentLevel);
	}
	
	public static ArrayList<Spawner> getSpawners() {
		if(spawnerList.get(currentLevel) == null) return new ArrayList<Spawner>();
		return spawnerList.get(currentLevel);
	}
	
	public static String[] getDescription() {
		if(levelDescriptions.get(currentLevel) == null) return new String[0];
		return levelDescriptions.get(currentLevel);
	}
	
	public static int getLimit() {
		return limitList.get(currentLevel);
	}
	
	public static void setPlayer(Player player) {
		Point p = playerList.get(currentLevel);
		player.setPosition(p.x, p.y);
		if(currentLevel.equals(LEVEL3_3)) {
			player.setStopSpeed(1);
		}
		if(currentLevel.equals(LEVEL2_1)) {
			player.setDimensions(40, 40);
		}
		if(currentLevel.equals(LEVEL3_1)) {
			player.setPull(0, 0.04);
		}
	}
	
	public static void setGoal(Goal goal) {
		Point p = goalList.get(currentLevel);
		goal.setPosition(p.x, p.y);
	}
	
	public static int getTime() {
		if(currentLevel.equals(LEVEL3_3)) return 60 * 15;
		if(currentLevel.equals(LEVEL2_2)) return 60 * 15;
		if(currentLevel.equals(LEVEL3_1)) return 60 * 30;
		return -1;
	}
	
	public static int[] getStars() {
		int[] stars = new int[MAX_LEVELS];
		int[] star3 = new int[] {22, 14, 17, 15, 25, 14, 16, 100};
		int[] star2 = new int[] {15, 11, 11, 10, 18, 10, 12, 100};
		int[] star1 = new int[] {8, 6, 6, 5, 10, 5, 6, 100};
		for(int i = 0; i < MAX_LEVELS; i++) {
			int score = GameData.getScore(i);
			if(score >= star3[i]) {
				stars[i] = 3;
			}
			else if(score >= star2[i]) {
				stars[i] = 2;
			}
			else if(score >= star1[i]) {
				stars[i] = 1;
			}
			else {
				stars[i] = 0;
			}
		}
		return stars;
	}
	
}