package game.pack;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected double x, y; 						//Location variables
	protected ID id;  							// ID of the created Object
	protected double speedX, speedY;				//Velocity of the object in both axis X and Y
	protected double damage;						//damage done bye the object
	protected int hp;
	
	public GameObject (double x, double y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render (Graphics g);
	
	public abstract Rectangle getBounds();
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public ID getId() {
		return id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public double getSpeedX() {
		return speedX;
	}
	
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
	public double getSpeedY() {
		return speedY;
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}

}
