package exercise2;

public enum CarDirection {
	NORTH(TrafficWorld.THIRD_ROTATION), EAST, SOUTH(TrafficWorld.FIRST_ROTATION), WEST(TrafficWorld.SECOND_ROTATION);
	private int rotation;
	
	private CarDirection(int rotation) {
		this.rotation = rotation;
	}
	private CarDirection(){
		this.rotation = TrafficWorld.NO_ROTATION;
	}
	
	public int getRotation(){
		return rotation;
	}
}