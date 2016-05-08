package game.pack;

public class STATS {
	//scores
	public static String[] Names = new String[15];
	public static String[] Scores = new String [15];
	
	//HERO
	public static double HEALTH = 125;
	public static double CURRENT_HEALTH = 125;
	public static int STAGE = 1;
	public static int HERO_LEVEL = 1;
	public static int HERO_MOVEMENT = 6;
	public static int DAMAGE = 10;
	public static double haki = .30;
	public static int g2_cd = 10;
	public static boolean haki_ready = false;
	public static String current_player;
	
	
	//GAME
	public static int SCORE = 0;
	public static int GAME_LEVEL = 1;
	public static int XP = 0;
	public static int kills = 0;
	public static int basic_kills = 0;
	public static int strong_kills = 0;
	public static boolean end = false;
	
	//Menu
	public static int GAME_START = 1;
	
	//Basic Enemy	
	public static int BASIC_ENEMY_HP = 10;
	public static int BASIC_ENEMY_DAMAGE = 5;
	public static int BASIC_ENEMY_MOVEMENT = 2;
	
	//Strong Enemy
	public static int STRONG_ENEMY_HP = 50;
	public static int STRONG_ENEMY_DAMAGE  = 10;
	public static int STRONG_ENEMY_MOVEMENT = 1;
	
	
	//Bosses' common damage by bullets
	public static int BOSS_DAMAGE = 15;
	
	//BOSS one
	public static boolean yet_1 = true;
	public static int BOSS_ONE_HP = 500;
	public static int BOSS_ONE_DAMAGE = 30;
	public static int BOSS_ONE_MOVEMENT = 7;
	
	
	//BOSS two
	public static boolean yet_2 = true;
	public static int BOSS_TWO_HP = 1500;
	public static int BOSS_TWO_DAMAGE = 30;
	public static int BOSS_TWO_MOVEMENT = 7;
	
	
	//BOSS three
	public static boolean yet_3 = true;
	public static int BOSS_THREE_HP = 2500;
	public static int BOSS_THREE_DAMAGE = 30;
	public static int BOSS_THREE_MOVEMENT = 7;
	
	
	
	//Methods of increments
	
	//Health Increment
	public static void healthIncrease(){
		HEALTH += HERO_LEVEL*50;
		CURRENT_HEALTH += HERO_LEVEL*50;
	}
	
	//damage increment
	public static void setHeroDamage(int increment){
		DAMAGE += increment;
	}
		
	
	//Hero Level Increase
	public static void heroLevelIncrease(){
		
	}
	
	
	//Reset methods
	
	//damage
	public void resetDamage(){
		DAMAGE = 10;
	}
	
	//leveling up method
	public static int getNextLevel(){
		return HERO_LEVEL*50;
	}
	
	//stage two
	public static void stage2(){
		DAMAGE += 3;
		
		BASIC_ENEMY_HP = 20;
		BASIC_ENEMY_DAMAGE = 10;
		
		STRONG_ENEMY_HP = 75;
		STRONG_ENEMY_DAMAGE = 20;
		
		BOSS_DAMAGE = 20;
	}
	
	//stage three
	public static void stage3(){
		DAMAGE += 3;
		
		BASIC_ENEMY_HP = 30;
		BASIC_ENEMY_DAMAGE = 15;
		
		STRONG_ENEMY_HP = 100;
		STRONG_ENEMY_DAMAGE = 30;
		
		BOSS_DAMAGE = 25;
	}
	
	//ALL reset :D :D 
	public static void reset (){
		//HERO
		HEALTH = 125;
		CURRENT_HEALTH = 125;
		STAGE = 1;
		HERO_LEVEL = 1;
		HERO_MOVEMENT = 7;
		DAMAGE = 10;
		haki = .30;
		haki_ready = false;
		current_player = "none";
		
		//GAME
		SCORE = 0;
		GAME_LEVEL = 1;
		XP = 0;
		kills = 0;
		basic_kills = 0;
		strong_kills = 0;
		end = false;
		
		//Player Spwan 
		GAME_START = 1;		
		
		
		//Basic Enemy	
		BASIC_ENEMY_HP = 10;
		BASIC_ENEMY_DAMAGE = 5;
		BASIC_ENEMY_MOVEMENT = 2;
		
		//Strong Enemy
		STRONG_ENEMY_HP = 50;
		STRONG_ENEMY_DAMAGE  = 10;
		STRONG_ENEMY_MOVEMENT = 1;
		
		//Bosses' common bullet damage 
		BOSS_DAMAGE = 10;
		
		//BOSS one
		yet_1 = true;
		BOSS_ONE_HP = 500;
		BOSS_ONE_DAMAGE = 30;
		BOSS_ONE_MOVEMENT = 7;
		
		
		//BOSS two
		yet_2 = true;
		BOSS_TWO_HP = 1500;
		BOSS_TWO_DAMAGE = 30;
		BOSS_TWO_MOVEMENT = 7;
		
		
		//BOSS three
		yet_3 = true;
		BOSS_THREE_HP = 2500;
		BOSS_THREE_DAMAGE = 30;
		BOSS_THREE_MOVEMENT = 7;
		
	}
	
}
