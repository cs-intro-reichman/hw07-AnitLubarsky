import java.util.Arrays;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}
	//To test the readDictionary method
	public static void printReadDictionary( String fileName) {
		System.out.print(Arrays.toString(readDictionary(fileName)));
	}
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}
		return dictionary;
	}
	//To test the exist\inDictionary method
	public static void existInDictionarTest(String word, String []dictionary) {
		if(existInDictionary(word, dictionary) == true){
		System.out.println("yes");
		} else {
		System.out.println("NO");
		}
	}
	
	public static boolean existInDictionary(String word, String []dictionary) {
		int count = 0; //Counts how many times the character at word is equal to the character at dictionary
		boolean existInDictionary = false;
		for( int i = 0; i < dictionary.length; i++){
			if(word.length() == dictionary[i].length()){
				count = 0; //nullify the count
				for(int j = 0; j < word.length(); j++){	
					if(word.charAt(j) == dictionary[i].charAt(j)){
						count++;
					}								
				}
			
				if(count == word.length()){
					existInDictionary = true;
					break;					
				}				
			}				
		}
		return existInDictionary;				
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		int i = 0;
        int N = hashtag.length();	
		// Checks if the string is a word. If the loop end and there is no match gets into recursion.
		for ( i = 1; i <= N; i++){			
				String hashtagSub = hashtag.substring(0, i);	
				if(existInDictionary(hashtagSub, dictionary) == true){
					System.out.println(hashtagSub);
				    break;
				}
		}
		//recursion: calling this method on the remaining hashtag string
		if (i < N ){
			String remainingHashtag = hashtag.substring(i);	
			breakHashTag(remainingHashtag,dictionary);
		}
	}	
}
