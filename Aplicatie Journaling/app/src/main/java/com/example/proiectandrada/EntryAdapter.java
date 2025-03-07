package com.example.proiectandrada;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {
    //Tema 5: adapter personalizat 2
    private Context context;
    private int layoutId;
    private List<Entry> entryList;
    private LayoutInflater inflater;

    public EntryAdapter(@NonNull Context context, int layoutId, @NonNull List<Entry> entryList, LayoutInflater inflater) {
        super(context, layoutId, entryList);
        this.context = context;
        this.layoutId = layoutId;
        this.entryList = entryList;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId, parent, false);
        Entry entry = entryList.get(position);

        TextView tvEntryView = view.findViewById(R.id.tvEntryView);
        TextView tvSpnView = view.findViewById(R.id.tvSpnView);

        tvEntryView.setText(entry.getEntryText());
        tvSpnView.setText(String.valueOf(entry.getAlegere()));


        if (entry.getAlegere() == Mood.FERICIT) {
            tvSpnView.setTextColor(Color.BLUE);
        } else if (entry.getAlegere() == Mood.TRIST) {
            tvSpnView.setTextColor(Color.RED);
            tvSpnView.setTypeface(tvSpnView.getTypeface(), Typeface.BOLD); // Text îngroșat pentru 'Trist'
        }

        return view;
    }

}