package VIEW;

import java.util.Date;
import java.util.Scanner;

import DAO.ListRoom;
import MODEL.Guest;
import MODEL.Order;
import MODEL.Person;
import MODEL.Room;
import SERVICE.Hotel;
import SERVICE.ReportWriter;

public class HotelMenu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Hotel hotel = Hotel.getInstance();

		// add room in hotel
		hotel.getRooms().addRoom(new Room(101, 1, 2));
		hotel.getRooms().addRoom(new Room(102, 1, 2));
		hotel.getRooms().addRoom(new Room(201, 2, 1));
		hotel.getRooms().addRoom(new Room(202, 2, 3));


		// add guest
		Guest g1 = new Guest(new Person("111111111", "נועה", "כהן", "מינץ 23", "0501111111"), 2);
		Guest g2 = new Guest(new Person("222222222", "דוד", "לוי", "וולינשטיין 62", "0502222222"), 3);
		Guest g3 = new Guest(new Person("333333333", "שרה", "כהן", "אדלסון 32", "0503333333"), 0);

		hotel.getGuests().addGuest(g1);
		hotel.getGuests().addGuest(g2);
		hotel.getGuests().addGuest(g3);

		// הוספת הזמנות
		hotel.getOrders().addOrder(new Order(g1, new Room(101, 1, 2), new Date(), 3));
		hotel.getOrders().addOrder(new Order(g2, new Room(102, 2, 1), new Date(), 2));

		// תפריט
		boolean running = true;
		while (running) {
			System.out.println("\nבחר פעולה:");
			System.out.println("1. הוספת הזמנה חדשה");
			System.out.println("2. מחיקת הזמנה קיימת");
			System.out.println("3. הדפסת פרטי המלון");
			System.out.println("4. הדפסת כל החדרים");
			System.out.println("5. חיפוש חדר לפי מספר חדר");
			System.out.println("9. יציאה");
			System.out.println("7. הדפסת כל פרטי הלקוחות");
			System.out.println("8. הדפסת כל ההזמנות");
			System.out.println("6. דוח חדרים תפוסים ופנויים");


			int choice = scanner.nextInt();
			scanner.nextLine(); // ניקוי קלט אחרי nextInt

			switch (choice) {
			case 1:
				// שגג םרגקר
				System.out.println("\n--- הוספת הזמנה חדשה ---");

				String id = getValidId(scanner);
				String firstName = getValidName(scanner, "שם פרטי");
				String lastName = getValidName(scanner, "שם משפחה");
				String address = getValidAddress(scanner);
				String phone = getValidPhone(scanner);

				int roomNum = 0;
				while (roomNum == 0) {
					System.out.print("הכנס מספר חדר: ");
					roomNum = scanner.nextInt();
					scanner.nextLine(); // ניקוי קלט
					if (hotel.findRoomByNumber(roomNum).isEmpty()) {
						System.out.println("חדר לא קיים, נסה שוב.");
						roomNum = 0;
					}
				}

				int numOfDays = 0;
				while (numOfDays <= 0) {
					System.out.print("הכנס מספר ימים: ");
					if (scanner.hasNextInt()) {
						numOfDays = scanner.nextInt();
						scanner.nextLine();
						if (numOfDays <= 0) {
							System.out.println("מספר ימים חייב להיות גדול מ-0.");
						}
					} else {
						System.out.println("הכנס מספר תקין.");
						scanner.nextLine();
					}
				}

				Person newPerson = new Person(id, firstName, lastName, address, phone);
				Guest newGuest = new Guest(newPerson, roomNum);
				hotel.getGuests().addGuest(newGuest);

				boolean added = hotel.addOrder(newGuest, hotel.findRoomByNumber(roomNum).orElse(null));

				if (added) {
					System.out.println("ההזמנה נוספה בהצלחה!");
				} else {
					System.out.println("ההזמנה נכשלה - החדר תפוס או לא קיים.");
				}
				break;

			case 2:
				//delete order
				System.out.println("\n--- מחיקת הזמנה קיימת ---");
				System.out.print("הכנס תעודת זהות של הלקוח למחיקת הזמנה: ");
				String idToDelete = scanner.nextLine().trim();

				// search coustomer by id
				Guest guestToDelete = hotel.getGuests().findGuestById(idToDelete);

				if (guestToDelete != null) {
					boolean deleted = hotel.deleteOrder(guestToDelete);

					if (deleted) {
						System.out.println("ההזמנה נמחקה בהצלחה.");
					} else {
						System.out.println("לא נמצאה הזמנה עבור הלקוח.");
					}
				} else {
					System.out.println("לקוח לא נמצא במערכת.");
				}
				break;


			case 3:
				// print detail hotel
				System.out.println("\n--- פרטי המלון ---");
				System.out.println(hotel); 
				break;
			case 4:
				// print all room
				System.out.println("\n--- רשימת חדרים ---");
				hotel.getRooms().printAllRooms();
				break;

			case 5:
				// search room by numRoom
				System.out.println("\n--- חיפוש חדר ---");
				System.out.print("הכנס מספר חדר: ");
				int searchRoom = scanner.nextInt();
				scanner.nextLine();
				hotel.findRoomByNumber(searchRoom)
				.ifPresentOrElse(
						room -> System.out.println(room),
						() -> System.out.println("חדר לא נמצא.")
						);
				break;

			case 6:
				String filePath = "room_report.txt"; 
				hotel.generateRoomReport(filePath);
				System.out.println("תוכן הדוח:");
				System.out.println(ReportWriter.readReport(filePath));

				break;

			case 7:
				// print all guest
				System.out.println("\n--- רשימת לקוחות ---");
				hotel.getGuests().printAllGuests();
				break;

			case 8:
				// print all order
				System.out.println("\n--- רשימת הזמנות ---");
				hotel.getOrders().printOrders();
				break;

			case 9:
				// outside
				System.out.println("להתראות!");
				running = false;
				break;

			default:
				System.out.println("בחירה לא חוקית. נסה שוב.");
			}
		}

		scanner.close();
	}

	/**
	 * Integrity check
	 * @param scanner
	 * @param prompt
	 * @return Option to change input
	 */
	private static String getInput(Scanner scanner, String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		while (input.trim().isEmpty()) {
			System.out.print("הקלט לא יכול להיות ריק. " + prompt + ": ");
			input = scanner.nextLine();
		}
		return input;
	}

	private static String getValidId(Scanner scanner) {
		System.out.print("הכנס תעודת זהות (9 ספרות): ");
		String id = scanner.nextLine().trim();
		while (!id.matches("\\d{9}")) {
			System.out.println("תעודת זהות לא תקינה. נסה שוב:");
			id = scanner.nextLine().trim();
		}
		return id;
	}

	private static String getValidName(Scanner scanner, String fieldName) {
		System.out.print("הכנס " + fieldName + ": ");
		String name = scanner.nextLine().trim();
		while (!name.matches("[a-zA-Zא-ת\\s]+")) {
			System.out.println(fieldName + " חייב להכיל אותיות בלבד. נסה שוב:");
			name = scanner.nextLine().trim();
		}
		return name;
	}

	private static String getValidPhone(Scanner scanner) {
		System.out.print("הכנס מספר טלפון (10 ספרות): ");
		String phone = scanner.nextLine().trim();
		while (!phone.matches("\\d{10}")) {
			System.out.println("מספר טלפון לא תקין. נסה שוב:");
			phone = scanner.nextLine().trim();
		}
		return phone;
	}

	private static String getValidAddress(Scanner scanner) {
		System.out.print("הכנס כתובת: ");
		String address = scanner.nextLine().trim();
		while (address.isEmpty()) {
			System.out.println("כתובת לא יכולה להיות ריקה. נסה שוב:");
			address = scanner.nextLine().trim();
		}
		return address;
	}
}
