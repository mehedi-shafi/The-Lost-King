package game.pack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{	
	
	private static final long serialVersionUID = 3099495050440014173L;	
	
	private Menu menu;

	public Thread thread;
	
	private boolean running = false;
	
	private Handler handler;
	
	private HUD hud;
	
	private Spwan spwan;
	
	private End end;
	
	private Credit credit;
	
	private Background back;
	
	private Loading load;
	
	public static int frames;
	
	public static boolean pause;
	
	private Image haki_pic;
	
	public static long haki_timer;
	
	public static boolean second_gear = false;
	
	public static boolean second_gear_in_use = false;
	
	public static boolean name;
	
	public HighScore high;
	
	private Welcome welcome;
	
	private Help help;
	
	private More more;
	
	private Intro_First first;
	
	private Intro_Second second;
	
	private Intro_Third third;
	
	public Game(){
		handler = new Handler();	
		AudioPlayer.load();
		hud = new HUD();
		spwan = new Spwan (handler, hud);
		menu = new Menu();
		end = new End();
		credit = new Credit();
		back = new Background ();
		load = new Loading();
		high = new HighScore();
		welcome = new Welcome();
		help = new Help();		
		more = new More();
		first = new Intro_First();
		second = new Intro_Second();
		third = new Intro_Third();
		
		//run = new Running();	
		new Window (Vars.display_width, Vars.display_height, "The Lost KING", this);	
		this.addKeyListener(new KeyControl(handler));
		this.addMouseListener(menu);
		pause = false;
		name = false;
		try {
			haki_pic = ImageIO.read(getClass().getResourceAsStream("/Background/haki_screen.jpg"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		STATS.reset();
	}
	
	public enum STATE{
		MENU,
		GAME,
		CREDIT,
		FIRST,
		SECOND,
		THIRD,
		END,
		HALL,
		LOADING,
		BossOneIntro,
		BossTwoIntro,
		BossThreeIntro,
		GameOver,
		Haki,
		More,
		Welcome,
		HELP;
	}
	
	public static STATE STAT = STATE.Welcome;
	
	public synchronized void start(){
		thread = new Thread (this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta --;
			}
			if (running){
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println ("FPS: " + frames);
				frames = 0;
			}
		}
		
	}
	public void tick(){
		//game starts		
		if (STAT == STATE.GAME){
			if (!pause){
				handler.tick();	
				spwan.tick();
				back.tick();
				hud.tick();
			}
		}	
		
		if (STAT == STATE.Haki){
			if (System.currentTimeMillis() - haki_timer >=2000){
				STAT = STATE.GAME;
				handler.haki();
			}
		}
		//Ending game
		if (STATS.CURRENT_HEALTH <= 0){
			handler.clearEnemies();	
			AudioPlayer.getSound("over").play();
			STAT = STATE.END;
			end.sort = true;
		}
		
		if (STAT == STATE.LOADING){
			load.tick();
		}
		
		if (STAT == STATE.HALL){
			high.tick();
		}
		
		if (STAT == STATE.END){
			end.tick();
		}
		
		if (STAT == STATE.Welcome){
			welcome.tick();
		}
		
		if (STAT == STATE.MENU){
			if (!Menu.isPlaying){
				AudioPlayer.getMusic("menu").loop();
				Menu.isPlaying = true;
			}
		}
		
		if (STAT == STATE.FIRST){
			first.tick();
		}
		
		if (STAT == STATE.SECOND){
			second.tick();
		}
		
		if (STAT == STATE.THIRD){
			third.tick();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();			
		
		//gear second
		if(STATS.g2_cd<=0){
			second_gear = true;
		}
		
		//Enabling multi OPTIONS
		if (STAT == STATE.MENU){
			menu.render(g);
		}
		
		else if (STAT == STATE.FIRST){
			first.render(g);
		}
		
		else if (STAT == STATE.SECOND){
			second.render(g);
		}
		
		else if (STAT == STATE.THIRD){
			third.render(g);
		}
		
		else if (STAT == STATE.GAME){
			if(!pause){
				back.render(g);
				handler.render(g);
				hud.render(g);
				
				if (second_gear){
					Font FoNt = new Font ("arial", Font.BOLD, 25);
					g.setFont(FoNt);
					g.setColor(Color.WHITE);
					if (second_gear_in_use){
						g.setColor(Color.WHITE);
						g.fillRect(Vars.display_width-200, Vars.display_height-20, 100, 50);
						g.setColor (Color.RED);
					}
					g.drawString ("Gear Second", Vars.display_width-200, Vars.display_height-50);
				}
			}
			else if (pause){
				g.setColor(Color.RED);
				Font font = new Font ("arial", Font.BOLD, 20);
				g.setFont(font);
				g.drawString("press \"esc\" to resume", Vars.display_width/2 - 225, Vars.display_height/2);
				g.drawString("press \"q\" to quit", Vars.display_width/2 + 50, Vars.display_height/2);
			}

		}
		
		else if (STAT == STATE.HALL){
			high.render(g);
		}
		else if (STAT == STATE.Haki){
			g.drawImage(haki_pic, 0, 0, Vars.display_width, Vars.display_height, null);
		}
		
		else if (STAT == STATE.END){
			end.render(g);
		}	
		
		
		else if (STAT == STATE.CREDIT){
			credit.render(g);
		}
		
		else if (STAT == STATE.LOADING){
			load.render(g);
		}
		
		else if (STAT == STATE.Welcome){
			welcome.render(g);
		}
		else if (STAT == STATE.HELP){
			help.render(g);
		}
		
		else if (STAT == STATE.More){
			more.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	public static double clamp (double var, double min , double max){
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}

}
