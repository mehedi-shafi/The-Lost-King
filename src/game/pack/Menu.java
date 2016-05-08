package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;


import game.pack.Game.STATE;

public class Menu extends MouseAdapter{	
	private Image back;
	public static boolean isPlaying = false;
	public Menu(){
		try {
			back = ImageIO.read(getClass().getResourceAsStream("/Background/background.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		
		// Mouse in MENU 
		if (Game.STAT == STATE.MENU){
			
			//play
			if (mouseOver(mx, my, 220, 100, 200, 40)){
				Game.STAT = STATE.LOADING;
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
			}
			
			//help
			
			if (mouseOver(mx, my, 220, 150, 200, 40)){
				Game.STAT = STATE.HELP;
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
			}
			
			
			//high scores
			
			if (mouseOver(mx, my, 220, 200, 200, 40)){
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
				ScoreManage.READ();
				Game.STAT = STATE.HALL;
			}
			
			
			//quit
			
			if (mouseOver(mx, my, 220, 250, 200, 40)){
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
				System.exit(0);
			}
			
			//credit
			
			if (mouseOver(mx, my, 220, 300, 200, 40)){
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
				Game.STAT = STATE.CREDIT;
			}
		}
		
		//Mouse in END
		else if (Game.STAT == STATE.END){
			if (mouseOver(mx, my, 215, 307, 150, 50)){
				AudioPlayer.getMusic("menu").pause();
				AudioPlayer.getSound("click").play();
				Game.STAT = STATE.MENU;
				Menu.isPlaying = false;
			}
		}
		
		//Mouse in game.
		else if (Game.STAT == STATE.GAME){
			
		}
				
		//Mouse in other screens i.e. -> Instruction	-> Credit
		else if (Game.STAT == STATE.CREDIT || Game.STAT == STATE.HELP || Game.STAT == STATE.HALL || Game.STAT == STATE.HELP){
			if (mouseOver (mx, my, 0, 0, Vars.display_width, Vars.display_height)){
				AudioPlayer.getSound("click").play();
				Game.STAT = STATE.MENU;
				Menu.isPlaying = false;
			}
		}
		
		else if (Game.STAT == STATE.More){
			if (mouseOver (mx, my, 0, 0, Vars.display_width, Vars.display_height)){
				AudioPlayer.getSound("click").play();
				Game.STAT = STATE.END;
				Menu.isPlaying = false;
			}
		}
		
	}
	
	public void mouseReleased (MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if (mx >= x && mx <= (x + width)){
			if (my >= y && my <= (y + height)){
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick(){
		
		
	}
	
	public void render (Graphics g){
		g.drawImage(back, 0, 0, Vars.display_width, Vars.display_height, null);
		
		Font font1 = new Font(Font.SANS_SERIF, 1, 30);
		//ont font2 = new Font ("arial", 1, 25);
		
		//play 
		g.setFont(font1);
		g.setColor (Color.BLACK);
		g.fillRect(220, 100, 200, 40);
		g.setColor (Color.white);
		g.drawString("Play", 290, 130);
		
		//help
		g.setColor (Color.BLACK);
		g.fillRect(220, 150, 200, 40);
		g.setColor (Color.white);
		g.drawString("instruction", 245, 180);
		
		//highscore
		g.setColor (Color.BLACK);
		g.fillRect(220, 200, 200, 40);
		g.setColor (Color.white);
		g.drawString("HighScore", 245, 230);
		
		//quit
		g.setColor (Color.BLACK);
		g.fillRect(220, 250, 200, 40);
		g.setColor (Color.white);
		g.drawString("Quit", 290, 280);
		
		//credit
		g.setColor (Color.BLACK);
		g.fillRect(220, 300, 200, 40);
		g.setColor (Color.white);
		g.drawString("Credit", 275, 330);
		
	}
}
