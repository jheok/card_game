package com.mini.card.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private int id;
    private String value;
    private boolean flipped;
    private boolean matched;

    public Card(int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", flipped=" + flipped +
                ", matched=" + matched +
                '}';
    }
}
