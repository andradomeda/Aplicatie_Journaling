package com.example.proiectandrada;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReviewActivity extends AppCompatActivity {
    private boolean isEditing=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etFeedbackReview = findViewById(R.id.etFeedbackReview);
        EditText etUtilizatorReview = findViewById(R.id.etUtilizatorReview);
        EditText etRatingReview = findViewById(R.id.etRatingReview);
        Button btnTrimiteReview = findViewById(R.id.btnTrimiteReview);

        Intent editIntent = getIntent();

        if (editIntent.hasExtra("editReview")) {
            isEditing=true;
            Review editReview = (Review) editIntent.getSerializableExtra("editReview");
            etFeedbackReview.setText(editReview.getReviewText());
            etUtilizatorReview.setText(editReview.getUtilizator());
            etRatingReview.setText(String.valueOf(editReview.getRating()));

        }

        btnTrimiteReview.setOnClickListener(v->{
            String feedback = etFeedbackReview.getText().toString();
            String utilizator = etUtilizatorReview.getText().toString();
            float pret=Float.parseFloat(etRatingReview.getText().toString());
            Review review= new Review(feedback,utilizator, pret);

            Intent intent = getIntent();

            if(isEditing){
                intent.putExtra("editReview",review);
                isEditing=false;
            }
            else
            {
                intent.putExtra("reviewForIntent", review);
            }

            setResult(RESULT_OK,intent);
            finish();
        });


    }
}