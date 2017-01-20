package com.leo.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Represents objects that can be updated. If it's updateable, then it's
 * createable.
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Updateable extends Createable {

    /**
     * The time this record last updated.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_updated")
    private Date timeUpdated;

    /**
     * Returns the time the record last updated.
     */
    public Date getTimeUpdated() {
        return timeUpdated;
    }

    /**
     * Sets the time the record last updated.
     */
    protected void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}