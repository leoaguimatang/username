package com.leo.model;

import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * External timestamp updater. This seems to be more reliable when it's not
 * sitting directly in the entity classes.
 */
@Component
public class EntityTimestampListener {

    @PrePersist
    public void prePersist(Createable createable) {
        createable.setTimeCreated(new Date());
        if (createable instanceof Updateable) {
            ((Updateable) createable).setTimeUpdated(new Date());
        }
    }

    @PreUpdate
    public void preUpdate(Updateable updateable) {
        updateable.setTimeUpdated(new Date());
    }
}