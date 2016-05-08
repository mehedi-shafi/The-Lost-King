package game.pack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class StrongEnemyBullet extends GameObject {
	private Image strong_bullet;
	
	
	public StrongEnemyBullet(double x, double y, ID id) {
		super(x, y, id);
		try {
			strong_bullet = ImageIO.read(getClass().getResourceAsStream("/bullets/strong_bullet.png"));
		}
		catch (Exception strong) {
			strong.printStackTrace();
		}
	}


	public void tick() {
		x -= 3;

	}


	public void render(Graphics g) {
		g.drawImage(strong_bullet, (int) x, (int)y, 30, 15, null); 
	}


	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 30, 15);
	}
	
}
