package game.pack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.pack.Game.STATE;
import game.pack.Player.pl_stat;


public class KeyControl extends KeyAdapter{
	private Handler handler;
	
	private boolean[] keyHold = new boolean[4];
	
	public KeyControl (Handler handler){
		this.handler = handler;
		
		keyHold[0] = keyHold[1] = keyHold[2] = keyHold[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
			if (Game.STAT == STATE.GAME){
				if (!Game.pause){
					if (key == KeyEvent.VK_ESCAPE){
						Game.pause = true;
						AudioPlayer.getMusic("battle").pause();
					}
				}
				else if (Game.pause){
					if (key == KeyEvent.VK_ESCAPE){
						Game.pause = false;
						AudioPlayer.getMusic("battle").resume();
					}
					else if (key == KeyEvent.VK_Q)
						Game.STAT = STATE.MENU;
				}
				for (int i = 0; i< handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
				
					if (tempObject.getId() == ID.Player){

						
						if (key == KeyEvent.VK_D){
							tempObject.setSpeedX(5);
							keyHold[2] = true;
						}
						else if (key == KeyEvent.VK_A){
							tempObject.setSpeedX(-5);
							keyHold[3] = true;
						}
						else if (key == KeyEvent.VK_SPACE){
							long timer = System.currentTimeMillis();
							Player.luffy = pl_stat.jump;
							Player.jump_st = timer;
							while ((System.currentTimeMillis() - timer) <=400){
								tempObject.speedY = -5;
							}
							Player.luffy = pl_stat.run;
							tempObject.setY(Vars.display_height-160);
							
						}
						
						//Fighting buttons
						//basic punch 
						else if (key == KeyEvent.VK_W){
							handler.addObject(new AirPunch(tempObject.getX(), tempObject.getY(), ID.AIRPUNCH, handler));
							Player.punch_timer = System.currentTimeMillis();
							Player.punch_total_timer = System.currentTimeMillis();
							AudioPlayer.getSound("punch").play();;
							Player.luffy = pl_stat.basic;
						}
						
						//haki
						else if (key == KeyEvent.VK_Q){
							if (HUD.h_bar >= 100){
								Game.haki_timer = System.currentTimeMillis();
								Game.STAT = STATE.Haki;
								AudioPlayer.getSound("haki").play();
								HUD.h_bar = 0;
							}
						}
						
						//gear second
						else if (key == KeyEvent.VK_E){
							if (Game.second_gear){
								Player.g2_timer = System.currentTimeMillis();
								Player.gear_second = true;
								AudioPlayer.getSound("gear").play();
								Game.second_gear_in_use = true;
							}
						}
					}
				}
				
			}
	}
	
	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode();
		for (int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player){
				
				/*if (key == KeyEvent.VK_W) {
					tempObject.setSpeedY(0);
					keyHold[0] = false;
				}
				 if (key == KeyEvent.VK_S){
					tempObject.setSpeedY(0);
					keyHold[1] = false;
				}*/
				if (key == KeyEvent.VK_D){
					tempObject.setSpeedX(0);
					keyHold[2] = false;
				}
				else if (key == KeyEvent.VK_A){
					tempObject.setSpeedX(0);
					keyHold[3] = false;
				}
				
				//Vertical movement
				if (!keyHold[0] && !keyHold[1]) tempObject.setSpeedY(0);
				
				//Horizontal movement
				if (!keyHold[2] && !keyHold[3]) tempObject.setSpeedX(0);
			}
		}
	}
}
