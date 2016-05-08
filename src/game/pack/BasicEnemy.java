package game.pack;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject{
	private Image [] b_e;
	private long timer, f_t;
	private int frame = 0;
	private Handler handler;

	public BasicEnemy(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		speedX = .4;
		this.handler = handler;
		timer = System.currentTimeMillis();
		f_t = System.currentTimeMillis();
		hp = STATS.BASIC_ENEMY_HP;
		b_e = new Image [12];
		try{
			b_e[0] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_1.png"));
			b_e[1] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_2.png"));
			b_e[2] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_3.png"));
			b_e[3] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_4.png"));
			b_e[4] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_3.png"));
			b_e[5] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_2.png"));
			b_e[6] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_1.png"));
			b_e[7] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_2.png"));
			b_e[8] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_3.png"));
			b_e[9] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_4.png"));
			b_e[10] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_3.png"));
			b_e[11] = ImageIO.read(getClass().getResourceAsStream("/basic_e/b_e_2.png"));

		}
		catch (Exception e){
			e.printStackTrace();
		}
		}
	
	//Bound of the object created for basic enemy	
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 100, 100);
	}
	
	public void tick() {
		x -= speedX;   //moving towards the champs who is coming + of X
		
		//Dying 
		if (hp<=0){
			STATS.XP += 10;
			STATS.SCORE += 200;
			++ STATS.kills;
			++ STATS.basic_kills;
			handler.removeObject(this);
			AudioPlayer.getSound("die").play();
		}
		
		//Bullet spawning
		if (System.currentTimeMillis()-timer>=5000){
			handler.addObject(new BasicEnemyBullet(x, y+40, ID.BasicEnemyBullet));
			AudioPlayer.getSound("fire").play();
			timer = System.currentTimeMillis();			
		}
		
		//animation 
		if (System.currentTimeMillis() - f_t >= 90){
			++ frame;
			f_t = System.currentTimeMillis();
			if (frame == 12)
				frame = 0;
		}
		
	}


	public void render(Graphics g) {

		g.drawImage(b_e[frame], (int) x, (int) y, 100, 100, null);
	}
	

}
