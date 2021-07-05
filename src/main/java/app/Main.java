package app;

import java.io.IOException;
import static helper.Helper.run;

public class Main {

    public static void main (String[] args) throws IOException {

        run(args);

        /*
        ArrayList<Stock> generatedStocks = new ArrayList<>();

        ArrayList<String[]> stocksAndSubredditsSplit = splitStocksFromSubreddits(args);

        String[] searchTerms = stocksAndSubredditsSplit.get(0);
        String[] subRedditChoice = stocksAndSubredditsSplit.get(1);


        String searchTerm = searchTerms[0];
        Stock testStock = new Stock(searchTerm, subRedditChoice);

        System.out.println(testStock.toString());
        System.out.println("-------------------");
*/

        /*
        //generating Stock-Objects from stockNames in run-config
        //and scraping information to fill Objects
        ArrayList<Stock> getData = createRunConfig(args);

        // converting and saving &| appending generated Objects
        // to .txt files in 'generatedData'- folder
        saveAllToFile(getData);
*/
    }
}
