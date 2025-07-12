package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import MODEL.Guest;
import MODEL.Room;

/**
 * Manages a list of hotel rooms.
 * This class is responsible for holding and managing all hotel rooms.
 */
public class ListRoom {

	private List<Room> arrayRoom;

	/**
	 * Constructor - Initializes an empty list of rooms.
	 */
	public ListRoom() {
		this.arrayRoom = new ArrayList<>();
	}

	/**
	 * Adds a new room to the list.
	 *
	 * @param newRoom the new room to add
	 */
	public void addRoom(Room newRoom) {
		if (newRoom != null) {
			arrayRoom.add(newRoom);
		}
	}

	/**
	 * Prints the room that matches the given room number.
	 * 
	 * @param numRoom the room number to search for
	 */
	public void printRoomByNum(int numRoom) {
		arrayRoom.stream()
		.filter(room -> room.getNumRoom() == numRoom)
		.forEach(System.out::println);
	}

	/**
	 * Prints all rooms that have the specified level.
	 * 
	 * @param level the level to search for
	 */
	public void printRoomByLevel(int level) {
		arrayRoom.stream()
		.filter(room -> room.getLevel() == level)
		.forEach(System.out::println);
	}

	/**
	 * Prints all rooms that are located on the specified floor.
	 * 
	 * @param floor the floor to search for
	 */
	public void printRoomByFloor(int floor) {
		arrayRoom.stream()
		.filter(room -> room.getFloor() == floor)
		.forEach(System.out::println);
	}

	/**
	 * Prints all rooms.
	 */
	public void printAllRooms() {
		arrayRoom.stream()
		.forEach(System.out::println);
	}
	
    public List<Room> getArrayRoom() {
        return arrayRoom;
    }
}
