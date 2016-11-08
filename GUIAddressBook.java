import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class GUIAddressBook extends JFrame implements  ActionListener
{

	private static final long serialVersionUID = 1L;

	private static final int [] FRAMEDIMENSION = {400, 300};     // the dimensions of the frame
	
	
	// name of file to be written along with the info to be displayed on first line of file
	private static final String FILENAME = "MyAddressBook.txt",
			                    ADDRESSBOOKINFO = "My address book containing: \n";
	
	
	// enum for the texts
	private enum text {NEW, SAVE, ADD, DISPLAY, NAME, 
		               ADDRESS, PHONENUM, FILE, BUDDYINFO,
		               EDIT, EDITBUDDY, REMOVE};                 
	
	private JFrame f,                                   // AddressBook JFrame instance
	               editFrame,                           // Editing JFrame instance
	               buddyFrame,                          // BuddyInfo JFrame instance
	               removeFrame;                         // Removing JFrame instance
	               
	
	JPanel pl;                                          // JPanel instance 
	
	private JMenuBar menuBar;                           // JMenuBar instance
	private AddressBook ab;                             // AddressBook instance
	
	private JTextArea name,                             // name of Buddy entered into JTextArea
	                  address,                          // address of Buddy inputed into JTextArea
	                  phone_num;                        // phone_num of Buddy inputed into JTextArea

	
	/**
	 * Constructor for the GUIAddressBook class
	 * 
	 * @param frameName -> String representing the title of
	 *                     the frame to be displayed at top
	 *                     bar
	 * 
	 */
	
	public GUIAddressBook(String frameName)
	{
		super(frameName);
		this.makeFrame(frameName);
	}
	
	
	/**
	 * This method creates the frame as well as calls the method
	 * responsible for creating the menu bar.
	 * 
	 * @param frameName -> String representing the title of
	 *                     the frame to be displayed at top
	 *                     bar
	 */
	
	private void makeFrame(String frameName)
	{
		// create frame
		this.f = new JFrame(frameName);
		
		
		// create JPanel
		this.pl = new JPanel();
		
		
		// add JPanel to JFrame
		this.f.add(this.pl);
		
		
		// set the size and closing feature
		this.f.setSize(FRAMEDIMENSION[0], FRAMEDIMENSION[1]);
		this.f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		// create menu
		this.makeMenu();
		
		
		// display the menu
		this.f.setJMenuBar(this.menuBar);
		
		
		// set frame visible
		this.f.setVisible(true);
	}
	
	
	/**
	 * This method is responsible for creating the
	 * menu bar and the menu items for the frame.
	 * 
	 */
	
	private void makeMenu()
	{
		// Menu Bar
		this.menuBar = new JMenuBar();
				
		
		// create different tabs on Menu Bar
		JMenu fileMenu = new JMenu(text.FILE.name());
		this.menuBar.add(fileMenu);
		
		JMenu editMenu = new JMenu(text.EDIT.name());
		this.menuBar.add(editMenu);
		editMenu.setEnabled(false);               // disable this menu at initialization
		
		JMenu buddyMenu = new JMenu(text.BUDDYINFO.name());
		this.menuBar.add(buddyMenu);
		buddyMenu.setEnabled(false);               // disable this menu at initialization
		
		
		//Menu Items for each tab
		JMenuItem item;
		
		item = new JMenuItem(text.NEW.name());
		item.addActionListener(this);
		fileMenu.add(item);
		
		item = new JMenuItem(text.SAVE.name());
		item.addActionListener(this);
		fileMenu.add(item);
		item.setEnabled(false);                     // disable this item at initialization
		
		item = new JMenuItem(text.DISPLAY.name());
		item.addActionListener(this);
		fileMenu.add(item);
		item.setEnabled(false);                     // disable this item at initialization
		
		item = new JMenuItem(text.EDITBUDDY.name());
		item.addActionListener(this);
		editMenu.add(item);
		item.setEnabled(false);                     // disable this item at initialization
		
		item = new JMenuItem(text.REMOVE.name());
		item.addActionListener(this);
		editMenu.add(item);
		item.setEnabled(false);                     // disable this item at initialization
		
		item = new JMenuItem(text.ADD.name());
		item.addActionListener(this);
		buddyMenu.add(item);
		
	}
	
	
	/**
	 * Writes to a file all the contacts in the address book.
	 * If error occurs, it will print to the console a message.
	 * 
	 * @param bIarray -> An array of all the BuddyInfos in the
	 *                   Address Book
	 *                   
	 */
	
	private void writeTOfile(BuddyInfo [] bIarray)
	{
		// try to create and write to a file
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME));
			
			out.write(ADDRESSBOOKINFO);                   // write to file opening line
			
			// for every BuddyInfo in array write to file
			for(BuddyInfo bI: bIarray)
			{
				String contact = "\n" + bI.getName() + "\n" + bI.getAddress() + "\n" + bI.getPhoneNum() + "\n";
				out.write(contact);
			}
			
			out.close();           // close the file
		}
		
		// catch an IOException and display message
		catch (IOException io)
		{
			System.out.println("Cannot create the file to write in.");
		}
		
	}
	
	
	/**
	 * Makes the BuddyInfo Frame for inputting
	 * a new buddy.
	 * 
	 */
	
	private void makeBuddyFrame()
	{
		//  initializing JTextArea instances 
		this.name = new JTextArea();
		this.address = new JTextArea();
		this.phone_num = new JTextArea();
		
		// create a new JFrame
		this.buddyFrame = new JFrame("Add BuddyInfo");
		
		// set the size and closing features
		this.buddyFrame.setSize(FRAMEDIMENSION[0], FRAMEDIMENSION[1]);
		this.buddyFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		// create a JButton
		JButton button = new JButton(text.ADD.name());
		
		// add an actionListener to the button
		button.addActionListener(this);
		
		// create a new JPanel with BorderLayout
		JPanel buddyPanel = new JPanel(new BorderLayout());
		
		// create a GridLayout
		GridLayout gl = new GridLayout(3, 2);
		
		// set the GridLayout vertical gaps
		gl.setVgap(2);
		
		// create a new JPanel with GridLayout
		JPanel buddyInfoPanel = new JPanel(gl);

		
		// add the JLabels and JTextArea instances to the buddyInfoPanel
		buddyInfoPanel.add(new JLabel(text.NAME.name()));
		buddyInfoPanel.add(this.name);
		buddyInfoPanel.add(new JLabel(text.ADDRESS.name()));
		buddyInfoPanel.add(this.address);
		buddyInfoPanel.add(new JLabel(text.PHONENUM.name()));
		buddyInfoPanel.add(this.phone_num);
		
		
		// add the button to the buddyPanel at the bottom
		buddyPanel.add(button, BorderLayout.SOUTH);
		
		// add the buddyInfoPanel to the buddyPanel at the center
		buddyPanel.add(buddyInfoPanel, BorderLayout.CENTER);
		
		// add the buddyPanel to the buddyFrame
		this.buddyFrame.add(buddyPanel);
		
		// set the location of the buddyFrame to be at FRAMEDIMENSION[0], FRAMEDIMENSION[1]
		this.buddyFrame.setLocation(FRAMEDIMENSION[0], FRAMEDIMENSION[1]);
		
		// set buddyFrame visible
		this.buddyFrame.setVisible(true);
		
	}
	
	
	private void removeFrame()
	{
		this.removeFrame = new JFrame("Removing a buddy");
	}
	
	
	/**
	 * Checks if an action as been performed and if it has
	 * then checks the individual source commands to find
	 * the appropriate information.
	 * 
	 * @param e -> ActionEvent instance representing an action
	 *             event.
	 * 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(e.getSource());
		//System.out.println(e.getActionCommand());
		
		if (e.getSource() instanceof JMenuItem)
		{
			// create new address book and enables menus and menu items
			if (e.getActionCommand().equals(text.NEW.name()))
			{
				this.ab = new AddressBook();              // initialize the address book
				System.out.println("Created");
				
				// enable the disable menu tab and menu item
				this.menuBar.getMenu(1).setEnabled(true);
				this.menuBar.getMenu(2).setEnabled(true);
				this.menuBar.getMenu(0).getMenuComponent(1).setEnabled(true);
				
								
			}
			
			
			// save address book to file
			else if (e.getActionCommand().equals(text.SAVE.name()))
			{
				System.out.println("Saved");
			
				BuddyInfo [] ab_array = this.ab.getAddressBookArray();    // get an array of buddyInfos
				
				this.writeTOfile(ab_array);                               // call writeTOfile method
			}
			
			
			// add a buddy to the address book
			else if (e.getActionCommand().equals(text.ADD.name()))
			{
				System.out.println("Added");
				
				if (! this.menuBar.getMenu(0).getMenuComponent(2).isEnabled())
					this.menuBar.getMenu(0).getMenuComponent(2).setEnabled(true);
				
				if (! this.menuBar.getMenu(1).getMenuComponent(0).isEnabled())
					this.menuBar.getMenu(1).getMenuComponent(0).setEnabled(true);
				
				if (! this.menuBar.getMenu(1).getMenuComponent(1).isEnabled())
					this.menuBar.getMenu(1).getMenuComponent(1).setEnabled(true);
				
				this.makeBuddyFrame();
			
			}
			
			// display address book to the GUI
			else if (e.getActionCommand().equals(text.DISPLAY.name()))
			{
				System.out.println("Displayed");				
				
				// JLabel
				JLabel label;
				
				// GridLayout with row size = number of contacts and col size = 1
				GridLayout gl = new GridLayout(this.ab.getAddressBookArray().length, 1);
				
				// remove previous JPanel
				this.f.remove(this.pl);
				
				// create new JPanel
				this.pl =  new JPanel(gl);
				
				// add JPanel to JFrame
				this.f.add(pl);
				
				BuddyInfo [] ab_array = this.ab.getAddressBookArray();    // get an array of buddyInfos
				
				
				// for each BuddyInfo in the array, display on GUI
				for (BuddyInfo bI: ab_array)
				{
					String nameString = text.NAME.name() + ": " + bI.getName() + " " 
				                      + text.ADDRESS.name()+ ": " + bI.getAddress() + " "
				                      + text.PHONENUM.name() + ": " + bI.getPhoneNum();

					label = new JLabel(nameString);

					this.pl.add(label);
					
				}
				
				this.f.setVisible(true);
				
			}
			
			// editing a buddy's information
			else if (e.getActionCommand().equals(text.EDITBUDDY.name()))
			{
				System.out.println("Edited");
			}
			
			
			// removing a buddy from the address book
			else if (e.getActionCommand().equals(text.REMOVE.name()))
			{
				System.out.println("Removed");
				
				this.removeFrame();
			}
						
			
			
		}
		
		if (e.getSource() instanceof JButton)
		{
			if (e.getActionCommand().equals(text.ADD.name()))
			{
				System.out.println("Button Pressed");
				
				BuddyInfo bI = new BuddyInfo(this.name.getText(), this.address.getText(), this.phone_num.getText());
				this.ab.addBuddy(bI.getName(), bI);
				
				this.buddyFrame.dispose();
			}
		}

	}
	
	
	
	public static void main(String [] args)
	{
		GUIAddressBook g = new GUIAddressBook("Gui Address Book");
		
	}
}