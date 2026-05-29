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

/**
 * Databáze, kde klíč je název kategorie a hodnota je seznam produktů v ní.
 */
public class SupplementDatabase {
    private Map<String, List<AbstractSupplement>> database;

    public SupplementDatabase() {
        database = new HashMap<>();
/**
 * NVytvoření seznamu pro všechny hlavní kategorie.
 */
        String[] defaultCategories = {
                "Síla a Budování svalů", "Energie a Předtréninkovky",
                "Vitamíny a Minerály", "Spánek a Zklidnění",
                "Mozek", "Klouby"
        };
        for (String cat : defaultCategories) {
            database.put(cat, new ArrayList<>());
        }
/**
 * Na startu načtu data ze souboru.
 */
        loadDataFromJson();
    }
    /**
     * Metoda, která přečte soubor suplementy.json a udělá z něj objekty.
     */
    private void loadDataFromJson() {
        try {
            /**
             * Načtení textu z JSON souboru.
             */
            String jsonText = new String(Files.readAllBytes(Paths.get("suplementy.json")), "utf-8");
            JsonArray jsonArray = JsonParser.parseString(jsonText).getAsJsonArray();
            /**
             * Projdu každý produkt v JSONU.
             */
            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
/**
 * Vezmu si texty podle názvu.
 */
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
/**
 * Cyklus pro benefity, protože to je pole textů a ne jen jeden text.
 */
                List<String> benefits = new ArrayList<>();
                JsonArray benArray = obj.getAsJsonArray("benefits");
                for (JsonElement b : benArray) {
                    benefits.add(b.getAsString());
                }
/**
 * Z dat vytvořím nový objekt suplementu.
 */
                MuscleSupplement supp = new MuscleSupplement(
                        name, category, scientificScore, summary, dosage,
                        timing, howItWorks, benefits, mythAndFact, specificWarning, imagePath
                );
/**
 * Uložím ho do správné kategorie v databázi.
 */
                database.putIfAbsent(category, new ArrayList<>());
                database.get(category).add(supp);
            }
/**
 * Kdyby se něco nepovedlo
 */
        } catch (Exception e) {
            System.out.println("Nepodařilo se načíst JSON soubor: " + e.getMessage());
        }
    }
    /**
     * Metoda, která vrací produkty z konkrétní kategorie.
     */
    public List<AbstractSupplement> getSupplementsByCategory(String category) {
        return database.get(category);
    }
    /**
     * Vrací seznam kategorií, co v databázi mám.
     */
    public List<String> getCategories() {
        return new ArrayList<>(database.keySet());
    }
}