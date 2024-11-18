import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author DTekabe
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree morseTree=new MorseCodeTree();
	
	public MorseCodeConverter() {
		
	}
	
	public static String printTree() {
		ArrayList<String> treeData=morseTree.toArrayList();

		StringBuilder result = new StringBuilder();
		
        for(String data : treeData) {
        	if(data.isEmpty()) {
        		result.append(" ");
        	}
        	else {
        		result.append(data).append(" ");
        	}
        	
        	
        	
        }
        return result.toString().trim();
	}
	
	/**
	 * 
	 * @param code
	 * @return the converted string
	 */
	public static String convertToEnglish(String code) {
		StringBuilder english=new StringBuilder();
		
		String[] words=code.split(" / ");
		
		for(String word : words) {
			
			String[] letters =word.split(" ");
			
			for(String letter : letters) {
				
				english.append(morseTree.fetch(letter));
			}
			english.append(" ");
			
		}
		
		return english.toString().trim();
		/**
		 * @return the converted string
		 */
	}
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder englishSentence = new StringBuilder();
        Scanner fileScanner = new Scanner(codeFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            englishSentence.append(convertToEnglish(line));
            englishSentence.append("\n");
        }

        fileScanner.close();
        return englishSentence.toString().trim();
    }

}
