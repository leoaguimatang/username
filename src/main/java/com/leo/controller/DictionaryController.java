package com.leo.controller;

import com.leo.exception.ErrorKey;
import com.leo.exception.UsernameException;
import com.leo.model.Dictionary;
import com.leo.resource.DictionaryResource;
import com.leo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.leo.controller.DictionaryController.DICTIONARY_BASE;

/**
 * Manages the mapping of {@link Dictionary}.
 */
@Controller
@RequestMapping(value = DICTIONARY_BASE)
public class DictionaryController {

    public static final String DICTIONARY_BASE = "/dictionary";
    private static final String DICTIONARY_ID = "/{id}";

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * Get {@link Dictionary} by word
     */
    @GetMapping
    public ResponseEntity<List<Dictionary>> getDictionaries(
        @RequestParam(value = "search", required = false, defaultValue = "") String search) {

        List<Dictionary> words = dictionaryService.searchWord(search);
        if (words == null) {
            throw new UsernameException(ErrorKey.DICTIONARY_EMPTY);
        }

        return new ResponseEntity(words, HttpStatus.OK);
    }

    /**
     * Get {@link Dictionary} by Id
     */
    @GetMapping(value = DICTIONARY_ID)
    public ResponseEntity<Dictionary> getDictionary(@PathVariable("id") Long id){

        Dictionary dictionary = dictionaryService.getDictionaryById(id);

        return new ResponseEntity(dictionary, HttpStatus.OK);
    }

    /**
     * Creates {@link Dictionary}
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Dictionary> createDictionary(
            @RequestBody @Valid DictionaryResource resource) throws Exception {

        Dictionary dictionary = dictionaryService.createDictionary(resource);

        return new ResponseEntity(dictionary, HttpStatus.OK);
    }

    /**
     * Updates {@link Dictionary}
     */
    @PutMapping(DICTIONARY_ID)
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable("id") Long id,
            @RequestBody @Valid DictionaryResource resource) throws Exception {

        resource.setId(id);
        Dictionary dictionary = dictionaryService.updateDictionary(resource);

        return new ResponseEntity(dictionary, HttpStatus.OK);
    }

    /**
     * Deletes {@link Dictionary}
     */
    @DeleteMapping(DICTIONARY_ID)
    public ResponseEntity<Void> deleteDictionary(@PathVariable("id") Long id) {

        dictionaryService.deleteDictionary(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}