package exercise1;

import java.awt.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Road extends Actor {
	int roadHeight, roadWidth;
	private Color roadGrey = new Color(25, 25, 25);
	GreenfootImage road;

	public Road(int roadHeight, int roadWidth) {
		this.roadHeight = roadHeight;
		this.roadWidth = roadWidth;
		fillImage();

	}

	public void fillImage() {
		road = new GreenfootImage(roadWidth, roadHeight);
		road.setColor(roadGrey);
		road.fill();
		this.setImage(road);
	}
}
