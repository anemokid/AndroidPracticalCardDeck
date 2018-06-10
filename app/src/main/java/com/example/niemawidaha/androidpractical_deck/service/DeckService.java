package com.example.niemawidaha.androidpractical_deck.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeckService {

    @GET("api/deck/new/shuffle")
    Call<NewCardDeckJSONResponse> getNewCardDeckJSON();

    @GET("api/deck/{deck_id}/shuffle")
    Call<NewCardDeckJSONResponse> getNewCardDeck(
            @Path("deck_id") String deck_id
    );

    @GET("api/deck/{deck_id}/draw/")
     Call<List<RemainingCardsJsonResponse>> getRemainingCardsJSON(
             @Path("deck_id") String deck_id,
            @Query("count") Integer count
    );

}
