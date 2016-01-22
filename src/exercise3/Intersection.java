package exercise3;

import java.util.ArrayList;
import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor {
	
	Random rand = new Random();
	final static private int GREEN_COUNTER = 175;
	final static private int YELLOW_COUNTER = 90;
	final static private int RED_COUNTER = GREEN_COUNTER + YELLOW_COUNTER;
	private int lightCounter = 0;
	final static private int RADIUS = 45;
	public StreetLight.LightColor verticalLightColor = StreetLight.LightColor.GREEN;
	public StreetLight.LightColor horizontalLightColor = StreetLight.LightColor.RED;
	private StreetLight streetLightSouth, streetLightNorth, streetLightEast, streetLightWest;
	GreenfootImage intersectionImage;
	ArrayList<IntersectionListener> inside, approaching;
	ArrayList<IntersectionListener> wasInside = new ArrayList<IntersectionListener>();
	ArrayList<IntersectionListener> wasApproaching = new ArrayList<IntersectionListener>();
	ArrayList<IntersectionListener> turnable = new ArrayList<IntersectionListener>();

	public Intersection() {
		intersectionImage = new GreenfootImage(TrafficWorld.ROAD_WIDTH, TrafficWorld.ROAD_WIDTH);
		this.setImage(intersectionImage);

	}

	public void addLights() {

		createLights();
		getWorld().addObject(streetLightSouth, this.getX(),
				getY() + TrafficWorld.ROAD_WIDTH / 2 + streetLightSouth.getImage().getHeight() / 2);
		getWorld().addObject(streetLightNorth, this.getX(),
				getY() - (TrafficWorld.ROAD_WIDTH / 2 + streetLightSouth.getImage().getHeight() / 2));
		getWorld().addObject(streetLightEast,
				this.getX() + TrafficWorld.ROAD_WIDTH / 2 + streetLightSouth.getImage().getHeight() / 2, this.getY());
		getWorld().addObject(streetLightWest,
				this.getX() - (TrafficWorld.ROAD_WIDTH / 2 + streetLightSouth.getImage().getHeight() / 2), this.getY());

	}

	public void createLights() {
		streetLightSouth = new StreetLight(verticalLightColor, LightDirection.SOUTH);
		streetLightNorth = new StreetLight(verticalLightColor, LightDirection.NORTH);
		streetLightEast = new StreetLight(horizontalLightColor, LightDirection.EAST);
		streetLightWest = new StreetLight(horizontalLightColor, LightDirection.WEST);

	}

	public StreetLight.LightColor getVLightColor() {
		return verticalLightColor;
	}

	public StreetLight.LightColor getHLightColor() {
		return horizontalLightColor;
	}

	public void setVLightColor(StreetLight.LightColor color) {
		verticalLightColor = color;
	}

	public void setHLightColor(StreetLight.LightColor color2) {
		horizontalLightColor = color2;
	}

	@SuppressWarnings("unchecked")
	public void createArrays() {
		inside = (ArrayList<IntersectionListener>) getIntersectingObjects(IntersectionListener.class);
		approaching = (ArrayList<IntersectionListener>) getObjectsInRange(RADIUS, IntersectionListener.class);
		assert(approaching != null);
		assert(inside != null);
	}

	public void notifyIfIn() {
		for (IntersectionListener il : wasApproaching) {
			if (inside.contains(il)) {
				il.isInside(this);
			}
		}
	}

	public void notifyIfApproaching() {
		for (IntersectionListener il : approaching) {
			if (!wasApproaching.contains(il)) {
				il.isApproaching(this);
			}
		}
	}

	public void notifyIfLeaving() {
		for (IntersectionListener il : wasInside) {
			if (!inside.contains(il)) {
				il.isLeaving(this);
				
			}
		}
	}
	


	public void changeVerticalLights() {
		streetLightSouth.setLightColor(verticalLightColor);
		streetLightNorth.setLightColor(verticalLightColor);
	}

	public void changeHorizontalLights() {
		streetLightEast.setLightColor(horizontalLightColor);
		streetLightWest.setLightColor(horizontalLightColor);
	}

	public void act() {
		createArrays();
		notifyIfIn();
		notifyIfApproaching();
		notifyIfLeaving();
		wasApproaching = approaching;
		wasInside = inside;
		lightCounter++;
		switch (verticalLightColor) {
		case GREEN:
			if (lightCounter == GREEN_COUNTER) {
				setVLightColor(StreetLight.LightColor.YELLOW);
				changeVerticalLights();
				lightCounter = 0;
			}
			break;
		case YELLOW:
			if (lightCounter == YELLOW_COUNTER) {
				setVLightColor(StreetLight.LightColor.RED);
				changeVerticalLights();
				setHLightColor(StreetLight.LightColor.GREEN);
				changeHorizontalLights();

				lightCounter = 0;
			}
			break;
		case RED:
			if (lightCounter == GREEN_COUNTER) {
				setHLightColor(StreetLight.LightColor.YELLOW);
				changeHorizontalLights();
			} else if (lightCounter == RED_COUNTER) {
				setVLightColor(StreetLight.LightColor.GREEN);
				changeVerticalLights();

				setHLightColor(StreetLight.LightColor.RED);
				changeHorizontalLights();
				lightCounter = 0;
			}
			break;
		}

	}

}
