package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Help {
	public Help(){
		
	}
	
	public void tick(){
		
	}
	
	public void render (Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, Vars.display_width, Vars.display_height);
		
		Font font = new Font ("arial", Font.BOLD, 30);
		g.setColor(Color.red);
		g.setFont(font);
		
		g.drawString("Instructions", Vars.display_width/2-100, 50);
		g.drawString("W  :  airpunch", 30, 100);
		g.drawString("q  :  haki", 30, 140);
		g.drawString("e  :  gear 2nd", 30, 180);
		g.drawString("a  :  move left", 30, 220);
		g.drawString("d  :  move right", 30, 260);
		g.drawString("space  :  jump", 30, 300);
				
	}
}
