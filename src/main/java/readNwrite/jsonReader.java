package readNwrite;

import app.Stock;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class jsonReader {
    public static ArrayList<Stock> readFromFile(String[] stocks) throws IOException {
        ArrayList<Stock> stocksFromJson = new ArrayList<>();

        for (String tickerName : stocks) {
            JsonReader reader = new JsonReader(new FileReader("c:\\TEMP\\" + tickerName + ".json"));
            reader.setLenient(true);
            Gson gson = new Gson();
            Stock returnStock = gson.fromJson(reader, Stock.class);
            stocksFromJson.add(returnStock);
            reader.close();
        }
        return stocksFromJson;
    }
}
