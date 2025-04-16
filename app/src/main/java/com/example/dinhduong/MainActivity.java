package com.example.dinhduong;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDiets;
    private DietAdapter dietAdapter;
    private ArrayList<Diet> dietList, filteredList;
    private Button buttonRecipes, buttonTrackNutrition;
    private EditText editTextSearch;
    private FloatingActionButton fabAddDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewDiets = findViewById(R.id.recyclerViewDiets);
        buttonRecipes = findViewById(R.id.buttonRecipes);
        buttonTrackNutrition = findViewById(R.id.buttonTrackNutrition);
        editTextSearch = findViewById(R.id.editTextSearch);
        fabAddDiet = findViewById(R.id.fabAddDiet);

        dietList = new ArrayList<>();
        filteredList = new ArrayList<>();
        dietList.add(new Diet("Low-Carb", "Focuses on reducing carbohydrate intake.", "None"));
        dietList.add(new Diet("Keto", "High-fat, low-carb diet to induce ketosis.", "Dairy"));
        dietList.add(new Diet("High-Protein", "Focuses on protein for muscle building.", "None"));
        filteredList.addAll(dietList);

        dietAdapter = new DietAdapter(filteredList);
        recyclerViewDiets.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDiets.setAdapter(dietAdapter);

        dietAdapter.setOnItemClickListener(diet -> {
            Intent intent = new Intent(MainActivity.this, DietDetailActivity.class);
            intent.putExtra("diet", diet);
            startActivity(intent);
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterDiets(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fabAddDiet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddDietActivity.class);
            startActivityForResult(intent, 1);
        });

        buttonRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
            startActivity(intent);
        });

        buttonTrackNutrition.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrackNutritionActivity.class);
            startActivity(intent);
        });
    }

    private void filterDiets(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(dietList);
        } else {
            for (Diet diet : dietList) {
                if (diet.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(diet);
                }
            }
        }
        dietAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Diet newDiet = (Diet) data.getSerializableExtra("newDiet");
            dietList.add(newDiet);
            filteredList.add(newDiet);
            dietAdapter.notifyDataSetChanged();
        }
    }
}