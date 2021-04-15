package ds.dictionary;

import java.util.ArrayList;
import java.util.Random;

public class SortedArrayDictionary_Tester_1 {

	public static void main(String[] args) {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		ArrayList<Character> list = new ArrayList<>(); 

		Random rnd = new Random(); 
 
		for (int i=0; i<50; i++) {
			int r = rnd.nextInt(26); 
			list.add(letters.charAt(r));  
		}

		System.out.println(list); 
		
		// add code to find the frequency of each letter in the list
		// using the dictionary
		DictionaryInterface<Character, Integer> dict = countFrequencies(list);  

		// display frequencies
		for (Entry<Character, Integer> e : dict.entries()) 
			System.out.println(e); 
	}

	private static DictionaryInterface<Character, Integer> 
	countFrequencies(ArrayList<Character> list) { 
		DictionaryInterface<Character, Integer> dict = new SortedArrayDictionary<>(); 
		
		for (Character c : list) { 
			Integer count = dict.getValue(c);
			if (count == null) 
				dict.add(c, 1); 
			else 
				dict.add(c, count+1); 
		}
		
		return dict; 
	}
}
