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

public class VizualizareDBEntry extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ListView listView = new ListView(this);
        TextView textView = new TextView(this);
        textView.setText("Lista Entries:");

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry("Zi buna", Mood.TRIST));
        entries.add(new Entry("Zi rea!", Mood.FERICIT));
        entries.add(new Entry("Zi ok", Mood.TRIST));

        AppDB dbInstance = AppDB.getInstance(getApplicationContext());
        EntryDAO entryDAO = dbInstance.getEntryDAO();

        for (Entry entry : entries) {
            entryDAO.insertEntry(entry);
        }

        List<Entry> listaEntries = entryDAO.getEntries();

        List<Entry> filteredEntries = entryDAO.getEntriesByMood(Mood.TRIST);

        entryDAO.deleteEntriesByMood(Mood.TRIST);


        List<Entry> updatedEntries = entryDAO.getEntries();
        ArrayAdapter<Entry> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedEntries);
        listView.setAdapter(adapter);

        linearLayout.addView(textView);
        linearLayout.addView(listView);
        scrollView.addView(linearLayout);


        setContentView(scrollView);
    }
}
