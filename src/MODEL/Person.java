package MODEL;

import java.io.Serializable;

/**
 * This class represents a Person with attributes such as ID, first name,
 *  last name, address, and phone.
 * It includes a constructor, getters, setters, and a toString method.
 * 
 * @author This User
 *
 */
public class Person  implements Serializable{

    private String id;        // ID of the person
    private String FName;     // First name of the person
    private String LName;     // Last name of the person
    private String address;   // Address of the person
    private String phone;     // Phone number of the person

    /**
     * Constructor to initialize a Person object with the provided parameters.
     * 
     * @param id      ID of the person
     * @param fName   First name of the person
     * @param lName   Last name of the person
     * @param address Address of the person
     * @param phone   Phone number of the person
     */
    public Person(String id, String fName, String lName, String address, String phone) {
        setId(id);
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setPhone(phone);
    }

    /**
     * Returns the ID of the person.
     * 
     * @return the ID of the person
     */
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String fName) {
        FName = fName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String lName) {
        LName = lName;
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

    /**
     * Converts the Person object to a string representation.
     * 
     * @return a string representing the person object
     */
    @Override
    public String toString() {
        return "Person [id=" + id + ", FName=" + FName + ", LName=" + LName +
        		", address=" + address + ", phone="+ phone + "]";
    }
}
