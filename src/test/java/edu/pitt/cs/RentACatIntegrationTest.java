package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatIntegrationTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

	@Before
	public void setUp() throws Exception {
		// INITIALIZE THE TEST FIXTURE

		// 1. Create a new RentACat object and assign to r using a call to RentACat.createInstance(InstanceType).
		// Passing InstanceType.IMPL as the first parameter will create a real RentACat object using your RentACatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock RentACat object using Mockito.
		// Which type is the correct choice for this integration test?  I'll leave it up to you.  The answer is in the Unit Testing Part 2 lecture. :)
		// TODO: Fill in
        r = RentACat.createInstance(InstanceType.IMPL);

		// 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call to Cat.createInstance(InstanceType, int, String).
		// Passing InstanceType.IMPL as the first parameter will create a real cat using your CatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock cat using Mockito.
		// Which type is the correct choice for this integration test?  Again, I'll leave it up to you.
		// TODO: Fill in
        c1 = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");

		// 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
        c2 = Cat.createInstance(InstanceType.IMPL, 2, "Old Deuteronomy");

		// 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
        c3 = Cat.createInstance(InstanceType.IMPL, 3, "Mistoffelees");
		
        //r.addCat(c1);
        //r.addCat(c2);
        //r.addCat(c3);
		// 5. Redirect system output from stdout to the "out" stream
		// First, make a back up of System.out (which is the stdout to the console)
        out = new ByteArrayOutputStream();
		stdout = System.out;
		// Second, update System.out to the PrintStream created from "out"
		// TODO: Fill in.  Refer to the textbook chapter 14.6 on Testing System Output.
        System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() throws Exception {
		// Restore System.out to the original stdout
		System.setOut(stdout);

		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix 
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNullNumCats0() {
		// TODO: Fill in
		try {
        java.lang.reflect.Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
        getCatMethod.setAccessible(true);
        
        Object result = getCatMethod.invoke(r, 2);
        
        assertNull(result);
        
		assertTrue(out.toString().startsWith("Invalid cat ID."));
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix 
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNumCats3() {
		// TODO: Fill in
		try {
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        java.lang.reflect.Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
        getCatMethod.setAccessible(true);
        
        Object result = getCatMethod.invoke(r, 2);
        
        assertNotNull(result);
        
        assertEquals(2, ((Cat) result).getId());
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats0() {
		// TODO: Fill in
		try {
        java.lang.reflect.Method listCatsMethod = r.getClass().getDeclaredMethod("listCats");
        listCatsMethod.setAccessible(true);
        
        Object result = listCatsMethod.invoke(r);
        
        assertEquals("", result);
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats3() {
		// TODO: Fill in
		try {
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        java.lang.reflect.Method listCatsMethod = r.getClass().getDeclaredMethod("listCats");
        listCatsMethod.setAccessible(true);
        
        Object result = listCatsMethod.invoke(r);
        
        String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n";
        
        assertEquals(expected, result);
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is false.
	 *                 c2 is not renamed to "Garfield".
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 */
	@Test
	public void testRenameFailureNumCats0() {
		// TODO: Fill in
		try {
        java.lang.reflect.Method renameCatMethod = r.getClass().getDeclaredMethod("renameCat", int.class, String.class);
        renameCatMethod.setAccessible(true);
        
        Object result = renameCatMethod.invoke(r, 2, "Garfield");
        
        assertFalse((boolean) result);
        
		assertTrue(out.toString().startsWith("Invalid cat ID."));
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is true.
	 *                 c2 is renamed to "Garfield".
	 * </pre>
	 */
	@Test
	public void testRenameNumCat3() {
		// TODO: Fill in
		try {
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        java.lang.reflect.Method renameCatMethod = r.getClass().getDeclaredMethod("renameCat", int.class, String.class);
        renameCatMethod.setAccessible(true);
        
        Object result = renameCatMethod.invoke(r, 2, "Garfield");
        
        assertTrue((boolean) result);
        
        java.lang.reflect.Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
        getCatMethod.setAccessible(true);
        
        Cat renamedCat = (Cat) getCatMethod.invoke(r, 2);
        assertEquals("Garfield", renamedCat.getName());
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is rented as a result of the execution steps.
	 *                 System output is "Old Deuteronomy has been rented." + newline
	 * </pre>
	 */
	@Test
	public void testRentCatNumCats3() {
		// TODO: Fill in
		try {
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        java.lang.reflect.Method rentCatMethod = r.getClass().getDeclaredMethod("rentCat", int.class);
        rentCatMethod.setAccessible(true);
        
        Object result = rentCatMethod.invoke(r, 2);
        
        assertTrue((boolean) result);
        
        java.lang.reflect.Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
        getCatMethod.setAccessible(true);
        Cat rentedCat = (Cat) getCatMethod.invoke(r, 2);
        assertTrue(rentedCat.getRented());
        
        assertEquals("Old Deuteronomy has been rented." + newline, out.toString());
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 stays rented.
	 *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
	 * </pre>
	 */
	@Test
	public void testRentCatFailureNumCats3() {
		// TODO: Fill in
		try {
        // Add cats to r
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        java.lang.reflect.Method rentCatMethod = r.getClass().getDeclaredMethod("rentCat", int.class);
        rentCatMethod.setAccessible(true);
        rentCatMethod.invoke(r, 2);
		
		out.reset();

        Object result = rentCatMethod.invoke(r, 2);
        assertFalse((boolean) result);

        java.lang.reflect.Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
        getCatMethod.setAccessible(true);
        Cat rentedCat = (Cat) getCatMethod.invoke(r, 2);
        assertTrue(rentedCat.getRented());

        assertEquals("Sorry, Old Deuteronomy is not here!" + newline, out.toString());
    	} catch (Exception e) {
        	fail("Exception thrown: " + e.getMessage());
    	}
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is returned as a result of the execution steps.
	 *                 System output is "Welcome back, Old Deuteronomy!" + newline
	 * </pre>
	 */
	@Test
	public void testReturnCatNumCats3() {
		// TODO: Fill in
		r.addCat(c1);
    	r.addCat(c2);
    	r.addCat(c3);
    
    	// Rent c2
    	r.rentCat(2);
    
    	// Capture System output
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    
    	// Act: Call returnCat(2)
    	boolean result = r.returnCat(2);
    
    	// Assert: Check return value and system output
    	assertTrue(result);
		assertFalse(c2.getRented());
    	assertEquals("Welcome back, Old Deuteronomy!" + System.lineSeparator(), outContent.toString());
    
    	// Reset System output
    	System.setOut(System.out);
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 stays not rented.
	 *                 System output is "Old Deuteronomy is already here!" + newline
	 * </pre>
	 */
	@Test
	public void testReturnFailureCatNumCats3() {
		// TODO: Fill in
		r.addCat(c1);
    	r.addCat(c2);
    	r.addCat(c3);
    
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    
    	boolean result = r.returnCat(2);
    
    	assertFalse(result);
    	assertFalse(c2.getRented());
    	assertEquals("Old Deuteronomy is already here!" + System.lineSeparator(), outContent.toString());
    
    	System.setOut(System.out);
	}

}