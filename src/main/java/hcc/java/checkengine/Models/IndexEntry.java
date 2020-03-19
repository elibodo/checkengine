package hcc.java.checkengine.Models;

import javafx.util.Pair;
import java.util.List;

public class IndexEntry {

    public String word;
    public List<Pair<Integer, Integer>> fileWordIndexPairs;

    public IndexEntry(String word, List<Pair<Integer, Integer>> fileWordIndexPair) {
        this.word = word;
        fileWordIndexPairs = fileWordIndexPair;
    }

}
