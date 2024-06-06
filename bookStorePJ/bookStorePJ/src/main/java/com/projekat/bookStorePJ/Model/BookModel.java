package com.projekat.bookStorePJ.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookModel {
    private String book_name;
    private String author;
    private String price;
    private Integer categoryId;
}
