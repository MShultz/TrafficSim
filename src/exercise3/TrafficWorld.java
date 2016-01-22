package exercise3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	private Random rand = new Random();

	public static final int WORLD_WIDTH = 1000;
	public static final int WORLD_HEIGHT = 750;
	private static final int CELL_SIZE = 1;
	public static final int ROAD_WIDTH = 50;
	private static final int NUM_WEST_ROADS = 5;
	private static final int NUM_NORTH_ROADS = 7;
	private static final int WEST_GAP_SIZE = ((WORLD_HEIGHT - (NUM_WEST_ROADS * ROAD_WIDTH)) / (NUM_WEST_ROADS - 1));
	private static final int NORTH_GAP_SIZE = ((WORLD_WIDTH - (NUM_NORTH_ROADS * ROAD_WIDTH)) / (NUM_NORTH_ROADS - 1));
	public static final int HALF_CAR_LENGTH = 20;
	public static final int NO_ROTATION = 0;
	public static final int FIRST_ROTATION = 90;
	public static final int SECOND_ROTATION = 180;
	public static final int THIRD_ROTATION = 270;
	public static final int MAX_COUNTER_NUM = 100;
	public static final int ENSURE_LARGER_SIZE = 50;
	public GreenfootImage background;
	private Road[] horizontalRoads = new Road[NUM_WEST_ROADS];
	private Road[] verticalRoads = new Road[NUM_NORTH_ROADS];
	private int roadX, roadY, carX, carY;
	private int carNum = 1;

	public TrafficWorld() {
		super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE);
		createBackground();
		drawHorizontalRoads();
		drawVerticalRoads();
		createIntersections();
		

	}

	public void createBackground() {
		GreenfootImage background = this.getBackground();
		Color colorGreen = new Color(0, 102, 22);
		background.setColor(colorGreen);
		background.fill();
	}

	public void drawHorizontalRoads() {
		roadX = WORLD_WIDTH / 2;
		roadY = ROAD_WIDTH / 2;

		for (int i = 0; i < NUM_WEST_ROADS; i++) {
			Road road = new Road(ROAD_WIDTH, WORLD_WIDTH);
			horizontalRoads[i] = road;
			this.addObject(road, roadX, roadY);
			roadY += (WEST_GAP_SIZE + ROAD_WIDTH);
		}
	}

	public void drawVerticalRoads() {
		roadX = ROAD_WIDTH / 2;
		roadY = WORLD_HEIGHT / 2;

		for (int j = 0; j < NUM_NORTH_ROADS; j++) {
			Road road = new Road(WORLD_HEIGHT, ROAD_WIDTH);
			verticalRoads[j] = road;
			this.addObject(road, roadX, roadY);
			roadX += (NORTH_GAP_SIZE + ROAD_WIDTH + CELL_SIZE);
		}
	}

	public void generateCar() {
		Car car = new Car(CarDirection.values()[rand.nextInt(4)]);
		switch (car.getCarDirection()) {
		case NORTH:
			carY = WORLD_HEIGHT;
			carX = (((3 * ROAD_WIDTH) / 4) + (rand.nextInt(NUM_NORTH_ROADS) * (ROAD_WIDTH + NORTH_GAP_SIZE)));
			this.addObject(car, carX, carY);
			break;
		case SOUTH:
			carY = 0;
			carX = ((ROAD_WIDTH / 4) + (rand.nextInt(NUM_NORTH_ROADS) * (ROAD_WIDTH + NORTH_GAP_SIZE)));
			this.addObject(car, carX, carY);
			break;
		case EAST:
			carY = (((3 * ROAD_WIDTH) / 4) + (rand.nextInt(NUM_WEST_ROADS) * (ROAD_WIDTH + WEST_GAP_SIZE)));
			carX = 0;
			this.addObject(car, carX, carY);
			break;
		case WEST:
			carY = ((ROAD_WIDTH / 4) + (rand.nextInt(NUM_WEST_ROADS) * (ROAD_WIDTH + WEST_GAP_SIZE)));
			carX = WORLD_WIDTH;
			this.addObject(car, carX, carY);
			break;
		}
	}

	public void createIntersections() {
		for (int m = 0; m < NUM_WEST_ROADS; m++) {
			for (int n = 0; n < NUM_NORTH_ROADS; n++) {
				Intersection intersection = new Intersection();
				this.addObject(intersection, verticalRoads[n].getX(), horizontalRoads[m].getY());
				intersection.addLights();
			}
		}
	}

	public void generateCarNum() {
		if (carNum == 0) {
			carNum = rand.nextInt(MAX_COUNTER_NUM) + ENSURE_LARGER_SIZE;
			generateCar();
		}
	}

	public void act() {
		generateCarNum();
		carNum--;
	}

}
