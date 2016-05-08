package game.pack;

import java.awt.Graphics;
import java.util.Random;

public class Spwan {
	private Handler handler;

	private Random rand;
	private long basic_e, strong_e, health_p;

	public Spwan (Handler handler, HUD hud){
		this.handler = handler;

		
		//timers initialize
		basic_e = System.currentTimeMillis();
		strong_e = System.currentTimeMillis();
		health_p = System.currentTimeMillis();
		strong_e = System.currentTimeMillis();		
		
		rand = new Random();
	}
	
	public void tick(){
		
		// Player spawning one time event in each time game starts
		if (STATS.GAME_START==1){
			
			handler.addObject(new Player (20, Vars.display_height-160, ID.Player, handler));
			STATS.GAME_START = 0;
		}
		
		// basic enemy spawning
		
		//STAGE :: 01
		if (STATS.STAGE == 1){
			if (System.currentTimeMillis()-basic_e >= 5000){
	
				handler.addObject(new BasicEnemy(rand.nextInt(400)+250, Vars.display_height-160, ID.BasicEnemy, handler));				
				basic_e = System.currentTimeMillis();
			}
		}
		
		//Stage :: 02
		else if(STATS.STAGE == 2){
			if (System.currentTimeMillis() - basic_e >= 4500){
				handler.addObject(new BasicEnemy(rand.nextInt(400)+250, Vars.display_height-160, ID.BasicEnemy, handler));
				basic_e = System.currentTimeMillis();
			}
		}
		
		//Stage :: 03
		else if (STATS.STAGE == 3){
			if (System.currentTimeMillis() - basic_e >= 4000){
				
				handler.addObject(new BasicEnemy(rand.nextInt(400)+250, Vars.display_height-160, ID.BasicEnemy, handler));
				basic_e = System.currentTimeMillis();
			}
		}
		
		//Strong enemy spawning
		
		//Stage :: 01
		if (STATS.STAGE == 1){
			if (System.currentTimeMillis()-strong_e >= 15000){
	
					handler.addObject(new StrongEnemy (rand.nextInt(400)+250, Vars.display_height-160, ID.StrongEnemy, handler));
					strong_e = System.currentTimeMillis();
			}
		}
		
		// Stage :: 02
		else if (STATS.STAGE == 2){
			if (System.currentTimeMillis() - strong_e >= 13000){
				handler.addObject(new StrongEnemy (rand.nextInt(400)+250, Vars.display_height-160, ID.StrongEnemy, handler));
				strong_e = System.currentTimeMillis();
			}
		}
		
		// Stage :: 03
		
		if (STATS.STAGE == 3){
			if (System.currentTimeMillis() - strong_e >= 12000){
				
				handler.addObject(new StrongEnemy (rand.nextInt(400)+250, Vars.display_height-160, ID.StrongEnemy, handler));
				strong_e = System.currentTimeMillis();
			}
			
		}
		
		//Health portion spawn 
		if (System.currentTimeMillis()-health_p >= 30000){
			handler.addObject(new HealthPotion(rand.nextInt(400), Vars.display_height-160, ID.HealthPotion ));
			health_p = System.currentTimeMillis();
		}
		
		//Boss spawning
		//Boss :: ONE
		if (STATS.STAGE == 1){
			if (STATS.basic_kills >= 30){
				if(STATS.yet_1){
					STATS.yet_1 = false;
					handler.addObject(new BOSS_1(Vars.display_width-100, Vars.display_height-160, ID.BossOne, handler));
				}
			}
		}
		//Boss :: two
		else if (STATS.STAGE == 2){
			if (STATS.basic_kills >= 45){
				if (STATS.yet_2){
					STATS.yet_2 = false;
					handler.addObject(new BOSS_2(Vars.display_width-100, Vars.display_height-160, ID.BossTwo, handler));
				}
			}
		}
		//Boss :: three
		else if (STATS.STAGE == 3){
			if (STATS.basic_kills >= 60){
				if(STATS.yet_3){
					STATS.yet_3 = false;
					handler.addObject(new BOSS_3(Vars.display_width-100, Vars.display_height-160, ID.BossThree, handler));
				}
			}
		}
	}
	
	public void render (Graphics g){

	}
	
}
