

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PasswordCheckerTestPublic {
	ArrayList<String> passwordsArray;
	String password = "Hello";
	String passwordConfirm = "hello";
	String allCaps = "HELLO";
	String withDigit = "Hello6";

	@BeforeEach
	void setUp() throws Exception {
		String[] p = {"334455BB", "Im2cool4U",withDigit,};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
	}

	@AfterEach
	void tearDown() throws Exception {
		passwordsArray = null;
	}

	@Test
	void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(password, passwordConfirm);				
			}
		});
		
		assertEquals("Passwords do not match", exception.getMessage());
	}
	
	@Test 
	void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
	}	
	
	@Test
	void testUppereAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIsValidLength() {
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	@Test
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
	    ArrayList<String> invalidPasswords = new ArrayList<>();

	    for (String password : passwords) {
	        try {
	            // Validate the password
	            PasswordCheckerUtility.isValidPassword(password);
	        } 
	        catch (LengthException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        catch (NoUpperAlphaException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        catch (NoLowerAlphaException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        catch (NoDigitException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        catch (NoSpecialCharacterException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        catch (InvalidSequenceException e) {
	            invalidPasswords.add(password + " - " + e.getMessage());
	        }
	        // Weak passwords are ignored, do not catch WeakPasswordException
	    }
	    return invalidPasswords;
	}

}
