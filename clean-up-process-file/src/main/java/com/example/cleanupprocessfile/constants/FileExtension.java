package com.example.cleanupprocessfile.constants;

public enum FileExtension {
    CSV(".csv"),
    TXT(".txt"),
    DOCS(".docs"),
    PDF(".pdf")
    ;

    public final String label;

    FileExtension(String label) {
        this.label = label;
    }
}
