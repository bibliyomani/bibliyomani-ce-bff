package com.bibliyomani.standalone.bff.modal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookMetadata implements Comparable<BookMetadata> {
    private Integer bookId;
    private String name;
    private String hash;
    private int read;
    private int total;
    private String size;
    private long lastInteraction;

    @Override
    public int compareTo(BookMetadata o) {
        if (lastInteraction == o.lastInteraction) return 0;
        else if (lastInteraction < o.lastInteraction) return 1;
        else return -1;
    }
}