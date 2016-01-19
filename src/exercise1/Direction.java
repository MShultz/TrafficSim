package exercise1;

public enum Direction {
	NORTH(TrafficWorld.THIRD_ROTATION), EAST, SOUTH(TrafficWorld.FIRST_ROTATION), WEST(TrafficWorld.SECOND_ROTATION);
	
	private int rotation;
	

	private Direction(int rotation) {
		this.rotation = rotation;
	}
	private Direction(){
		this.rotation = TrafficWorld.NO_ROTATION;
	}
	
	public int getRotation(){
		return rotation;
	}
}