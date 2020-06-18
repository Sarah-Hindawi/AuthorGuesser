import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    private final String punctuation = ";,'.:\"()/?!";

    // Hash table objects for each author
    private FrequencyHashTable eapHashTable = new FrequencyHashTable();
    private FrequencyHashTable hplHashTable = new FrequencyHashTable();
    private FrequencyHashTable mwsHashTable = new FrequencyHashTable();

    String path = new java.io.File(".").getCanonicalPath();
    private File f = new File(path + "\\src\\train.csv");
    private Scanner scan = new Scanner(f);

    public Driver() throws IOException {

        while (scan.hasNext()) {
            String line = scan.nextLine();
            Record r = new Record(line);
            if (r.getAuthor().equals("EAP")) {
                //it's written by EAP, increment each word
                for (String word : r.getText().split(" ")) {
                    eapHashTable.increment(word);
                }
            } else if (r.getAuthor().equals("HPL")) {
                for (String word : r.getText().split(" ")) {
                    hplHashTable.increment(word);
                }
            } else for (String word : r.getText().split(" ")) {
                mwsHashTable.increment(word);
            }
        }
    }

    /**
     * @param sentence that will be classified to a specific author
     * @return the classified author
     */
    public String classifiedAuthor(String sentence) {
        //change the words of the sentence to lower case
        String sentenceLower = sentence.toLowerCase();
        //make an array of word in the sentence
        String[] words = sentenceLower.split(" ");
        //variables that will hold the number of words count for each author
        double eapFrequency = 1, mwsFrequency = 1, hplFrequency = 1;
        //for each word in the array
        for (int i = 0; i < words.length; i++) {
            //calculate its frequency associated to eap author
            eapFrequency = eapFrequency * eapHashTable.get(wordChecker(words[i]));
            //calculate its frequency associated to mws author
            mwsFrequency = mwsFrequency * mwsHashTable.get(wordChecker(words[i]));
            //calculate its frequency associated to hpl author
            hplFrequency = hplFrequency * hplHashTable.get(wordChecker(words[i]));
        }

        // if the frequency of the word s in EAP sayings is the most then it should EAP who said it
        if (eapFrequency > mwsFrequency && eapFrequency > hplFrequency) return "EAP";
            // else if the frequency for hpl is more than eap and more than mws
        else if (eapFrequency < hplFrequency && hplFrequency > mwsFrequency) return "HPL";
            // else it must be mws
        else return "MWS";
    }

    /**
     * a method to remove punctuation marks from the words
     *
     * @param word that will be examined
     * @return the word without punctuation
     */
    public String wordChecker(String word) {
        String checkedWord = word;

        if (word.length() >= 2) {
            //if the first character in the word is punctuation
            if (punctuation.indexOf(word.charAt(0)) != -1) {
                //take the substring of the word starting at the second character
                checkedWord = checkedWord.substring(1);
            }
            //if the last is punctuation
            if (punctuation.indexOf(word.charAt(word.length() - 1)) != -1) {
                //take a substring that ends one letter less than the original one
                checkedWord = checkedWord.substring(0, checkedWord.length() - 1);
            }
        }
        return checkedWord;
    }

    public static void main(String[] args) throws IOException {
        //creating Driver object
        Driver driver = new Driver();

        String path = new java.io.File(".").getCanonicalPath();
        Scanner in = new Scanner(new File(path + "\\src\\test.csv"));

        //the number the classifications that were correct
        int count = 0;

        in.useDelimiter("\",\"");
        //ignoring the first id
        in.next();
        while (in.hasNext()) {
            in.useDelimiter("\",\"");
            //the saying
            String sentence = in.next();
            //guess the author
            String guessedAuthor = driver.classifiedAuthor(sentence);
            System.out.println(sentence);
            System.out.println("Classified author: " + guessedAuthor);
            String actualAuthor = in.next().substring(0, 3);
            System.out.println("Expected: " + actualAuthor);
            //if guessing was right
            if (guessedAuthor.equals(actualAuthor)) count++;
            System.out.println();
        }
        System.out.println(count + " sentences were classified correctly.");
    }
}
