package com.example.proiectandrada;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class VizualizareDBReview extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ListView listView = new ListView(this);
        TextView textView = new TextView(this);
        textView.setText("Lista Reviews:");

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Am ramas profund socata...", "Andrada", 9.1F));
        reviews.add(new Review("Un review foarte bun!", "Ion", 8.5F));
        reviews.add(new Review("Nu mi-a placut deloc", "Maria", 4.3F));

        AppDB dbInstance = AppDB.getInstance(getApplicationContext());
        ReviewDAO reviewDAO = dbInstance.getReviewDAO();


        for (Review review : reviews) {
            reviewDAO.insertReview(review);
        }


        List<Review> listaReviews = dbInstance.getReviewDAO().getReviews();


        List<Review> highRatedReviews = reviewDAO.getReviewsByRating(8.0F);


        Review updatedReview = new Review("Updated text", "Andrada", 9.5F);
        reviewDAO.updateReview(updatedReview);


        reviewDAO.deleteReviewsByUser("Andrada");

        ArrayAdapter<Review> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaReviews);
        listView.setAdapter(adapter);


        linearLayout.addView(textView);
        linearLayout.addView(listView);
        scrollView.addView(linearLayout);

        setContentView(scrollView);
    }
}