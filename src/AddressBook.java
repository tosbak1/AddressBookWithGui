import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;


public class AddressBook implements Serializable 
{
	// hash map representing a collection of BuddyInfos
	private HashMap<String, BuddyInfo> addressBook;          
	private BuddyInfo newBud = null;
	
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
	
	public void export(String str) {
		PrintWriter out;
		
		try {
			out = new PrintWriter(str);
			for (BuddyInfo b : addressBook.values()) {
				out.println(b.toString());
			}
			out.close();
		} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	}
	
	public BuddyInfo importer(String str){

        // The name of the file to open.
        String fileName = str;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
            	this.export("output2.txt");
            	this.newBud = newBud.importer(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return newBud;
	}
	
	public void objExport(){
		
		try {
			FileOutputStream out = new FileOutputStream("objout.txt");
			ObjectOutputStream oout = new ObjectOutputStream(out);
			for (BuddyInfo b : addressBook.values()) {
				oout.writeObject(b);
				out.close();
				// create an ObjectInputStream for the file we created before
		        // ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
		         
			}
			
		} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	}
	
	public BuddyInfo objImport(){
		BuddyInfo bd = null;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objout.txt"));
			bd = (BuddyInfo) ois.readObject();
			System.out.println("Object: " + bd);//ois.readObject());
			
			//System.out.println("" + ois.readObject());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return bd;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "Toyin$judhdfhd$56551";
		AddressBook ad = new AddressBook();
		BuddyInfo bd = new BuddyInfo("Toyin", "Junction", "5445554");
		BuddyInfo bd2 = new BuddyInfo("","","");
		BuddyInfo bd3 = new BuddyInfo("ALex", "School", "5465121");
		System.out.println("" + bd3);
		//bd = BuddyInfo.importer(str1);
		//bd.toString();
		
		String str = bd.toString();
		System.out.println(str);
		ad.addBuddy("Toyin", bd);
		ad.export("output.txt");
		bd2 = ad.importer("output.txt");
		if(bd.equals(bd2)){
			System.out.println("They are the same object");
		}
		
		ad.objExport();
		ad.objImport();
		//System.out.println(ad.getSize());
	}
}