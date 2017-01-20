package com.leo.service;

import com.leo.exception.ErrorKey;
import com.leo.exception.UsernameException;
import com.leo.model.Dictionary;
import com.leo.repository.DictionaryRepository;
import com.leo.resource.DictionaryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * Gets the dictionary details
     */
    public List<Dictionary> searchWord(String search) {
        List<Dictionary> words = dictionaryRepository.getByWord(search);
        return words;
    }

    /**
     * Gets the dictionary details
     */
    public Dictionary getDictionaryById(Long id) {
        Dictionary dictionary = dictionaryRepository.findOne(id);
        if (dictionary == null) {
            throw new UsernameException(ErrorKey.WORD_NOT_FOUND);
        }

        return dictionary;
    }

    /**
     * Creates the dictionary details
     */
    public Dictionary createDictionary(DictionaryResource resource) {
        Dictionary dictionary = resource.to(new Dictionary());
        return dictionaryRepository.save(dictionary);
    }

    /**
     * Updates the dictionary details
     */
    public Dictionary updateDictionary(DictionaryResource resource) {
        Dictionary dictionary = resource.to(dictionaryRepository.findOne(resource.getId()));
        return dictionaryRepository.save(dictionary);
    }

    /**
     * Deletes the dictionary details
     */
    public void deleteDictionary(Long id) {
        dictionaryRepository.delete(id);
    }
}