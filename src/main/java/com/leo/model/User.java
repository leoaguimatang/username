package com.leo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(value = { EntityTimestampListener.class })
public class User extends Updateable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}