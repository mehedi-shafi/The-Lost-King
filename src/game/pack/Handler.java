package game.pack;

import java.awt.Graphics;
import java.util.ArrayList;



public class Handler {
	ArrayList <GameObject> object = new ArrayList<GameObject>();
	
	public void tick()
	{
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			
			//auto disposal 
			if (tempObject.getId() != ID.Player){
				if (tempObject.getX()<=-50 || tempObject.getX()>=Vars.display_width+50 || tempObject.getY()<=-50 || tempObject.getY()>=Vars.display_height+50){
					removeObject(tempObject);
				}
			}
		}
	}
	
	public void render (Graphics g){
		for (int i = 0; i< object.size(); i++){
			GameObject tempObject = object.get(i);			
			tempObject.render(g);
		}	
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void clearEnemies(){
		for (int i = 0; i<object.size(); i++){
			//GameObject tempObject = object.get(i);
			object.clear();
		}
	}
	
	public void haki (){
		for (int i = 0 ; i< object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.StrongEnemy){
				removeObject(tempObject);
			}
			
			if (tempObject.getId() == ID.BossOne || tempObject.getId() == ID.BossTwo || tempObject.getId() == ID.BossThree){
				tempObject.hp -= (int) (tempObject.hp*STATS.haki);
			}
		}
	}
}
