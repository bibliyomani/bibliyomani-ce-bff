package com.bibliyomani.standalone.bff.modal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookMetadata {
    private Integer bookId;
    private String name;
    private String hash;
    private int last;
    private int total;
    private String size;
    private long lastInteraction;
}