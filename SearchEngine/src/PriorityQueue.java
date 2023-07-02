


public class PriorityQueue {
	public NodeP headNode;
	public PriorityQueue(String data, double priority)
	{
	headNode = new NodeP();
	headNode.setData(data);
	headNode.setPriority(priority);
	headNode.setNext(null);
	}
	// Return the max priority NodeP
	public NodeP getMaxPriority()
	{
	 return headNode;
	}
	// Remove and return the max priority NodeP
	public NodeP removeMaxPriority()
	{
	 (headNode) = (headNode).getNext();
	 return headNode;
	}
	
	
	// Check is list is empty
	public boolean isEmpty()
	{
	 return ((headNode) == null)?true:false;
	}
	// Insert according to priority
	public NodeP insert(String data, double priority)
	{
	 // Create new NodeP
	 NodeP temp = new NodeP();
	 temp.setData(data);
	 temp.setPriority(priority);

	 // Special Case: The head of list has lesser
	 // priority than new NodeP. So insert new
	 // NodeP before head NodeP and change head NodeP.
	 if ((this.headNode).getPriority() < priority) {

	 // Insert New NodeP before head
	 temp.setNext(this.headNode);
	 (this.headNode) = temp;
	 }
	 else {

	 NodeP start = headNode;

	 // Traverse the list and find a
	 // position to insert new NodeP
	 while (start.getNext() != null && start.getNext().getPriority() > priority) {
	 start = start.getNext();
	 }

	 // Either at the ends of 
	// or at required position
	 temp.setNext(start.getNext());
	 start.setNext(temp);
	 }
	 return headNode;
	}
	// Show data and priority all queue list
	public void showQueue()
	{
	NodeP start = headNode;

	 // Traverse the list and find a
	 // position to insert new NodeP
	 do {

	 System.out.println("Data : " + start.getData());
	 System.out.println("Priority : " + start.getPriority());

	 start = start.getNext();

	 } while (start.getNext() != null);

	 // Show last

	 System.out.println("Data : " + start.getData());
	 System.out.println("Priority : " + start.getPriority());
	}

}


