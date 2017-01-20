package com.leo.service;

import com.leo.BaseIT;
import com.leo.model.Dictionary;
import com.leo.resource.DictionaryResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Integration tests for {@link DictionaryService}.
 */
public class DictionaryServiceIT extends BaseIT {
    @Autowired
    private DictionaryService service;

    @Test
    public void testGetWord() {
        Dictionary dictionary = service.searchWord("grass").get(0);

        assertTrue(dictionary.getId() == 6);
    }

    @Test
    public void testGetMissingWord() {
        List<Dictionary> words = service.searchWord("grasses");

        assertTrue(CollectionUtils.isEmpty(words));
    }

    @Test
    public void testPostWord() {
        DictionaryResource dictionaryResource = new DictionaryResource();
        dictionaryResource.setWord("racist");

        Dictionary dictionary = service.createDictionary(dictionaryResource);

        assertNotNull(dictionary.getId());
    }

    @Test
    public void testPutWord() {

        DictionaryResource dictionaryResource = new DictionaryResource();
        dictionaryResource.setWord("cocane");
        Dictionary dictionary = service.createDictionary(dictionaryResource);

        assertTrue(dictionary.getWord().equals("cocane"));

        dictionaryResource = dictionaryResource.from(dictionary);
        dictionaryResource.setWord("cocaine");
        dictionary = service.updateDictionary(dictionaryResource);

        assertFalse(dictionary.getWord().equals("cocane"));
    }

    @Test
    public void testDeleteWord() {

        DictionaryResource dictionaryResource = new DictionaryResource();
        dictionaryResource.setWord("deleted");
        Dictionary dictionary = service.createDictionary(dictionaryResource);

        Long id = dictionary.getId();
        assertNotNull(id);

        service.deleteDictionary(id);

        assertTrue(CollectionUtils.isEmpty(service.searchWord("deleted")));
    }
}
