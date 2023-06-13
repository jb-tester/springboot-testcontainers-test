package com.mytests.spring.springboottestcontainerstest.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class Task {
    @Id
    private UUID id;
    private String name;
    private Date startDate;
    private String state;

    public Task(UUID id, String name, Date startDate, String state) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.state = state;
    }

    public Task() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}