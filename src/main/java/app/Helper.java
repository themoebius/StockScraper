package app;

import java.io.IOException;
import java.util.ArrayList;

public class Helper {

    public static ArrayList<Stock> createRunConfig(String[] stocks) throws IOException {
        ArrayList<Stock> stockObjects = new ArrayList<>();
        for(String stock : stocks) {
            stockObjects.add(new Stock(stock));
        }
        return stockObjects;
    }
}
