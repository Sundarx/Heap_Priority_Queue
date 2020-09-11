//SUNDAR RAJ
public interface HeapPriorityQueue
{
	//Returns true if queue is empty
	public abstract boolean isEmpty();

	//Returns true if queue is full
	public abstract boolean isFull();

	//Adds item to the back of queue
	public abstract void enqueue(Comparable item) throws HeapOverflowException;

	//Removes item from the front of queue
	public abstract Comparable dequeue() throws HeapUnderflowException;

	//Item moves up until it has a parent with higher priority
	public abstract void reheapifyUpward(Comparable newValue);

	//Item moves down until it has a child with lower priority
	public abstract void reheapifyDownward(Comparable tempRoot);

	//Reposition the items as needed by reheapify methods
	public abstract int reposition(int valIndex);
}