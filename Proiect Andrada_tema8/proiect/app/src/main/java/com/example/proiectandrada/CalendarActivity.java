package com.example.proiectandrada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    ListView lvIntrari;
    CalendarView cvIntrari;
    FloatingActionButton btnmeniu;

    ImageView imageView;

    List<Entry> entryList = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;
    private int pozitieEntryInLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvIntrari = findViewById(R.id.lvIntrari);
        cvIntrari = findViewById(R.id.cvIntrari);



        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();


                if (intent != null && intent.hasExtra("entryForIntent")) {
                    Entry entry = (Entry) intent.getSerializableExtra("entryForIntent");
                    if (entry != null) {
                        entryList.add(entry);
                    }

                    EntryAdapter adapter = new EntryAdapter(this, R.layout.view_entry, entryList, getLayoutInflater());
                    lvIntrari.setAdapter(adapter);
                }


                else if (intent != null && intent.hasExtra("edit")) {
                    Entry entry = (Entry) intent.getSerializableExtra("edit");
                    if (entry != null) {

                        Entry editedEntry = entryList.get(pozitieEntryInLista);
                        editedEntry. setEntryText(entry. getEntryText());
                        editedEntry.setAlegere(entry.getAlegere());


                        EntryAdapter adapter = (EntryAdapter) lvIntrari.getAdapter();
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        lvIntrari.setOnItemClickListener((adapterView, view, position, l) -> {
            pozitieEntryInLista = position;
            Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
            intent.putExtra("edit", entryList.get(pozitieEntryInLista));
            launcher.launch(intent);
        });
        btnmeniu = findViewById(R.id.fabMeniuCalendar);
        btnmeniu.setOnClickListener(view -> showPopupMenu(view));

    }
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.meniu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.option1) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.option2) {
                Intent intent = new Intent(CalendarActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
         else if (item.getItemId() == R.id.option3) {
            Intent intent = new Intent(CalendarActivity.this, EntryActivity.class);
            //startActivity(intent);//va trebui comentat
                launcher.launch(intent);
        } else if (item.getItemId() == R.id.option4) {
                Intent intent = new Intent(CalendarActivity.this, VizualizareDBReview.class);
                startActivity(intent);

            }
            else if (item.getItemId() == R.id.option5) {
                Intent intent = new Intent(CalendarActivity.this, VizualizareDBEntry.class);
                startActivity(intent);

            }
            else if (item.getItemId() == R.id.option6) {
                Intent intent = new Intent(CalendarActivity.this, VizualizareRetea.class);
                startActivity(intent);

            }

            return true;
        });
        popupMenu.show();
    }
}