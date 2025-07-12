package SERVICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import DAO.ListGuest;
import DAO.ListOrder;
import DAO.ListRoom;
import MODEL.Guest;
import MODEL.Order;
import MODEL.Room;

/**
 * A class that manages the hotel operations.
 */
public class Hotel {

	private String hotelName;
	private String address;
	private String phone;
	private ListRoom rooms; // List of rooms
	private ListGuest guests; // List of guests
	private ListOrder orders; // List of orders
	private static Hotel instance; // Static variable to hold the singleton instance

	/**
	 * Private constructor to initialize hotel details and lists.
	 * 
	 * @param hotelName Name of the hotel.
	 * @param address Address of the hotel.
	 * @param phoneNumber Phone number of the hotel.
	 */
	private Hotel(String hotelName, String address, String phoneNumber) {
		setHotelName(hotelName);
		setAddress(address);
		setPhone(phoneNumber);
		this.orders = new ListOrder();
		this.rooms = new ListRoom();
		this.guests = new ListGuest(orders);
	}

	/**
	 * Returns the single instance of the Hotel (Singleton pattern).
	 * 
	 * @return Hotel instance.
	 */
	public static Hotel getInstance() {
		if (instance == null) 
			instance = new Hotel("Grand Hotel", "123 Main St", "087614185");
		return instance;
	}

	/**
	 *  Getters and Setters
	 */
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ListRoom getRooms() {
		return rooms;
	}

	public void setRooms(ListRoom rooms) {
		this.rooms = rooms;
	}

	public ListGuest getGuests() {
		return guests;
	}

	public void setGuests(ListGuest guests) {
		this.guests = guests;
	}

	public ListOrder getOrders() {
		return orders;
	}

	public void setOrders(ListOrder orders) {
		this.orders = orders;
	}

	public static void setInstance(Hotel instance) {
		Hotel.instance = instance;
	}

	/**
	 * Returns a string representation of the hotel details.
	 */
	@Override

	public String toString() {
		return "שם המלון: " + hotelName + "\n" +
				"כתובת: " + address + "\n" +
				"טלפון: " + phone;
	};


	/**
	 * Returns the hash code for the Hotel object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(address, guests, hotelName, orders, phone, rooms);
	}

	/**
	 * Checks if two Hotel objects are equal based on their attributes.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Hotel))
			return false;
		Hotel other = (Hotel) obj;
		return Objects.equals(address, other.address) && Objects.equals(guests, other.guests)
				&& Objects.equals(hotelName, other.hotelName) && Objects.equals(orders, other.orders)
				&& Objects.equals(phone, other.phone) && Objects.equals(rooms, other.rooms);
	}

	/**
	 * Adds a new order for a guest if the room is available.
	 * 
	 * @param guest The guest making the reservation.
	 * @param numRoom The room to be reserved.
	 */
	public boolean addOrder(Guest guest, Room numRoom) {

		boolean roomAvailable = false;

		for (Room room : rooms.getArrayRoom()) {
			if (room.getNumRoom() == numRoom.getNumRoom() && !room.isActive()) {
				roomAvailable = true;
				room.setActive(true);
				break;
			}
		}

		if (roomAvailable) {
			guests.addGuest(guest);

			Order newOrder = new Order(guest, numRoom);
			orders.addOrder(newOrder);
			return true;
		}
		return false;
	}

	/**
	 * Deletes an existing order for a guest and frees up the room.
	 * 
	 * @param guest The guest whose order is to be deleted.
	 */
	public boolean deleteOrder(Guest guest) {

		int roomNumber = orders.deleteOrder(guest);

		if (roomNumber != -1) {
			findRoomByNumber(roomNumber).ifPresent(room -> room.setActive(false));
			return true;
		}
		return false;
	}

	/**
	 * Finds a room by its number.
	 * 
	 * @param roomNumber The room number to search for.
	 * @return An Optional containing the found room, or empty if not found.
	 */
	//TODO: call roomList function

	public Optional<Room> findRoomByNumber(int roomNumber) {
		return rooms. getArrayRoom().stream()
				.filter(room -> room.getNumRoom() == roomNumber)
				.findFirst();
	}
	
	/**
	 * Generates a room report and writes it to a file.
	 * The report will indicate whether each room is occupied or available.
	 * 
	 * @param filePath The path where the report file will be saved.
	 */
	public void generateRoomReport(String filePath) {

	    // Initialize a list to hold the lines of the report.
	    List<String> reportLines = new ArrayList<>();

	    // Loop through each room in the list of rooms.
	    for (Room room : this.getRooms().getArrayRoom()) {
	        // Check if the room is occupied by any order directly with the orders list.
	        boolean isOccupied = this.getOrders().arrayOrder
	                .stream()
	                .anyMatch(order -> order.getRoom().equals(room)); // Check if room is in any order

	        // Add an appropriate line to the report based on whether the room is occupied or not.
	        if (isOccupied) {
	            reportLines.add("חדר " + room.getNumRoom() + " - תפוס"); // Room is occupied
	        } else {
	            reportLines.add("חדר " + room.getNumRoom() + " - פנוי"); // Room is available
	        }
	    }

	    // Write the report lines to the file.
	    ReportWriter.writeReport(filePath, reportLines);

	    // Print confirmation that the report was successfully created.
	    System.out.println("הדוח נוצר בהצלחה בקובץ: " + filePath);
	}
}
