package game.pack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class BossBullet extends GameObject {
	private Image boss_bullet;
	
	public BossBullet(double x, double y, ID id) {
		super(x, y, id);
		try {
			boss_bullet = ImageIO.read(getClass().getResourceAsStream("/bullets/boss_bullet.png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void tick() {
		x -= 5;
	}


	public void render(Graphics g) {
		g.drawImage(boss_bullet, (int) x, (int) y , 30, 15, null);
		
	}


	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 30, 15);
	}

}
