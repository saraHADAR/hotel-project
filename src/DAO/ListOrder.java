package DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import MODEL.Guest;
import MODEL.Order;
import MODEL.Room;

/**
 * Manages a list of orders.
 */
public class ListOrder {

	public List<Order> arrayOrder;

	/**
	 * Constructor to initialize the order list.
	 */
	public ListOrder() {
		this.arrayOrder = new ArrayList<>();
	}

	/**
	 * Adds a new order to the list.
	 * 
	 * @param newOrder The order to add.
	 */
	public void addOrder(Order newOrder) {
		if (newOrder != null) {
			arrayOrder.add(newOrder);
		}
		System.out.println("נוספת בהצלחה");
	}

	/**
	 * Searches for an order by guest ID.
	 *
	 * @param string The ID of the guest.
	 * @return An Optional containing the found order, or empty if not found.
	 */
	private Optional<Order> findOrderByGuestId(String string) {
		return arrayOrder.stream()
				.filter(order -> order.getGuest().getPerson().getId() == string)
				.findFirst();
	}

	/**
	 * Searches for an order by guest object.
	 *
	 * @param guest The guest.
	 * @return An Optional containing the found order, or empty if not found.
	 */
	public Optional<Order> searchOrderByGuest(Guest guest) {
		return findOrderByGuestId(guest.getPerson().getId());
	}
	/**
	 * Searches for an order by room number.
	 *
	 * @param roomNumber The room number.
	 * @return An Optional containing the found order, or empty if not found.
	 */
	public Optional<Order> searchOrderByRoomNumber(int roomNumber) {
		return arrayOrder.stream()
				.filter(order -> order.getRoom().getNumRoom() == roomNumber)
				.findFirst();
	}

	/**
	 * Deletes an order for a given guest.
	 *
	 * @param guest The guest whose order is to be deleted.
	 * @return The room number of the deleted order, or -1 if no order was found.
	 */
	public int deleteOrder(Guest guest) {
		Optional<Order> orderOpt = findOrderByGuestId(guest.getPerson().getId());
		if (orderOpt.isPresent()) {
			Order orderToDelete = orderOpt.get();
			int roomNumber = orderToDelete.getRoom().getNumRoom();
			arrayOrder.remove(orderToDelete);
			System.out.println("ההזמנה נמחקה בהצלחה. חדר שהתפנה: " + roomNumber);
			return roomNumber;
		}
		System.out.println("לא נמצאה הזמנה עבור האורח.");
		return -1;
	}

	/**
	 * Prints orders that match the specified number of days.
	 *
	 * @param days Number of days to filter by.
	 */
	public void printOrdersByDays(int days) {
		arrayOrder.stream()
		.filter(order -> order.getNumDays() == days)
		.forEach(System.out::println);
	}

	/**
	 * Prints orders that match the specified date.
	 *
	 * @param date Date to filter by.
	 */
	public void printOrdersByDate(Date date) {
		arrayOrder.stream()
		.filter(order -> order.getOrderDate().equals(date))
		.forEach(System.out::println);
	}

	/**
	 * Prints the order of the specified guest.
	 *
	 * @param guest The guest.
	 */
	public void printOrderByGuest(Guest guest) {
		searchOrderByGuest(guest)
		.ifPresentOrElse(
				System.out::println,
				() -> System.out.println("לא נמצאה הזמנה עבור האורח.")
				);
	}

	/**
	 * Prints the order of the specified room.
	 *
	 * @param room The room.
	 */
	public void printOrderByRoom(Room room) {
		searchOrderByRoomNumber(room.getNumRoom())
		.ifPresentOrElse(
				System.out::println,
				() -> System.out.println("לא נמצאה הזמנה עבור חדר זה.")
				);
	}

	/**
	 * Prints all current orders.
	 */
	public void printOrders() {
		arrayOrder.forEach(System.out::println);
	}
}



