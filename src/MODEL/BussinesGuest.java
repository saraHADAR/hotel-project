package MODEL;

import java.util.Objects;

/**
 * A class that describes a business guest.
 * This class inherits from the Guest class and adds a unique business guest code and a 
 * discount percentage.
 */
public class BussinesGuest extends Guest {

	private int BussinesGuestCode;
	private static double discountPercent=10.0; 

	/**
	 * Constructs a BusinessGuest with the specified person, number of visits, 
	 * and business guest code.
	 *
	 * @param person the person associated with the business guest
	 * @param numVisit the number of visits the guest has made
	 * @param bussinesGuestCode the unique code identifying the business guest
	 */
	public BussinesGuest(Person person, int numVisit, int bussinesGuestCode) {
		super(person, numVisit);
		setBussinesGuestCode(bussinesGuestCode);
	}

	/**
	 * Getters and setters for business guest fields.
	 */
	public int getBussinesGuestCode() {
		return BussinesGuestCode;
	}

	public void setBussinesGuestCode(int bussinesGuestCode) {
		BussinesGuestCode = bussinesGuestCode;
	}

	public static double getDiscountPercent() {
		return discountPercent;
	}

	public static void setDiscountPercent(double discountPercent) {
		BussinesGuest.discountPercent = discountPercent;
	}

	/**
	 * Returns a string representation of the BusinessGuest, including the inherited Guest 
	 * fields and the business guest code.
	 */
	@Override
	public String toString() {
		return super.toString() + ", BussinesGuest [BussinesGuestCode=" + BussinesGuestCode + "]";
	}

	/**
	 * Checks if this BusinessGuest is equal to another object.
	 * Equality is based on the parent class (Guest) and the business guest code.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BussinesGuest other = (BussinesGuest) obj;
		return BussinesGuestCode == other.BussinesGuestCode;
	}

	/**
	 * Returns a hash code value for the object,
	 * based on the parent class and the business guest code.
	 */
	@Override
	public int hashCode() {
		final int prime = 100;
		int result = super.hashCode();
		result = prime * result + BussinesGuestCode;
		return result;
	}

	/**
	 * we add function over project
	 * 
	 * Calculates the discounted price based on the original price
	 * and the static discount percentage.
	 *
	 * @param originalPrice the original price before discount
	 * @return the price after the business guest discount
	 */
	public double calculateDiscountedPrice(double originalPrice) {
		return originalPrice * (1 - discountPercent / 100);
	}

}
