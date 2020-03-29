package hcc.java.checkengine.Models;

public class IndexFile {
    private int id;
    private String filePath;
    private long lastModifiedTime;
    private FileStatus fileStatus;

    public IndexFile(String filePath, long lastModifiedTime, int lastId) {
        this.filePath = filePath;
        this.lastModifiedTime = lastModifiedTime;
        this.id = ++lastId;
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
