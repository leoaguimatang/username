package com.leo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The base class of all objects within the model.
 */
@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(EntityTimestampListener.class)
public abstract class Createable implements Serializable {

    /**
     * The unique value that identifies the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    @Access(AccessType.PROPERTY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created", nullable = false, updatable = false)
    private Date timeCreated;

    /**
     * Returns this object's hashcode.
     */
    @Override
    public int hashCode() {
        return id == 0 ? super.hashCode() : getClass().hashCode() ^ Long.valueOf(id).hashCode();
    }

    /**
     * Returns true if that object equals this object.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || obj != null &&
            obj.getClass().equals(getClass()) &&
            ((Createable) obj).id == id &&
            ((Createable) obj).id != 0;
    }

    /**
     * Returns the id of the entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the creation time of the entity.
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets the creation time of the entity.
     */
    protected void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
