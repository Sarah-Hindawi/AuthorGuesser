public class Entry implements Comparable<Entry> {

    private String key;
    private int value;

    /**
     * constructor
     *
     * @param k the word
     * @param v its number of occurrences
     */
    public Entry(String k, int v) {
        key = k;
        value = v;
    }

    /**
     * @param value setting its number of occurrences
     */
    public void setValue(int value) {
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return getting the word
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the number of times the word occurred
     */
    public int getValue() {
        return value;
    }

    /**
     * self-defined compare to method
     *
     * @param o the other entry that will be compared to
     * @return if its larger, smaller or equal
     */
    @Override
    public int compareTo(Entry o) {
        return Integer.compare(value, o.getValue());
    }
}
