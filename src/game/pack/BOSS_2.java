package game.pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class BOSS_2 extends GameObject{
	private Image[] kick = new Image [6];
	private Image[] punch = new Image[6];
	private Image[] run = new Image [5];
	
	//timers
	private long r_timer;
	private long k_timer;
	private long p_timer;
	private long s_timer;
	
	//frame controller 
	private int r_frame = 0;
	private int p_frame = 0;
	private int k_frame = 0;
	
	//state
	private int state = 0;
	private int h_p;
	private Handler handler;
	
	private long ability;
	
	private double pos = 50;
	
	public BOSS_2(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		ability = System.currentTimeMillis();
		STATS.BOSS_DAMAGE = 15;	
		hp = STATS.BOSS_TWO_HP;
		
		try {
			kick[0] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_1.png"));
			kick[1] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_2.png"));
			kick[2] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_3.png"));
			kick[3] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_4.png"));
			kick[4] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_5.png"));
			kick[5] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_kick_6.png"));			
		} 
		catch (Exception e) {
			
			System.out.println("problem in dofla kick loading");
		}
		
		try {
			punch[0] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_1.png"));
			punch[1] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_2.png"));
			punch[2] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_3.png"));
			punch[3] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_4.png"));
			punch[4] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_5.png"));
			punch[5] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_punch_6.png"));
			
		} 
		catch (Exception ex){
			System.out.println ("Problem in dofla punch loading");
		}
		
		try {
			run[0] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_stand_1.png"));
			run[1] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_stand_2.png"));
			run[2] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_stand_3.png"));
			run[3] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_stand_4.png"));
			run[4] = ImageIO.read(getClass().getResourceAsStream("/dofla/dofla_stand_5.png"));
		} 
		catch (Exception exd){
			System.out.println("problem in dofla running loading ");
		}
		
		s_timer = System.currentTimeMillis();
		r_timer = System.currentTimeMillis();
	}


	public void tick() {
		//HP BAR decreaser
		h_p = (hp*50/STATS.BOSS_TWO_HP);
		
		//Moving back and forth
		if (x > Vars.display_width/2){
			x-=.5;
		}
		else if (x <= Vars.display_width/2){
			x+=.5;
		}
		
		// State changer
		if (state == 0){
			if (System.currentTimeMillis() - s_timer >= 4000){
				++ state;
				handler.addObject(new BossBullet(x, y+50, ID.BossBullet));
				AudioPlayer.getSound("fire").play();
				s_timer = System.currentTimeMillis();
				p_timer = System.currentTimeMillis();
			}
		}
		else if (state == 1){
			if (System.currentTimeMillis() - s_timer >= 550){
				++ state;
				s_timer = System.currentTimeMillis();
				r_timer = System.currentTimeMillis();
			}
		}
		else if (state == 2){
			if (System.currentTimeMillis() - s_timer >= 4000){
				++ state;
				handler.addObject(new BossBullet(x, y+50, ID.BossBullet));
				AudioPlayer.getSound("fire").play();
				s_timer = System.currentTimeMillis();
				k_timer = System.currentTimeMillis();			}
		}
		else if (state == 3){
			if (System.currentTimeMillis() - s_timer >= 550){
				++state;
				s_timer = System.currentTimeMillis();
				r_timer = System.currentTimeMillis();
			}
		}
		
		// resetting to 0 :P :P
		if (state == 4){
			state = 0;
		}
		
		//frame changer 
		//running
		if  (state == 0 || state == 2){
			if (System.currentTimeMillis() - r_timer >= 90){
				++ r_frame;
				r_timer = System.currentTimeMillis();
			}
			
			if (r_frame == 5)
				r_frame = 0;
		}
		else if (state == 1){
			if (System.currentTimeMillis() - p_timer >= 90){
				++ p_frame;
				p_timer = System.currentTimeMillis();
			}
			if (p_frame == 6)
				p_frame = 0;
		}
		else if (state == 3){
			if (System.currentTimeMillis() - k_timer >=90){
				++ k_frame;
				k_timer = System.currentTimeMillis();
			}
			if (k_frame == 6)
				k_frame = 0;
		}
			
		//Deploying ability 
		if (System.currentTimeMillis() - ability >= 15000){
			for (int i = 0; i<10; i++){
				handler.addObject(new BossBullet(x, (y-(pos/2)+50), ID.BossBullet));
				pos	-=	9;
			}
			ability = System.currentTimeMillis();
			pos = 50;
		}
		
		//Dying
		if (hp <= 0){
			handler.removeObject(this);
			AudioPlayer.getSound("die").play();
			STATS.STAGE  = 3;
			STATS.SCORE += 7500;
			STATS.XP += 1000;
			STATS.basic_kills = 0;
			STATS.stage3();
			Intro_Third.timer = System.currentTimeMillis();
			Game.STAT = STATE.THIRD;
		}
	}


	public void render(Graphics g) {
			if (state == 0 || state == 2){
				g.drawImage(run[r_frame], (int) x, (int) y, 120, 120, null);
			}
			else if (state == 1){
				g.drawImage(punch[p_frame],  (int) x, (int) y, 120, 120, null);
			}
			else if (state == 3){
				g.drawImage(kick[k_frame], (int) x, (int) y, 120, 120, null); 
			}
			
			//HUD for BOSS
			g.setColor(Color.WHITE);
			g.fillRect((int) x+35, (int) y-5, 50, 8);
			g.setColor(Color.red);
			g.fillRect((int) x+35, (int) y-5, h_p, 8);

	}


	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 120, 120);
	}

}
