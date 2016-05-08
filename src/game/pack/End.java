package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class End {
	private Image endImage;
	public boolean sort;
	private boolean h_s = false; 
	public End(){
		try{
			endImage = ImageIO.read(getClass().getResourceAsStream("/Background/game_over.png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		sort = false;
	}
	
	public void tick(){		
		if (sort){
			System.out.println("it works\n" + STATS.SCORE + " " + STATS.current_player);
			ScoreManage.SORTING();
			ScoreManage.Update();
			STATS.CURRENT_HEALTH = 125;
			sort = false;
		}
		
		
		if (STATS.SCORE >= Integer.parseInt(STATS.Scores[9])){
			h_s = true;
		}
		else
			h_s = false;
	}
	
	public void render(Graphics g){
		
		
		g.drawImage(endImage, 0, 0, Vars.display_width,  Vars.display_height, null);
		Font font = new Font ("arial", Font.BOLD, 25);		
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Score :                 " + Integer.toString(STATS.SCORE), 200, 200);
		g.drawString("Hero Level 	:   			 " + Integer.toString(STATS.HERO_LEVEL), 200, 230);
		g.drawString("Stage Reached : 	" + Integer.toString(STATS.STAGE), 200, 260);
		
		//Completion
		if (STATS.end){
			g.drawString("You Have Completed the Game", 120, 300);
		}
		
		//back button		
		Font font2 = new Font("arial", 1, 50);
		g.setFont(font2);
		g.setColor(Color.white);
		g.drawString("Back", 230, 350);
		g.drawRect (215, 307, 150, 50);
		
		if (h_s){
			g.setColor(Color.red);
			g.drawString("You Are in Hall of Bests", 30, Vars.display_height-50);
		}
	}
}
