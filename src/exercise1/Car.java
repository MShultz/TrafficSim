package exercise1;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor {
	private final static int FULL_SPEED = 5;
	private Direction carDirection;
	private GreenfootImage carImage;
	private int carNum;
	private String[] carFiles = { "images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png",
			"images\\topCarYellow.png" };

	Random rand = new Random();

	public Car(Direction movement) {
		this.carDirection = movement;
		randomizeCarColor();
		this.setImage(carImage);
		this.setRotation(movement.getRotation());

	}

	public void randomizeCarColor() {
		carNum = rand.nextInt(4);
		carImage = new GreenfootImage(carFiles[carNum]);
	}

	public void act() {
		switch (carDirection) {
		case NORTH:
			move(1);
			if (this.isAtEdge()) {
				this.setLocation(this.getX(), TrafficWorld.WORLD_HEIGHT - TrafficWorld.HALF_CAR_LENGTH);
			}
			break;
		case SOUTH:
			move(1);
			if (this.isAtEdge()) {
				this.setLocation(this.getX(), TrafficWorld.HALF_CAR_LENGTH);
			}
			break;
		case EAST:
			move(1);
			if (this.isAtEdge()) {
				this.setLocation(TrafficWorld.HALF_CAR_LENGTH, this.getY());
			}
			break;
		case WEST:
			move(1);
			if (this.isAtEdge()) {
				this.setLocation(TrafficWorld.WORLD_WIDTH - TrafficWorld.HALF_CAR_LENGTH, this.getY());
			}
			break;
		}

	}

}
