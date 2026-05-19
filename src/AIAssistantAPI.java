import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AIAssistantAPI {
    private static String getApiKey() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("config.properties")) {
            props.load(in);
            return props.getProperty("API_KEY");
        } catch (Exception e) {
            System.out.println("Nepodařilo se načíst soubor config.properties s API klíčem.");
            return null;
        }
    }

    public static String askAI(String userGoal) throws Exception {
        String apiKey = getApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            return "Chyba: API klíč nebyl nalezen v config.properties.";
        }

        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String systemPrompt = "Jsi expert na fitness a doplňky stravy. Uživatel píše: '" + userGoal + "'. " +
                "Stručně a česky mu doporuč suplementy z tohoto seznamu: Kreatin, Protein, Hořčík, Melatonin, Zinek.";
        // zbytek produktu sem ještě dopíšu, protože ted mám hotový pouze jeden a nevím přesně kolik toho udělam a nechci v tom mít bordel

        String jsonInputString = "{\"contents\": [{\"parts\":[{\"text\": \"" + systemPrompt + "\"}]}]}";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            Scanner errorScanner = new Scanner(conn.getErrorStream(), "utf-8");
            String errorMsg = "";
            while(errorScanner.hasNext()){
                errorMsg += errorScanner.nextLine();
            }
            errorScanner.close();
            System.out.println("Chyba od Googlu: " + errorMsg);

            return "Chyba API. Kód: " + responseCode;
        }

        String inline = "";
        Scanner scanner = new Scanner(conn.getInputStream(), "utf-8");
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        JsonObject dataObj = JsonParser.parseString(inline).getAsJsonObject();

        return dataObj.getAsJsonArray("candidates")
                .get(0).getAsJsonObject()
                .getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
    }
}
