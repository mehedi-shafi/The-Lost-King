package game.pack;


//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import game.pack.Game.STATE;

public class Player extends GameObject{
	//timers
	private long run_st = System.currentTimeMillis();
	public static long jump_st;
	
	//frame controllers
	private int run_frame = 0;
	private int jump_frame = 0;
	
	private Handler handler;
	private Image[] running = new Image[12];
	private Image[] jump = new Image[10];
	private Image[] punch = new Image[25];
	private int punch_frame = 0;
	public static long punch_timer;
	public static long punch_total_timer;
	public static boolean gear_second = false;
	public static long g2_timer;
	
	//Constructor
	public Player (float x, float y, ID id, Handler handler){
		super (x, y, id);
		this.handler = handler;
		loadImage();
		collision();
	}
	
	public void loadImage(){	
			try{
				//loading running pictures
				running[0] = ImageIO.read(getClass().getResourceAsStream("/player/run_0.gif"));
				running[1] = ImageIO.read(getClass().getResourceAsStream("/player/run_1.gif"));
				running[2] = ImageIO.read (getClass().getResourceAsStream("/player/run_2.gif"));
				running[3] = ImageIO.read (getClass().getResourceAsStream("/player/run_3.gif"));
				running[4] = ImageIO.read (getClass().getResourceAsStream("/player/run_4.gif"));
				running[5] = ImageIO.read (getClass().getResourceAsStream("/player/run_5.gif"));
				running[6] = ImageIO.read (getClass().getResourceAsStream("/player/run_6.gif"));
				running[7] = ImageIO.read (getClass().getResourceAsStream("/player/run_7.gif"));
				running[8] = ImageIO.read (getClass().getResourceAsStream("/player/run_8.gif"));
				running[9] = ImageIO.read (getClass().getResourceAsStream("/player/run_9.gif"));
				running[10] = ImageIO.read (getClass().getResourceAsStream("/player/run_10.gif"));
				running[11] = ImageIO.read (getClass().getResourceAsStream("/player/run_11.gif"));
				
				//loading jumping pictures
				jump[0] = ImageIO.read(getClass().getResourceAsStream("/player/jump_1.png"));
				jump[1] = ImageIO.read(getClass().getResourceAsStream("/player/jump_2.png"));
				jump[2] = ImageIO.read(getClass().getResourceAsStream("/player/jump_3.png"));
				jump[3] = ImageIO.read(getClass().getResourceAsStream("/player/jump_4.png"));
				jump[4] = ImageIO.read(getClass().getResourceAsStream("/player/jump_5.png"));
				jump[5] = ImageIO.read(getClass().getResourceAsStream("/player/jump_6.png"));
				
				//loading punching pictures
				punch[0] = ImageIO.read(getClass().getResourceAsStream("/player/punch_1.png"));
				punch[1] = ImageIO.read(getClass().getResourceAsStream("/player/punch_2.png"));
				punch[2] = ImageIO.read(getClass().getResourceAsStream("/player/punch_3.png"));
				punch[3] = ImageIO.read(getClass().getResourceAsStream("/player/punch_4.png"));
				punch[4] = ImageIO.read(getClass().getResourceAsStream("/player/punch_5.png"));
				punch[5] = ImageIO.read(getClass().getResourceAsStream("/player/punch_6.png"));
				punch[6] = ImageIO.read(getClass().getResourceAsStream("/player/punch_7.png"));
				punch[7] = ImageIO.read(getClass().getResourceAsStream("/player/punch_8.png"));
				punch[8] = ImageIO.read(getClass().getResourceAsStream("/player/punch_9.png"));
				punch[9] = ImageIO.read(getClass().getResourceAsStream("/player/punch_10.png"));
				punch[10] = ImageIO.read(getClass().getResourceAsStream("/player/punch_11.png"));
				punch[11] = ImageIO.read(getClass().getResourceAsStream("/player/punch_12.png"));
				punch[12] = ImageIO.read(getClass().getResourceAsStream("/player/punch_13.png"));
				punch[13] = ImageIO.read(getClass().getResourceAsStream("/player/punch_14.png"));
				punch[14] = ImageIO.read(getClass().getResourceAsStream("/player/punch_15.png"));
				punch[15] = ImageIO.read(getClass().getResourceAsStream("/player/punch_16.png"));
				punch[16] = ImageIO.read(getClass().getResourceAsStream("/player/punch_17.png"));
				punch[17] = ImageIO.read(getClass().getResourceAsStream("/player/punch_18.png"));
				punch[18] = ImageIO.read(getClass().getResourceAsStream("/player/punch_19.png"));
				punch[19] = ImageIO.read(getClass().getResourceAsStream("/player/punch_20.png"));
				punch[20] = ImageIO.read(getClass().getResourceAsStream("/player/punch_21.png"));
				punch[21] = ImageIO.read(getClass().getResourceAsStream("/player/punch_22.png"));
				
			}
			catch (Exception e){
				e.printStackTrace();
			}
	}
	
	//Player states
	public enum pl_stat{
		run,
		jump,
		basic,
		gear_second,
		haki
		;
	}

	public static pl_stat luffy = pl_stat.run;
	//Getting Bounds which will determine if the other object is in the boundary of the player
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 100, 100);
	}
	
	
	//Checking the collision 
	
	public void collision (){
		for (int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			//Collision with basic enemy's bullets
			if (tempObject.getId() == ID.BasicEnemyBullet){
				if (getBounds().intersects(tempObject.getBounds())){					
					STATS.CURRENT_HEALTH -= STATS.BASIC_ENEMY_DAMAGE;
					handler.removeObject(tempObject);
				}
			}
			
			//Collision with strong enemy's bullet
			if (tempObject.getId() == ID.StrongEnemyBullet){
				if (getBounds().intersects(tempObject.getBounds())){
					STATS.CURRENT_HEALTH -= STATS.STRONG_ENEMY_DAMAGE;
					handler.removeObject(tempObject);
				}
			}
			
			//Collision with boss's bullets
			if (tempObject.getId() == ID.BossBullet){
				if (getBounds().intersects(tempObject.getBounds())){
					STATS.CURRENT_HEALTH -= STATS.BOSS_DAMAGE;
					handler.removeObject(tempObject);
				}
			}
			
			// Collecting health portions
			else if (tempObject.getId() == ID.HealthPotion){
				if (getBounds().intersects(tempObject.getBounds())){
					if ((STATS.HEALTH - STATS.CURRENT_HEALTH) >= 20){
						STATS.CURRENT_HEALTH += 20;
					}
					else{
						STATS.CURRENT_HEALTH += (STATS.HEALTH-STATS.CURRENT_HEALTH);
					}
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	
	//Update every time 
	public void tick(){
		x += speedX;
		y += speedY;
		
		x = Game.clamp(x, -50, Vars.display_width-50);
		
		//Leveling up Hero
		if (STATS.XP >= STATS.getNextLevel()){
			STATS.XP = 0;
			++ STATS.HERO_LEVEL;
			
			//Gear second
			if (Game.second_gear_in_use){
				if (System.currentTimeMillis()-g2_timer>=600){
					gear_second = false;
					Game.second_gear = false;
					Game.second_gear_in_use = false;
					STATS.g2_cd = 10;
				}
			}
			
			//Health Increasing
			{
				STATS.HEALTH += (int) (STATS.HEALTH * 0.1);
				STATS.CURRENT_HEALTH += (int) (STATS.CURRENT_HEALTH * 0.1);
			}
			
			//Damage increasing
			{
				STATS.DAMAGE += 2;
				STATS.haki += .01;
			}
		}
		
		//run frame controller
		if (System.currentTimeMillis() - run_st >= 90){
			run_frame++;
			run_st = System.currentTimeMillis();
			if (run_frame == 11)
				run_frame = 0;
		}		
		
		//jump frame colntroller
		if(System.currentTimeMillis() - jump_st >= 100){
			jump_frame++;
			jump_st = System.currentTimeMillis();
			if (jump_frame == 6)
				jump_frame = 0;
		}
		
		//punch frame controller
		if(luffy == pl_stat.basic){
			if (System.currentTimeMillis() - punch_timer >= 90){
				punch_timer = System.currentTimeMillis();
				++punch_frame;
			}
			if(punch_frame == 21)
				punch_frame = 0;
			//changing to run again
			if (System.currentTimeMillis() - punch_total_timer >= 500){
				luffy = pl_stat.run;
			}
		}
		
		//every time collision checker
		collision();
		
		if (STATS.CURRENT_HEALTH <= 0){
			Game.STAT = STATE.END;
		}
	}
	
	//graphics works
	public void render(Graphics g) {	
		if (luffy == pl_stat.run){
			g.drawImage(running[run_frame], (int) x, (int) y, 100, 100, null);		
		}
		else if (luffy == pl_stat.jump){
			g.drawImage(jump[jump_frame], (int) x , (int) y, 50, 100, null); 
		}
		else if (luffy == pl_stat.basic){
			g.drawImage(punch[punch_frame], (int) x, (int) y, 100, 100, null);
		}
	}
}
