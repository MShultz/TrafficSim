package exercise3;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Explosion extends Actor {
	GreenfootImage background;
	private String[] explosionFiles = {"images\\explosion1.png", "images\\explosion2.png", "images\\explosion3.png"};
	private final int EXPLOSION_COUNTER_ONE = 75;
	private final int EXPLOSION_COUNTER_TWO = 50;
	private final int EXPLOSION_COUNTER_THREE = 25;
	private final int EXPLOSION_COUNTER_NONE = 0;
	private final int EXPLOSION_WIDTH = 20;
	private final int EXPLOSION_HEIGHT = 48;
	private int currentCounter = EXPLOSION_COUNTER_ONE;
	
	public Explosion(){
		background = new GreenfootImage(EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
		this.setImage(background);
	
	}
	
	public void act(){
		if ((EXPLOSION_COUNTER_TWO < currentCounter) && (currentCounter < EXPLOSION_COUNTER_ONE)){
			this.setImage(explosionFiles[0]);
		}
		else if((EXPLOSION_COUNTER_THREE < currentCounter) && (currentCounter <= EXPLOSION_COUNTER_TWO)){
			this.setImage(explosionFiles[1]);
		}
		else if((EXPLOSION_COUNTER_NONE < currentCounter) && (currentCounter <= EXPLOSION_COUNTER_THREE)){
			this.setImage(explosionFiles[2]);
		}
		else if(currentCounter == 0){
			this.setImage(background);
			getWorld().removeObject(this);
		}
		currentCounter--;
	}
}
