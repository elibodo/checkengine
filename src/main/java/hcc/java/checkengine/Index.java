package hcc.java.checkengine;

import hcc.java.checkengine.Models.FileStatus;
import hcc.java.checkengine.Models.IndexEntry;
import hcc.java.checkengine.Models.IndexFile;
import hcc.java.checkengine.Utilities.DataAccess;
import javafx.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Index {

    private List<IndexFile> indexFiles = new ArrayList<>();
    private List<IndexEntry> indexEntries = new ArrayList<>();

    public List<IndexFile> getIndexFiles() {
        return indexFiles;
    }

    public List<IndexEntry> getIndexEntries() {
        return indexEntries;
    }

    public void updateFileStatus() {
        for (IndexFile indexFile : indexFiles) {
            File file = new File(indexFile.getFilePath());
            if (indexFile.getLastModifiedTime() != file.lastModified()) {
                indexFile.setFileStatus(FileStatus.Out_Of_Date);
            } else {
                indexFile.setFileStatus(FileStatus.Indexed);
            }
        }
    }

    public void addFile(IndexFile indexFile) {
        for (IndexFile file : indexFiles) {
            if (file.getFilePath().equals(indexFile.getFilePath())) {
                return;
            }
        }
        indexFiles.add(indexFile);
        generateIndex();
    }

    public void removeFile(IndexFile indexFile) {
        for (IndexFile file : indexFiles) {
            if (file.getFilePath().equals(indexFile.getFilePath())) {
                indexFiles.remove(indexFile);
                generateIndex();
            }
        }
    }

    public void regenerateIndex() {
        for (IndexFile indexFile : indexFiles) {
            if (indexFile.getFileStatus() == FileStatus.Out_Of_Date) {
                File file = new File(indexFile.getFilePath());
                indexFile.setLastModifiedTime(file.lastModified());
                indexFile.setFileStatus(FileStatus.Indexed);
            }
        }
        generateIndex();
    }

    public void generateIndex() {

        indexEntries.clear();

        for (IndexFile indexFile: indexFiles) {
            File file = new File(indexFile.getFilePath());

            try {
                Scanner scanner = new Scanner(file);
                int wordIndex = 0;
                while (scanner.hasNext()) {
                    String word = scanner.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    indexWord(word, new Pair<>(indexFile.getId(), wordIndex));
                    wordIndex++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        DataAccess.writeIndex(this);
    }

    private void indexWord(String word, Pair<Integer, Integer> fileWordIndexPair) {
        for (IndexEntry entry : indexEntries) {
            if (entry.word.equals(word)) {
                entry.fileWordIndexPairs.add(fileWordIndexPair);
                return;
            }
        }
        IndexEntry indexEntry = new IndexEntry(word,
                new ArrayList<>(){{
                    add(fileWordIndexPair);
                }});
        indexEntries.add(indexEntry);
    }
}
