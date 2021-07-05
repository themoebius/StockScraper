package scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class RedditScraper {

    public static ArrayList<String> getRedditComments(String connectionString) throws IOException {
        URL connectionURL = new URL(connectionString);
        ArrayList<String> redditComments = new ArrayList<>();
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
            JsonNode bodyNodeInLoop = jsonIterator.get("body");
            if(!bodyNodeInLoop.asText().isBlank()) {
                redditComments.add(bodyNodeInLoop.asText());
            }
        }
        return redditComments;
    }
}
