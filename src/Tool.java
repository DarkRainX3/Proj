/**
 * The following class defines the elements of a Tool object, and has several
 * getter methods to allow other classes to access these elements
 * @author PROFESSOR
 *
 */

public class Tool {

	private int id;
	private String name;
	private int quantity;
	private double price;
	private int supplierID;
	
	/**
	 * Constructor for the tool class which creates a tool with an id, name, quantity, price and supplier id
	 * @param id desired tool id
	 * @param name desired tool name
	 * @param quantity desired tool quantity
	 * @param price desired tool price
	 * @param supplierID desired supplier id
	 */
	public Tool(int id, String name, int quantity, double price, int supplierID)
	{
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.supplierID = supplierID;
	}
	/**
	 * getter for tool ID
	 * @return tool id
	 */
	public int getID()
	{
		return id;
	}
	/**
	 * getter for tool name
	 * @return tool name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * getter for tool quantity
	 * @return tool quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}
	/**
	 * getter for tool price
	 * @return tool price
	 */
	public double getPrice()
	{
		return price;
	}
	/**
	 * getter for tool supplier id
	 * @return tool supplier id
	 */
	public int getSupplierID()
	{
		return supplierID;
	}
	/**
	 * to string method displays tool information
	 */
	@Override
	public String toString()
	{
		String tool = this.id + " " + this.name + " " + this.quantity + " " + this.price + " " + this.supplierID;
		return tool;
	}
}
