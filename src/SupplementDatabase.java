import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplementDatabase {
    private Map<String, List<AbstractSupplement>> database;

    public SupplementDatabase() {
        database = new HashMap<>();

        String[] defaultCategories = {
                "Síla a Budování svalů", "Energie a Předtréninkovky",
                "Vitamíny a Minerály", "Spánek a Zklidnění",
                "Mozek", "Klouby"
        };
        for (String cat : defaultCategories) {
            database.put(cat, new ArrayList<>());
        }

        loadDataFromJson();
    }

    private void loadDataFromJson() {
        try {
            String jsonText = new String(Files.readAllBytes(Paths.get("suplementy.json")), "utf-8");
            JsonArray jsonArray = JsonParser.parseString(jsonText).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();

                String name = obj.get("name").getAsString();
                String category = obj.get("category").getAsString();
                String scientificScore = obj.get("scientificScore").getAsString();
                String summary = obj.get("summary").getAsString();
                String dosage = obj.get("dosage").getAsString();
                String timing = obj.get("timing").getAsString();
                String howItWorks = obj.get("howItWorks").getAsString();
                String mythAndFact = obj.get("mythAndFact").getAsString();
                String specificWarning = obj.get("specificWarning").getAsString();
                String imagePath = obj.get("imagePath").getAsString();

                List<String> benefits = new ArrayList<>();
                JsonArray benArray = obj.getAsJsonArray("benefits");
                for (JsonElement b : benArray) {
                    benefits.add(b.getAsString());
                }

                MuscleSupplement supp = new MuscleSupplement(
                        name, category, scientificScore, summary, dosage,
                        timing, howItWorks, benefits, mythAndFact, specificWarning, imagePath
                );

                database.putIfAbsent(category, new ArrayList<>());
                database.get(category).add(supp);
            }

        } catch (Exception e) {
            System.out.println("Nepodařilo se načíst JSON soubor: " + e.getMessage());
        }
    }

    public List<AbstractSupplement> getSupplementsByCategory(String category) {
        return database.get(category);
    }

    public List<String> getCategories() {
        return new ArrayList<>(database.keySet());
    }
}