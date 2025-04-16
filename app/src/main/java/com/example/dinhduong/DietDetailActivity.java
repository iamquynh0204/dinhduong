package com.example.dinhduong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DietDetailActivity extends AppCompatActivity {
    private TextView textViewName, textViewDescription, textViewAllergies, textViewRecipes, textViewMealPlan, textViewTips;
    private ImageView imageViewDiet;
    private Button buttonFavorite, buttonShare, buttonBack;
    private Diet diet;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_detail);

        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewAllergies = findViewById(R.id.textViewAllergies);
        textViewRecipes = findViewById(R.id.textViewRecipes);
        textViewMealPlan = findViewById(R.id.textViewMealPlan);
        textViewTips = findViewById(R.id.textViewTips);
        imageViewDiet = findViewById(R.id.imageViewDiet);
        buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonShare = findViewById(R.id.buttonShare);
        buttonBack = findViewById(R.id.buttonBack);

        diet = (Diet) getIntent().getSerializableExtra("diet");
        if (diet != null) {
            textViewName.setText(diet.getName());
            textViewAllergies.setText(String.format(getString(R.string.allergies_details), diet.getAllergies()));

            if (diet.getName().equals("Keto")) {
                textViewDescription.setText("The Keto (Ketogenic) diet is a high-fat, low-carb, and moderate-protein diet. Its main goal is to bring the body into a state of ketosis, where the body uses fat as its primary energy source instead of carbohydrates. This can aid in weight loss, blood sugar control, and improved energy levels.");

                textViewRecipes.setText("- Grilled salmon with butter and asparagus\n" +
                        "- Avocado salad with olive oil and walnuts\n" +
                        "- Fried eggs with bacon and cheese\n" +
                        "- Pork ribs with garlic butter sauce");

                textViewMealPlan.setText("Breakfast: Fried eggs with avocado and bacon\n" +
                        "Lunch: Salmon salad with olive oil\n" +
                        "Dinner: Pork ribs with garlic butter sauce and steamed greens\n" +
                        "Snack: A handful of almonds or cheese");

                textViewTips.setText("- Drink plenty of water to avoid dehydration during the early stages of ketosis.\n" +
                        "- Supplement with salt and minerals (like potassium, magnesium) to avoid the 'Keto flu'.\n" +
                        "- Monitor daily carbohydrate intake, keeping it under 20-50g depending on your body.\n" +
                        "- Incorporate healthy fats like olive oil, coconut oil, and butter.");
            } else {
                textViewDescription.setText(diet.getDescription());
                textViewRecipes.setText("- Vegetable salad with chicken breast\n- Boiled eggs and avocado\n- Grilled salmon with asparagus");
                textViewMealPlan.setText("Breakfast: Fried eggs and avocado\nLunch: Chicken breast salad\nDinner: Grilled salmon");
                textViewTips.setText("No specific tips for this diet.");
            }

            switch (diet.getName()) {
                case "Low-Carb":
                    imageViewDiet.setImageResource(R.drawable.ic_diet_lowcarb);
                    break;
                case "Keto":
                    imageViewDiet.setImageResource(R.drawable.ic_diet_keto);
                    break;
                case "High-Protein":
                    imageViewDiet.setImageResource(R.drawable.ic_diet_highprotein);
                    break;
                default:
                    imageViewDiet.setImageResource(R.drawable.ic_diet_keto);
            }
        }

        buttonFavorite.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            buttonFavorite.setText(isFavorite ? R.string.unfavorite : R.string.favorite);
        });

        buttonShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Diet: " + diet.getName() + "\n" + diet.getDescription());
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
        });

        buttonBack.setOnClickListener(v -> finish());
    }
}