package game.pack;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class Welcome {
	private Image back;
	private long timer;
	
	public Welcome(){
		try {
			back = ImageIO.read(getClass().getResourceAsStream("/Background/welcome.jpg"));
		}
		catch (Exception e){
			System.out.println("error from welcome");
		}
		timer = System.currentTimeMillis();
	}
	
	public void tick(){
		if (System.currentTimeMillis() - timer >= 5000){
			Game.STAT = STATE.MENU;
		}
	}
	
	public void render (Graphics g){
		g.drawImage(back, 0,  0 , Vars.display_width, Vars.display_height, null);
	}
}
