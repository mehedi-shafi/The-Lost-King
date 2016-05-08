package game.pack;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AirPunch extends GameObject{
	private Handler handler;
	
	
	public AirPunch(double x, double y, ID id, Handler handler) {
		super(x, y+40, id);
		this.handler = handler;
	}

	public void collision (){
		for (int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() != ID.Player && tempObject.getId() != ID.AIRPUNCH && 
					tempObject.getId() != ID.BasicEnemyBullet  && tempObject.getId() != ID.BossBullet &&
					tempObject.getId() != ID.StrongEnemyBullet && tempObject.getId() != ID.HealthPotion){
					
					if (tempObject.getBounds().intersects(getBounds())){
						if (Player.gear_second)
							tempObject.hp -= (STATS.DAMAGE + 10);
						else 
							tempObject.hp -= STATS.DAMAGE;
						-- STATS.g2_cd;
						handler.removeObject(this);
					}
				}
		}
	}
	public void tick() {
		collision();
		x += 5;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		//g.drawOval((int)x, (int)y, 10, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle ((int) x, (int) y, 10, 10);
	}
}
