package game.pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class BOSS_1	extends GameObject {
	private Handler handler;
	private long timer;
	private Image[] standing = new Image [4];
	private Image[] shot = new Image [5];
	private int stand_frame;
	private int shot_frame;
	private long change;
	private boolean blow = false;
	private long stand_timer;
	private long shot_timer;
	private int h_p;

	public BOSS_1(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		timer = System.currentTimeMillis();
		stand_timer = System.currentTimeMillis();
		hp = STATS.BOSS_ONE_HP;
		stand_frame = 0;
		shot_frame = 0;
		try {
			standing[0] = ImageIO.read(getClass().getResourceAsStream("/arlong/arlong_1.png"));
			standing[1] = ImageIO.read(getClass().getResourceAsStream("/arlong/arlong_2.png"));
			standing[2] = ImageIO.read(getClass().getResourceAsStream("/arlong/arlong_3.png"));
			standing[3] = ImageIO.read(getClass().getResourceAsStream("/arlong/arlong_4.png"));
			
			shot[0] = ImageIO.read(getClass().getResourceAsStream("/arlong/blow_1.png"));
			shot[1] = ImageIO.read(getClass().getResourceAsStream("/arlong/blow_2.png"));
			shot[2] = ImageIO.read(getClass().getResourceAsStream("/arlong/blow_3.png"));
			shot[3] = ImageIO.read(getClass().getResourceAsStream("/arlong/blow_4.png"));
			shot[4] = ImageIO.read(getClass().getResourceAsStream("/arlong/blow_5.png"));			
		}
		catch (Exception E){
			System.out.println("error from arlong image loading");
		}
	}


	public void tick() {
		//HP BAR decreaser
		h_p = (hp*50/STATS.BOSS_ONE_HP);
		
		//moving 
		if (x > Vars.display_width/2){
			x-=.5;
		}
		else if (x <= Vars.display_width/2){
			x+=.5;
		}
		
		//Bullet spawn
		if (System.currentTimeMillis()-timer >= 3000){
			handler.addObject(new BossBullet(x, y+50, ID.BossBullet));
			AudioPlayer.getSound("fire").play();
			change = System.currentTimeMillis();
			blow = true;
			shot_frame = 0;
			shot_timer = System.currentTimeMillis();
			timer = System.currentTimeMillis();
		}
		
		if (System.currentTimeMillis() - change >= 1500){
			blow = false;			
		}
		
		//timers and frames :P 
		if (System.currentTimeMillis() - stand_timer >= 90){
			stand_frame++;
			if (stand_frame == 4)
				stand_frame = 0;
			stand_timer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - shot_timer >= 90){
			shot_frame ++;
			if (shot_frame == 5)
				shot_frame = 0;
			shot_timer = System.currentTimeMillis();
		}
		
		if (hp<=0){
			handler.removeObject(this);
			AudioPlayer.getSound("die").play();
			STATS.STAGE = 2;
			STATS.SCORE += 5000;
			STATS.XP += 750;
			STATS.basic_kills = 0;
			STATS.stage2();
			Intro_Second.timer = System.currentTimeMillis();
			Game.STAT = STATE.SECOND;
		}
	}

	public void render(Graphics g) {
		if (!blow){
			g.drawImage(standing[stand_frame], (int) x , (int) y, 120, 120, null);
		}
		
		else{
			g.drawImage(shot[shot_frame],(int) x,(int) y, 120, 120, null);
		}
		
		//HUD for BOSS
		g.setColor(Color.WHITE);
		g.fillRect((int) x+35, (int) y-5, 50, 8);
		g.setColor(Color.red);
		g.fillRect((int) x+35, (int) y-5, h_p, 8);
	}


	public Rectangle getBounds() {

		return new Rectangle ((int)x , (int) y, 120, 120);
	}
}
