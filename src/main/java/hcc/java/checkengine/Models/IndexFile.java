package hcc.java.checkengine.Models;

public class IndexFile {
    private static int nextID = 0;
    private int id;
    private String filePath;
    private long lastModifiedTime;
    private FileStatus fileStatus;

    {
        id = nextID++;
    }

    public IndexFile(String filePath, long lastModifiedTime) {
        this.filePath = filePath;
        this.lastModifiedTime = lastModifiedTime;
        fileStatus = FileStatus.Indexed;
    }

    public int getId() {
        return id;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }
}
