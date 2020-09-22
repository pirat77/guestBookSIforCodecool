package com.codecool.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "GuestEntry")
public class GuestEntry {

    @Column(name="name")
    private String name;

    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDate date;

    @JsonCreator
    public GuestEntry(@JsonProperty("name") String name,
                      @JsonProperty("content") String content,
                      @JsonProperty("date") LocalDate date){
        this.content = content;
        this.date = date;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }
}
