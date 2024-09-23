import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility{
	
	public PasswordCheckerUtility() {
		
	}
	
	/**
	 *  Checks if the entered passwords are equal
	 *  
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if(!password.equals(passwordConfirm)) {
			throw new UnmatchedException("Passwords do not match");
		}
		
	}
	/**
	 * Checks if the entered password is equal and returns a boolean
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return
	 */
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		if(password.equals(passwordConfirm)) {
			return true;
		}
		else return false;
	}
	/**
	 * Checks if the password is not a valid and throws if the value is less than 6
	 * 
	 * @param password
	 * @return
	 * @throws LengthException
	 */
	
	public static boolean isValidLength(String password) throws LengthException{
		if(password.length()<6) {
			throw new LengthException("The password must be at least 6 characters long");
			}
		else return true;
	}
	
	/**
	 * Checks if the password has uppercase letter
	 * 
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException
	 */
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
	    for(int i=0;i<password.length();i++) {
	    	if(Character.isUpperCase(password.charAt(i))) {
	    		return true;
	    	}
	    	
	    }
	    throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character.");
	}
	/**\
	 * Checks if the password has lower case letter
	 * 
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for(int i=0;i<password.length();i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				return true ;
			}
		}
		throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
	}
	/**
	 * Checks if the password has digits 
	 * 
	 * @param password
	 * @return
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException{
		for(int i=0;i<password.length();i++) {
			if(Character.isDigit(password.charAt(i))) {
				return true;
			}
		}
		throw new NoDigitException("The password must contain at least one digit");
		
	}
	/**
	 * Checks if the password has special char
	 * 
	 * @param password
	 * @return
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password)throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher=pattern.matcher(password);
		
		if(matcher.find()) {
			return true;
		}
		
		throw new NoSpecialCharacterException("The password must contain at least one special character");
	}
	/**
	 * Checks if the password has two or more of the same letter or digit
	 * 
	 * @param password
	 * @return
	 * @throws InvalidSequenceException
	 */
	public static boolean NoSameCharInSequence(String password)throws InvalidSequenceException {
		
		Pattern pattern=Pattern.compile("(.)\\1{2,}");
		Matcher matcher=pattern.matcher(password);
		
		if(matcher.find()) {
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in ");
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	
	public static boolean isValidPassword(String password) throws LengthException,NoUpperAlphaException, NoLowerAlphaException, NoDigitException,NoSpecialCharacterException,InvalidSequenceException{
			
		//check for valid length
		isValidLength(password);
	        
	        // Check for at least one uppercase letter
	    hasUpperAlpha(password);
	        
	        // Check for at least one lowercase letter
	    hasLowerAlpha(password);
	        
	        // Check for at least one digit
	    hasDigit(password);
	        
	        // Check for at least one special character
	    hasSpecialChar(password);
	        
	        // Check for no more than two of the same character in sequence
	    NoSameCharInSequence(password);
	    
	  
			
	        
	        // If all checks pass, return true
	        return true;
	        
			
			
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		//checks if the password is valid and between the 6-9 range and throws weak password exception
		
			
				if((password.length()>=6&&password.length()<=9)) {
					throw new WeakPasswordException("Password is OK but weak");
				}
				
			return false;
		}
	/**
	 * checks an array list an throws the appropriate exception
	 * 
	 * @param passwords
	 * @return
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
	    ArrayList<String> invalidPasswords = new ArrayList<>();

	    for (String password : passwords) {
	        try {
	            // Validate the password
	            isValidPassword(password);  
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
	        // Note: WeakPasswordException is not included as it does not count as invalid
	    }
	    return invalidPasswords;
	
	    
	}}
        
	
