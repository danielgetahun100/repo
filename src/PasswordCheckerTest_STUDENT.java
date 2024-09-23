
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;
	String password1, password2;
	
	@Before
	 public void setUp() throws Exception {
        passwords = new ArrayList<>();

        // Add invalid passwords for testing
        passwords.add("short");       // LengthException
        passwords.add("TESTING12");   // NoLowerAlphaException
        passwords.add("afsf@12");     // NoUpperAlphaException
        passwords.add("12@abC");      // Valid (for contrast)
    }

	@After
	public void tearDown() throws Exception {
	
		passwords=null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			String password="short";
			PasswordCheckerUtility.isValidPassword(password);
		}
		catch(LengthException e) {
			assertTrue("correctly threw lengthEXcepion",true);
		}
		catch(Exception e) {
			fail("Incorrect exception thrown");
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		//A passing test satifying other requirenments
		try {
			String password="Atesting12@";//test case satisfying all other conditions
			boolean check=PasswordCheckerUtility.isValidPassword(password);
			assertTrue("has uppercase alpha",check);
		}
			
		catch(Exception e) {
			fail("Other exception thrown");
		}
		
		// Test case where the password does not contain an uppercase letter and should throw NoUpperAlphaException
	    try {
	        String password = "testing12!";  // No uppercase letter, but valid otherwise
	        PasswordCheckerUtility.isValidPassword(password);
	        fail("Expected NoUpperAlphaException to be thrown");
	    } catch (NoUpperAlphaException e) {
	        assertTrue("Correctly threw NoUpperAlphaException", true);
	    } catch (Exception e) {
	        fail("Incorrect exception thrown. Expected NoUpperAlphaException but got: " + e.getMessage());
	    }
	
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
	        String password = "1@BASBA"; // This password lacks a lowercase letter
	        PasswordCheckerUtility.isValidPassword(password);
	        fail("Expected NoLowerAlphaException to be thrown");// Expecting this exception
	    } catch (NoLowerAlphaException e) {
	        assertTrue("NoLowerAlphaException thrown as expected", true); // Correct behavior
	    } catch (Exception e) {
	        fail("Incorrect exception thrown. Expected NoLowerAlphaException but got: " + e.getMessage());
	    }
		}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			 
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("AD@123");
			assertTrue("Did not throw WeakPassword Exception",weakPwd);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		// Case 1: Password without more than 2 of the same character in sequence
	    try {
	        String validPassword = "Abdv123!@#"; // No sequence of more than 2 identical characters
	        boolean result = PasswordCheckerUtility.isValidPassword(validPassword);
	        assertTrue("Password should be valid and not throw an exception", result);
	    } catch (InvalidSequenceException e) {
	        fail("InvalidSequenceException should not be thrown for valid password");
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getMessage());
	    }
		//throws InvalidSequenceException if the exception is thrown
		  try {
		        String invalidPassword = "Aaabbbb123!@#"; // Sequence of 'b' occurs more than 2 times
		        PasswordCheckerUtility.isValidPassword(invalidPassword);
		        fail("Expected InvalidSequenceException to be thrown");
		    } catch (InvalidSequenceException e) {
		        assertTrue("Correctly threw InvalidSequenceException", true);
		    } catch (Exception e) {
		        fail("Incorrect exception thrown: " + e.getMessage());
		    }
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		  try {
		        String invalidPassword = "Aaabbbb@"; 
		        PasswordCheckerUtility.isValidPassword(invalidPassword);
		        fail("Expected InvalidSequenceException to be thrown");
		    } catch (NoDigitException e) {
		        assertTrue("Correctly threw InvalidSequenceException", true);
		    } catch (Exception e) {
		        fail("Incorrect exception thrown: " + e.getMessage());
		    }
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
	        String invalidPassword = "Aaabb@1223"; 
	        boolean check=PasswordCheckerUtility.isValidPassword(invalidPassword);
	        assertTrue("No exception was thrown",check);
	    
	    } catch (Exception e) {
	        fail("Shouldnt be throwing exceptions: " + e.getMessage());
	    }
	    
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testGetInvalidPasswords() {
	    ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(passwords);

	    // Check results for invalid passwords
	    assertEquals("short - The password must be at least 6 characters long", results.get(0));
	    assertEquals("TESTING12 - The password must contain at least one lowercase alphabetic character", results.get(1));
	    assertEquals("afsf@12 - The password must contain at least one uppercase alphabetic character.", results.get(2));
	}
}
