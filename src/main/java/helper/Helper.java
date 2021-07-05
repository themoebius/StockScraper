package helper;

import app.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.copyOfRange;
import static readNwrite.JsonCRU.createJsonStringFromObject;
import static readNwrite.JsonCRU.writeJsonStringToFile;

public class Helper {

    public static ArrayList<String[]> splitStocksFromSubreddits(String[] args) {
        ArrayList<String[]> stocksAndSubreddits = new ArrayList<>();
        boolean isInArray = Arrays.asList(args).contains(",");

        if (isInArray) {
            int splitCharPosition = Arrays.asList(args).indexOf(",");
            String[] subreddits = copyOfRange(args, splitCharPosition + 1, args.length);
            String[] stocks = copyOfRange(args, 0, splitCharPosition - 1);
            stocksAndSubreddits.add(stocks);
            stocksAndSubreddits.add(subreddits);
            return stocksAndSubreddits;
        }
        else {
            System.out.println("NANANA");
            return null;
        }
    }

    public static void saveAllToFile(ArrayList<Stock> stocksToSave) {
        for (Stock stock : stocksToSave) {
            String stockJsonToSave = createJsonStringFromObject(stock);
            writeJsonStringToFile(stockJsonToSave);
        }
    }

    public static void run(String[] args) throws IOException {
        ArrayList<Stock> generatedStocks = new ArrayList<>();

        ArrayList<String[]> stocksAndSubredditsSplit = splitStocksFromSubreddits(args);

        String[] searchTerms = stocksAndSubredditsSplit.get(0);
        String[] subRedditChoice = stocksAndSubredditsSplit.get(1);

        for(String tickerName : searchTerms) {
            Stock stock = new Stock(tickerName, subRedditChoice );
            generatedStocks.add(stock);
        }

        saveAllToFile(generatedStocks);
        System.out.println("All operations Complete");
        System.out.println("Run again tomorrow after Market close.");
    }
}