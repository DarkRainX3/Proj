import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author WaleyAndy
 *
 */
public class ClientManager {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	public String databaseName = "ClientDB", tableName = "ClientTable", dataFile = "clients.txt";
	public String connectionInfo = "jdbc:mysql://localhost:3306/ClientDB?verifyServerCertificate=false&useSSL=true",  
				  login          = "root",
				  password       = "Student";
	/**
	 * Constructor for the client manager application
	 */
	public ClientManager()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	/**
	 * creates new database using defined name
	 */
	public void createDB()
	{
		try {
			statement = jdbc_connection.prepareStatement("CREATE DATABASE " + databaseName);
			statement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * creates table in database with client details
	 */
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
					 "ID INT(4) NOT NULL AUTO_INCREMENT, " + 
				     "FIRSTNAME VARCHAR(20) NOT NULL, " +
				     "LASTNAME VARCHAR(20) NOT NULL, " + 
				     "ADDRESS VARCHAR(50) NOT NULL, " + 
				     "POSTALCODE CHAR(7) NOT NULL, " + 
				     "PHONENUMBER CHAR(12) NOT NULL, " + 
				     "TYPE CHAR(1) NOT NULL, " + 
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * removes table in database
	 */
	public void removeTable()
	{
		String sql = "DROP TABLE " + tableName;
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Removed Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method that adds client to the db using an inputed Client object
	 * @param client: input client
	 */
	public void addClient(Client client)
	{
		String sql = "INSERT INTO " + tableName + " (ID, FIRSTNAME, LASTNAME, ADDRESS, POSTALCODE, PHONENUMBER, TYPE) values (?,?,?,?,?,?,?)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, client.getID());
			statement.setString(2, client.getFirstName());
			statement.setString(3, client.getLastName());
			statement.setString(4, client.getAddress());
			statement.setString(5, client.getPostalCode());
			statement.setString(6, client.getPhoneNumber());
			statement.setString(7, client.getType());
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method that deletes client based on id
	 * @param clientID: client id to delete
	 */
	public void delClient(int clientID)
	{
		String sql = "DELETE FROM " + tableName + " WHERE ID= ?";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, clientID);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * fills table with clients based on datafile
	 */
	public void fillTable()
	{
		try{
			Scanner sc = new Scanner(new FileReader(dataFile));
			while(sc.hasNext())
			{
				String clientInfo[] = sc.nextLine().split(";");
				addClient( new Client(0,clientInfo[0], clientInfo[1],clientInfo[2],clientInfo[3],clientInfo[4], clientInfo[5]));
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File " + dataFile + " Not Found!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method that prints table of clients to console
	 */
	public void printTable()
	{
		try {
			String sql = "SELECT * FROM " + tableName;
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet clients = statement.executeQuery();
			System.out.println("Clients:");
			while(clients.next())
			{
				System.out.println(clients.getInt("ID") + " " + 
						clients.getString("FIRSTNAME") + " " + 
						clients.getString("LASTNAME") + " " + 
						clients.getString("ADDRESS") + " " + 
						clients.getString("POSTALCODE") + " " + 
						clients.getString("PHONENUMBER") + " " + 
						clients.getString("TYPE"));
			}
			clients.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * method that updates fields for a client matching an existing ID
	 * @param client the client to update
	 */
	public void updateClient(Client client)
	{
		String sql = "UPDATE " + tableName + " SET FIRSTNAME = ?, LASTNAME = ?, ADDRESS = ?, POSTALCODE = ?, PHONENUMBER = ?, TYPE = ? WHERE ID= ?";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, client.getFirstName());
			statement.setString(2, client.getLastName());
			statement.setString(3, client.getAddress());
			statement.setString(4, client.getPostalCode());
			statement.setString(5, client.getPhoneNumber());
			statement.setString(6, client.getType());
			statement.setInt(7, client.getID());
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return;
	}
	/**
	 * method that searches for client from table based on ID
	 * @param clientID: client id to search for
	 * @return: client object matching searched ID
	 */
	public Client searchClient(int clientID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID= ?";
		ResultSet client;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, clientID);
			client = statement.executeQuery();
			if(client.next())
			{
				return new Client(client.getInt("ID"),
						client.getString("FIRSTNAME"), 
						client.getString("LASTNAME"), 
						client.getString("ADDRESS"), 
						client.getString("POSTALCODE"), 
						client.getString("PHONENUMBER"), 
						client.getString("TYPE"));
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	/**
	 * searches table using client last name and returns client arraylist with selected name
	 * @param clientName the last name of client to search for
	 * @return list of clients matching the searched last name
	 */
	public ArrayList<Client> searchClientName(String clientName)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE LASTNAME= ?";
		ResultSet client;
		ArrayList<Client> list = new ArrayList<Client>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, clientName);
			client = statement.executeQuery();
			while(client.next())
			{
				list.add( new Client(client.getInt("ID"),
						client.getString("FIRSTNAME"), 
						client.getString("LASTNAME"), 
						client.getString("ADDRESS"), 
						client.getString("POSTALCODE"), 
						client.getString("PHONENUMBER"), 
						client.getString("TYPE")));
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	}
	/**
	 * searches list of clients for selected client type (R or C) and returns client arraylist with selected type
	 * @param clientType they type of client to search for
	 * @return list of clients matching the searched type
	 */
	public ArrayList<Client> searchClientType(String clientType)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE TYPE= ?";
		ResultSet client;
		ArrayList<Client> list = new ArrayList<Client>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, clientType);
			client = statement.executeQuery();
			while(client.next())
			{
				list.add( new Client(client.getInt("ID"),
						client.getString("FIRSTNAME"), 
						client.getString("LASTNAME"), 
						client.getString("ADDRESS"), 
						client.getString("POSTALCODE"), 
						client.getString("PHONENUMBER"), 
						client.getString("TYPE")));
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	}
	public static void main(String args[])
	{
		ClientManager inventory = new ClientManager();
		
		// You should comment this line out once the first database is created (either here or in MySQL workbench)
		//inventory.createDB();
//		inventory.removeTable();
//
//		inventory.createTable();
//		
//		System.out.println("\nFilling the table with clients");
//		inventory.fillTable();
//
//		System.out.println("Reading all clients from the table:");
//		inventory.printTable();
//		
//		Client searchResult = inventory.searchClient(20);
//		if(searchResult == null)
//			System.out.println("Search Failed to find a client matching ID: " + "20");
//		else
//			System.out.println("Search Result: " + searchResult.toString());
//		Client newclient = new Client(20,"bob","Pig","123 st","1hh h4h","123-123-1234","R");
//		inventory.updateClient(newclient);
//		searchResult = inventory.searchClient(20);
//		if(searchResult == null)
//			System.out.println("Search Failed to find a client matching ID: " + "20");
//		else
//			System.out.println("Search Result: " + searchResult.toString());
//		inventory.delClient(20);
//		searchResult = inventory.searchClient(20);
//		if(searchResult == null)
//			System.out.println("Search Failed to find a client matching ID: " + "20");
//		else
//			System.out.println("Search Result: " + searchResult.toString());
//		inventory.printTable();
//		Client newclient = new Client(444,"bob","Pig","123 st","1hh h4h","123-123-1234","R");
//		inventory.addClient(newclient);
//		inventory.printTable();
//		ArrayList temp = inventory.searchClientName("Pig");
//		for (int i =0;i<temp.size();i++) {
//			System.out.println(temp.get(i));
//		}
//		System.out.println(inventory.searchClientType("R"));
		try {
			inventory.statement.close();
			inventory.jdbc_connection.close();
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally
		{
			System.out.println("\nThe program is finished running");
		}
	}

}
