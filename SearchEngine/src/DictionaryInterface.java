

import java.util.Iterator;

public interface DictionaryInterface<K, V, F, W> {

	/**
	* Adds a new entry to this dictionary. If the given search key  and value already exists in the dictionary, adds filename and wordCounter to the list
	*@param key   an object search key of the new entry
	*@param value an object associated with the search key
	*@param fileName is the name of the file containing the value
	*@param wordCounter  is the number of times the value is found in the file
	**/
	public void add(K key, V value,F fileIndex,W wordCount);


	/**
	 * Sees whether this dictionary is empty.
	 * 
	 * @return true if the dictionary is empty
	 */
	public boolean isEmpty();

	/**
	 * Gets the size of this dictionary.
	 * 
	 * @return the number of entries (key-value pairs) currently in the dictionary
	 */
	public int getSize();

	
}



