package com.codecool.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "GuestEntry")
public class GuestEntry {

    @Column(name="name")
    private String name;

    @Column(name="content")
    private String content;

    @Column(name="date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;

    @JsonCreator
    public GuestEntry(@JsonProperty("name") String name,
                      @JsonProperty("content") String content,
                      @JsonProperty("date") Date date){
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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
