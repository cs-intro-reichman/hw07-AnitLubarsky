
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
		int character1 = -1;
		int character2 = -1;

		for(int i = 0; i <  dictionary.length; i++){
			if (levenshtein(word, dictionary[i]) <= (threshold)){
				if(word.length() == dictionary[i].length()){
					character1 = i;
					System.out.println("!!!!!");

				} else if(word.length() > dictionary[i].length()) {	
					System.out.println("****");
					character2 = i;
				}					
			}
			
		}
		if(character1 >= 0){
				return dictionary[character1];
			}
			if(character2 >= 0){
				return dictionary[character2];
			}
		return word;
	}
}
