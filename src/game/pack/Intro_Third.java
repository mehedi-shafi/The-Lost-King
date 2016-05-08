package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class Intro_Third {
	private Image back;
	public static long timer;
	
	public Intro_Third(){
		try{
			back = ImageIO.read(getClass().getResourceAsStream("/Background/intro_three.jpg"));
		}
		catch(Exception e){
			System.out.println("error in intro 3 loading");
		}
	}
	
	public void tick(){
		if (System.currentTimeMillis() - timer >= 4000){
			Game.STAT = STATE.GAME;
		}
	}
	
	public void render (Graphics g){
		g.drawImage(back, 0, 0, Vars.display_width, Vars.display_height, null);
		Font font = new Font ("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Final Show-Down", 100, Vars.display_height/2+30);
	}
}
