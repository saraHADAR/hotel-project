package MODEL;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class that describes a hotel room
 */
public class Room implements Serializable{

	private int numRoom;
	private int floor;
	private int level;
	private boolean isActive;
	private static double startPrice;

	/**
	 * Constructor to initialize a room with its details.
	 * @param numRoom the room number
	 * @param floor the floor number
	 * @param level the level of the room
	 * @param isActive status if the room is active or not
	 */
	public Room(int numRoom, int floor, int level) {
		setNumRoom(numRoom);
		setFloor(floor);
		setLevel(level);
		setActive(false);
	}

	/**
	 * Getters and Setters for the Room class.
	 * Getters return the current value of the property.
	 * Setters allow you to update the value of the property.
	 */
	public int getNumRoom() {
		return numRoom;
	}

	private void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static double getStartPrice() {
		return startPrice;
	}

	public static void setStartPrice(double startPrice) {
		Room.startPrice = startPrice;
	}

	/**
	 * Converts the Room object into a string representation.
	 * @return a string containing the room details
	 */
	@Override
	public String toString() {
		return "Room [numRoom=" + numRoom + ", floor=" + floor + ", level=" + level +
				", isActive=" + isActive + "]";
	}

	/**
	 * Generates a hash code for the Room object based on its properties.
	 * @return hash code value for the room
	 */
	@Override
	public int hashCode() {
		return Objects.hash(floor, isActive, level, numRoom);
	}

	/**
	 * Compares this Room object with another object for equality.
	 * @param obj the object to compare with
	 * @return true if the rooms are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Room)) 
			return false;
		Room other = (Room) obj;
		return numRoom == other.numRoom;
	}
}


