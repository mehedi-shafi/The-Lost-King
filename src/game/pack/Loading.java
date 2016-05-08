package game.pack;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import game.pack.Game.STATE;

public class Loading {
	private Image loading;

	
	private int filler;
	
	public Loading(){
		try {
			loading = ImageIO.read(getClass().getResourceAsStream("/Background/loading.jpg"));

		}
		catch(Exception e){
			e.printStackTrace();
		}
		filler = 0;
	}
	
	public void tick (){

		++filler;
		if (filler == 200){
			filler = 0;
			STATS.reset();
			//STATS.stage2();
			STATS.current_player = JOptionPane.showInputDialog("Enter your Player Name");
			ScoreManage.READ();
			//ScoreManage.print();
			AudioPlayer.getMusic("battle").play();
			Intro_First.timer = System.currentTimeMillis();
			Game.STAT = STATE.FIRST;
		}
	}
	
	public void render (Graphics g){
			g.drawImage(loading, 0, 0, Vars.display_width, Vars.display_height, null);
			g.setColor(Color.WHITE);
			g.drawRect(10, Vars.display_height/2-20, 200, 30);
			g.fillRect(10, Vars.display_height/2-20, filler, 30);
	}
}
