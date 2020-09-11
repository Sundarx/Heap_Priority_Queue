//SUNDAR RAJ
public class HeapPriorityQueueClass implements HeapPriorityQueue
{
	private final int MAXSIZE = 250;
	private Comparable[] heapArray;
	private int current;
	private int last;

	public HeapPriorityQueueClass()
	{
		heapArray = new Comparable[MAXSIZE];
		current = -1;
		last = MAXSIZE - 1;
	}

	//Returns true if queue is empty
	public boolean isEmpty()
	{
		return (current == -1);
	}

	//Returns true if queue is full
	public boolean isFull()
	{
		return (current == last);
	}

	//Adds item to the back of queue
	public void enqueue(Comparable value) throws HeapOverflowException
	{
		if(isFull())
		{
			throw new HeapOverflowException("Exception: Heap Overflow");
		}

		current++;
		reheapifyUpward(value);
	}

	//Removes item from the front of queue
	public Comparable dequeue() throws HeapUnderflowException
	{
		if(isEmpty())
		{
			throw new HeapUnderflowException("Exception: Heap Underflow");
		}

		Comparable priority = heapArray[0];
		heapArray[0] = heapArray[current];
		current--;
		reheapifyDownward(heapArray[0]);

		return priority;
	}

	//Item moves up until it has a parent with higher priority
	public void reheapifyUpward(Comparable newValue)
	{
		int position = current;
		int parent = (position - 1) / 2;

		if(position == 0 || newValue.compareTo(heapArray[parent]) > 0)
		{
			heapArray[position] = newValue;
		}

		while(position > 0 && newValue.compareTo(heapArray[parent]) < 0)
		{
			Comparable temp = heapArray[parent];
			heapArray[parent] = newValue;
			heapArray[position] = temp;
		}
	}

	//Item moves down until it has a child with lower priority
	public void reheapifyDownward(Comparable tempRoot)
	{
		int valueIndex = 0;
		int moveTo = reposition(valueIndex);

		while(moveTo != valueIndex)
		{
			Comparable temp = heapArray[moveTo];
			heapArray[moveTo] = heapArray[valueIndex];
			heapArray[valueIndex] = temp;
			valueIndex = moveTo;
			moveTo = reposition(valueIndex);
		}

		if(moveTo == valueIndex)
		{
			heapArray[valueIndex] = tempRoot;
		}
	}

	//Reposition the items as needed by reheapify methods
	public int reposition(int valIndex)
	{
		int leftChild = (valIndex * 2) + 1;
		int rightChild = (valIndex * 2) + 2;

		//If both children exist, compare children first
		//Then compare value with the higher priority child
		if(heapArray[leftChild] != null && heapArray[rightChild] != null)
		{
			if(heapArray[leftChild].compareTo(heapArray[rightChild]) < 0)
			{
				if(heapArray[leftChild].compareTo(heapArray[valIndex]) < 0)
				{
					return leftChild;
				}
			}

			if(heapArray[leftChild].compareTo(heapArray[rightChild]) > 0)
			{
				if(heapArray[rightChild].compareTo(heapArray[valIndex]) < 0)
				{
					return rightChild;
				}
			}

			if(heapArray[leftChild].compareTo(heapArray[rightChild]) == 0)
			{
				if(heapArray[leftChild].compareTo(heapArray[valIndex]) < 0)
				{
					return leftChild;
				}
			}
		}

		//If only right child doesn't exist, compare value with left child
		if(heapArray[leftChild] != null && heapArray[rightChild] == null)
		{
			if(heapArray[leftChild].compareTo(heapArray[valIndex]) < 0)
			{
				return leftChild;
			}

			if(heapArray[leftChild].compareTo(heapArray[valIndex]) == 0)
			{
				return valIndex;
			}
		}

		//If only left child doesn't exist, compare value with right child
		if(heapArray[leftChild] == null && heapArray[rightChild] != null)
		{
			if(heapArray[rightChild].compareTo(heapArray[valIndex]) < 0)
			{
				return rightChild;
			}
			if(heapArray[rightChild].compareTo(heapArray[valIndex]) == 0)
			{
				return valIndex;
			}

		}

		//If both children don't exist, return original position
		if(heapArray[leftChild] == null && heapArray[rightChild] == null)
		{
			return valIndex;
		}

		return valIndex;
	}

	public String toString()
	{
		int level = 0;
		int valuesInLevel = 1;
		int i = 0;

		StringBuilder str = new StringBuilder();

		while(i <= current)
		{
			for(int j = 0; j < valuesInLevel && i <= current; j++)
			{
				str.append("Level " + level + ": " + heapArray[i] + "\n");
				i++;
			}

			level++;
			valuesInLevel *= 2;
		}

		return str.toString();
	}
}