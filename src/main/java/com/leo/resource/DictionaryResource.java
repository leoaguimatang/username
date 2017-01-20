package com.leo.resource;

import com.leo.model.Dictionary;

/**
 * Defines the dictionary resource.
 */
public class DictionaryResource extends UpdateableResource<Dictionary> {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public DictionaryResource from(Dictionary entity) {
        setId(entity.getId());
        word = entity.getWord();
        setTimeCreated(entity.getTimeCreated());
        setTimeUpdated(entity.getTimeUpdated());

        return this;
    }

    public Dictionary to(Dictionary entity) {
        entity.setWord(word);

        return entity;
    }
}