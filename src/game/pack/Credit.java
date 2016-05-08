package game.pack;



import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Credit {
	
	private Image img;

	public Credit(){
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/Background/credit.jpg"));
						
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void tick (){
		
	}
	
	public void render (Graphics g){
		g.drawImage(img, 0, 0, Vars.display_width, Vars.display_height, null);
		
	}
}
