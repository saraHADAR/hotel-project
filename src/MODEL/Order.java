package MODEL;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A class that represents a hotel order.
 */
public class Order implements Serializable{

	private static int counter = 0;
	private final int NUM_ORDER;
	private Guest guest;
	private Room room;
	private Date orderDate;
	private int numDays;

	/**
	 * Constructs a new Order with all properties specified.
	 * 
	 * @param numorder the order number
	 * @param guest the guest associated with the order
	 * @param room the room booked
	 * @param orderDate the date the order was made
	 * @param numDays the number of days for the booking
	 */
	public Order(Guest guest, Room room, Date orderDate, int numDays) {
		this.NUM_ORDER = ++counter;
		setGuest(guest);
		setRoom(room);
		setOrderDate(orderDate);
		setNumDays(numDays);
	}

	/**
	 * Constructs a new Order with default date (today) and 1 day booking.
	 * 
	 * @param guest the guest associated with the order
	 * @param room the room booked
	 */
	public Order(Guest guest, Room room) {
		this(guest, room, new Date(System.currentTimeMillis()), 1);
	}

	/**
	 * Getters And Setters
	 * @return
	 */
	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Order.counter = counter;
	}

	public Guest getGuest() {
		return guest;
	}

	private void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	private void setRoom(Room room) {
		this.room = room;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public int getNUM_ORDER() {
		return NUM_ORDER;
	}

	/**
	 * Returns a string representation of the order.
	 */
	@Override
	public String toString() {
		return "Order [NUM_ORDER=" + NUM_ORDER + ", guest=" + guest + ", room=" + room +
				", orderDate=" + orderDate + ", numDays=" + numDays + "]";
	}

	/**
	 * Generates a hash code for the order.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(guest, room, orderDate, numDays);
	}

	/**
	 * Compares this order to another object for equality.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		return numDays == other.numDays;
	}

	/**
	 * Calculates the final price of the order.
	 * The price is based on the number of days multiplied by the room's starting price.
	 * A discount is applied if the guest is a business guest.
	 * 
	 * @return the total price to pay
	 */
	public double calcPrice() {
		double basePrice = numDays * Room.getStartPrice();
		double discount = 0.0;

		if (guest instanceof BussinesGuest) {
			discount = BussinesGuest.getDiscountPercent();
			basePrice = basePrice * (1 - discount / 100);
		}
		return basePrice;
	}
}