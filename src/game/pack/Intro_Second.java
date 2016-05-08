package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class Intro_Second {
	private Image back;
	public static long timer;
	
	public Intro_Second(){
		try{
			back = ImageIO.read(getClass().getResourceAsStream("/Background/intro_two.jpg"));
		}
		catch (Exception e){
			System.out.println("error in intro two loading");
		}
	}
	
	public void tick(){
		if (System.currentTimeMillis()-timer > 5000){
			Game.STAT = STATE.GAME;
		}
	}
	
	public void render (Graphics g){
		g.drawImage(back, 0, 0, Vars.display_width, Vars.display_height, null);
		Font font = new Font ("arial", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("STAGE TWO", 200, Vars.display_height/2-80);
	}
}
