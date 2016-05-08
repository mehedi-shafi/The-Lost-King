package game.pack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class BasicEnemyBullet extends GameObject{
	private Image basic_bullet;

	
	public BasicEnemyBullet(double x, double y, ID id) {
		super(x, y, id);
		
		try {
			basic_bullet = ImageIO.read(getClass().getResourceAsStream("/bullets/basic_bullet.png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}


	public void tick() {
		x -= 2;
	}


	public void render(Graphics g) {
		g.drawImage(basic_bullet, (int) x, (int) y, 20, 10, null) ;	
	}


	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 20, 10);
	}

}
