package app;

import java.io.IOException;
import java.util.ArrayList;
import static readNwrite.JsonCRU.createJsonStringFromObject;
import static readNwrite.JsonCRU.writeJsonStringToFile;

public class Helper {
    //generating Stock-Objects from stockNames in run-config
    //and scraping information to fill Objects
    public static ArrayList<Stock> createRunConfig(String[] stocks) throws IOException {
        ArrayList<Stock> stockObjects = new ArrayList<>();
        for(String stock : stocks) {
            stockObjects.add(new Stock(stock));
        }
        return stockObjects;
    }

    public static void saveAllToFile(ArrayList<Stock> stocksToSave) {
        for (Stock stock : stocksToSave) {
            String stockJsonToSave = createJsonStringFromObject(stock);
            writeJsonStringToFile(stockJsonToSave);
        }
    }
}
