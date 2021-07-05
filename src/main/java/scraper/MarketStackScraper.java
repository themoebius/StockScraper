package scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;


public class MarketStackScraper {

    public static HashMap<String, String> getStockData(String connectionString) throws IOException {
        URL connectionURL = new URL(connectionString);
        HashMap<String, String> marketDataPoints = new HashMap<>();
        HttpURLConnection conn = (HttpURLConnection) connectionURL.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String jsonString = "";
        Scanner scanner = new Scanner(connectionURL.openStream());

        while (scanner.hasNext()) {
            jsonString += scanner.nextLine();
        }
        scanner.close();

        ObjectMapper objectMapper  = new ObjectMapper();
        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode arrayNode = node.get("data");

        for(JsonNode jsonIterator : arrayNode) {
            marketDataPoints.put("open", jsonIterator.get("open").asText());
            marketDataPoints.put("close", jsonIterator.get("close").asText());
            marketDataPoints.put("high", jsonIterator.get("high").asText());
            marketDataPoints.put("low", jsonIterator.get("low").asText());
            marketDataPoints.put("volume", jsonIterator.get("volume").asText());
            marketDataPoints.put("symbol", jsonIterator.get("symbol").asText());

        }
        return marketDataPoints;
    }
}
