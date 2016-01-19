package exercise2;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	private final static int FULL_SPEED = 5;
	private final static int SLOW_SPEED = 2;
	private final static int STOP_SPEED = 0;
	private int movementSpeed = FULL_SPEED;
	private CarDirection carDirection;
	private GreenfootImage carImage;
	private int carNum;
	private String[] carFiles = { "images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png",
			"images\\topCarYellow.png" };

	Random rand = new Random();

	public Car(CarDirection movement) {
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
			move(movementSpeed);
			if (this.isAtEdge()) {
				this.setLocation(this.getX(), TrafficWorld.WORLD_HEIGHT - TrafficWorld.HALF_CAR_LENGTH);

			}
			break;
		case SOUTH:
			move(movementSpeed);
			if (this.isAtEdge()) {
				this.setLocation(this.getX(), TrafficWorld.HALF_CAR_LENGTH);
			}
			break;
		case EAST:
			move(movementSpeed);
			if (this.isAtEdge()) {
				this.setLocation(TrafficWorld.HALF_CAR_LENGTH, this.getY());
			}
			break;
		case WEST:
			move(movementSpeed);
			if (this.isAtEdge()) {
				this.setLocation(TrafficWorld.WORLD_WIDTH - TrafficWorld.HALF_CAR_LENGTH, this.getY());
			}
			break;
		}

	}

	@Override
	public void isApproaching(Intersection currentIntersection) {
		if (this.carDirection.equals(CarDirection.EAST) || this.carDirection.equals(CarDirection.WEST)) {
			switch (currentIntersection.getHLightColor()) {
			case GREEN:
				movementSpeed = FULL_SPEED;
				break;
			case YELLOW:
				movementSpeed = SLOW_SPEED;
				break;
			case RED:
				movementSpeed = SLOW_SPEED;
				break;
			}
		}

		else if (this.carDirection.equals(CarDirection.NORTH) || this.carDirection.equals(CarDirection.SOUTH)) {
			switch (currentIntersection.getVLightColor()) {
			case GREEN:
				movementSpeed = FULL_SPEED;
				break;
			case YELLOW:
				movementSpeed = SLOW_SPEED;
				break;
			case RED:
				movementSpeed = SLOW_SPEED;
				break;
			}
		}
	}

	@Override
	public void isInside(Intersection currentIntersection) {
		if (this.carDirection.equals(CarDirection.EAST) || this.carDirection.equals(CarDirection.WEST)) {
			switch (currentIntersection.getHLightColor()) {
			case GREEN:
				movementSpeed = FULL_SPEED;
				break;
			case YELLOW:
				movementSpeed = FULL_SPEED;
				break;
			case RED:
				movementSpeed = STOP_SPEED;
				break;

			}
		} else if (this.carDirection.equals(CarDirection.NORTH) || this.carDirection.equals(CarDirection.SOUTH)) {
			switch (currentIntersection.getVLightColor()) {
			case GREEN:
				movementSpeed = FULL_SPEED;
				break;
			case YELLOW:
				movementSpeed = FULL_SPEED;
				break;
			case RED:
				movementSpeed = STOP_SPEED;
				break;

			}
		}

	}

	@Override
	public void isLeaving(Intersection currentIntersection) {
		movementSpeed = FULL_SPEED;
	}

	
	
}
