package app;

import java.io.IOException;
import java.util.ArrayList;
import static app.Helper.createRunConfig;
import static readNwrite.jsonReader.readFromFile;
import static readNwrite.jsonWriter.writeToFile;

public class Main {

    public static void main (String[] args) throws IOException {

        ArrayList<Stock> test = createRunConfig(args);

        writeToFile(test);

        ArrayList<Stock> returnTest = readFromFile(args);

        for(Stock stock : returnTest){
            System.out.println(stock.tickerName);
            System.out.println(stock.volume);
            System.out.println(stock.socialMediaMentionsCount);
        }
    }
}
