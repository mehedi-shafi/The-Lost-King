package game.pack;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;



public class StrongEnemy extends GameObject{
	private long timer;	
	private Handler handler;
	private Image [] kizaru;
	private long frame_timer;
	private int frame;
	
	public StrongEnemy(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		timer = System.currentTimeMillis();
		kizaru = new Image [8];
		frame = 0;
		hp = STATS.STRONG_ENEMY_HP;
		frame_timer = System.currentTimeMillis();
		try {
			kizaru[0] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_1.png"));
			kizaru[1] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_2.png"));
			kizaru[2] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_3.png"));
			kizaru[3] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_4.png"));
			kizaru[4] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_5.png"));
			kizaru[5] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_6.png"));
			kizaru[6] = ImageIO.read(getClass().getResourceAsStream("/kizaru/kizaru_7.png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	

	public void tick() {
		x -= STATS.STRONG_ENEMY_MOVEMENT;
		
		//bullet spwaning
		if (System.currentTimeMillis() - timer >= 3000){
			handler.addObject(new StrongEnemyBullet(x, y+50, ID.StrongEnemyBullet));
			AudioPlayer.getSound("fire").play();
			timer = System.currentTimeMillis();
		}
		
		//frame controller
		if (System.currentTimeMillis() - frame_timer >= 90){
			++frame;
			frame_timer = System.currentTimeMillis();
			if (frame == 6){
				frame = 0;
			}
		}
		
		//Dying
		if (hp<=0){
			STATS.XP += 15;
			STATS.SCORE += 300;
			++ STATS.kills;
			handler.removeObject(this);
			AudioPlayer.getSound("die").play();
		}
	}


	public void render(Graphics g) {
		g.drawImage(kizaru[frame], (int) x, (int) y, 100, 100, null);
	}


	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 100, 100);
	}

}
