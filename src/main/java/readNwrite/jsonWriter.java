package readNwrite;

import app.Stock;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class jsonWriter {

    public static void writeToFile(ArrayList<Stock> stocks) throws IOException {
        for(Stock stock : stocks) {
            Gson gson = new Gson();
            Writer fileWriter = new java.io.FileWriter("c:\\TEMP\\"+ stock.tickerName +".json", true);
            gson.toJson(stock, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
