package model;

/**
 * WordWithCount is a POJO class which contains word and associated count the occurrence of the word.
 */
public class WordWithCounts {

    public String word;
    public long count;

    public WordWithCounts() {}

    public WordWithCounts(String word, long count) {
        this.word = word;
        this.count = count;
    }


    @Override
    public String toString() {
        return word + " : " + count;
    }
}
