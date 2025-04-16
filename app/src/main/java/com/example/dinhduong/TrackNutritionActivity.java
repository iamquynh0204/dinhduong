package com.example.dinhduong;



import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TrackNutritionActivity extends AppCompatActivity {
    private TextView textViewNutritionInfo;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_nutrition);

        textViewNutritionInfo = findViewById(R.id.textViewNutritionInfo);
        buttonBack = findViewById(R.id.buttonBack);

        String nutritionInfo = getString(R.string.calories) + ": 1800 kcal\n" +
                getString(R.string.carbs) + ": 50g\n" +
                getString(R.string.protein) + ": 120g\n" +
                getString(R.string.fat) + ": 80g";
        textViewNutritionInfo.setText(nutritionInfo);

        buttonBack.setOnClickListener(v -> finish());
    }
}