


//For SingleLinkedList
public class Node {
	private String fileName;
	private int wordCounter;
	private Node link;
	 public Node(String fileNameToAdd,int wordCounterToAdd) {
		  fileName=fileNameToAdd;
		  wordCounter=wordCounterToAdd;
		  link=null;
	  }
	public String getFileName()
	{ return this.fileName; }
	
	public void setFileName(String fileName)
	{ this.fileName = fileName; }
	
	public int getWordCounter()
	{ return this.wordCounter; }
	
	public void setWordCounter()
	{ this.wordCounter++ ; }
	
	
	public void setWordCounterAdd(int wCount)
	{ this.wordCounter+=wCount; }
	
	public Node getLink() {
		return link;
	}
	public void setLink(Node link) {
		this.link = link;
	}

}

