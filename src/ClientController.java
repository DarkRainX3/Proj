import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * The following class registers all action listeners to buttons on the GUI, and ensures
 * that inputs are as per requirements of the text field
 * @author WaleyAndy
 *
 */
public class ClientController {
	private ClientManager cm;
	private ClientGUI gui;
	RandomGenerator rand = new RandomGenerator();
	
	/**
	 * Constructor for the controller between the gui and the client manager application
	 * @param g input gui object
	 * @param cm input client manager application object
	 */
	public ClientController(ClientGUI g, ClientManager cm) {
		gui = g;
		this.cm = cm;
		gui.registerSaveListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gui.checkInput()) {
					if(!gui.getPostCode().matches("[A-Z]{1}[0-9]{1}[A-Z]{1}\\s{1}[0-9]{1}[A-Z]{1}[0-9]{1}")) {
						JOptionPane.showMessageDialog(null,"Please enter a valid postal code!\n Example A1A 1A1.","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!gui.getPhone().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
						JOptionPane.showMessageDialog(null,"Please enter a valid phone number in the form \n"
								+ "123-123-1234","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (gui.checkInput()&&gui.getClientId()!=-1) {
					Client temp = new Client(gui.getClientId(),gui.getFirstName(),gui.getLastName(),gui.getAddress(),gui.getPostCode(),gui.getPhone(),gui.getClientType());
					cm.updateClient(temp);
					//gui.listClient(temp);
					gui.clearListItem();
					gui.clearText();
					JOptionPane.showMessageDialog(null,"Successfully Updated Client ID: "+temp.getID(),"Message",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if(gui.checkInput()&&gui.getClientId()==-1) {
					int id = rand.discrete(1, 9999);
					while (cm.searchClient(id)!=null) {
						//System.out.println(id);
						id = rand.discrete(1, 9999);
					}
					gui.showID(Integer.toString(id));
					Client temp = new Client(id,gui.getFirstName(),gui.getLastName(),gui.getAddress(),gui.getPostCode(),gui.getPhone(),gui.getClientType());
					//gui.listClient(temp);
					cm.addClient(temp);
					//gui.clearListItem();
					JOptionPane.showMessageDialog(null,"Successfully Added Client ID: "+temp.getID(),"Message",JOptionPane.PLAIN_MESSAGE);
					gui.clearText();
				}
				else {
					JOptionPane.showMessageDialog(null,"Please fill in all fields!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		gui.registerClearListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{		
					gui.clearText();
					gui.deselectListing();
				}catch (Exception ex){
					ex.getMessage();
				}
			}
		});
		gui.registerDeleteListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					cm.delClient(gui.listSelected().getID());
					JOptionPane.showMessageDialog(null,"Successfully Deleted Client ID: "+gui.listSelected().getID(),"Message",JOptionPane.PLAIN_MESSAGE);
					gui.clearText();
					gui.clearListItem();
				}catch (Exception ex){
					ex.getStackTrace();
				}
			}
		});
		gui.registerSearchListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{		
					int temp = gui.getRdbSelected();
					ArrayList a;
					switch (temp) {
					case 1: try{
						Client load = cm.searchClient(Integer.parseInt(gui.getSearchParam()));
						if (load == null) {
							JOptionPane.showMessageDialog(null,"No Client Found!","Error",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						gui.listClient(load);
						}catch (Exception ex) {
							JOptionPane.showMessageDialog(null,"Please enter a valid client ID!","Error",JOptionPane.ERROR_MESSAGE);
							return;
							}
					break;
					case 2: 
						a = cm.searchClientName(gui.getSearchParam());
						if (a.size()<1) {
							JOptionPane.showMessageDialog(null,"No Client Found!","Error",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						for (int i =0;i<a.size();i++)
						gui.listClient((Client)a.get(i));
						break;
					case 3:
						while (!gui.getSearchParam().toLowerCase().equals("r")&&!gui.getSearchParam().toLowerCase().equals("c")) {
							JOptionPane.showMessageDialog(null,"Please enter a valid client Type!\n Use R or C","Error",JOptionPane.ERROR_MESSAGE);
							return;
							}
						a = cm.searchClientType(gui.getSearchParam());
						if (a.size()<1) {
							JOptionPane.showMessageDialog(null,"No Client Found!","Error",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						for (int i =0;i<a.size();i++)
							gui.listClient((Client)a.get(i));
						break;
						} 
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		gui.registerClearSearchListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if (gui.listSelected()!=null)
						gui.clearText();
					gui.clearSearch();
					
				}catch (Exception ex){
					ex.getMessage();
				}
			}
		});
	}
}
