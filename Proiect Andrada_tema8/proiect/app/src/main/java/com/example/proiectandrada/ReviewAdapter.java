package com.example.proiectandrada;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {
    //Tema 5: adapter personalizat 3
    private Context context;
    private int layoutId;
    private List<Review> reviewList;
    private LayoutInflater inflater;

    public ReviewAdapter(@NonNull Context context, int layoutId, @NonNull List<Review> reviewList, LayoutInflater inflater) {
        super(context, layoutId, reviewList);
        this.context = context;
        this.layoutId = layoutId;
        this.reviewList = reviewList;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId, parent, false);
        Review review = reviewList.get(position);

        TextView tvReviewView = view.findViewById(R.id.tvReviewView);
        TextView tvNumeView = view.findViewById(R.id.tvNumeView);
        TextView tvRatingView = view.findViewById(R.id.tvRatingView);

        tvReviewView.setText(review.getReviewText());
        tvNumeView.setText(review.getUtilizator());
        tvRatingView.setText(String.valueOf(review.getRating()));
//filtrare daca utilizatorul este admin ul
        if ("Admin".equals(review.getUtilizator())) {
            tvReviewView.setTextColor(Color.BLUE);
        }


        return view;
    }
}