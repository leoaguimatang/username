package com.leo.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.leo.model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines the user resource.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "id", "timeCreated", "timeUpdated" }, allowGetters = true, allowSetters = false)
public class UserResource extends UpdateableResource<User> {
    @NotEmpty(message = "username.blank")
    @Size(min = 6, message = "username.size")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserResource from(User entity) {
        setId(entity.getId());
        username = entity.getUsername();
        setTimeCreated(entity.getTimeCreated());
        setTimeUpdated(entity.getTimeUpdated());

        return this;
    }

    public User to(User entity) {
        entity.setUsername(username);

        return entity;
    }
}