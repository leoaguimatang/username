package com.leo.resource;

import com.leo.model.Updateable;

import java.util.Date;

/**
 * Base class of all REST resources. This class is the resource mirror of the {@link Updateable} class.
 */
public abstract class UpdateableResource<T extends Updateable> extends CreateableResource<T> {

    protected Date timeUpdated;

    /**
     * Sets the time the related entity was last updated.
     */
    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    /**
     * Returns the time the related entity was last updated.
     */
    public Date getTimeUpdated() {
        return timeUpdated;
    }
}
