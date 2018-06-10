package com.example.niemawidaha.androidpractical_deck.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.niemawidaha.androidpractical_deck.NewCardDeckModel;
import com.example.niemawidaha.androidpractical_deck.R;
import com.example.niemawidaha.androidpractical_deck.service.DeckQueryModel;

import java.util.ArrayList;
import java.util.List;

public class CardDeckAdapter extends RecyclerView.Adapter<CardDeckAdapter.ViewHolder>{

    // private member variables:
    private List<DeckQueryModel> cardDeck;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public CardDeckAdapter(Context context, ArrayList<DeckQueryModel> cardDeck) {
        this.mInflater = LayoutInflater.from(context);
        this.cardDeck = cardDeck;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.users_card_itemview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the image view in each cell:
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imageUrl = cardDeck.get(position).getImage();


    }

    @Override
    public int getItemCount() {
        return cardDeck.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // private member variables:
        private ImageView cardView;

        // constructor for view holder:
        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.iv_user_cards);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        } // ends onClick



    } // ends viewholder

    // convenience method: for getting data at click position;
    public String getItem(int id){
        return cardDeck.get(id).toString();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
