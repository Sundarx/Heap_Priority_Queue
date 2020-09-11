//SUNDAR RAJ
public class HeapDemo
{
	public static void main(String[] args) throws HeapOverflowException, HeapUnderflowException
	{
		HeapPriorityQueue heapPQ = new HeapPriorityQueueClass();

		for(int i = 1; i <= 10; i++)
		{
			heapPQ.enqueue(i);
		}

		System.out.println("Original heap:");
		System.out.println(heapPQ);

		heapPQ.dequeue();
		heapPQ.dequeue();

		System.out.println("\nHeap after two removals:");
		System.out.println(heapPQ);
	}
}