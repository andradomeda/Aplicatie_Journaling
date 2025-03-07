package com.example.proiectandrada;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNumeUtilizator = findViewById(R.id.etNumeUtilizator);
        EditText etParola = findViewById(R.id.etParola);
        Button btnSalveazaMain = findViewById(R.id.btnSalveazaMain);

        btnSalveazaMain.setOnClickListener(v -> {

            String numeUtilizator = etNumeUtilizator.getText().toString();
            String parola = etParola.getText().toString();
            User user = new User(numeUtilizator, parola);
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });





    }
}