package com.gdemecki.crudtask.dto;

import com.gdemecki.crudtask.domain.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 64)
    private String name;

    @Valid
    private UnifiedIdentifier createdBy;

    public UserDTO() {
        // needed for serialization/ deserialization
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnifiedIdentifier getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UnifiedIdentifier createdBy) {
        this.createdBy = createdBy;
    }

    public User toModel() {
        return new User(id, name);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name=" + name + ", createdBy=" + createdBy + "}";
    }

}
