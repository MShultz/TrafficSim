package exercise2;

import java.awt.Color;
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
	public GreenfootImage background;
	private Road[] horizontalRoads = new Road[NUM_WEST_ROADS];
	private Road[] verticalRoads = new Road[NUM_NORTH_ROADS];
	private int roadX, roadY, carRightX, carRightY, carLeftX, carLeftY;

	public TrafficWorld() {
		super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE);
		createBackground();
		drawHorizontalRoads();
		drawVerticalRoads();
		drawWestCars();
		drawNorthCars();
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

	public void drawWestCars() {
		carRightY = (3 * ROAD_WIDTH) / 4;
		carLeftY = ROAD_WIDTH / 4;

		for (int k = 0; k < NUM_WEST_ROADS; k++) {
			this.addObject(new Car(CarDirection.WEST), rand.nextInt(WORLD_WIDTH) + 1, carLeftY);
			this.addObject(new Car(CarDirection.EAST), rand.nextInt(WORLD_WIDTH) + 1, carRightY);
			carLeftY += ((ROAD_WIDTH) + WEST_GAP_SIZE);
			carRightY += ((ROAD_WIDTH) + WEST_GAP_SIZE);
		}
	}

	public void drawNorthCars() {
		carRightX = (3 * ROAD_WIDTH) / 4;
		carLeftX = ROAD_WIDTH / 4;

		for (int l = 0; l < NUM_NORTH_ROADS; l++) {
			this.addObject(new Car(CarDirection.NORTH), carRightX, rand.nextInt(WORLD_HEIGHT) + 1);
			this.addObject(new Car(CarDirection.SOUTH), carLeftX, rand.nextInt(WORLD_HEIGHT) + 1);
			carRightX += (ROAD_WIDTH + NORTH_GAP_SIZE);
			carLeftX += (ROAD_WIDTH + NORTH_GAP_SIZE + CELL_SIZE);

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

}
