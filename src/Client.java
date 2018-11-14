/**
 * The following class defines the elements of a Client object, and has several
 * getter and setter methods to allow other classes to access these elements
 * @author WaleyAndy
 *
 */
public class Client {
	private int ID;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String type;
	
	public Client(int id, String fn, String ln, String ad, String pc, String pn, String t) {
		setID(id);
		setFirstName(fn);
		setLastName(ln);
		setAddress(ad);
		setPostalCode(pc);
		setPhoneNumber(pn);
		setType(t);
	}
	/**
	 * getter method for Client ID
	 * @return: Client ID number
	 */
	public int getID() {
		return ID;
	}
	/**
	 * setter method for Client ID
	 * @param iD: Client ID number
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * getter method for Client first name
	 * @return: Client first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * setter method for Client first name
	 * @param firstName: Client first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * getter method for Client last name
	 * @return: Client last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * setter method for Client last name
	 * @param lastName: Client last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * getter method for Client address
	 * @return: Client address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * setter method for Client address
	 * @param address: Client Address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * getter method for business type
	 * @return: business type (R or C)
	 */
	public String getType() {
		return type;
	}
	/** 
	 * setter method for business type
	 * @param type: business type (R or C)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * getter method for postal code
	 * @return: postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * setter method for postal code
	 * @param postalCode: postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * getter method for client phone number
	 * @return: client phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * setter method for client phone number
	 * @param phoneNumber: client phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * method that returns string of client details
	 */
	@Override
	public String toString() {
		return "ID:" +ID+" FirstName: "+firstName+" LastName: "+lastName+" Address: "+ address+" PostalCode: "+ postalCode+" PhoneNumber: "+phoneNumber+" Type: "+type;
		
	}
}
