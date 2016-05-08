package game.pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class More {
	public void tick (){
		
	}
	
	public void render (Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, Vars.display_width, Vars.display_height);
		
		Font font = new Font ("arial", Font.BOLD, 25);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		g.drawString("More to come but the game ends here for now", 10, 100);
		g.drawString("Thanks for Playing " + STATS.current_player, 10, 180);
		g.drawString("click anywhere to continue", 50, 250);
	}
}
