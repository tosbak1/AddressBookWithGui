import junit.framework.TestCase;

public class BuddyInfoTest extends TestCase {
	private BuddyInfo bd;
	
	public void setUp(){
		bd = new BuddyInfo("Toyin", "Junction", "54561231");
	}
	
	public void testBuddyInfo(){
		BuddyInfo bd2 = new BuddyInfo(bd);
		assertEquals(bd2.getName(), bd.getName());
		assertEquals(bd2.getAddress(), bd.getAddress());
		assertEquals(bd2.getPhoneNum(), bd.getPhoneNum());

	}
	
	public void testgetName(){
		assertEquals("Toyin", bd.getName());
	}
	
	public void testAgeSetterGetter(){
		int age = 21;
		bd.setAge(age);
		assertEquals(21, bd.getAge());
	}
	
	public void testisOver18true(){
		bd.setAge(21);
		assertTrue(bd.isOver18());
		
	}
	
	public void testisOver18false(){
		bd.setAge(10);
		assertFalse(bd.isOver18());
		
	}
	
	public void testisOver18Middle(){
		bd.setAge(18);
		assertFalse(bd.isOver18());
		
	}
	
	
	public void testgetAddress(){
		assertEquals("Junction", bd.getAddress());
	}
	
	public void testgetPhoneNum(){
		assertEquals("54561231", bd.getPhoneNum());
	}

	public void testgreeter(){
		assertEquals("Wasssap my g", bd.greeter());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		junit.textui.TestRunner.run(BuddyInfoTest.class);

	}
}
