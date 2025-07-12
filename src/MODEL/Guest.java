package MODEL;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class that describes a hotel guest
 */
public class Guest implements Serializable {

	private Person person;
	private int numVisit;

	/**
	 * Constructs a new Guest with the specified person and number of visits.
	 *
	 * @param person   the person guest
	 * @param numVisit the number of visits the guest has made
	 */
	public Guest(Person person, int numVisit) {
		setPerson(person);
		setNumVisit(numVisit);
	}

	/**
	 * Getters and setters
	 * 
	 * @return
	 */
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(int numVisit) {
		this.numVisit = numVisit;
	}

	/**
	 * Returns a string representation of the guest.
	 */
	@Override
	public String toString() {
		return "Guest [person=" + person + ", numVisit=" + numVisit;
	}

	/**
	 * Generates a hash code based on the person and number of visits.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(numVisit, person);
	}

	/**
	 * Compares this guest to another object for equality. Two guests are considered
	 * equal if they have the same person.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Guest))
			return false;
		Guest other = (Guest) obj;
		return person.equals(other.getPerson());
	}

}
