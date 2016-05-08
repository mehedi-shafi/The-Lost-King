package game.pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class BOSS_3 extends GameObject{
	private Handler handler;

	//mage arrays
	private Image[] run = new Image[8];
	private Image[] punch1 = new Image[4];
	private Image[] punch2 = new Image[4];
	private Image[] ending = new Image[4];
	
	//timers
	private long p1_timer;
	private long p2_timer;
	private long r_timer;
	private long e_timer;
	private long timer;
	private long ability;
	
	//frame controllers
	private int p1_frame = 0;
	private int p2_frame = 0;
	private int e_frame = 0;
	private int r_frame = 0;
	
	//state
	private int stat = 0;
	private int pos = 50;
	private int h_p;
	
	public BOSS_3(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		timer = System.currentTimeMillis();
		ability = System.currentTimeMillis();
		hp = STATS.BOSS_THREE_HP;
		
		try {
			run[0] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_1.png"));
			run[1] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_2.png"));
			run[2] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_3.png"));
			run[3] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_4.png"));
			run[4] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_5.png"));
			run[5] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_6.png"));
			run[6] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_7.png"));
			run[7] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_running_8.png"));
		}
		catch (Exception e){
			System.out.println ("error in enel run image loading");
		}
		
		try {
			punch1[0] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_1_1.png"));
			punch1[1] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_1_2.png"));
			punch1[2] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_1_3.png"));
			punch1[3] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_1_4.png"));
		}
		catch (Exception e){
			System.out.println ("error in enel punch 1 image loading");
		}
		try {
			punch2[0] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_2_1.png"));
			punch2[1] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_2_2.png"));
			punch2[2] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_2_3.png"));
			punch2[3] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_punch_2_4.png"));
		}
		catch (Exception e){
			System.out.println ("error in enel punch 2 image loading");
		}
		
		try {
			ending[0] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_final_1.png"));
			ending[1] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_final_2.png"));
			ending[2] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_final_3.png"));
			ending[3] = ImageIO.read(getClass().getResourceAsStream("/enel/enel_final_4.png"));
		}
		catch (Exception e){
			System.out.println ("error in enel ending image loading");
		}
		
		r_timer = System.currentTimeMillis();
	}


	public void tick() {
		//HP BAR decreaser
		h_p = (hp*50/STATS.BOSS_THREE_HP);
		
		//Moving back and forth	::
		if (x > Vars.display_width/2){
			x-=.5;
		}
		else if (x <= Vars.display_width/2){
			x+=.5;
		}
		
		//state changer
		if (stat == 0){
			if (System.currentTimeMillis() - timer >= 4000){
				++stat;
				handler.addObject(new BossBullet(x, y+50, ID.BossBullet));
				AudioPlayer.getSound("fire").play();
				timer = System.currentTimeMillis();
				p1_timer = System.currentTimeMillis();
			}
				
		}
		
		else if (stat == 1){
			if (System.currentTimeMillis() - timer >= 400){
				++stat;
				timer = System.currentTimeMillis();
				r_timer = System.currentTimeMillis();
			}
		}
		
		else if (stat == 2){
			if (System.currentTimeMillis() - timer >= 4000){
				++stat;
				handler.addObject(new BossBullet(x, y+50, ID.BossBullet));
				AudioPlayer.getSound("fire").play();
				timer = System.currentTimeMillis();
				p2_timer = System.currentTimeMillis();
			}
		}		
		
		else if (stat == 3){
			if (System.currentTimeMillis() - timer >= 400){
				++stat;
				timer = System.currentTimeMillis();
				r_timer = System.currentTimeMillis();
			}
		}
		else if (stat == 5){
			if(System.currentTimeMillis() - timer >= 360){
				stat = 0;
				r_timer = System.currentTimeMillis();
				timer = System.currentTimeMillis();
			}
		}
		
		//ability timer setting
		if (System.currentTimeMillis() - ability >= 13000){
			stat = 5;
			ability = System.currentTimeMillis();
			timer = System.currentTimeMillis();
			e_timer = System.currentTimeMillis();
			for (int i = 0; i<10; i++){
				handler.addObject(new BossBullet(x, (y-(pos/2)+50), ID.BossBullet));
				pos	-=	9;
			}
			ability = System.currentTimeMillis();
			pos = 50;
		}
		
		//state reset
		if (stat == 4){
			stat = 0;	
		}
		
		//frame controller
		if (stat == 0 || stat == 2){
			if (System.currentTimeMillis() - r_timer >= 120){
				++ r_frame;
				r_timer = System.currentTimeMillis(); 
			}
			if (r_frame == 8){
				r_frame = 0;
			}
		}
		else if (stat == 1){
			if (System.currentTimeMillis() - p1_timer >= 90){
				++ p1_frame;
				p1_timer = System.currentTimeMillis();
			}
			if (p1_frame == 4){
				p1_frame = 0;
			}
		}
		else if (stat == 3){
			if (System.currentTimeMillis() - p2_timer >=90 ){
				++p2_frame;
				p2_timer = System.currentTimeMillis();
			}
			if (p2_frame == 4){
				p2_frame = 0;
			}
		}
		else if (stat == 5){
			if (System.currentTimeMillis() - e_timer >= 90){
				++ e_frame;
				e_timer = System.currentTimeMillis();
			}
			if (e_frame == 4){
				e_frame = 0;
			}
		}
		//dying 
		if (hp <= 0){
			handler.removeObject(this);
			AudioPlayer.getSound("die").play();
			STATS.XP += 1500;
			STATS.SCORE += 10000;
			STATS.XP += 1000;
			STATS.CURRENT_HEALTH = 0;
			STATS.end = true;
		}
	}

	public void render(Graphics g) {
		if (stat == 0 || stat == 2){
			g.drawImage(run[r_frame], (int) x , (int) y, 120, 120, null);
		}
		else if (stat == 1){
			g.drawImage(punch1[p1_frame], (int) x , (int) y, 120, 120, null);
		}
		else if (stat == 3){
			g.drawImage(punch2[p2_frame], (int) x , (int) y, 120, 120, null);
		}
		else if (stat == 5){
			g.drawImage(ending[e_frame], (int) x, (int) y, 120, 120, null);
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
