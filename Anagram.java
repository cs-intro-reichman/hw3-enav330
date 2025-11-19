/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
	String pStr1 = preProcess(str1);
    String pStr2 = preProcess(str2);
        if (pStr1.length() != pStr2.length()) {
            return false;
        }
    
        for (int i = 0; i < pStr1.length(); i++) {
            char targetChar = pStr1.charAt(i);
            boolean found = false;
            
            for (int j = 0; j < pStr2.length(); j++) {
                if (pStr2.charAt(j) == targetChar) {
                    
                    pStr2 = pStr2.substring(0, j) + pStr2.substring(j + 1);
                    found = true;
                    break; 
                }
            }
            if (!found) {
                return false;
            }
            

        }
        
        return pStr2.length() == 0;
    }
	
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
        
        String result = "";
        
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            
            if (currentChar >= 'a' && currentChar <= 'z') {
                result += currentChar;
            }
        }
        
        return result;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String remainingChars = str; 
        String result = "";
        while (remainingChars.length() > 0) 
		{
            int index = (int) (Math.random() * remainingChars.length()); 
            char randomChar = remainingChars.charAt(index);
            result += randomChar;
            remainingChars = remainingChars.substring(0, index) + remainingChars.substring(index + 1);
        }
        
        return result;
    
	}
}
