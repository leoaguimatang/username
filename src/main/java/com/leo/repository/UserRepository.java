package com.leo.repository;

import com.leo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {
    /**
     * Returns the {@link User} for the supplied username.
     */
    @Query("from User where lower(username) like lower(concat('%', :search, '%'))")
    List<User> getUsersByUsername(@Param("search") String search);

    /**
     * Returns the {@link User} for the supplied username.
     */
    @Query("from User where lower(username) = lower(:username)")
    User getByUsername(@Param("username") String username);
}
