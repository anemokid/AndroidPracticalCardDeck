package com.example.niemawidaha.androidpractical_deck.service;

public class DeckQueryModel {

    // private fields:
    private String value;
    private String suit;
    private String images[];
    private String image;
    private String code;

    public DeckQueryModel() {
    }

    //getters:
    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String[] getImages() {
        return images;
    }

    public String getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }
}
