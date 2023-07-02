

public class HashedDictionary<K, V, F, W> implements DictionaryInterface<K, V,F, W> {
	private TableEntry<K, V, F, W>[] hashTable; 
	private int numberOfEntries;
	private int locationsUsed;
	private static final int DEFAULT_SIZE = 2477; 
	private static final double MAX_LOAD_FACTOR = 0.5;

	public HashedDictionary() {
		this(DEFAULT_SIZE); 
	} 

	@SuppressWarnings("unchecked")
	public HashedDictionary(int tableSize) {
		int primeSize = getNextPrime(tableSize);
		hashTable = new TableEntry[primeSize];
		numberOfEntries = 0;
		locationsUsed = 0;
	}

	public boolean isPrime(int num) {
		boolean prime = true;
		for (int i = 2; i <= num / 2; i++) {
			if ((num % i) == 0) {
				prime = false;
				break;
			}
		}
		return prime;
	 }

	 public int getNextPrime(int num) {
		if (num <= 1)
            return 2;
		else if(isPrime(num))
			return num;
        boolean found = false;   
        while (!found)
        {
            num++;     
            if (isPrime(num))
                found = true;
        }     
        return num;
	 }

	 public void add(K key, V value, F fileName, W wordCount) {
		
		if (isHashTableTooFull()) {
			rehash();
		}
		int index = probeDH( key, value); 

		if ((hashTable[index] == null) ) { 
			hashTable[index] = new TableEntry<K, V, F, W>(key, value, fileName, wordCount);
			numberOfEntries++;
			locationsUsed++;
			
		} else {
			//Adds or sets filename and wordCount to the linked list of pre-existing keys and values.
			hashTable[index].setFileSLL(fileName,wordCount);
		} 
	  }
	  
	  
	  //USED FOR PAF CONDITION
	  public static int keyComputePAF(String value) {
		 int key=0;
		 for (int i = 0; i < value.length(); i++) {
	            String tempChar = value.substring(i, i + 1);
	            key+=Character.getNumericValue(tempChar.charAt(0))* Math.pow(5,i);
	        }
		 return key;
	  }
 
	   public boolean isHashTableTooFull() {
		double load_factor = (double)locationsUsed / (double)hashTable.length;
		if (load_factor >= MAX_LOAD_FACTOR)
			return true;
		return false;
	   }

	   @SuppressWarnings("unchecked")
	   public void rehash() {
		TableEntry<K, V, F, W>[] oldTable = hashTable;
		int oldSize = hashTable.length;
		int newSize = getNextPrime(2 * oldSize);
		hashTable = new TableEntry[newSize]; 
		numberOfEntries = 0; 
		locationsUsed = 0;

		 for (int index = 0; index < oldSize; index++) {
			if(oldTable[index]!=null && oldTable[index].isIn()) {
				//this loop exists to add all files and wordCounts inside the list
				for(int i=0;i < oldTable[index].getFileSize();i++)
				{ add(oldTable[index].getKey(),oldTable[index].getValue(),oldTable[index].getFileName(i), oldTable[index].getWordCount(i));
				}
			} 
		 }
	    }
	   
	
	   //Indexing withDouble Hash Function
	   private int probeDH(K key,V value) {
		int index=(int)key;
		int f1=index % hashTable.length;
		int f2= 17 + (index % 17);
		index=f1;
		boolean found = false;
		int i=0;//coefficient of the second function
		  while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn()) {
				if (key.equals(hashTable[index].getKey())&& value.equals(hashTable[index].getValue()))
					found = true; 
				else { 
					index = (f1 + (i*f2)) % hashTable.length;
					i++;
			    }
			} 
			
		   } 
		  return index;  
	     }
	
       //Checks whether the word exists according to the double hash function, returns an index.
	   private int locateDH( K key, V value) {
		int index=(Integer)key;
		int f1=index % hashTable.length;
		int f2= 17 + (index % 17);
		index=f1;
		int i=0;//coefficient of the second function
		boolean found = false;
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn() && key.equals(hashTable[index].getKey())&& value.equals(hashTable[index].getValue()))
				found = true; 
			else 
			{  
				index = (f1 + (i*f2)) % hashTable.length;
				i++; 
			}
		} 
		int result = -1;//if does not exist
		if (found)
			result = index;
		return result;
	   }
	

	   //Adds the files containing the searched word and the number of occurrences in these files to the list.
	   public void addSLL(int index, SingleLinkedList searchSLL) {
		  boolean flag=true;
		        for(int i=0;i < hashTable[index].getFileSize();i++)
				{ if(searchSLL.size()!=0) {
					  flag=	searchSLL.searchAndAdd((String)hashTable[index].getFileName(i), (Integer)hashTable[index].getWordCount(i));
					}
					if(searchSLL.size()==0 || flag==false)
					{
						searchSLL.add((String)hashTable[index].getFileName(i), (Integer)hashTable[index].getWordCount(i));
					}
					
			    }
	     }
			
	
	    public boolean search(K key, V value, SingleLinkedList searchSLL) {
		 
		  int index = locateDH(key, value);
		    if (index != -1) {
			  addSLL(index, searchSLL);
			  return true;
		    }
		
		  return false;
	    }
	

	   public boolean isEmpty() {
		return numberOfEntries == 0;
	   }

	   public int getSize() {
		return numberOfEntries;
	   }


	private class TableEntry<S, T, U, Y > {
		private S key;
		private T value;
		private U fileName;
		private Y wordCount;
		private SingleLinkedList fileSLL;
		private boolean inTable;

		private TableEntry(S key, T value, U fileName , Y wordCount) {
			this.key = key;
			this.value = value;
			this.fileName=fileName;
			this.wordCount=wordCount;
			fileSLL=new SingleLinkedList();
			inTable = true;
			fileSLL.add((String)fileName,(Integer)wordCount);
		}
       
		public void  setFileSLL(U fileName,Y wordCount) {
		
			if(fileSLL.search((String)fileName)==true)//if this file has been added before
			{
				fileSLL.wordCounterIncrease((String) fileName );//wordcount is increased by 1
			}
			else {//if the file is to be added for the first time
				fileSLL.add((String)fileName,(Integer)wordCount);
			}
		}
		
		private S getKey() {
			return key;
		}

		private T getValue() {
			return value;
		}

		private void setValue(T value) {
			this.value = value;
		}
		
		//returns the WordCount at the desired node of the list
		private Y getWordCount(int fileNumber) {
			wordCount= (Y)fileSLL.searchFileNumForWordCount(fileNumber);
			return wordCount;
		}
		
		//returns the filename at the desired node of the list
        private U getFileName(int fileNumber) {
			fileName=(U) fileSLL.searchFileNumForName(fileNumber);
			return fileName;
		}
		
	    //returns the size of the list
        private int getFileSize() {
			int size = fileSLL.size();
			return size;
		}
		
	
		private boolean isIn() {
			return inTable == true;
		}
	}

	
}




