package exercise3;


import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class StreetLight extends Actor {
	private String[] lightFiles = { "images\\trafficLightGreen.png", "images\\trafficLightYellow.png",
			"images\\trafficLightRed.png" };

	public static enum LightColor {
		GREEN, YELLOW, RED;
	}
	public StreetLight(LightColor initialColor, LightDirection direction) {
		GreenfootImage trafficLight = new GreenfootImage(lightFiles[initialColor.ordinal()]);
		this.setImage(trafficLight);
		this.setRotation(direction.getRotation());
	}

	public void setLightColor(LightColor newColor) {
		setImage(lightFiles[newColor.ordinal()]);
	}

}
