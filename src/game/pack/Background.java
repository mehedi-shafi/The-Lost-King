package game.pack;

import java.awt.Graphics;
import java.awt.Image;


import javax.imageio.ImageIO;
 class Background {
	private Image[] running_image, background_sky;
	private Image front;
	private Image back;
	
	private double sk1, sk2;
	private double grnd1, grnd2;
	
	public Background (){
		running_image = new Image[4];
		background_sky = new Image[4];
		try{
			//Running ground (front)
			running_image[0] = ImageIO.read(getClass().getResourceAsStream("/Background/running_ground_1.png"));
			running_image[1] = ImageIO.read(getClass().getResourceAsStream("/Background/running_ground_2.png"));
			running_image[2] = ImageIO.read(getClass().getResourceAsStream("/Background/running_ground_3.png"));
			
			//running sky (back)
			background_sky[0] = ImageIO.read(getClass().getResourceAsStream("/Background/sky_background_1.png"));
			background_sky[1] = ImageIO.read(getClass().getResourceAsStream("/Background/sky_background_2.png"));
			background_sky[2] = ImageIO.read(getClass().getResourceAsStream("/Background/sky_background_3.png"));
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		sk1 = grnd1 = 0;
		sk2 = grnd2 = Vars.display_width;
	}
	

	public void tick (){
		//sky mover
		sk1 -= .5;
		sk2 -= .5;
		if (sk1 == -Vars.display_width){
			sk1 = Vars.display_width;
		}
		if (sk2 == -Vars.display_width){
			sk2 = Vars.display_width;
		}
		
		//ground mover
		grnd1 -= 1;
		grnd2 -= 1;
		if (grnd1 == -Vars.display_width){
			grnd1 = Vars.display_width;
		}
		if (grnd2 == -Vars.display_width){
			grnd2 = Vars.display_width;
		}
		//picture changer
		//Stage :: 01 
		if (STATS.STAGE == 1){
			front = running_image[0];
			back = background_sky[0];
		}
		else if (STATS.STAGE == 2){
			front = running_image[1];
			back = background_sky[1];
		}
		else if (STATS.STAGE == 3){
			front = running_image[2];
			back = background_sky[2];
		}
	}
	
	public void render (Graphics g){		
		//sky
		g.drawImage(back, (int) sk1, 0, Vars.display_width, Vars.display_height, null);
		g.drawImage(back, (int) sk2, 0, Vars.display_width, Vars.display_height, null);
		
		//ground
		g.drawImage(front, (int) grnd1, Vars.display_height-100, Vars.display_width, 100, null);
		g.drawImage(front, (int) grnd2, Vars.display_height-100, Vars.display_width, 100, null);
	}	
}
