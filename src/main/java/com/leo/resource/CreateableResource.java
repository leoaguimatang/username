package com.leo.resource;

import com.leo.model.Createable;

import java.util.Date;

/**
 * Base class of all REST resources that need management of the associated {@link Createable}.
 */
public abstract class CreateableResource<T extends Createable> {

    protected Long id;

    protected Date timeCreated;

    /**
     * Returns the id of the related entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the related entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the time the related entity was created.
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * Returns the creation time of the related entity.
     */
    public Date getTimeCreated() {
        return timeCreated;
    }
}
