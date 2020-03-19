package hcc.java.checkengine.Models;

public enum FileStatus {

    Indexed("Indexed."),
    Out_Of_Date("Out of date.");

    private String displayName;

    FileStatus(final String displayName) {
        this.displayName = displayName;
    }

    @Override public String toString() {
        return this.displayName;
    }

}
