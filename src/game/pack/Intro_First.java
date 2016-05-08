package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class Intro_First {
	private Image back;
	public static long timer;
	
	public Intro_First(){
		try{
			back = ImageIO.read(getClass().getResourceAsStream("/Background/intro_one.jpg"));
		}
		catch (Exception e){
			System.out.println("Error in intro 1 image loading");
		}
	}
	
	public void tick(){
		if (System.currentTimeMillis() - timer >= 9000){
			Game.STAT = STATE.GAME;
		}
	}
	
	public void render (Graphics g){
		
		g.drawImage(back, 0, 0, Vars.display_width, Vars.display_height, null);
		
		Font font = new Font ("arial", Font.BOLD, 30);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		if (System.currentTimeMillis() - timer <= 5000){
			g.drawString("Welcome to project :: ", 150, 150);
			g.drawString("The Lost KING", 190, 180);
		}
		else if (System.currentTimeMillis() - timer >5000){
			g.drawString("STAGE ONE", 130, Vars.display_height/2-50);
		}
	}
}
