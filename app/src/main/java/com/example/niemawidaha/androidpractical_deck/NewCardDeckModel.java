package com.example.niemawidaha.androidpractical_deck;

public class NewCardDeckModel {

    // private member vars:
    private boolean success;
    private boolean shuffled;
    private String deck_id;
    private int remaining;

    // default constructor:
    public NewCardDeckModel(){

    }

    // getters + setters:

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
        }

    @Override
    public String toString() {
        return "NewCardDeckModel{" +
                "success=" + success +
                ", shuffled=" + shuffled +
                ", deck_id='" + deck_id + '\'' +
                ", remaining=" + remaining +
                '}';
    }
}
