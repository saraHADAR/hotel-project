package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import MODEL.Guest;
import MODEL.Order;
import SERVICE.ReportWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StackWalker.Option;

/**
 * Manages a list of hotel guests.
 */
public class ListGuest {

	private ListOrder listOrder;
	public List<Guest> arrayGuest;

	/**
	 * Constructor that initializes the guest list and listOrder.
	 * @param listOrder the associated list of orders
	 */
	public ListGuest(ListOrder listOrder) {
		this.listOrder = listOrder; 
		this.arrayGuest = new ArrayList<>();
	}

	/**
	 * Adds a new guest if their ID doesn't already exist.
	 *
	 * @param newGuest the guest to add
	 * @return true if added successfully, false if guest already exists
	 */
	public boolean addGuest(Guest newGuest) {
		if (findGuestByIdOpt(newGuest.getPerson().getId()).isEmpty()) {
			arrayGuest.add(newGuest);
			return true;
		}
		return false;
	}

	/**
	 * Finds a guest by ID (as Optional).
	 *
	 * @param id the ID to search for
	 * @return an Optional with the found guest or empty
	 */
	private Optional<Guest> findGuestByIdOpt(String id) {
		return arrayGuest.stream()
				.filter(g -> g.getPerson().getId().equals(id))
				.findFirst();
	}

	/**
	 * Public version of find by ID.
	 */
	public Optional<Guest> searchOfId(String id) {
		return findGuestByIdOpt(id);
	}

	/**
	 * Finds a guest by full name.
	 *
	 * @param fName first name
	 * @param lName last name
	 * @return Optional with guest if found
	 */
	public Optional<Guest> searchOfName(String fName, String lName) {
		return arrayGuest.stream()
				.filter(g -> g.getPerson().getFName().equals(fName) &&
						g.getPerson().getLName().equals(lName))
				.findFirst();
	}

	/**
	 * Finds a guest by phone number.
	 *
	 * @param phone the phone number
	 * @return Optional with guest if found
	 */
	public Optional<Guest> searchOfPhone(String phone) {
		return arrayGuest.stream()
				.filter(g -> g.getPerson().getPhone().equals(phone))
				.findFirst();
	}

	/**
	 * Deletes all guests who do not have any active orders.
	 */
	public void deleteCustomersWithoutOrders() {
		arrayGuest.removeIf(guest ->
		listOrder.searchOrderByGuest(guest).isEmpty()
				);
	}

	/**
	 * Generates a report file with all active guests (those with active orders).
	 *
	 * @param filePath where the report will be saved
	 */
	public void createActiveGuestsReport(String filePath) {
		List<String> activeGuests = arrayGuest.stream()
				.filter(guest -> listOrder.searchOrderByGuest(guest).isPresent())
				.map(Guest::toString)
				.collect(Collectors.toList());

		ReportWriter.writeReport(filePath, activeGuests);
	}

	/**
	 * Prints guests whose name matches.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public void printGuestsByName(String firstName, String lastName) {
		arrayGuest.stream()
		.filter(g -> g.getPerson().getFName().equals(firstName) &&
				g.getPerson().getLName().equals(lastName))
		.forEach(System.out::println);
	}

	/**
	 * Prints guest(s) by ID.
	 *
	 * @param id the ID
	 */
	public void printGuestsById(String id) {
		findGuestByIdOpt(id)
		.ifPresentOrElse(
				System.out::println,
				() -> System.out.println("לא נמצא אורח עם תז " + id)
				);
	}

	/**
	 * Prints all guests.
	 */
	public void printAllGuests() {
		arrayGuest.forEach(System.out::println);
	}

	/**
	 * Finds a guest by ID (legacy version that returns null if not found).
	 * Avoid using this — prefer searchOfId.
	 *
	 * @param id the guest ID
	 * @return Guest object or null if not found
	 */
	public Guest findGuestById(String id) {
		return findGuestByIdOpt(id).orElse(null);
	}
}
