


public class SingleLinkedList {
    Node head;
    

	public void add(String fileName , int wordCount) {//ekleme methodu
		if(head==null) {
			Node newNode= new Node(fileName,wordCount);
			head=newNode;
		}
		else {
			Node temp=head;
			while(temp.getLink()!=null) {
				temp=temp.getLink();
			}
			Node newNode= new Node(fileName,wordCount);
			temp.setLink(newNode);
			
		}
	}
	
	public int size() {
		if(head==null) {
			return 0;
		}
		else {
			int count=0;
			Node temp=head;
			while (temp!=null) {
				temp=temp.getLink();
				count++;
			}
			return count;
		}
	}
	
	public void display() {
		if(head==null) {
			System.out.println("List is empty.");
			
		}else {
			Node temp=head;
			while(temp!=null) {
				System.out.print(temp.getFileName()+"  "+ temp.getWordCounter()+"\n");
				temp=temp.getLink();
			}
		}
	}
	
	//checks if the file is in the list
	public boolean  searchAndAdd(String fileName, int wordCount) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			return false;
		}else {
			Node temp=head;
			Boolean flag=false;
			while(temp!=null) {
				if(temp.getFileName()== fileName) {
					temp.setWordCounterAdd(wordCount);//sums the old value with the new value 
					return true;
				}
				temp=temp.getLink();
			}
			return false;
			
			
		}
	}
	
	//checks if file exists 
	public boolean search(String fileName) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			return false;
		}else {
			Node temp=head;
			while(temp!=null) {
				if(temp.getFileName()== fileName) {
					return true;
				}
				temp=temp.getLink();
			}
			return false;
		}
	}
	
	//returns the filename at the desired node of the list
	public String searchFileNumForName(int fileNum) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			return null;
		}else {
			Node temp=head;
			int counter=0;
			while(counter!=fileNum) {
				temp=temp.getLink();
				counter++;
			}
			return temp.getFileName();
		}
	}
	
	//returns the WordCount at the desired node of the list
	public Object searchFileNumForWordCount(int fileNum) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			return 0;
		}else {
			Node temp=head;
			int counter=0;
			while(counter!=fileNum) {
				temp=temp.getLink();
				counter++;
			}
			return temp.getWordCounter();
		}
	}
	
	//wordcount is increased by 1
	public void wordCounterIncrease(String fileName ) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			
		}else {
			Node temp=head;
			while(temp!=null) {
				if(temp.getFileName()== fileName) {
					temp.setWordCounter();
					
				}
				temp=temp.getLink();
			}
			
		}
	}
	
}



