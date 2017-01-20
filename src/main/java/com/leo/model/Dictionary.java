package com.leo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(value = { EntityTimestampListener.class })
public class Dictionary extends Updateable {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
