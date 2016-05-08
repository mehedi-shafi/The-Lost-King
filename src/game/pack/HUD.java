package game.pack;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;


public class HUD {
	private double h_p;
	private double greenValue = 255;
	public static double h_bar = 0;	//haki bar controller
	
	public void tick(){
		h_p = (STATS.CURRENT_HEALTH*100/STATS.HEALTH);
		h_p = Game.clamp (h_p, 0, 100);
		greenValue = Game.clamp( greenValue, 0, 255);
		greenValue = h_p * 1.5;
		
		if(h_bar<=100){
			h_bar += .05;
		}
	}
	public void render(Graphics g){
		Font font = new Font("arial", Font.BOLD, 12);
		g.setFont(font);
		
		//Player Health Bar -> Left Top
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, 100, 20);
		g.setColor(new Color (75, (int) greenValue, 0));
		g.fillRect(10, 10, (int) h_p, 20);
		g.setColor(Color.BLACK);
		g.drawString(Double.toString(STATS.CURRENT_HEALTH) + " / " + Double.toString(STATS.HEALTH), 22, 23);
		
		
		//XP  && Levels
		g.setColor(Color.RED);
		g.drawString("XP : " + Integer.toString(STATS.XP), 10, 50);
		g.drawString("Level : " + Integer.toString(STATS.HERO_LEVEL), 10, 65);
		
		
		//SCORE
		Font score_font = new Font ("arial", Font.BOLD, 20);
		g.setFont(score_font);
		g.drawString(Integer.toString(STATS.SCORE), Vars.display_width/2-20, 20);
		
		//FPS		
		g.setFont(font);
		g.drawString(Integer.toString(Game.frames), Vars.display_width-50, 20);
		
		//haki bar
		g.setColor(Color.BLACK);
		g.drawRect(20, Vars.display_height-50, 100, 10);
		g.setColor(Color.BLUE);
		g.fillRect(20, Vars.display_height-50, (int) h_bar, 10);
	}
}
