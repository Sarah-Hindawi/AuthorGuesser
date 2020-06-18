import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListMap {

    //Linked List of Entry objects
    private LinkedList<Entry> linkedList;

    /**
     * The constructor
     */
    public LinkedListMap() {
        linkedList = new LinkedList<>();
    }

    /**
     * @param word the vocabulary in which we want to know how many times it appeared in a specific author sayings
     * @return the number of times it appeared
     */
    public int get(String word) {
        //iterator to go throw the entries in the linked list
        Iterator<Entry> entryIterator = linkedList.iterator();
        //while there is still entries to be examined in the linked list
        while (entryIterator.hasNext()) {
            //get the next entry
            Entry entry = entryIterator.next();
            //check whether this entry holds the given word
            if (entry.getKey().equals(word)) {
                //if true return the value associated with the word
                return entry.getValue();
            }
        }
        //if the word was not found
        return -1;
    }

    /**
     * adding a new word to the linked list or adding its time of occurrence by one
     *
     * @param word that will be added to the linked list
     */
    public void put(String word) {
        //if the word does not already exists in the linked list
        if (get(word) == -1) {
            //create a new entry holding the word and 1 as a value to indicate it is its first appearance
            Entry entry = new Entry(word, 1);
            //add that entry to the linked list
            linkedList.add(entry);
        }
        //else if it was not already in the linked list
        else {
            Iterator<Entry> entryIterator = linkedList.iterator();
            while (entryIterator.hasNext()) {
                Entry entry = entryIterator.next();
                if (entry.getKey().equals(word)) {
                    //get its time of occurrence
                    int f = entry.getValue();
                    //set it to its old count plus one as it appeared one more time
                    entry.setValue(f + 1);
                }
            }
        }
    }

    /**
     * the method to remove the entry with the given word
     *
     * @param word that is called to be removed
     */
    public void remove(String word) {
        Iterator<Entry> entryIterator = linkedList.iterator();
        while (entryIterator.hasNext()) {
            Entry entry = entryIterator.next();
            if (entry.getKey().equals(word)) {
                //invoking linked list remove method
                linkedList.remove(entry);
            }
        }
    }
}