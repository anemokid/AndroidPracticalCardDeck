package com.example.niemawidaha.androidpractical_deck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.niemawidaha.androidpractical_deck.controller.CardDeckAdapter;
import com.example.niemawidaha.androidpractical_deck.service.DeckQueryModel;
import com.example.niemawidaha.androidpractical_deck.service.DeckService;
import com.example.niemawidaha.androidpractical_deck.service.NewCardDeckJSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CardDeckAdapter.ItemClickListener {

    // private members:
    private TextView mNumberOfCardsRemaining_tv;
    private Button mShuffleNewDeck_btn;
    private EditText mSpecifiedCards_et;
    private Button mDrawCards_btn;
    private RecyclerView cardDeckRecyclerView;
    private CardDeckAdapter cardDeckAdapter;
    private List<DeckQueryModel> currentDeckOfCards;
    private int currentCount; // stores the current count of the remaining cards
    private String deck_id; // stores the DECK ID

    public static final String ShuffleNewDeckEndPoint = "https://deckofcardsapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find views:
        findViews();
    }

    /**
     * findsview:
     */
    public void findViews(){
        mNumberOfCardsRemaining_tv = findViewById(R.id.tv_cards_remaining);
        mShuffleNewDeck_btn = findViewById(R.id.btn_shuffle_new_deck);
        mSpecifiedCards_et = findViewById(R.id.et_draw_cards);
        mDrawCards_btn = findViewById(R.id.btn_display_drawn_cards);
        cardDeckRecyclerView = findViewById(R.id.rv_user_cards);

        setUpRV();
    }

    /**
     * setup recycler view:
     */
    public void setUpRV(){

        // set up RV:
        int numOfColumns = 2;
        cardDeckRecyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));

        // cardDeckAdapter = new CardDeckAdapter(this, currentDeckOfCards)
        // cardDeckAdapter.setClickListener(this);
        // cardDeckRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked card " + cardDeckAdapter.getItem(position) + ", which is at cell position " + position);
    }
    /**
     * shuffleNewDeck():
     * - when the button is clicked:
     *  = makes an http GET request to the "shuffle cards" API ,
     *    which will return:
     *      - id of deck
     *      - shuffle status
     *
     * @param view
     */
    public void shuffleNewDeck(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ShuffleNewDeckEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DeckService requestNewDeck = retrofit.create(DeckService.class);

        Call<NewCardDeckJSONResponse> call = requestNewDeck.getNewCardDeckJSON();

        call.enqueue(new Callback<NewCardDeckJSONResponse>() {
            @Override
            public void onResponse(Call<NewCardDeckJSONResponse> call, Response<NewCardDeckJSONResponse> response) {

                NewCardDeckJSONResponse newCardDeckJSONResponse = response.body();
                NewCardDeckModel model = newCardDeckJSONResponse.getNewCardDeckModel();


                deck_id = model.getDeck_id();

                Log.d("shuffleNewDeck:", response.body().toString());

            }

            @Override
            public void onFailure(Call<NewCardDeckJSONResponse> call, Throwable t) {
                Log.d("error", t.getMessage());

            }
        });


    }

    /**
     * drawDeck():
     * - validate user input when this button's clicked:
     * @param view
     */
    public void drawDeck(View view) {

        // validate user input:
        // if the user input is blank or less than one: set an error message on:
        if( ( mSpecifiedCards_et.getText().equals(0) || (Integer.valueOf(mSpecifiedCards_et.getText().toString()) < 0))){

            mSpecifiedCards_et.setText("ERROR: you must specify at least one card!");
            // edit text: " you must draw at least one card// if the user inputs greater than the number of cards remaining in the current deck,

        } else if ( Integer.valueOf(mSpecifiedCards_et.getText().toString()) > currentCount) {

            // set an error message on edit text: " there are only x amount of cards remaining" , with x amount of cards being the ramining in the deck
            mSpecifiedCards_et.setText("ERROR: there are " + currentCount + " cards remaining");

        }

        // if user input passes both validations described above: then draw cards:
        int cardNumSelection = Integer.valueOf(mSpecifiedCards_et.getText().toString());

        // pass this number into the Query path :
        // Make an HTTP GET request to the "draw cards" API endpoint
        // https://deckofcardsapi.com/api/deck/{deck_id}/draw/?count={num_cards}

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ShuffleNewDeckEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DeckService drawCardDeck = retrofit.create(DeckService.class);

        // Call<RemainingCardsJsonResponse> cardsJSON=  drawCardDeck.getRemainingCardsJSON(cardNumSelection);
    }
}
