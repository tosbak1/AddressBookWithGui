import junit.framework.TestCase;

public class AddressBookTest extends TestCase {
	private AddressBook adr = null;
	private BuddyInfo bd = null;
	
	public void setUp(){
		adr = new AddressBook();
		bd = new BuddyInfo("Toyin", "Junction rd", "656146545");

	}
	
	public void testemptyList(){
		assertEquals(0, adr.getSize());
	}
	
	public void testaddBuddy(){
		adr.addBuddy("Toyin", bd);

		assertEquals(1, adr.getSize());
	}
	
	public void testgetSize(){
		adr.addBuddy("Toyin", bd);

		assertEquals(1, adr.getSize());
	}
	
	public void testclearList(){
		adr.addBuddy("Toyin", bd);

		adr.clearList();
		assertEquals(0, adr.getSize());
	}
	
	public void testremove(){
		adr.addBuddy("Toyin", bd);

		adr.remove("Toyin");
		assertEquals(0, adr.getSize());
	}
	
	public void testremoveNotthere(){
		adr.addBuddy("Toyin", bd);
		adr.remove("Peter");
		assertEquals(1, adr.getSize());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		junit.textui.TestRunner.run(AddressBookTest.class);

	}
}

