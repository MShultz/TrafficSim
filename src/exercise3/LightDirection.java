package exercise3;

public enum LightDirection {
	NORTH(TrafficWorld.SECOND_ROTATION), EAST(TrafficWorld.FIRST_ROTATION), SOUTH, WEST(TrafficWorld.THIRD_ROTATION);
	private int rotation;

	private LightDirection(int rotation) {
		this.rotation = rotation;
	}

	private LightDirection() {
		this.rotation = TrafficWorld.NO_ROTATION;
	}

	public int getRotation() {
		return rotation;
	}
}
