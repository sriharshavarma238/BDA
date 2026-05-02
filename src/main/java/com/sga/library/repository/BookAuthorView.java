package com.sga.library.repository;

import java.math.BigDecimal;

public interface BookAuthorView {

    Long getId();

    String getTitle();

    String getIsbn();

    Integer getPublishedYear();

    BigDecimal getPrice();

    String getAuthorName();
}