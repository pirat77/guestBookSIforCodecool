package com.codecool.model;

import java.util.ArrayList;
import java.util.List;

public class GuestBook {
    private List<GuestEntry> guestBook;

    public void addEntry(GuestEntry entry){
        this.guestBook.add(entry);
    }

    public void setGuestBook(List<GuestEntry> guestBook) {
        this.guestBook = guestBook;
    }

    public List<GuestEntry> getGuestBook() {
        return guestBook;
    }

    public GuestBook(){
        this.setGuestBook(new ArrayList<>());
    }
}
