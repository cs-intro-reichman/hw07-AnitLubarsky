import java.util.Arrays;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		////printReadDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
		//existInDictionarTest(hashTag, dictionary);
	}
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
	public static void existInDictionarTest(String word, String []dictionary) {
		if(existInDictionary(word, dictionary) == true){
		System.out.println("yes");
		} else {
			System.out.println("NO");
		}
	}
	public static boolean existInDictionary(String word, String []dictionary) {
		int count = 0;
		boolean existInDictionary = false;
		for( int i = 0; i < dictionary.length; i++){
			if(word.length() == dictionary[i].length()){
				count = 0;
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
		for ( i = 1; i <= N; i++){			
				String hashtagSub = hashtag.substring(0, i);	
				if(existInDictionary(hashtagSub, dictionary) == true){
					System.out.printf("%s ",hashtagSub );
				    break;
				}
		}
		if (i < N ){
			String remainingHashtag = hashtag.substring(i);	
			breakHashTag(remainingHashtag,dictionary);
		}
	}	
}
