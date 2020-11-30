package com.example.jsondict;

import androidx.annotation.NonNull;

public class Word {
    private String word;
    private String definition;

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public Word() {
        this.word = "";
        this.definition = "";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @NonNull
    @Override
    public String toString() {
        return this.word+ " "+ this.definition;
    }
}
//meo1 hieu63 :((((
//thua