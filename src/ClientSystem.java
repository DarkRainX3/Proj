/**
 * Class that contains main function for running Client Management System
 * @author WaleyAndy
 *
 */
public class ClientSystem {
	public static void main (String [] args){
		

		ClientManager db = new ClientManager();
		ClientGUI gui = new ClientGUI();
//		db.createDB();
//		db.createTable();
//		db.fillTable();
//		db.removeTable();
		
		ClientController controller = new ClientController (gui, db);
		
		controller.run();
	}

}
