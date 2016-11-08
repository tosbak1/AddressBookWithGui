import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;


public class AddressBook 
{
	// hash map representing a collection of BuddyInfos
	private HashMap<String, BuddyInfo> addressBook;          
	
	
	/**
	 * Constructor for AddressBook class
	 * 
	 */
	
	public AddressBook()
	{
		addressBook = new HashMap<>();
	}
	
	
	/**
	 * Adds a BuddyInfo to the AddressBook
	 * 
	 * @param name -> String representing the name of the Buddy
	 * @param buddy_info -> BuddyInfo instance to be added to the
	 *                      collection
	 */
	
	public void addBuddy(String name, BuddyInfo buddy_info)
	{
		this.addressBook.put(name, buddy_info);
	}
	
	public int getSize(){
		return addressBook.size();
	}
	
	public void clearList(){
		addressBook.clear();
	}
	/**
	 * Removes a BuddyInfo from the AddressBook
	 * 
	 * @param name -> String representing the name of the Buddy
	 *                to be deleted.
	 */
	
	public void remove(String name)
	{
		this.addressBook.remove(name);
	}
	
	
	/**
	 * Gets all the BuddyInfos that are in the AddressBook in
	 * an array.
	 * 
	 * @return bI -> An array of BuddyInfos that are in the
	 *               AddressBook
	 */
	
	public BuddyInfo [] getAddressBookArray()
	{
		// create an array
		BuddyInfo [] bI = new BuddyInfo [this.addressBook.size()];
		
		// index of array
		int i = 0; 
		
		// for each buddy, add to array and increment the index
		for (String s: this.addressBook.keySet())
		{
			bI[i] = this.addressBook.get(s);
			i++;
		}
		
		return bI;
	}
	
	public void export() {
		PrintWriter out;
		
		try {
			out = new PrintWriter("output.txt");
			for (BuddyInfo b : addressBook.values()) {
				out.println(b.toString());
			}
			out.close();
		} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "Toyin$judhdfhd$56551";
		AddressBook ad = new AddressBook();
		BuddyInfo bd = new BuddyInfo("Toyin", "Junctin", "5445554");
		bd = BuddyInfo.importer(str1);
		//bd.toString();
		
		String str = bd.toString();
		System.out.println(str);
		ad.addBuddy("Toyin", bd);
		ad.export();
		//System.out.println(ad.getSize());
	}
}