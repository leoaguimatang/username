package com.leo.repository;

import com.leo.model.Dictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictionaryRepository extends BaseRepository<Dictionary> {
    /**
     * Returns {@link Dictionary}s for the supplied word.
     */
    @Query("from Dictionary where lower(word) like lower(concat('%', :search, '%'))")
    List<Dictionary> getByWord(@Param("search") String search);
}
