
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tailStr = str.substring(1);		
		return tailStr; 
	}
	//To test the levinshtein method
	public static void levenshteinTest(String word1, String word2) {
	System.out.println(levenshtein(word1, word2));	
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		 
		if(word1.isEmpty()){
			return word2.length();
		} else if(word2.isEmpty()){
			return word1.length();
		} else if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		} else {
			int lev1 = levenshtein(tail(word1), word2);
			int lev2 = levenshtein(word1, tail(word2));
			int lev3 = levenshtein(tail(word1), tail(word2));
			 return 1 + Math.min(lev1, Math.min(lev2, lev3));
		}		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// To remember the i in the loop in witch the word is in the dictionary 
		int character1 = -1;
		int character2 = -1;

		for(int i = 0; i <  dictionary.length; i++){
			//If the levinshtein distence is grater than the threshold then return the word
			if (levenshtein(word, dictionary[i]) <= (threshold)){
				//The word in the dictionary is either equal or longer form the word
				if(word.length() == dictionary[i].length()){
					character1 = i;
				} else if(word.length() > dictionary[i].length()) {	
					character2 = i;
				}					
			}
			
		}
		//If the word is equal in length to the word in the dictionary than this is the return word
		if(character1 >= 0){
				return dictionary[character1];
			}
		//If the word is shorter than the word in the dictionary than this is the return word 
		if(character2 >= 0){
			return dictionary[character2];
		}
		return word;
	}
}
