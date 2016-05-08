package game.pack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class HealthPotion extends GameObject{
	private Image healthPotion;

	
	public HealthPotion(double x, double y, ID id) {
		super(x, y, id);
		try {
			healthPotion = ImageIO.read(getClass().getResourceAsStream("/other/healthpotion.png"));
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	

	public void tick() {
		
	}


	public void render(Graphics g) {
		g.drawImage(healthPotion, (int) x, (int) y , 30, 50,  null); 
	}


	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 30, 50);
	}
	
}
