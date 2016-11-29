import java.io.Serializable;

public class BuddyInfo implements Serializable 
{
	private String name,               // name of buddy
	               address,            // address of buddy
	               phone_num;          // phone number of buddy
	private int age;
	/**
	 * Constructor for the BuddyInfo class
	 * 
	 * @param name -> String representing the name of the buddy
	 * @param address -> String representing the address of the 
	 *                   buddy.
	 * @param phone_num -> String representing the phone number 
	 *                     of the buddy.
	 *
	 */
	
	public BuddyInfo(String name, String address, String phone_num)
	{
		this.name = name;
		this.address = address;
		this.phone_num = phone_num;
	}
	
	public BuddyInfo(BuddyInfo bd){
		this.name = bd.name;
		this.address = bd.address;
		this.phone_num = bd.phone_num;
	}
	
	/**
	 * Accessor for name of the buddy.
	 * 
	 * @return name -> String representing the name of the
	 *                 buddy
	 */
	
	public String getName()
	{
		return this.name;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public boolean isOver18(){
		if(this.age > 18){
			return true;
		}
		return false;
	}
	/**
	 * Accessor for the address of the buddy.
	 * 
	 * @return address -> String representing the address of the 
	 *                    buddy.
	 */
	
	public String getAddress()
	{
		return this.address;
	}
	
	
	/**
	 * Accessor for the phone number of the buddy.
	 * 
	 * @return phone_num -> String representing the phone number 
	 *                      of the buddy.
	 */
	
	public String getPhoneNum()
	{
		return this.phone_num;
	}
	
	public String greeter(){
		return "Wasssap my g";
	}
	
	public String toString(){
		return ""+ this.name +"$"+""+this.address +"$" + "" +this.phone_num;
	}
	
	public static BuddyInfo importer(String str){
		String[] str1 = str.split("\\$");
		BuddyInfo bd = new BuddyInfo(str1[0], str1[1], str1[2]);
		return bd;
	}
	
	public boolean equals(BuddyInfo bd){
		return (this.name.equals(bd.name) && this.address.equals(bd.address) && this.phone_num.equals(bd.phone_num));
	}
	
	public String toXML(){
		return "<BuddyInfo>"+"\n\t<name> "+this.name+" </name>\n"+"\t<address> "+this.address+" </address>\n"+"\t<phoneNumber> "+this.phone_num+" </phoneNumber>\n"+"</BuddyInfo>\n";
	}
}