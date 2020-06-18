public class FrequencyHashTable {

    // The hash table array that will hold the linked lists
    private LinkedListMap[] hashTable;
    // The  number of distinct words in the hash table
    private int size;
    // The number of total words in the hash table
    private int count;
    // Small value if the word was not mentioned in a specific author sayings
    private final double small = 0.0000001;

    /**
     * constructor
     */
    public FrequencyHashTable() {
        hashTable = new LinkedListMap[9973];
        size = 0;
        count = 0;
    }

    /**
     * increment the current count for this key or insert key with count 1
     *
     * @param word that will be added or its value incremented
     */
    public void increment(String word) {
        //increase the number of total words
        count++;
        //a formula that gives a value in the range of indices of the hash table
        int index = ((Math.abs(word.hashCode()) + 100) % 10007) % hashTable.length;
        //get the linked list with the cell associated with the hash code
        LinkedListMap l = hashTable[index];
        //if the cell at the given index does not hold any value
        if (l == null) {
            //create a new linked list
            LinkedListMap linkedListMap = new LinkedListMap();
            //add to the linked list the passed word
            linkedListMap.put(word);
            //assign the newly created linked list to the array
            hashTable[index] = linkedListMap;
            //increase the number of words
            size++;
        } else {
            //if the exact word does not exist in the linked list increase the size of distinct words
            if (l.get(word) == -1) size++;
            //add to the hash table the word
            hashTable[index].put(word);
        }
    }

    // remove a given word from the table
    public void remove(String word) {
        int index = ((Math.abs(word.hashCode()) + 100) % 10007) % hashTable.length;
        int c = hashTable[index].get(word);
        //decrease the count (number of occurrence) of the word by its value
        count = count - c;
        //remove the word with the given index
        hashTable[index].remove(word);
        //decrease the number of words
        size--;
    }

    /**
     * @param word that its time of occurrence in a given author sayings will be computed
     * @return the frequency of the word
     */
    public double get(String word) {
        int index = ((Math.abs(word.hashCode()) + 100) % 10007) % hashTable.length;
        //if the linked list that should hold the word does not exist of a specific author then return a small value
        if (hashTable[index] == null) return small;
        LinkedListMap l = hashTable[index];
        //if word does not exist in the linked list at that index
        if (l.get(word) == -1) return small;
        //else return its frequency
        return (l.get(word) / (double) totalCount());
    }

    /**
     * @return the number of keys in the table
     */
    public int size() {
        return size;
    }

    /**
     * @return total sum of all the counts
     */
    public int totalCount() {
        return count;
    }

    /**
     * @return whether the hash table holds any words or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
