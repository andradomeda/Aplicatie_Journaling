package com.example.proiectandrada;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AboutUsActivity extends AppCompatActivity {
    List<Review> reviewList=new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;
    private int pozitieReviewInLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnInapoiAbout;
        btnInapoiAbout=findViewById(R.id.btnInpoiAbout);
        Button btnAdd;
        btnAdd=findViewById(R.id.btnAddAbout);
        ListView lvReviewList=findViewById(R.id.lvReviewList);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intentReview = result.getData();

                if (intentReview.hasExtra("reviewForIntent")) {
                    Review review = (Review) intentReview.getSerializableExtra("reviewForIntent");
                    if (review != null) {
                        reviewList.add(review);
                    }
                    ReviewAdapter adapter = new ReviewAdapter(this, R.layout.view_review, reviewList, getLayoutInflater());
                    lvReviewList.setAdapter(adapter);
                }

                else if (intentReview.hasExtra("editReview")) {
                    Review editedReview = (Review) intentReview.getSerializableExtra("editReview");
                    if (editedReview != null) {
                        Review existingReview = reviewList.get(pozitieReviewInLista);
                        existingReview.setReviewText(editedReview.getReviewText());
                        existingReview.setUtilizator(editedReview.getUtilizator());
                        existingReview.setRating(editedReview.getRating());

                        ReviewAdapter adapter = (ReviewAdapter) lvReviewList.getAdapter();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        lvReviewList.setOnItemClickListener((adapterView, view, position, l) -> {
            pozitieReviewInLista = position;
            Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
            intent.putExtra("editReview", reviewList.get(pozitieReviewInLista));
            launcher.launch(intent);
        });
        btnInapoiAbout.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(v->{
            Intent intent=new Intent(AboutUsActivity.this, ReviewActivity.class);
            //startActivity(intent);//de comentat
            launcher.launch(intent);
        });
    }
}