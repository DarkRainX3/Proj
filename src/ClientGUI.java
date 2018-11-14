import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
/**
 * Class that defines the GUI layout for the Client Management System
 * @author WaleyAndy
 *
 */
public class ClientGUI {

	private JFrame frmClientManagementScreen;
	private JTextField textFieldSearchParam = new JTextField();
	private JTextField textFieldID = new JTextField();
	private JTextField textFieldFirstName = new JTextField();
	private JTextField textFieldLastName = new JTextField();
	private JTextField textFieldAddress = new JTextField();
	private JTextField textFieldPostalCode = new JTextField();
	private JTextField textFieldPhoneNum = new JTextField();
	private JTextField [] addClient = {textFieldID,textFieldFirstName,textFieldLastName,textFieldAddress,textFieldPostalCode,textFieldPhoneNum};
	private final ButtonGroup searchType = new ButtonGroup();
	private JRadioButton rdbtnClientId = new JRadioButton("Client ID");
	private JRadioButton rdbtnLastName = new JRadioButton("Last Name");
	private JRadioButton rdbtnClientType = new JRadioButton("Client Type");
	private int rdbSelected = 1;
	private JButton btnSave = new JButton("Save");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnClear = new JButton("Clear");
	private JButton btnSearch = new JButton("Search");
	private JButton btnClearSearch = new JButton("Clear Search");
	private JTextArea textAreaSearchResults = new JTextArea();
	private DefaultListModel<Client> list = new DefaultListModel<Client>();
	private JList listing = new JList(list);
	private String [] clientTypes = {"Residential", "Commercial"};
	private JComboBox clientType = new JComboBox(clientTypes);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frmClientManagementScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * method that sets the GUI to be visible
	 */
	public void makeVisable() {
		frmClientManagementScreen.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientManagementScreen = new JFrame();
		frmClientManagementScreen.setTitle("Client Management Screen");
		frmClientManagementScreen.setBounds(100, 100, 1444, 876);
		frmClientManagementScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientManagementScreen.getContentPane().setLayout(null);
		
		JPanel panelTopLeft = new JPanel();
		panelTopLeft.setBounds(10, 10, 750, 410);
		frmClientManagementScreen.getContentPane().add(panelTopLeft);
		panelTopLeft.setLayout(null);

		btnClearSearch.setBounds(570, 350, 167, 37);
		panelTopLeft.add(btnClearSearch);
		btnSearch.setBounds(440, 350, 105, 37);
		panelTopLeft.add(btnSearch);
		
		textFieldSearchParam.setBounds(20, 350, 400, 35);
		panelTopLeft.add(textFieldSearchParam);
		textFieldSearchParam.setColumns(10);
		
		JLabel lblSearchClients = new JLabel("Search Clients");
		lblSearchClients.setBounds(260, 30, 201, 34);
		panelTopLeft.add(lblSearchClients);
		lblSearchClients.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JLabel lblSelectTypeOf = new JLabel("Select type of Search to be performed:");
		lblSelectTypeOf.setBounds(22, 84, 416, 29);
		panelTopLeft.add(lblSelectTypeOf);
		
		searchType.add(rdbtnClientId);
		rdbtnClientId.setSelected(true);
		rdbtnClientId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setRdbSelected(1);
			}
		});
		rdbtnClientId.setBounds(18, 133, 129, 37);
		panelTopLeft.add(rdbtnClientId);
		
		searchType.add(rdbtnLastName);
		rdbtnLastName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setRdbSelected(2);
			}
		});
		rdbtnLastName.setBounds(18, 186, 153, 37);
		panelTopLeft.add(rdbtnLastName);
		
		searchType.add(rdbtnClientType);
		rdbtnClientType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setRdbSelected(3);
			}
		});
		rdbtnClientType.setBounds(18, 244, 157, 37);
		panelTopLeft.add(rdbtnClientType);
		
		JLabel lblSearchParam = new JLabel("Enter the search parameter below:");
		lblSearchParam.setBounds(22, 327, 373, 29);
		panelTopLeft.add(lblSearchParam);
		
		JPanel panelBotLeft = new JPanel();
		panelBotLeft.setBounds(10, 430, 750, 400);
		frmClientManagementScreen.getContentPane().add(panelBotLeft);
		panelBotLeft.setLayout(null);
		
		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setBounds(10, 11, 184, 29);
		panelBotLeft.add(lblSearchResults);
		listing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Client temp = listSelected();
				listClientToText(temp);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(listing);
		scrollPane.setBounds(0, 40, 750, 350);
		panelBotLeft.add(scrollPane);
		
		textAreaSearchResults.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JPanel panelRight = new JPanel();
		panelRight.setBounds(770, 10, 650, 820);
		frmClientManagementScreen.getContentPane().add(panelRight);
		panelRight.setLayout(null);
		
		JLabel lblClientInformation = new JLabel("Client Information");
		lblClientInformation.setBounds(200, 30, 278, 34);
		panelRight.add(lblClientInformation);
		lblClientInformation.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(100, 160, 104, 35);
		panelRight.add(lblClientId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(100, 240, 140, 35);
		panelRight.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(100, 320, 122, 35);
		panelRight.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(100, 400, 104, 35);
		panelRight.add(lblAddress);
		
		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setBounds(100, 480, 140, 35);
		panelRight.add(lblPostalCode);
		
		JLabel lblHoneNumber = new JLabel("Phone Number:");
		lblHoneNumber.setBounds(100, 560, 182, 35);
		panelRight.add(lblHoneNumber);
		
		JLabel lblClientType = new JLabel("Client Type:");
		lblClientType.setBounds(100, 640, 168, 35);
		panelRight.add(lblClientType);
		
		textFieldID.setBounds(300, 160, 113, 35);
		textFieldID.setEditable(false);
		textFieldID.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldID.getText().length() >= 4 ) // limit to 12 characters
	                e.consume();
	        }
	    });
		panelRight.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldFirstName.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldFirstName.getText().length() >= 20 ) // limit to 20 characters
	                e.consume();
	        }
	    });
		textFieldFirstName.setBounds(300, 240, 206, 35);
		panelRight.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldLastName.getText().length() >= 20 ) // limit to 20 characters
	                e.consume();
	        }
	    });
		textFieldLastName.setBounds(300, 320, 206, 35);
		panelRight.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		textFieldAddress.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldAddress.getText().length() >= 50 ) // limit to 50 characters
	                e.consume();
	        }
	    });
		textFieldAddress.setBounds(300, 400, 330, 35);
		panelRight.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		textFieldPostalCode.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldPostalCode.getText().length() >= 7 ) // limit to 7 characters
	                e.consume();
	        }
	    });
		textFieldPostalCode.setBounds(300, 480, 206, 35);
		panelRight.add(textFieldPostalCode);
		textFieldPostalCode.setColumns(10);
		
		textFieldPhoneNum.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textFieldPhoneNum.getText().length() >= 12 ) // limit to 12 characters
	                e.consume();
	        }
	    });
		textFieldPhoneNum.setBounds(300, 560, 206, 35);
		panelRight.add(textFieldPhoneNum);
		textFieldPhoneNum.setColumns(10);

		btnSave.setBounds(40, 730, 155, 37);
		panelRight.add(btnSave);
		
		btnDelete.setBounds(220, 730, 155, 37);
		panelRight.add(btnDelete);
		
		btnClear.setBounds(400, 730, 155, 37);
		panelRight.add(btnClear);
		
		clientType.setBounds(300, 640, 113, 35);
		panelRight.add(clientType);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		btnQuit.setBounds(551, 11, 89, 23);
		panelRight.add(btnQuit);
	}
	/**
	 * registers action listener to save button
	 * @param ac: save
	 */
	void registerSaveListener (ActionListener ac){
		btnSave.addActionListener(ac);
	}
	/**
	 * registers action listener to delete button
	 * @param ac: delete
	 */
	void registerDeleteListener (ActionListener ac){
		btnDelete.addActionListener(ac);
	}
	/**
	 * registers action listener to clear button
	 * @param ac: clear
	 */
	void registerClearListener (ActionListener ac){
		btnClear.addActionListener(ac);
	}
	/**
	 * registers action listener to search button and search parameter text field
	 * @param ac: search
	 */
	void registerSearchListener (ActionListener ac){
		btnSearch.addActionListener(ac);
		textFieldSearchParam.addActionListener(ac);
	}
	/**
	 * registers action listener to clear search button
	 * @param ac: clear search
	 */
	void registerClearSearchListener (ActionListener ac){
		btnClearSearch.addActionListener(ac);
	}
	/**
	 * getter method for userID text field.
	 * @return client id or -1
	 */
	public int getClientId() {
		int temp;
		try {
			temp = Integer.parseInt(textFieldID.getText());
		}catch(NumberFormatException e) {
			//JOptionPane.showMessageDialog(null,"Please enter a valid client ID","Error",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		return temp;
	}
	/**
	 * getter method for getting user entered first name from text box
	 * @return: first name
	 */
	public String getFirstName() {
		return textFieldFirstName.getText();
	}
	/**
	 * getter method for getting user entered last name from text box
	 * @return: last name
	 */
	public String getLastName() {
		return textFieldLastName.getText();
	}
	/**
	 * getter method for getting user entered address from text box
	 * @return: address
	 */
	public String getAddress() {
		return textFieldAddress.getText();
	}
	/**
	 * getter method for user entered postal code
	 * @return: postal code
	 */
	public String getPostCode() {
		return textFieldPostalCode.getText();
	}
	/**
	 * getting method for user entered phone number
	 * @return: phone number
	 */
	public String getPhone() {
		return textFieldPhoneNum.getText();
	}
	/**
	 * getter method for user entered business type
	 * @return: R or C
	 */
	public String getClientType() {
		if (clientType.getSelectedItem().equals("Residential"))
			return "R";
		else
			return "C";
	}
	/**
	 * method for clearing the text fields
	 */
	public void clearText() {
		for (int i =0; i< addClient.length;i++)
			addClient[i].setText(null);
	}
	/**
	 * method for checking that all text fields are entered
	 * @return
	 */
	public boolean checkInput() {
		for (int i =1; i< addClient.length;i++) {
			if(addClient[i].getText().length()<1)
				return false;
		}
		return true;
	}
	/**
	 * method that clears the search area
	 */
	public void clearSearch() {
		textFieldSearchParam.setText(null);
		list.clear();
	}
	/**
	 * method that returns the search parameter from text field
	 * @return: search parameter
	 */
	public String getSearchParam() {
		return textFieldSearchParam.getText();
	}
	/**
	 * method that adds a client object to the search list
	 * @param c: client
	 */
	public void listClient(Client c) {
		list.addElement(c);
	}
	/**
	 * method that removes selected list element
	 */
	public void clearListItem() {
		list.removeElement(listing.getSelectedValue());
	}
	/**
	 * method that returns selected client from list
	 * @return
	 */
	public Client listSelected() {
		return (Client)listing.getSelectedValue();
	}
	/**
	 * getter for the integer rdbSelected
	 * @return: integer value
	 */
	public int getRdbSelected() {
		return rdbSelected;
	}
	/**
	 * setter for the integer rdbSelected
	 * @param i: desired value for the integer
	 */
	public void setRdbSelected(int i) {
		this.rdbSelected=i;
	}
	/** 
	 * method that outputs client information into their respective textFields
	 * @param a: selected client
	 */
	public void listClientToText(Client a) {
		addClient[0].setText(Integer.toString(a.getID()));
		addClient[1].setText(a.getFirstName());
		addClient[2].setText(a.getLastName());
		addClient[3].setText(a.getAddress());
		addClient[4].setText(a.getPostalCode());
		addClient[5].setText(a.getPhoneNumber());
		if (a.getType().equals("R"))
			clientType.setSelectedIndex(0);
		else
			clientType.setSelectedIndex(1);
	}
	/**
	 * method that displays selected client id in textFieldID
	 * @param s: client id
	 */
	public void showID(String s) {
		textFieldID.setText(s);
	}
	/**
	 * method that deselects selected item from list
	 */
	public void deselectListing() {
		listing.clearSelection();
	}
}