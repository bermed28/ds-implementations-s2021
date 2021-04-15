package ds.dictionary;

import java.util.Iterator;



public class SortedArrayDictionary_Tester_2 {

	public static void main(String[] args) {
		Character[] letras = {'a', 'c', 'a', 'd', 'b', 'f', 'h', 'w', 'k', 'b'}; 
		Integer[] keys = {5, 9, 8, 1, 6, 10, 13, 2, 11, 20}; 
		DictionaryInterface<Integer, Character> dict = 
				new SortedArrayDictionary<Integer, Character>(5); 
		
		for (Integer i=0; i<letras.length; i++)
			dict.add(keys[i],  letras[i]); 
		
		Iterator<Integer> iter = dict.getKeyIterator(); 
		
		while (iter.hasNext()) { 
			
			System.out.println(iter.next()); 
		}
 
		for (Entry<Integer, Character> e : dict.entries()) {
			System.out.println(e); 
		}
		
		System.out.println(dict.getValue(5) == 'b'); 
	}

}
