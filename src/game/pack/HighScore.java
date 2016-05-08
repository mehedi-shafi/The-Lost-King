package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class HighScore {
	private Image background;
	private int a = 100;
	
	public HighScore(){
		try{
			background = ImageIO.read(getClass().getResourceAsStream("/Background/highscore_back.jpg"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		g.drawImage(background, 0, 0, Vars.display_width, Vars.display_height, null);
		Font font1 = new Font ("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("Highscores", Vars.display_width/2-120, 50);
		
		Font font = new Font ("arial", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.RED);
		
		for (int i = 1; i<11; i++){
			g.drawString(("1. " + STATS.Names[i-1] + "        " + STATS.Scores[i-1]), 30, a);
			a+=30;
			if (i == 10)
				a = 100;
		}
	}
}
