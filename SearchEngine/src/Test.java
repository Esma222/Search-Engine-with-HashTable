
import java.io.IOException;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.*;

public class Test {
	
	
	 public static void main(String[] args) throws IOException
	 {
		 try {
		
		 //ADD PART
		 String[] stopWords = getTXTWords("stop_words_en.txt");//Stop words array
		 //Hash Dictionary created.
         HashedDictionary<Integer, String , String, Integer> sportFilesDataBase = new HashedDictionary<Integer, String, String, Integer>();
         
         //one hundred txt will read
         String fileName;
         Files[] filesArray= new Files[100];// keeps how many words are in each file.
       
		 for(int a =1;a<=100;a++) {
			 if(a<10) {
				 fileName="00"+Integer.toString(a);
			 }
			 else if(a<100) {
				 fileName="0"+Integer.toString(a);
			 }
			 else {
				 fileName=Integer.toString(a);
			 }
			 
		 File sportFile = new File(fileName+".txt");
		 
         Scanner sportFileReader = new Scanner(sportFile);
         
         int counter=0;//keeps the total number of words in a file
         
         boolean flagStopWord=false,flagPunction=false;//
         
          while (sportFileReader.hasNextLine()) {
        	 String[] line= sportFileReader.nextLine().split(" ");
             for(int j=0 ;j<line.length;j++)
             {   
            	flagStopWord=false;
            	flagPunction=false;
            	 
            	 String value=line[j];
            	 if(removePunction(value)=="")// for example value is "9!" or "2022" etc.
            		 flagPunction=true;
            	 if(flagPunction!=true)
            	 { for (int k = 0; k < stopWords.length; k++) {
            		 //Remove punctuation 
            		 value=(removePunction(value)).toLowerCase(Locale.ENGLISH); 
                         if (value.equalsIgnoreCase(stopWords[k])) {
                        	 flagStopWord=true;
                        	 break;
                         }
                     }
                if(flagStopWord!=true)//skips without adding stop words
                {
                	sportFilesDataBase.add(sportFilesDataBase.keyComputePAF(value), value ,fileName, 1);
                }
            	 }
             }
             counter+=line.length;
	      }
         sportFileReader.close();
         
         // the name of the files and the total number of words in it are taken for the frequency
         // throws into an array.
         Files temp = new Files(fileName,counter);
         filesArray[a-1]=temp;
         }
		
		 //SEARCH PART
		 String[] searchWordsArray;
		 do {
		 Scanner sc =new Scanner(System.in);
         System.out.print("\nPlease enter three word : ");
         String searchWords=sc.nextLine();
         System.out.print("\n"); 
		 
         searchWordsArray= searchWords.split(" ");
		 }while(searchWordsArray.length!=3);//it must be three
		 
         SingleLinkedList searchWordsFilesSLL=new SingleLinkedList();
         
         for(int i=0;i< searchWordsArray.length;i++) {
        	 String word = searchWordsArray[i].toLowerCase(Locale.ENGLISH);
        	 sportFilesDataBase.search(sportFilesDataBase.keyComputePAF(word), word, searchWordsFilesSLL);
         }
         
         //if the list is not empty, it calculates the frequency 
         // And adds it to the priority queue according to the frequencies.
		
         PriorityQueue priorityQ = new PriorityQueue("The file containing these words could not be found", 0);
         for(int i=0;i < searchWordsFilesSLL.size();i++) {
        	 
        	 for(int j=0;j < filesArray.length ;j++) {//filesArray.length
        	 
        	   if( filesArray[j].name == searchWordsFilesSLL.searchFileNumForName(i)) {
        		   String name= "File "+filesArray[j].name;
        		   int searchFileNumForWordCount = (int)searchWordsFilesSLL.searchFileNumForWordCount(i);
        		   double frequency=(double) searchFileNumForWordCount /(double)filesArray[j].getTotalWordCount();
        		   priorityQ .insert(name, frequency);
        		  
        	   }
        	 }
          }
         
         //shows the most relevant file.
		 System.out.println();
		 System.out.println(priorityQ.getMaxPriority().getData());
		
		 }
		 catch (FileNotFoundException e) {
	        	System.out.println("  File not found! Try again.");
		 }
		 catch(NullPointerException e)
	        {
	            System.out.print("NullPointerException Caught");
	        }
		 catch (Exception e) {
	            System.out.println("\nException caught");
		 }
	 }
	
	   //Punctuation marks and numbers are removed from the entered value.
	   //The new value is returned.
		public static String removePunction(String value) {
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
	        String tempStr = value;
	        for (int j = 0; j < value.length(); j++) {

	            String tempChar = value.substring(j, j + 1);
	            if (!alphabet.contains(tempChar.toLowerCase(Locale.ENGLISH))) {
	                tempStr = tempStr.replace(tempChar, "");
	            }
	        }
	        return tempStr; 
	     }
		
		 //It puts the words in the incoming file into a array. Returns the array.
	     public static String[] getTXTWords(String fileName) {
	        try {
	            File TXTWordsFile = new File(fileName);
	            Scanner TXTLineReader = new Scanner(TXTWordsFile);
	            int TXTLineCounter = 0;
	            while (TXTLineReader.hasNextLine()) {
	            	TXTLineCounter++;
	            	TXTLineReader.nextLine();
	            }
	            TXTLineReader.close();
	            String[] TXTWords = new String[TXTLineCounter];

	            Scanner TXTReader = new Scanner(TXTWordsFile);
	            int arrCounter = 0;
	            while (TXTReader.hasNextLine()) {
	            	TXTWords[arrCounter] = TXTReader.nextLine();
	                arrCounter++;
	            }
	            TXTReader.close();

	            return TXTWords;
	        } catch (FileNotFoundException e) {
	        	System.out.println("  File not found! Try again.");
	        }
	        return null;
	    }
	 
}




