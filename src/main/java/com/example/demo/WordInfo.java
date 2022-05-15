package com.example.demo;

public class WordInfo {
    private String Word;
    private String Filename;
    private String Patch;
    private int count;

    public WordInfo(String word, String filename, String patch, int count) {
        Word = word;
        Filename = filename;
        Patch = patch;
        this.count = count;
    }


    public String getWord() {
        return Word;
    }

    public String getFilename() {
        return Filename;
    }

    public String getPatch() {
        return Patch;
    }

    public int getCount() {
        return count;
    }
}

