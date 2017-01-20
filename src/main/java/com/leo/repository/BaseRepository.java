package com.leo.repository;

import com.leo.model.Createable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Generic repository base class used for all JPA repository classes.
 */
@NoRepositoryBean
public interface BaseRepository<T extends Createable> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}