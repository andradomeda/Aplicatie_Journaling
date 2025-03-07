package com.example.proiectandrada;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
public class VizualizareRetea extends AppCompatActivity {
    private static final String urlReviews = "https://jsonkeeper.com/b/YIHF";
    private ListView lvReviews;
    private List<Review> reviews = new ArrayList<>();
    private static final String urlEntries = "https://www.jsonkeeper.com/b/AWJ5";
    private ListView lvEntries;
    private List<Entry> entries = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare_retea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponente();

        incarcareReviewsDinRetea();

        initComponenteEntries();
        incarcareEntriesDinRetea();

    }
    private void incarcareReviewsDinRetea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager httpsManager = new HttpsManager(urlReviews);
                String rezultat = httpsManager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    preluareReviwsDinJson(rezultat);
                });
            }
        };
        thread.start();
    }

    private void preluareReviwsDinJson(String json) {
        reviews.addAll(ReviewParser.parsareJson(json));
        ArrayAdapter<Review> adapter = (ArrayAdapter<Review>) lvReviews.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initComponente() {
        lvReviews = findViewById(R.id.lvReviewsViz);
        ArrayAdapter<Review> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, reviews);
        lvReviews.setAdapter(adapter);
    }

    private void incarcareEntriesDinRetea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager httpsManager = new HttpsManager(urlEntries);
                String rezultat = httpsManager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    preluareEntriesDinJson(rezultat);
                });
            }
        };
        thread.start();
    }

    private void preluareEntriesDinJson(String json) {
        entries.addAll(EntryParser.parsareJson(json));
        ArrayAdapter<Entry> adapter_nou = (ArrayAdapter<Entry>) lvEntries.getAdapter();
        adapter_nou.notifyDataSetChanged();
    }

    private void initComponenteEntries() {
        lvEntries = findViewById(R.id.lvEntriesViz);
        ArrayAdapter<Entry> adapter_nou = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, entries);
        lvEntries.setAdapter(adapter_nou);
    }
}