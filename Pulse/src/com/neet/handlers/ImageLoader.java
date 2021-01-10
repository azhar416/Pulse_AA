/**
 * Loads all images at game start.
 */

package com.neet.handlers;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage LOGO = load("/logopaslon.png");
	public static BufferedImage BG = load("/menubg.png");
	public static BufferedImage STAR = load("/star.png");
	public static BufferedImage STAROUTLINE = load("/staroutline.png");
	
	private static BufferedImage load(String s) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageLoader.class.getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
