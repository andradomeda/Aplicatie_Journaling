package com.example.proiectandrada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EntryActivity extends AppCompatActivity {
    private boolean isEditing=false;
    EditText etEntryMemorie;
    Spinner spnTag;
    Button btnSalveazaEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entry);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSalveazaEntry = findViewById(R.id.btnSalveazaEntry);
        etEntryMemorie = findViewById(R.id.etEntryMemorie);
        spnTag = findViewById(R.id.spnTag);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Mood, android.R.layout.simple_spinner_dropdown_item);
        spnTag.setAdapter(adapter);

        Intent editIntent = getIntent();

        if (editIntent.hasExtra("edit")) {

            isEditing=true;
            Entry editEntry = (Entry) editIntent.getSerializableExtra("edit");
            etEntryMemorie.setText(editEntry.getEntryText());


            ArrayAdapter<String> moodAdapter = (ArrayAdapter<String>) spnTag.getAdapter();
            for (int i = 0; i < moodAdapter.getCount(); i++) {
                if (editEntry.getAlegere().toString().equals(moodAdapter.getItem(i))) {
                    spnTag.setSelection(i);
                    break;
                }
            }

        }

        btnSalveazaEntry.setOnClickListener(v -> {
        String memorie = etEntryMemorie.getText().toString();
        Mood alegere = Mood.valueOf(spnTag.getSelectedItem().toString());  // Folosește enum-ul FericitTrist

        Entry entry = new Entry(memorie, alegere);

        Intent entryListIntent = getIntent();


        if(isEditing){
            entryListIntent.putExtra("edit",entry);
            isEditing=false;
        }
        else
        {
            entryListIntent.putExtra("entryForIntent", entry);
        }

        setResult(RESULT_OK, entryListIntent);
        finish();
        });
    }


}
