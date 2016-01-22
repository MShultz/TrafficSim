package exercise3;

import java.util.Random;

import exercise2.StreetLight.LightColor;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	private final static int FULL_SPEED = 5;
	private final static int SLOW_SPEED = 2;
	private final static int TURN_SPEED = 1;
	private final static int STOP_SPEED = 0;
	private final static int QUARTER_INTERSECTION = 50 / 4;
	private int movementSpeed = FULL_SPEED;
	private CarDirection carDirection;
	private GreenfootImage carImage;
	private int carNum;
	private boolean isTurningRight = false;
	private boolean hasTurned = false;
	private String[] carFiles = { "images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png",
			"images\\topCarYellow.png" };

	Random rand = new Random();

	public Car(CarDirection movement) {
		this.carDirection = movement;
		randomizeCarColor();
		this.setImage(carImage);
		this.setRotation(movement.getRotation());
	}

	public CarDirection getCarDirection() {
		return carDirection;
	}

	public void randomizeCarColor() {
		carNum = rand.nextInt(4);
		carImage = new GreenfootImage(carFiles[carNum]);
	}

	public void canTurnRight(Intersection currentIntersection) {
		if (isTurningRight) {
			switch (carDirection) {
			case NORTH:
				if (currentIntersection.getY() + QUARTER_INTERSECTION == this.getY()) {
					this.carDirection = CarDirection.EAST;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case SOUTH:
				if (currentIntersection.getY() - QUARTER_INTERSECTION == this.getY()) {
					this.carDirection = CarDirection.WEST;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case EAST:
				if (currentIntersection.getX() - QUARTER_INTERSECTION == this.getX()) {
					this.carDirection = CarDirection.SOUTH;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case WEST:
				if (currentIntersection.getX() + QUARTER_INTERSECTION == this.getX()) {
					this.carDirection = CarDirection.NORTH;
					isTurningRight = rand.nextBoolean();
				}
				break;
			}
		}
	}

	public void canTurnLeft(Intersection currentIntersection) {
		

		if (!isTurningRight) {
			switch (carDirection) {
			case NORTH:
				if (currentIntersection.getY() - QUARTER_INTERSECTION == this.getY()) {
					this.carDirection = CarDirection.WEST;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case SOUTH:
				if (currentIntersection.getY() + QUARTER_INTERSECTION == this.getY()) {
					this.carDirection = CarDirection.EAST;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case EAST:
				if (currentIntersection.getX() + QUARTER_INTERSECTION == this.getX()) {
					this.carDirection = CarDirection.NORTH;
					isTurningRight = rand.nextBoolean();
				}
				break;
			case WEST:
				if (currentIntersection.getX() - QUARTER_INTERSECTION == this.getX()) {
					this.carDirection = CarDirection.SOUTH;
					isTurningRight = rand.nextBoolean();
				}
				break;
			}
		}
	}

	public void determineTurn(Intersection currentIntersection) {
		if (rand.nextBoolean()) {
			assert(movementSpeed != 0);
			movementSpeed = TURN_SPEED;
			canTurnRight(currentIntersection);
			canTurnLeft(currentIntersection);
			hasTurned = true;
		}
	}

	public void act() {
		try {
			move(movementSpeed);
			if (this.isAtEdge()) {
				getWorld().removeObject(this);
			}
			else if (this.isTouching(Car.class)) {
				throw new Throwable("Two Cars Collided");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			getWorld().addObject(new Explosion(), this.getX(), this.getY());
			this.removeTouching(Car.class);;
			getWorld().removeObject(this);
		}

	}

	@Override
	public void isApproaching(Intersection currentIntersection) {
		hasTurned = false;
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
			case YELLOW:
				movementSpeed = FULL_SPEED;
				determineTurn(currentIntersection);
				break;
			case RED:
				movementSpeed = (hasTurned ? FULL_SPEED : STOP_SPEED);
				break;

			}
		} else if (this.carDirection.equals(CarDirection.NORTH) || this.carDirection.equals(CarDirection.SOUTH)) {
			switch (currentIntersection.getVLightColor()) {
			case GREEN:
			case YELLOW:
				movementSpeed = FULL_SPEED;
				determineTurn(currentIntersection);
				break;
			case RED:
				movementSpeed = (hasTurned ? FULL_SPEED : STOP_SPEED);
				break;

			}
		}
		this.setRotation(carDirection.getRotation());
	}

	@Override
	public void isLeaving(Intersection currentIntersection) {
		movementSpeed = FULL_SPEED;
	}

}
